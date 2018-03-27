package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import algorithms.Algorithm;
import controllers.popups.PopupMessage;
import driver.Driver;
import driver.Summary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
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
	@FXML private ImageView northeastern;
	@FXML private TextField hw;
	@FXML private MenuButton strategy;
	
	private Image emptyFolder;
	private Image filledFolder;
	private SaveFileObject<String> root;
	private Algorithm algo = Algorithm.DEFAULT;
	
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
		northeastern.setImage(new Image(getClass()
				.getResource("/images/logo.png")
				.toExternalForm()));
		hw.setPromptText("Select e.g. HW1...");
	}
	
	/**
	 * This method applies the CSS properties to the controls.
	 */
	private void applyStyle() {
		strategy.getStyleClass().add("primary");
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
		List<String> allPaths =  new ArrayList<>();
		getListOfPaths(allPaths, root);
		if(hw.getText() == null 
				|| allPaths.isEmpty() 
				|| root == null 
				|| "".equals(hw.getText())) {
			PopupMessage.getInstance().showAlertMessage(AlertType.ERROR,
					"Error", 
					"An error occurred", 
					"Make sure to select a directory and enter homework number");
		} else {
			if(algo == null || algo == Algorithm.DEFAULT) {
				PopupMessage.getInstance().showAlertMessage(AlertType.INFORMATION,
						"Information", 
						"Running Weighted Average", 
						"Since no strategy was provided, a weighted average of the two will be reported");
			}
			Driver drive = Driver.getInstance();
			drive.checkForPlagiarism(allPaths, hw.getText(), algo);
			routeToSummary(drive.viewSummary());
		}
	}
	
	/**
	 * This method routes to the summary page passing it the necessary detail
	 * 
	 * @param summary
	 */
	private void routeToSummary(Summary summary) {
		ScreenController screenController = ScreenController.getInstance();
		if(screenController != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Summary.fxml"));
			loader.setController(new SummaryController(summary));
			try {
				screenController.addScreen("summary", loader.load());
			} catch (IOException e) {
				logger.error(e.toString());
			}
			screenController.activate("summary");
		} else {
			PopupMessage.getInstance().showAlertMessage(AlertType.ERROR,
					"Error", 
					"An error occurred", 
					"Cannot route to the summary page. Try again later.");
		}
	}
	
	/**
	 * This is a helper method to extract all selected items in the TreeView
	 */
	private void getListOfPaths(List<String> allPaths, TreeItem<String> rootDir) {
		if(rootDir == null) {
			return;
		}
		if(((SaveFileObject<String>) rootDir).isSelected()) {
			allPaths.add(((SaveFileObject<String>)rootDir).getFile().getAbsolutePath());
		}
		Iterator<TreeItem<String>> it = rootDir.getChildren().iterator();
		while(it.hasNext()) {
			getListOfPaths(allPaths, it.next());
		}
	}
	
	/**
	 * This method populates the TreeView of the Start page
	 * 
	 * @param directory a directory as the File object
	 * @return a tree view of the directory structure
	 */
	public CheckBoxTreeItem<String> populateView(File directory) {
		dirContent.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
		SaveFileObject<String> rootDirectory  
				= new SaveFileObject<>(directory.getName(), directory);
		rootDirectory.setIndependent(true);
        for(File file : directory.listFiles()) {
            if(file.isDirectory()) {
                rootDirectory.getChildren().add(populateView(file));
            }
        }
        root = rootDirectory;
        return rootDirectory;
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
	 * Event that occurs when user selects Textual similarity Strategy
	 */
	@FXML private void selectLCS() {
		strategy.setText("Textual Similarity");
		algo = Algorithm.LCS;
	}
	
	/**
	 * Event that occurs when user selects Code similarity Strategy
	 */
	@FXML private void selectNW() {
		strategy.setText("Code Similarity");
		algo = Algorithm.NW;
	}
	
	/**
	 * The purpose of this class is to differentiate between the internal value and 
	 * the displayed value of the checkbox of the tree
	 * 
	 * @author Samanjate Sood
	 */
	private class SaveFileObject<T> extends CheckBoxTreeItem<T> {
		
		public SaveFileObject(T value, File file) {
			super(value);
			this.file = file;
		}
		
		public File getFile() {
			return file;
		}
		 
		private File file;
	}
}
