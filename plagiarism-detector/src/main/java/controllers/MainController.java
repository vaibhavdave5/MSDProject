package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import driver.Driver;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;

/**
 * This Controller is responsible to load the main page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class MainController {
	
	// Controller injectors
	@FXML private TreeView<String> dirContent;
	@FXML private Button summary;
	@FXML private Label logo;
	@FXML private Label chooseDir;
	@FXML private ImageView folder;
	@FXML private TextField hw;
	
	private Image emptyFolder;
	private Image filledFolder;
	
	private static Logger logger = Logger.getLogger(MainController.class);
	
	public MainController() {
		emptyFolder = new Image(getClass()
				.getResource("/images/folder.png")
				.toExternalForm());
		filledFolder = new Image(getClass()
				.getResource("/images/folder-filled.png")
				.toExternalForm());
	}
	
	/**
	 * This method runs on page load and initializes all components of the Start.fxml page
	 */
	@FXML protected void initialize() {
		applyStyle();
		folder.setImage(emptyFolder);
	}
	
	/**
	 * This method applies the CSS properties to the controls of the Start page.
	 */
	private void applyStyle() {
		summary.getStyleClass().add("primary");
		logo.getStyleClass().add("logo");
		chooseDir.getStyleClass().add("drag-folder");
	}
	
	/**
	 * This method handles the event when the open button is clicked on the Start page.
	 * It open the browse dialog box where users can choose a valid directory only.
	 */
	@FXML public void browseDirectory() {
		try {
			File directory = selectDirectory();
			if(directory != null && directory.isDirectory()) {
				hideImage();
				dirContent.setRoot(populateView(directory));
				dirContent.setShowRoot(true);
			}
		} catch(Exception e) { 
			logger.error(e.toString());
		}
	}
	
	/**
	 * This method is responsible to open the browse directory dialog box
	 * @return the directory chooser dialog box
	 */
	private File selectDirectory() {
		return new DirectoryChooser().showDialog(null);
	}
	
	/**
	 * This method runs the algorithm
	 */
	@FXML public void runAlgorithm() {
		Driver.getInstance().checkForPlagiarism(getListOfPaths(), hw.getText());
	}
	
	/**
	 * This is a helper method to extract all selected items in the TreeView
	 */
	private List<String> getListOfPaths() {
		return new ArrayList<>();
	}
	
	/**
	 * This method populates the TreeView of the Start page
	 * 
	 * @param directory a directory as the File object
	 * @return a tree view of the directory structure
	 */
	public CheckBoxTreeItem<String> populateView(File directory) {
		dirContent.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
		SaveDisplayValue<String> root  
				= new SaveDisplayValue<>(directory.getName(), directory);
		root.setIndependent(true);
        for(File file : directory.listFiles()) {
            if(file.isDirectory()) {
                root.getChildren().add(populateView(file));
            }
        }
        return root;
    }
	
	/**
	 * This method acknowledges if the dragged file is acceptable in the application
	 * 
	 * @param event
	 */
	@FXML public void handleDragOver(DragEvent event) {
		if(event.getDragboard().hasFiles()) {
			folder.setImage(filledFolder);
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	/**
	 * This method accepts only only directory in the application, which will be 
	 * the first selected directory by the user that is dragged into the application.
	 * 
	 * @param event
	 */
	@FXML public void handleDrop(DragEvent event) {
		folder.setImage(emptyFolder);
		List<File> files = event.getDragboard().getFiles();
		if(!files.isEmpty() && files.get(0).isDirectory()) {
			hideImage();
			dirContent.setRoot(populateView(files.get(0)));
			dirContent.setShowRoot(true);
		}
	}
	
	/**
	 * A method to handle the mouse enter event on the ImageView of the page
	 */
	@FXML public void onMouseEntered() {
		folder.setCursor(Cursor.HAND);
		folder.setImage(filledFolder);
	}
	
	/**
	 * A method to handle the mouse exit event on the ImageView of the page
	 */
	@FXML public void onMouseExited() {
		folder.setImage(emptyFolder);
	}
	
	/**
	 * This method hides the image and label in the Tree View
	 */
	private void hideImage() {
		folder.setVisible(false);
		chooseDir.setVisible(false);
	}
	
	/**
	 * The purpose of this class is to differentiate between the internal value and 
	 * the displayed value of the checkbox of the tree
	 * 
	 * @author Samanjate Sood
	 *
	 * @param <String>
	 */
	private class SaveDisplayValue<T> extends CheckBoxTreeItem<T> {
		
		public SaveDisplayValue(T value, File file) {
			super(value);
			this.file = file;
		}
		
		public File getFile() {
			return file;
		}
		 
		private File file;
	}
}
