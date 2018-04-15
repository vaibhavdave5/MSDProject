package controllers;

import controllers.popups.PopupMessage;
import database.Connect;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;

/**
 * This Controller is responsible to load the menu of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class MenuController {

	@FXML private MenuBar menu;
	
	/**
	 * This method runs on page load and initializes all components of the Start.fxml page
	 */
	@FXML protected void initialize() {
		applyStyle();
	}
	
	/**
	 * This method applies the CSS properties to the controls of the Start page.
	 */
	private void applyStyle() {
		menu.getStyleClass().add("menu-color");
	}
	
	/**
	 * This methods shows the information about the application
	 */
	@FXML public void showAbout() {
		PopupMessage.getInstance().showAlert(AlertType.INFORMATION,
				"About", 
				"© Team 107\nManaging Software Development, Spring 2018", 
				"This Software was developed by:\n\nDave, Vaibhav\nPanse, Darshan\nShah, Shail\nSood, Samanjate");
	}
	
	/**
	 * This methods shows the information about the application
	 */
	@FXML public void showStatistics() {
		PopupMessage.getInstance().showAlert(AlertType.INFORMATION,
				"Global Statistics", "Statistics can be found here",
				"Number of files scanned till now are :"
				+Connect.getNumberofFilesFromStatistics()
				+System.lineSeparator()
				+"Number of plagiarism cases detected :"+
				Connect.getCases());
	}
	
	/**
	 * This method shuts down the application
	 */
	@FXML public void closeApplication() {
		Platform.exit();
        System.exit(0);
	}
}
