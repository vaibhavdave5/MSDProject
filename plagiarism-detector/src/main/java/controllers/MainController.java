package controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.stage.DirectoryChooser;

/**
 * This Controller is responsible to load the main page of the application.
 * 
 * @author Samanjate Sood
 * @author Shail Shah
 *
 */
public class MainController {
	
	@FXML private TreeView<String> dirContent;
	@FXML private TextField search;
	@FXML private Button dir;
	@FXML private Button summary;
	@FXML private Label logo;
	
	@FXML protected void initialize() {
		applyStyle();
	}
	
	public void browseDirectory() {
		try {
			File directory = selectDirectory();
			dirContent.setRoot(populateView(directory));
			dirContent.setShowRoot(true);
		} catch(Exception e) { }
	}
	
	private File selectDirectory() {
		return new DirectoryChooser().showDialog(null);
	}
	
	public CheckBoxTreeItem<String> populateView(File directory) {
		dirContent.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
		CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>(directory.getName());
        for(File f : directory.listFiles()) {
            if(f.isDirectory()) {
            		root.getChildren().add(new CheckBoxTreeItem<String>(f.getName()));
                root.getChildren().add(populateView(f));
            }
        }
        return root;
    }
	
	private void applyStyle() {
		dir.getStyleClass().add("primary");
		summary.getStyleClass().add("danger");
		logo.getStyleClass().add("logo");
		search.setPromptText("Search...");
		
	}
}
