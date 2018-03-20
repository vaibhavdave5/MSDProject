package controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

/**
 * This Controller is responsible to load the main page of the application.
 * 
 * @author Samanjate Sood
 * @author Shail Shah
 *
 */
public class MainController {
	
	@FXML private Label dirName;
	@FXML private ListView<String> dirContent;
	@FXML private TextField filterVal;
	
	public void browseDirectory() {
		try {
			File directory = selectDirectory();
			setDirectoryLabel(directory);
			populateView(directory);
		} catch(Exception e) { }
	}
	
	private File selectDirectory() {
		return new DirectoryChooser().showDialog(null);
	}
	
	private void setDirectoryLabel(File directory) {
		if(directory == null) {
			dirName.setText("No Directory selected");
		} else {
			dirName.setText(directory.getAbsolutePath());
		}
	}
	
	private void populateView(File directory) {
		dirContent.getItems().clear();
		for(File file : directory.listFiles()) {
			dirContent.getItems().add(file.getName());
		}
	}
}
