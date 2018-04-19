package view;

import org.apache.log4j.Logger;

import constants.AlertStrings;
import controllers.ScreenController;
import controllers.popups.PopupMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry point of application  
 * @author Samanjate Sood
 * @author Shail Shah
 * @since 01-03-2018
 *
 */ 

public class Main extends Application {

	private static Logger logger = Logger.getLogger(Main.class);

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Start.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/theme.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("/images/app-icon.png"));
			ScreenController.setInstance(scene);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Integrity Plagiarism Checker by Team-107");
			primaryStage.show();
			PopupMessage.getInstance().showInfo("INSTRUCTIONS", 
					AlertStrings.NO_DIRECTORY_SELECTED_MESSAGE
					+ AlertStrings.HOMEWORK_NUMBER_MESSAGE
					+ AlertStrings.NO_EXEL_FILE_MESSAGE);
		} catch(Exception e) {
	 		PopupMessage.getInstance().showError(null, null);
			logger.error(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	
	}
}
