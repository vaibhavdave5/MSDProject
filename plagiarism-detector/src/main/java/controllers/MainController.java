package controllers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import algorithms.LCSAlgorithm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This Controller is responsible to load the main page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class MainController {
	
	@FXML private Label upload;
	@FXML private Label file1;
	@FXML private Label file2;
	@FXML private AnchorPane ap;
	
	private File fileOne;
	private File fileTwo;
	
	private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());
	private String errorMessage = "An Error Occured!";
	
	/** 
	 * This method handles the event when the setting button on the main page is pressed.
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
	 * This method runs the algorithm on the two .C files uploaded.
	 * 
	 * Note: The method only runs LCS for now and will be more flexible in the next sprint.
	 */
	public void computeSimilarity() {
		if(fileOne != null && fileTwo != null) {
			AlgorithmController algo = new AlgorithmController(fileOne, fileTwo);			
			try {
				ScreenController screenController = ScreenController.getInstance();
				if(screenController != null) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/Summary.fxml"));
					loader.setController(new SummaryController(algo.getAns(new LCSAlgorithm())));
					screenController.addScreen("summary", loader.load());
					screenController.activate("summary");
				} else {
					upload.setText("Application Error!");
				}
			} catch(IOException e) {
				upload.setText("Error Occured!");
			}
		} else {
			upload.setText("Please Upload both files!");
		}
	}
	
	/**
	 * This method uploads the first C file
	 */
	public void openFile1() {
		final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
        		fileOne = file;
        		file1.setText(file.getName());
        }
        
    }
	
	/**
	 * This method uploads the second C file
	 */
	public void openFile2() {
		final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
        		fileTwo = file;
        		file2.setText(file.getName());
        }
        
    }

}
