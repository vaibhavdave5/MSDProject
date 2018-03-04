package controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is a Controller for Compare page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class CompareController {
	
	private ScreenController screenController;
	
	@FXML private ScrollPane code1;
	@FXML private ScrollPane code2;
	@FXML private AnchorPane ap;
	
	private static final Logger LOGGER = Logger.getLogger(CompareController.class.getName());
	private String errorMessage = "An Error Occured!";
	
	public CompareController() {
		this.screenController = ScreenController.getInstance();
	}
 	
	/**
	 * This function initializes the content of the page.
	 * 
	 * Note: This is hard-coded and will be addressed in the next sprint.
	 */
	@FXML protected void initialize() {
		VBox student1Code = new VBox();
	    Label label1 = new Label("int main()\n" +
	            "{\n" +
	            "\tscanf(\"%d\",&t);\n" +
	            "\tprintf(t):\n" +
	            "}");
	    label1.setWrapText(true);
	    student1Code.getChildren().addAll(label1);
	    code1.setContent(student1Code);
	    code1.setFitToWidth(true); 
	    
	    VBox student2Code = new VBox();
	    Label label2 = new Label("int main()\n" +
	            "{\n" +
	            "\tscanf(\"%d\",&p);\n" +
	            "\tprintf(p):\n" +
	            "}");
	    label2.setWrapText(true);
	    student2Code.getChildren().addAll(label2);
	    code2.setContent(student2Code);
	    code2.setFitToWidth(true); 
    }

	/**
	 * This method handles the event when the settings button on the Compare page
	 * is pressed.
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
	 * This method handles the event when the Back button is pressed on the Compare page.
	 */
	public void goHome() {
		if(screenController != null) {
			screenController.activate("summary");
		}
	}
}
