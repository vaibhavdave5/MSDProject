package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import utils.Utils;

import java.io.*;

/**
 * Controller for the UI that shows comparision between two submissions
 * @author Shail Shah
 */
public class CompareController {
	// Controller injectors
	@FXML private TextFlow studentACode;
	@FXML private TextFlow studentBCode;
	@FXML private Button reportButton;
	@FXML private Label studentAName;
	@FXML private Label studentBName;

	/**
	 * This method runs on page load and initializes all components of the Start.fxml page
	 */
	@FXML
	protected void initialize() {
		studentACode.setPrefWidth(Region.USE_COMPUTED_SIZE);
		String family = "Helvetica";
		double size = 12;


		Text text1 = new Text(Utils.getFileString(new File("sample3.c"), 4, 10));

		text1.setFont(Font.font(family, size));
		text1.setFill(Color.RED);
		Text text2 = new Text("Bold");
		text2.setFill(Color.ORANGE);
		text2.setFont(Font.font(family, FontWeight.BOLD, size));
		Text text3 = new Text(" World");
		text3.setFill(Color.GREEN);
		text3.setFont(Font.font(family, FontPosture.ITALIC, size));
		studentACode.getChildren().addAll(text1, text2, text3);
		studentBCode.getChildren().addAll(text1, text2, text3);
	}


}
