package controllers.popups;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * This class is responsible for alerting the user for any errors, alerts, messages
 * that occurs during the application run
 * 
 * @author Samanjate Sood
 *
 */
public class PopupMessage {

	private static PopupMessage instance;
	
	private PopupMessage() {
		
	}
	
	/**
	 * This follows the singleton pattern
	 * 
	 * @return the instance of this class
	 */
	public static PopupMessage getInstance() {
		if(instance == null) {
			return new PopupMessage();
		} else {
			return instance;
		}
	}
	
	/**
	 * This method returns an alert message w.r.t. the configurtions provided
	 * 
	 * @param type The type of the Alert Message
	 * @param title
	 * @param headerText 
	 * @param contentText
	 */
	public void showAlertMessage(AlertType type, String title, String headerText, String contentText) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
		alert.showAndWait();
	}
}
