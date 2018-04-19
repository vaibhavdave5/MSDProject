package controllers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;

import controllers.popups.PopupMessage;
import database.Connect;
import driver.Driver;
import driver.IStudentPair;
import driver.ISummary;
import driver.StudentPair;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;

/**
 * This Controller is responsible to load the Summary page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class SummaryController {

	// These are injected when the Controller is binded with the FXML view and hence,
	// they don't need to be instantiated.
	@FXML private ProgressBar progress;
	@FXML private Label info;
	@FXML private ListView<StudentPair> danger;
	@FXML private ListView<StudentPair> medium;
	@FXML private ListView<String> safe;
	@FXML private Button back;
	@FXML private Label score;
	
	private ISummary summary;
	private ScreenController screenController;
	
	private static Logger logger = Logger.getLogger(SummaryController.class);
	
	public SummaryController(ISummary iSummary) {
		this.screenController = ScreenController.getInstance();
		this.summary = iSummary;
	}
	 
	/**
	 * This method runs on page load and initializes all components of the Summary.fxml page
	 */
	@FXML protected void initialize() {
		applyStyle();
		setDefaultText(danger, "No Plagiarism Detected");
		setDefaultText(medium, "No Warnings Detected");
		setDefaultText(safe, "No Clean Assignments");
		safe.setMouseTransparent(true);
		safe.setFocusTraversable(false);
		populateView(danger, summary.getRedPairs());
		Connect.increaseCases(summary.getRedPairs().size());
		addListener(danger);
		populateView(medium, summary.getYellowPairs());
		addListener(medium);
		populateSafeStudents();
		progress.setProgress(0.0);
		score.setText("-");
	}
	
	/**
	 * Sets the message for empty ListViews
	 * @param <T>
	 */
	/**
	 * Sets the message for a ListView
	 * @param listView a ListView
	 * @param message the message to set for the ListView
	 * @param <T> the type of ListView
	 */
	private <T> void setDefaultText(ListView<T> listView, String message) {
		listView.setPlaceholder(new Label(message));
	}
	
	private void addListener(ListView<StudentPair> listView) {
		listView
			.getSelectionModel()
			.selectedItemProperty()
			.addListener((ObservableValue<? extends StudentPair> observable, 
							StudentPair oldValue, 
							StudentPair newValue) ->  {
								if(newValue != null) {
									progress.setProgress(newValue.getSimilarityScore());
									DecimalFormat df = new DecimalFormat("###.##");
									score
									 .setText(df.format(newValue.getSimilarityScore() * 100.0) + "%");
								} 
								
							});
	}
	
	/**
	 * This method populates the list view with the pairs of student
	 */
	private void populateView(ListView<StudentPair> view, Set<IStudentPair> set) {
		for(IStudentPair pair : set) {
			view.getItems().add((StudentPair) pair);
		}
	}
	
	/**
	 * This method populates the list view with students who didn't plagiarize
	 */
	private void populateSafeStudents() {
		for(Integer i : summary.getGreenIds()) {
			safe.getItems().add("Student-" + i.toString());
		}
	}
	
	/**
	 * This method takes the user back to the Start screen
	 */
	@FXML public void goBack()
	{
		Optional<ButtonType> type 
		    = PopupMessage.getInstance().showConfirm(null,
				"You will lose all your changes.");
		if(type.isPresent() && type.get() == ButtonType.CANCEL) return;
		if(screenController != null) {
			Driver.getInstance().resetState();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Start.fxml"));
			try {
				screenController.addScreen("main", loader.load());
				screenController.activate("main");
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}
	
	/**
	 * This method applies the CSS properties to the controls.
	 */
	private void applyStyle() {
		info.getStyleClass().add("logo");
		score.getStyleClass().add("logo");
		danger.getStyleClass().add("danger-students");
		medium.getStyleClass().add("medium-students");
		safe.getStyleClass().add("safe-student");
		back.getStyleClass().add("primary");
	}

	/**
	 * unselects selected highly suspicious and safe students
	 * @param event the mouse event that occured
	 */
	@FXML public void unselectDangerAndSafe(MouseEvent event) {
		unselectItems(danger, safe);
		if(event.getClickCount() == 2 && medium.getSelectionModel().getSelectedIndex() != -1) {
			routeToCompare(medium
							.getItems()
							.get(medium
									.getSelectionModel()
									.getSelectedIndex()));
		}
	}

	/**
	 * unselects selected mildly suspicious and safe students
	 * @param event the mouse event that occured
	 */
	@FXML public void unselectMediumAndSafe(MouseEvent event) {
		unselectItems(medium, safe);
		if(event.getClickCount() == 2 && danger.getSelectionModel().getSelectedIndex() != -1) {
			routeToCompare(danger
							.getItems()
							.get(danger
									.getSelectionModel()
									.getSelectedIndex()));
		}
	}

	/**
	 * unselects selected highly suspicious and mildly suspicious students
	 */
	@FXML public void unselectMediumAndDanger() {
		unselectItems(medium, danger);
	}

	/**
	 * Removes selected items in two listViews
	 * @param listView1 the first ListView
	 * @param listView2 the second ListView
	 * @param <T> the type of first ListView
	 * @param <E> the type of second ListView
	 */
	private <T, E> void unselectItems(ListView<T> listView1, ListView<E> listView2) {
		listView1.getSelectionModel().clearSelection();
		listView2.getSelectionModel().clearSelection();
	}

	/**
	 * routes to the compare page
	 * @param studentPair an object containing information about a pair of students
	 */
	public void routeToCompare(StudentPair studentPair) {
		if(screenController != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Compare.fxml"));
			loader
				.setController(new CompareController(Driver
														.getInstance()
														.generateSnippet(
																studentPair.getStudent1Id(),
																studentPair.getStudent2Id())));
			try {
				screenController.addScreen("compare", loader.load());
				screenController.activate("compare");
			} catch (IOException e) {
				logger.error(e);
			}
		} 
	}
}
