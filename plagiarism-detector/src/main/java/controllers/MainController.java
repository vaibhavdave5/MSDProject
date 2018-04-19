package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import algorithms.Algorithm;
import constants.AlertStrings;
import controllers.popups.PopupMessage;
import driver.Driver;
import driver.IDriver;
import driver.ISummary;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressIndicator;
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
import utils.ConfigUtils;
/**
 * This Controller is responsible to load the main page of the application.
 * 
 * @author Samanjate Sood
 *
 */
public class MainController {
	
	// These are injected when the Controller is binded with the FXML view and hence,
	// they don't need to be instantiated.
	@FXML private TreeView<DirectoryView> dirContent;
	@FXML private Button summary;
	@FXML private Button excel;
	@FXML private Label logo;
	@FXML private Label chooseDir;
	@FXML private ImageView folder;
	@FXML private ImageView northeastern;
	@FXML private TextField hw;
	@FXML private MenuButton strategy;
	@FXML private ProgressIndicator progress;
	@FXML private Button clearButton;
	
	// Configuration Variables
	private String enterHW;
	private String pathToEmptyFolder;
	private String pathToFilledFolder;
	private String pathToLogo;
	private String greenStyle;
	private String redStyle;
	private String blueStyle;
	private String customLogoStyle;
	private String customFolderStyle;
	
	private Image emptyFolder;
	private Image filledFolder;
	private Image logoImg;
	private File excelFile;
	private CheckBoxTreeItem<DirectoryView> root;
	private Algorithm algo = Algorithm.DEFAULT;
	
	private static Logger logger = Logger.getLogger(MainController.class);
	
	public MainController() {
		initConfigVar();
		initImages();
		excelFile = null;
	}
	
	/**
	 * This is a method used to initialize the configuration variables used in 
	 * the class
	 */
	private void initConfigVar() {
		ConfigUtils configUtils = new ConfigUtils();
		enterHW = configUtils.readConfig("ENTER_HW");
		pathToEmptyFolder = configUtils.readConfig("PATH_EMPTY_FOLDER");
		pathToFilledFolder = configUtils.readConfig("PATH_FILLED_FOLDER");
		pathToLogo = configUtils.readConfig("PATH_LOGO");
		greenStyle = configUtils.readConfig("GREEN");
		redStyle = configUtils.readConfig("RED");
		blueStyle = configUtils.readConfig("BLUE");
		customLogoStyle = configUtils.readConfig("CUSTOM_LOGO");
		customFolderStyle = configUtils.readConfig("CUSTOM_FOLDER");
		
	}
	
	/**
	 * This method sets the image files that are used for the Main screen.
	 * The three images are the empty folder and the filled folder that are displayed on
	 * the drag/drop/select image area and the logo image shown at the top-left side of the 
	 * application page
	 */
	private void initImages() {
		emptyFolder = new Image(getClass()
								.getResource(pathToEmptyFolder)
								.toExternalForm());
		filledFolder = new Image(getClass()
								.getResource(pathToFilledFolder)
								.toExternalForm());
		logoImg = new Image(getClass()
								.getResource(pathToLogo)
								.toExternalForm());
	}
	
	/**
	 * This method runs on page load and initializes all components of the Start.fxml page.
	 * It initialized 
	 *  ~ the folder image of the screen to an empty folder.
	 *  ~ the logo on the screen.
	 *  ~ sets the prompt text of the text box  
	 *  ~ hides the progress that is shown when the application is processing a user request.
	 */
	@FXML protected void initialize() {
		applyStyle();
		folder.setImage(emptyFolder);
		northeastern.setImage(logoImg);
		hw.setPromptText(enterHW);
		progress.setVisible(false);
	}
	
	/**
	 * This method applies the CSS properties to the controls of the Start.fxml page.
	 * It adds style to:
	 *  ~ the drop down menu to select strategy
	 *  ~ the run button (summary)
	 *  ~ the excel upload button
	 *  ~ the clear directory button
	 *  ~ the 'Integrity' text with the logo of Northeastern
	 *  ~ the the choose directory button
	 */
	private void applyStyle() {
		strategy.getStyleClass().add(blueStyle);
		summary.getStyleClass().add(blueStyle);
		excel.getStyleClass().add(redStyle);
		clearButton.getStyleClass().add(redStyle);
		logo.getStyleClass().add(customLogoStyle);
		chooseDir.getStyleClass().add(customFolderStyle);
	}
	
	/**
	 * This method handles the event when the open button is clicked on the Start page.
	 * The open button here means the click-able folder image in the middle of the view.
	 * It open the browse dialog box where users can choose a valid directory only and not files.
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
		FileChooser.ExtensionFilter extFilter = 
				  new FileChooser.ExtensionFilter("XLST files (*.xlsx)", "*.xlsx");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
        if (file != null) {
        		excelFile = file;
	        	excel.getStyleClass().removeAll(redStyle);
	    		excel.getStyleClass().add(greenStyle);
        }
	}
	
	/**
	 * Helper method returns an error message when no student directory is set by the user.
	 * @return a message if no student directory is selected
	 */
	private String errNoStudentDir(List<String> allPaths) {
		return allPaths.isEmpty() ?
				AlertStrings.NO_DIRECTORY_SELECTED_MESSAGE :
				"";
	}
	
