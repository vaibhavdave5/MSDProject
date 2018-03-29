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
import driver.IDriver;
import driver.ISummary;
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
import javafx.stage.FileChooser;
/**
 * This Controller is responsible to load the main page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class MainController {
	
	// Controller injectors
	@FXML private TreeView<DirectoryView> dirContent;
	@FXML private Button summary;
	@FXML private Button excel;
	@FXML private Label logo;
	@FXML private Label chooseDir;
	@FXML private ImageView folder;
	@FXML private ImageView northeastern;
	@FXML private TextField hw;
	@FXML private MenuButton strategy;
	
	private Image emptyFolder;
	private Image filledFolder;
	private File excelFile;
	private CheckBoxTreeItem<DirectoryView> root;
	private Algorithm algo = Algorithm.DEFAULT;
	
	private static Logger logger = Logger.getLogger(MainController.class);
	
	public MainController() {
		emptyFolder = new Image(getClass()
				.getResource("/images/folder.png")
				.toExternalForm());
		filledFolder = new Image(getClass()
				.getResource("/images/folder-filled.png")
				.toExternalForm());
		excelFile = null;
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
		excel.getStyleClass().add("danger");
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
	 * This method uploads the excel sheet provided by the user, used to validate
	 * repo names and student IDs 
	 */
	@FXML public void uploadExcel() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLST files (*.xlsx)", "*.xlsx");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
        if (file != null) {
        		excelFile = file;
	        	excel.getStyleClass().removeAll("danger");
	    		excel.getStyleClass().add("success");
        }
	}
	
	/**
	 * This method runs the algorithm
	 */
	@FXML public void runAlgorithm() {
		List<String> allPaths =  new ArrayList<>();
		getListOfPaths(allPaths, root);
		if(hw.getText() == null 
				|| allPaths.isEmpty() 
				|| "".equals(hw.getText())
				|| excelFile == null) {
			PopupMessage.getInstance().showAlertMessage(AlertType.ERROR,
					"Error", 
					"An error occurred", 
					"Make sure to select a directory, upload an excel validator sheet, and to enter a homework number");
		} else {
			if(algo == null || algo == Algorithm.DEFAULT) {
				PopupMessage.getInstance().showAlertMessage(AlertType.INFORMATION,
						"Information", 
						"Running Weighted Average", 
						"Since no strategy was provided, a weighted average of the two will be reported");
			}
			IDriver drive = Driver.getInstance();
			drive.getStudentData(excelFile);
			String returnMessage = drive.checkForPlagiarism(allPaths, hw.getText(), algo);
			if(!returnMessage.isEmpty()) {
				PopupMessage.getInstance().showAlertMessage(AlertType.ERROR,
						"Error!", 
						"Some error occurred", 
						returnMessage);
			} else {
				routeToSummary(drive.viewSummary());
			}
		}
	}
	
	/**
	 * This method routes to the summary page passing it the necessary detail
	 * 
	 * @param iSummary
	 */
	private void routeToSummary(ISummary iSummary) {
		ScreenController screenController = ScreenController.getInstance();
		if(screenController != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Summary.fxml"));
			loader.setController(new SummaryController(iSummary));
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
	private void getListOfPaths(List<String> allPaths, 
							   	  CheckBoxTreeItem<DirectoryView> rootDir) {
		if(rootDir == null) {
			return;
		}
		Iterator<TreeItem<DirectoryView>> it = rootDir.getChildren().iterator();
		while(it.hasNext()) {
			CheckBoxTreeItem<DirectoryView> dir = (CheckBoxTreeItem<DirectoryView>) it.next();
			if(dir.isSelected()) {
				allPaths.add(dir.getValue().getFile().getAbsolutePath());
			}
		}
	}
	
	/**
	 * This method populates the TreeView of the Start page
	 * 
	 * @param directory a directory as the File object
	 * @return a tree view of the directory structure
	 */
	public CheckBoxTreeItem<DirectoryView> populateView(File directory) {
		dirContent.setCellFactory(CheckBoxTreeCell.<DirectoryView>forTreeView());
		CheckBoxTreeItem<DirectoryView> rootDirectory  
				= new CheckBoxTreeItem<>(new DirectoryView(directory));
        for(File file : directory.listFiles()) {
            if(file.isDirectory()) {
                rootDirectory
                			.getChildren()
                			.add(new CheckBoxTreeItem<>(new DirectoryView(file)));
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
	 * This is the internal representation of a tree item on the TreeView
	 * 
	 * @author Samanjate Sood
	 */
	private class DirectoryView {
		
		public DirectoryView(File file) {
			this.file = file;
		}
		
		public File getFile() {
			return file;
		}
		
		@Override
		public String toString() {
			return file.getName();
		}
		 
		private File file;
	}
}
