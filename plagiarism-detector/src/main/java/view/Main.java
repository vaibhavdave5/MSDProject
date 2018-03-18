package view;
	
import java.util.logging.Logger;

import controllers.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Entry point of application  
 * @author Samanjate
 * @since 01-03-2018
 *
 */

public class Main extends Application {
	
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	private String errorMessage = "An Error Occured!";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Start.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			ScreenController.setInstance(scene);
			primaryStage.show();
		} catch(Exception e) {
			LOGGER.info(errorMessage);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
