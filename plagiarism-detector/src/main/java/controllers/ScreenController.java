package controllers;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * This class is a Controller used to switch between different pages of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class ScreenController {
	
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;
    private static ScreenController instance;

    private ScreenController(Scene main) { 
    		this.main = main;
    } 
    
    /**
     * Since the constructor is marked private, it follows a Singleton pattern
     * 
     * @return the instance of this class.
     */
    public static ScreenController getInstance() {
    		return instance;
    }
    
    /**
     * Initialize the instance of the class. Can only be done once.
     * 
     * @param The main application scene.
     */
    public static void setInstance(Scene main) {
    		if(instance == null) {
    			instance = new ScreenController(main);
    		}
    }

    /**
     * Adds a new screen on the application.
     * 
     * @param name of the screen you would like to use to retrieve it later.
     * @param pane the screen representation
     */
    public void addScreen(String name, Pane pane){
         screenMap.put(name, pane);
    }

    /**
     * Removes the screen from the application session.
     * 
     * @param name of the screen that you need to remove
     */
    public void removeScreen(String name){
        screenMap.remove(name);
    }

    /**
     * This activates the screen meaning that will be displayed to the user.
     * 
     * @param name of the screen that needs to be activated
     */
    public void activate(String name){
        main.setRoot(screenMap.get(name) );
    }
}
