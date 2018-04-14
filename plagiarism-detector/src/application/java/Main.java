package java;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {
	
	
	private Logger logger = Logger.getLogger(this.getClass().toString());
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			logger.log(Level.SEVERE, e.toString());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
