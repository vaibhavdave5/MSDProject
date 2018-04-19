package controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

/**
 * This Controller is responsible to switch between different screens of the application
 * 
 * @author Samanjate Sood
 *
 */
public class ScreenController {
	
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;
    private static ScreenController instance;

    /**

     * @param main The start screen of the application
     */
    private ScreenController(Scene main) { 
    		this.main = main;
    } 
    
    /**
     * This class follows the Singleton pattern and this is the only way to 
     * get the instance of the class. If this object has not been set with the setter
     * below, this will return null
     * 
     * @return the instance of the class
     */
    public static ScreenController getInstance() {
    		return instance;
    }
    
    /**
     * Sets the instance of the class with the provided scene. This can only be 
     * done once in one application run.
     * 
     * @param main The first scene of the application
     */
    public static void setInstance(Scene main) {
    		if(instance == null) {
    			instance = new ScreenController(main);
    		}
    }

    /**
     * This method adds another Screen and saves it in the application session.
     * 
     * @param name the name of the screen
     * @param pane The representation of the screen in form of a Pane
     */
    public void addScreen(String name, Pane pane){
         screenMap.put(name, pane);
    }

    /**
     * This method is used to remove a screen from the application session.
     * This is seldom used.
     * 
     * @param name The name of the screen that needs to be removed.
     */
    public void removeScreen(String name){
        screenMap.remove(name);
    }

    /**
     * This method activates the provided screen and switches it to that from the 
     * current screen
     * 
     * @param name The name of the screen that needs to be activated
     */
    public void activate(String name){
        main.setRoot(screenMap.get(name) );
    }
}
