package view;
	
import org.apache.log4j.Logger;

import controllers.ScreenController;
import controllers.popups.PopupMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
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
			ScreenController.setInstance(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			PopupMessage.getInstance().showAlertMessage(AlertType.ERROR,
					"Error", 
					"An error occurred", 
					"Try again later.");
			logger.error(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
