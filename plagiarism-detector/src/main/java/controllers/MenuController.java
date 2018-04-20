package controllers;

import controllers.popups.PopupMessage;
import database.Connect;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import utils.ConfigUtils;
import javafx.scene.control.MenuBar;

/**
 * This Controller is responsible to load the menu of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class MenuController {

	// This is injected when the Controller is binded with the FXML view and hence,
	// it doesn't need to be instantiated.
	@FXML private MenuBar menu;
	
	// Configuration Variables
	private String menuColor;
	private String aboutTitle;
	private String aboutHeader;
	private String aboutDetails;
	private String gsTitle;
	private String gsHeader;
	private String gsDetailsFiles;
	private String gsDetailsCases;
	
	/**
	 * This method runs on page load and initializes the configuration variables and 
	 * applies style to the Menu
	 */
	@FXML protected void initialize() {
		initConfigVar();
		applyStyle();
	}
	
	/**
	 * This is a method used to initialize the configuration variables used in 
	 * the class
	 */
	private void initConfigVar() {
		ConfigUtils configUtils = new ConfigUtils();
		menuColor = configUtils.readConfig("MENU_COLOR");
		aboutTitle = configUtils.readConfig("ABOUT_TITLE");
		aboutHeader = configUtils.readConfig("ABOUT_HEADER");
		aboutDetails = configUtils.readConfig("ABOUT_DETAILS");
		gsTitle = configUtils.readConfig("GS_TITLE");
		gsHeader = configUtils.readConfig("GS_HEADER");
		gsDetailsFiles = configUtils.readConfig("GS_DETAILS_FILES");
		gsDetailsCases = configUtils.readConfig("GS_DETAILS_CASES");
	}
	
	/**
	 * This method applies the CSS properties to the Menu Bar.
	 */
	private void applyStyle() {
		menu.getStyleClass().add(menuColor);
	}
	
	/**
	 * This methods shows the information about the application
	 */
	@FXML public void showAbout() {
		PopupMessage.getInstance().showAlert(AlertType.INFORMATION, 
											aboutTitle,
											aboutHeader, 
											aboutDetails);
	}
	
	/**
	 * This methods shows the information about the application.
	 */
	@FXML public void showStatistics() {
		PopupMessage.getInstance().showAlert(AlertType.INFORMATION,
											gsTitle, 
											gsHeader,
											gsDetailsFiles + Connect.getNumberofFilesFromStatistics()
											+ System.lineSeparator()
											+ gsDetailsCases + Connect.getCases());
	}
	
	/**
	 * This method shuts down the application.
	 */
	@FXML public void closeApplication() {
		Platform.exit();
        System.exit(0);
	}
}
