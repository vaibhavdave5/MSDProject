package controllers.popups;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import utils.ConfigUtils;

import java.util.Optional;

/**
 * This class is responsible for alerting the user for any errors, info, confirmations
 * that occurs during the application run
 * 
 * @author Samanjate Sood
 *
 */
public class PopupMessage {

	private static PopupMessage instance;
	private String errorTitle;
	private String errorHeader;
	private String infoTitle;
	private String infoHeader;
	private String confirmTitle;
	private String confirmHeader;
	
	private PopupMessage() {
		initConfigVar();
	}
	
	/**
	 * This is a method used to initialize the configuration variables used in 
	 * the class
	 */
	private void initConfigVar() {
		ConfigUtils configUtils = new ConfigUtils();
		errorTitle = configUtils.readConfig("ERROR_TITLE");
		errorHeader = configUtils.readConfig("ERROR_HEADER");
		infoTitle = configUtils.readConfig("INFO_TITLE");
		infoHeader = configUtils.readConfig("INFO_HEADER");
		confirmTitle = configUtils.readConfig("CONFIRM_TITLE");
		confirmHeader = configUtils.readConfig("CONFIRM_HEADER");
	}
	
	/**
	 * This follows the singleton pattern
	 * 
	 * @return the instance of this class
	 */
	public static PopupMessage getInstance() {
		if(instance == null) {
			instance = new PopupMessage();
		}
		return instance;
	}
	
	/**
	 * This method returns a ButtonType which gives the caller of the function to
	 * choose from the different actions on the Dialog Box as configured. 
	 * 
	 * @param type The type of Alert Dialog box the user wishes to display.
	 * 
	 * @param title The title of the alert dialog box. Will use defaults if null.
	 * 
	 * @param headerText The text shown on the upper-half of the alert dialog box
	 * 
	 * @param contentText The text shown on the lower-half of the alert dialog box
	 * 
	 * @return an optional Button type that are on the the alert box
	 */
	public Optional<ButtonType> showAlert(AlertType type,
										 String title,
										 String headerText, 
										 String contentText) {
		if(type == AlertType.CONFIRMATION) {
			return showDialog(AlertType.CONFIRMATION, 
					  title == null ? confirmTitle : title, 
					  headerText, 
					  confirmHeader, 
					  contentText);
		} else if(type == AlertType.ERROR) {
			return showDialog(AlertType.ERROR, 
					  title == null ? errorTitle : title, 
					  headerText, 
					  errorHeader, 
					  contentText);
		} else {
			return showDialog(AlertType.INFORMATION, 
					  title == null ? infoTitle : title, 
					  headerText, 
					  infoHeader, 
					  contentText);
		}
	}
	
	/**
	 * This method returns a ButtonType which gives the caller of the function to
	 * choose from the different actions on the Error Message. In this case the only 
	 * button returned would be a close button.
	 * 
	 * @param headerText The text shown in the upper half of the Error Message, 
	 * this can be null if you want to use the default Error Message header Text.
	 * 
	 * @param contentText The text shown in the lower half of the Error Message,
	 * since this provides the details of the error message, this ideally should not be null
	 * 
	 * @return an optional Button type that are on the the alert box
	 */
	public Optional<ButtonType> showError(String headerText, 
										 String contentText) {
		return showDialog(AlertType.ERROR, 
				  errorTitle, 
				  headerText, 
				  errorHeader, 
				  contentText);
	}
	
	/**
	 * This method returns a ButtonType which gives the caller of the function to
	 * choose from the different actions on the Info Message. In this case the  
	 * buttons returned would be a close button and an OK button.
	 * 
	 * @param headerText The text shown in the upper half of the Info Message, 
	 * this can be null if you want to use the default Info Message header Text.
	 * 
	 * @param contentText The text shown in the lower half of the Info Message,
	 * since this provides the details of the info message, this ideally should not be null
	 * 
	 * @return an optional Button type that are on the the alert box
	 */
	public Optional<ButtonType> showInfo(String headerText, 
										 String contentText) {
		return showDialog(AlertType.INFORMATION, 
						  infoTitle, 
						  headerText, 
						  infoHeader, 
						  contentText);
	}
	
	/**
	 * This method returns a ButtonType which gives the caller of the function to
	 * choose from the different actions on the Confirmation Message. In this case the  
	 * buttons returned would be a close button, a YES button, and a NO button.
	 * 
	 * @param headerText The text shown in the upper half of the Confirmation Message, 
	 * this can be null if you want to use the default Confirmation Message header Text.
	 * 
	 * @param contentText The text shown in the lower half of the Confirmation Message,
	 * since this provides the details of the Confirmation message, this ideally should not be null
	 * 
	 * @return an optional Button type that are on the the alert box
	 */
	public Optional<ButtonType> showConfirm(String headerText, 
										 String contentText) {
		return showDialog(AlertType.CONFIRMATION, 
						  confirmTitle, 
						  headerText, 
						  confirmHeader, 
						  contentText);
	}
	
	/**
	 * This is a generalized helper method that generalizes the four cases for
	 * Error, Info, Confirmation, and Custom Alert Types.
	 * 
	 * @param type The type of Alert Dialog box requested
	 * 
	 * @param title The title of the Alert Dialog box
	 * 
	 * @param headerText The text that will be displayed on the upper half of the Alert 
	 * dialog box
	 * 
	 * @param defaultHeaderText The default header text incase the provided header text 
	 * is null
	 * 
	 * @param contentText The text that will be displayed in the lower half of the Alert
	 * Dialog box
	 * 
	 * @return an optional Button type that are on the the alert box
	 */
	private Optional<ButtonType> showDialog(AlertType type, 
										   String title, 
										   String headerText, 
										   String defaultHeaderText,
										   String contentText) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		if(headerText != null) {
			alert.setHeaderText(headerText);
		} else {
			alert.setHeaderText(defaultHeaderText);
		}
		if(contentText != null) {
			alert.setContentText(contentText);
		}
		addCloseOptionToDialog(alert);
		return alert.showAndWait();
	}
	
	/**
	 * This is a helper method that adds a hidden close button to the provided alert box.
	 * 
	 * Note: This was added because the default close button on JavaFx was not operational
	 * on some OS's
	 * 
	 * @param alert an Alert where the close option needs to be added.
	 */
	private void addCloseOptionToDialog(Alert alert) {
		alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
	}
}
