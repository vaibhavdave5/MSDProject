package controllers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;

import controllers.popups.PopupMessage;
import driver.StudentPair;
import driver.Summary;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;

/**
 * This Controller is responsible to load the Summary page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class SummaryController {

	@FXML private ProgressBar progress;
	@FXML private Label info;
	@FXML private ListView<StudentPair> danger;
	@FXML private ListView<StudentPair> medium;
	@FXML private ListView<String> safe;
	@FXML private Button back;
	@FXML private Label score;
	
	private Summary summary;
	private ScreenController screenController;
	
	private static Logger logger = Logger.getLogger(SummaryController.class);
	
	public SummaryController(Summary summary) {
		this.screenController = ScreenController.getInstance();
		this.summary = summary;
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
		populateView(danger, summary.getRed());
		addListener(danger);
		populateView(medium, summary.getYellow());
		addListener(medium);
		populateSafeStudents();
		progress.setProgress(0.0);
		score.setText("-");
	}
	
	/**
	 * Sets the message for empty ListViews
	 * @param <T>
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
								} else {
									progress.setProgress(0.0);
									score.setText("-");
								}
								
							});
	}
	
	/**
	 * This method populates the list view with the pairs of student
	 */
	private void populateView(ListView<StudentPair> view, Set<StudentPair> pairs) {
		for(StudentPair pair : pairs) {
			view.getItems().add(pair);
		}
	}
	
	private void populateSafeStudents() {
		for(Integer i : summary.getGreen()) {
			safe.getItems().add("Student-" + i.toString());
		}
	}
	
	/**
	 * This method takes the user back to the Start screen
	 */
	@FXML public void goBack()
	{
		Optional<ButtonType> type 
		    = PopupMessage.getInstance().showAlertMessage(AlertType.CONFIRMATION,
				"Confirmation", 
				"Are you sure?", 
				"You will lose all your changes.");
		if(type.isPresent() && type.get() == ButtonType.CANCEL) return;
		if(screenController != null) {
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
	 * This method removes the selections of danger and safe students
	 */
	@FXML public void unselectDangerAndSafe() {
		unselectItems(danger, safe);
	}
	
	/**
	 * This method removes the selections of medium and safe students
	 */
	@FXML public void unselectMediumAndSafe() {
		unselectItems(medium, safe);
	}
	
	/**
	 * This method removes the selections of medium and danger students
	 */
	@FXML public void unselectMediumAndDanger() {
		unselectItems(medium, danger);
	}
	/**
	 * This method removes the selections of the list items of the two list items provided.
	 * @param <T>
	 * @param <E>
	 * 
	 * @param listView1
	 * @param listView2
	 */
	private <T, E> void unselectItems(ListView<T> listView1, ListView<E> listView2) {
		listView1.getSelectionModel().clearSelection();
		listView2.getSelectionModel().clearSelection();
	}
}
