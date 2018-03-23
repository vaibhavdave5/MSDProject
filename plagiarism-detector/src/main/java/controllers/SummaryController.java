package controllers;

import driver.Summary;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

/**
 * This Controller is responsible to load the Summary page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class SummaryController {

	@FXML private ProgressBar progress;
	@FXML private Label info;
	@FXML private ListView<String> danger;
	@FXML private ListView<String> medium;
	@FXML private ListView<String> safe;
	@FXML private Button back;
	
	private Summary summary;
	
	public SummaryController(Summary summary) {
		this.summary = summary;
	}
	
	/**
	 * This method runs on page load and initializes all components of the Summary.fxml page
	 */
	@FXML protected void initialize() {
		applyStyle();
		progress.setProgress(0.25);
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		danger.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		medium.getItems().add("Darshan - Vaibhav");
		safe.getItems().add("Samanjate");
		safe.getItems().add("Samanjate");
		safe.getItems().add("Samanjate");
		safe.getItems().add("Samanjate");
		safe.getItems().add("Samanjate");
	}
	
	/**
	 * This method applies the CSS properties to the controls.
	 */
	private void applyStyle() {
		info.getStyleClass().add("logo");
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
	 * 
	 * @param listView1
	 * @param listView2
	 */
	private void unselectItems(ListView<String> listView1, ListView<String> listView2) {
		listView1.getSelectionModel().clearSelection();
		listView2.getSelectionModel().clearSelection();
	}
}
