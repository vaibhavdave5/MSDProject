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
import utils.ConfigUtils;

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
	
	// Configuration Variables
	private String startPath;
	private String startPage;
	private String comparePage;
	private String comparePath;
	private String blueStyle;
	private String customLogoStyle;
	private String dangerListStyle;
	private String mediumListStyle;
	private String safeListStyle;
	private String backWarning;
	private String noPlagiarism;
	private String noMedium;
	private String noSafe;
	
	private ISummary summary;
	private ScreenController screenController;
	
	private static Logger logger = Logger.getLogger(SummaryController.class);
	
	/**
	 * 
	 * @param iSummary a summary object passed by the main controller, used to populate
	 * this page's components
	 */
	public SummaryController(ISummary iSummary) {
		initConfigVar();
		this.screenController = ScreenController.getInstance();
		this.summary = iSummary;
	}
	
	/**
	 * This is a method used to initialize the configuration variables used in 
	 * the class
	 */
	private void initConfigVar() {
		ConfigUtils configUtils = new ConfigUtils();
		startPath = configUtils.readConfig("START_PATH");
		startPage = configUtils.readConfig("START_PAGE");
		comparePage = configUtils.readConfig("COMPARE_PAGE");
		comparePath = configUtils.readConfig("COMPARE_PATH");
		blueStyle = configUtils.readConfig("BLUE");
		customLogoStyle = configUtils.readConfig("CUSTOM_LOGO");
		dangerListStyle = configUtils.readConfig("DANGER_LIST");
		mediumListStyle = configUtils.readConfig("MEDIUM_LIST");
		safeListStyle = configUtils.readConfig("SAFE_LIST");
		backWarning = configUtils.readConfig("BACK_WARNING");
		noPlagiarism = configUtils.readConfig("NO_PLAGIARISM");
		noMedium = configUtils.readConfig("NO_MEDIUM");
		noSafe = configUtils.readConfig("NO_CLEAN");
	}
	 
	/**
	 * This method runs on page load and initializes all components of the Summary.fxml page
	 */
	@FXML protected void initialize() {
		applyStyle();
		setDefaultText(danger, noPlagiarism);
		setDefaultText(medium, noMedium);
		setDefaultText(safe, noSafe);
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
	
	/**
	 * This method is used to update the progress bar indicator to correspond to the 
	 * user selection of a student pair in the list passed. This then adds a listner to 
	 * the view and when a user selects any student pair in the list, the progress bar at 
	 * the bottom of the page reflects the change in selection.
	 * 
	 * @param listView The list view where a student pair is selected.
	 */
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
	 * This method populates the List View provided with the student pairs provided.
	 * 
	 * @param view The list view that needs to be populate
	 * @param set the pair of students that go into the list view.
	 */
	private void populateView(ListView<StudentPair> view, Set<IStudentPair> set) {
		for(IStudentPair pair : set) {
			view.getItems().add((StudentPair) pair);
		}
	}
	
	/**
	 * This method populates the list view with students who didn't plagiarize
	 * on their submission.
	 */
	private void populateSafeStudents() {
		for(Integer i : summary.getGreenIds()) {
			safe.getItems().add("Student-" + i.toString());
		}
	}
	
	/**
	 * This method takes the user back to the Start screen. It also throws a warning 
	 * to the user about losing all their changes if the choose to go back.
	 */
	@FXML public void goBack()
	{
		Optional<ButtonType> type 
		    = PopupMessage.getInstance().showConfirm(null, backWarning);
		if(type.isPresent() && type.get() == ButtonType.CANCEL) return;
		if(screenController != null) {
			Driver.getInstance().resetState();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(startPath));
			try {
				screenController.addScreen(startPage, loader.load());
				screenController.activate(startPage);
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}
	
	/**
	 * This method applies the CSS properties to the controls.
	 */
	private void applyStyle() {
		info.getStyleClass().add(customLogoStyle);
		score.getStyleClass().add(customLogoStyle);
		danger.getStyleClass().add(dangerListStyle);
		medium.getStyleClass().add(mediumListStyle);
		safe.getStyleClass().add(safeListStyle);
		back.getStyleClass().add(blueStyle);
	}

	/**
	 * When the user selects a student pair on the other list view, 
	 * this method then unselects selected highly suspicious and safe students.
	 * It also routes you to the compare page if the user double clicks on a student pair
	 * 
	 * @param event the mouse event that occurred
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
	 * When the user selects a student pair on the other list view, 
	 * this method then unselects selected mildly suspicious and safe students.
	 *  It also routes you to the compare page if the user double clicks on a student pair
	 * 
	 * @param event the mouse event that occurred
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
	 * When the user selects a student pair on the other list view, 
	 * this method then unselects selected highly suspicious and mildly suspicious students
	 */
	@FXML public void unselectMediumAndDanger() {
		unselectItems(medium, danger);
	}

	/**
	 * Removes selected items in two listViews
	 * 
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
	 * This method takes the user to the compare page sending the object information
	 * necessary to initialize the Compare Controller.
	 * 
	 * @param studentPair an object containing information about a pair of students
	 */
	public void routeToCompare(StudentPair studentPair) {
		if(screenController != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(comparePath));
			loader
				.setController(new CompareController(Driver
														.getInstance()
														.generateSnippet(
																studentPair.getStudent1Id(),
																studentPair.getStudent2Id())));
			try {
				screenController.addScreen(comparePage, loader.load());
				screenController.activate(comparePage);
			} catch (IOException e) {
				logger.error(e);
			}
		} 
	}
}
