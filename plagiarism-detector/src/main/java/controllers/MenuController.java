package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;

public class MenuController {

	@FXML private MenuBar menu;
	
	/**
	 * This methods shows the information about the application
	 */
	@FXML public void showAbout() {
		Alert alert = new Alert(null);
		alert.setTitle("About");
		alert.setHeaderText("© Team 107\nManaging Software Development, Spring 2018");
		alert.setContentText("This Software was developed by:\n\nDave, Vaibhav\nPanse, Darshan\nShah, Shail\nSood, Samanjate");
		alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
		alert.showAndWait();
	}
	
	/**
	 * THis method shuts down the application
	 */
	@FXML public void closeApplication() {
		Platform.exit();
        System.exit(0);
	}
}