	/**
	 * Helper method returns an error message when only one student directory is selected.
	 * @param allPaths a list of all the paths containing student code
	 * @return a message if only one student directory is selected
	 */
	private String errOnlyOneSelected(List<String> allPaths) {
		return allPaths.size() == 1 ?
				AlertStrings.ONLY_ONE_STUDENT_SELECTED_MAKE_SURE_TO_SELECT_ATLEAST_TWO_STUDENTDS :
				"";
	}
	
	/**
	 * Helper method returns an error message when no HW is selected.
	 * @return a message if there is a problem getting the homework textbox
	 */
	private String errNoHW() {
		return (hw.getText() == null  || "".equals(hw.getText()))?
				AlertStrings.HOMEWORK_NUMBER_MESSAGE :
				"";
	}
	
	/**
	 * Helper method returns an error message when no excel file is selected.
	 * @return a message if no excel file is selected
	 */
	private String errNoExcel() {
		return (excelFile == null) ?
				AlertStrings.NO_EXEL_FILE_MESSAGE :
				"";

	}
	
	/**
	 * This method runs the algorithm
	 */
	@FXML public void runAlgorithm() {
		List<String> allPaths =  new ArrayList<>();
		getListOfPaths(allPaths, root);
		String errorMsg = errNoStudentDir(allPaths) + errNoHW() + errNoExcel() + errOnlyOneSelected(allPaths);
		if(!"".equals(errorMsg)) {
			PopupMessage.getInstance().showError(null, errorMsg);
		} else {
			if(algo == null || algo == Algorithm.DEFAULT) {
				PopupMessage.getInstance().showInfo( 
						"Running Weighted Average", 
						"Since no strategy was provided, a weighted average of the two will be reported");
				}
				runAlgorithmAndReturnResults(allPaths, Driver.getInstance());
		}
	}
	
	/**
	 * This method runs a new Thread to compute similarity on the files provided
	 * @param allPaths The path of all the files
	 * @param drive the instance of the application Driver, used globally
	 */
	private void runAlgorithmAndReturnResults(List<String> allPaths, IDriver drive) {
		Task<String> task = new Task<String>() {
		    @Override public String call() {
				drive.getStudentData(excelFile);
				return drive.checkForPlagiarism(allPaths, hw.getText(), algo);
		    }
		};
		task.setOnRunning(e -> {
			progress.setVisible(true);
			progress.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
			summary.setDisable(true);
		});
		task.setOnSucceeded(e -> {
		    try {
				String returnMessage = task.get();
				if(!returnMessage.isEmpty()) {
					progress.setVisible(false);
					summary.setDisable(false);
					PopupMessage.getInstance().showError(null, returnMessage);
				} else {
					routeToSummary(drive.viewSummary());
				}
			} catch (InterruptedException e1) {
				logger.error(e1.toString());
				Thread.currentThread().interrupt();
			} catch (ExecutionException e1) {
				logger.error(e1.toString());
			}
		});
		task.setOnFailed(e -> {
			progress.setVisible(false);
			summary.setDisable(false);
			PopupMessage.getInstance().showError(null, null);
			});
		new Thread(task).start();
	}
	
	/**
	 * This method routes to the summary page passing it the necessary detail
	 * 
	 * @param summary the summary of the plagiarism detector run
	 */
	private void routeToSummary(ISummary summary) {
		ScreenController screenController = ScreenController.getInstance();
		if(screenController != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Summary.fxml"));
			loader.setController(new SummaryController(summary));
			try {
				screenController.addScreen("summary", loader.load());
				screenController.activate("summary");
			} catch (IOException e) {
				logger.error(e.toString());
			}
		} else {
			PopupMessage.getInstance().showError(null, null);
		}
	}

	/**
	 * add all the paths of the selected directories
	 * @param allPaths a list of all the paths
	 * @param rootDir all the selected directories
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
		dirContent.setCellFactory(CheckBoxTreeCell.forTreeView());
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
	 * @param event an event containing a Dragboard
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
	 * @param event a drag event
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
	 * Show the folder icon and prompt to select a root directory
	 */
	@FXML public void clearDirContent() {
		dirContent.setRoot(null);
		folder.setVisible(true);
		chooseDir.setVisible(true);
	}
	
	/**
	 * Event that occurs when user selects LCS Strategy
	 */
	@FXML private void selectLCS() {
		strategy.setText("LCS Strategy");
		algo = Algorithm.LCS;
	}
	
	/**
	 * Event that occurs when user selects Levenshtein Distance Strategy
	 */
	@FXML private void selectNW() {
		strategy.setText("Levenshtein Strategy");
		algo = Algorithm.NW;
	}
	
	/**
	 * Event that occurs when user selects Weighted Average Strategy
	 */
	@FXML private void selectWA() {
		strategy.setText("Weighted Average");
		algo = Algorithm.DEFAULT;
	}
	
	/**
	 * This is the internal representation of a tree item on the TreeView
	 * 
	 * @author Samanjate Sood
	 */
	private class DirectoryView {

		/**
		 * Constructor to create a new DirectoryView
		 * @param file a file in the DirectoryView
		 */
		public DirectoryView(File file) {
			this.file = file;
		}

		/**
		 *
		 * @return this object's file
		 */
		public File getFile() {
			return file;
		}

		/**
		 *
		 * @return the name of the file in this object
		 */
		@Override
		public String toString() {
			return file.getName();
		}
		 
		private File file;
	}
}
