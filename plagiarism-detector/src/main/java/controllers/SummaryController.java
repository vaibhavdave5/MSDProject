package controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is a Controller for the Summary page of the application
 * 
 * @author Samanjate Sood
 *
 */
public class SummaryController {
	
	private Double similarity;
	private ScreenController screenController;
	
	@FXML private ListView<String> green;
	@FXML private ListView<String> yellow;
	@FXML private ListView<String> red;
	@FXML private AnchorPane ap;
	
	private static final Logger LOGGER = Logger.getLogger(SummaryController.class.getName());
	private String errorMessage = "An Error Occured!";
	
	public SummaryController(double similarity) {
		this.screenController = ScreenController.getInstance();
		this.similarity = similarity;
	}
	
	/**
	 * This method initializes the content on the Summary page of the application
	 * 
	 * Note: This is hard-coded and will be addressed in the next sprint.
	 */ 
	@FXML protected void initialize() {
		if(similarity == null || similarity < 0 || similarity > 100) {
			return;
		}
		double percentage = similarity * 100;
		// Hard-coded values will be addressed in the next iteration
		String pair = "Student1 - Student2";
		if(percentage >= 60) {
			red.getItems().add(pair);
		} else if(30 <= percentage &&  percentage < 60) {
			yellow.getItems().add(pair);
		} else {
			green.getItems().add(pair);
		}
    }
	
	/**
	 * This method handles the event when the Settings button is pressed on the Summary page.
	 */
	public void openSettings() {
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(ap.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Settings.fxml"));
		loader.setController(new SettingsController());
        Scene dialogScene;
		try {
			dialogScene = new Scene(loader.load(), 300, 200);
			dialog.setScene(dialogScene);
	        dialog.show();
		} catch (IOException e) {
			LOGGER.info(errorMessage);
		}
	}
	
	/**
	 * This method handles the event when the Back button is pressed on the Summary page.
	 */
	public void goHome() {
		if(screenController != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Start.fxml"));
			try {
				screenController.addScreen("main", loader.load());
				screenController.activate("main");
			} catch (IOException e) { 
				LOGGER.info(errorMessage);
			}
		}
	}
	
	/**
	 * This method handles the event when a student in the green box is selected
	 */
	@FXML public void generateSummaryGreen() {
	    // Implementation will be added in the next iteration.
	}
	
	/**
	 * This method handles the event when a pair of students in the yellow box is selected
	 */
	@FXML public void generateSummaryYellow() {
		// Implementation will be added in the next iteration.
	}
	
	/**
	 * This method handles the event when a pair of students in the red box is selected 
	 * and double clicked.
	 */
	@FXML public void generateSummaryRed(MouseEvent event) {
		if(event.getClickCount() == 2 && screenController != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Compare.fxml"));
			loader.setController(new CompareController());
			try {
				screenController.addScreen("compare", loader.load());
				screenController.activate("compare");
			} catch (IOException e) { 
				LOGGER.info(errorMessage);
			}
		}
	}
}
