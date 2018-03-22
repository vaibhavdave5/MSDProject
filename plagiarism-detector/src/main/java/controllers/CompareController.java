package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;

/**
 * Controller for the UI that shows comparision between two submissions
 * @author Shail Shah
 */
public class CompareController {
	// Controller injectors
	@FXML private TextFlow studentACode;
	@FXML private TextFlow studentBCode;
	@FXML private Button reportButton;
	@FXML private Label StudentAName;
	@FXML private Label StudentBName;

	/**
	 * This method runs on page load and initializes all components of the Start.fxml page
	 */
	@FXML
	protected void initialize() {

	}


}
