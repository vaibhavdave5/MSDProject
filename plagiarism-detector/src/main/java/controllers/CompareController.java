package controllers;

import static utils.FileUtils.getFileString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import algorithms.SimilaritySnippet;
import algorithms.SnippetPair;
import constants.MailStrings;
import driver.Driver;
import driver.ICodeSnippets;
import driver.IDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import utils.ConfigUtils;
import utils.FileUtils;
import utils.MailUtils;

/**
 * This Controller is responsible to load the Compare page of the application.
 * 
 * @author Shail Shah
 * @author Samanjate Sood
 */
public class CompareController {
	
	// These are injected when the Controller is binded with the FXML view and hence,
	// they don't need to be instantiated.
	@FXML private TextFlow studentACode;
	@FXML private TextFlow studentBCode;
	@FXML private Label studentAName;
	@FXML private Label studentBName;
	@FXML private Button reportButton;
	@FXML private Button prev;
	@FXML private Button next;
	@FXML private Button reveal;
	@FXML private Button back;
	@FXML private Button emailButton;
	@FXML private Label filePath1;
	@FXML private Label filePath2;
	
	// Configuration Variables
	private String greenStyle;
	private String redStyle;
	private String blueStyle;
	private String customLogoStyle;
	private String superflow1;
	private String superflow2;
	private String reportValidExtn;
	private String reportValidExtnDisplay;
	
	private ICodeSnippets codeSnippets;
	private int currentSnippet = 0;
	private List<SnippetPair> snippetPairs;
	private ScreenController screenController;
	 
	private static Logger logger = Logger.getLogger(CompareController.class);

	/**
	 * 
	 * @param iCodeSnippets object passed by the summary controller, used to populate
	 * this page's components
	 */
	public CompareController(ICodeSnippets iCodeSnippets) {
		initConfigVar();
		this.screenController = ScreenController.getInstance();
		this.codeSnippets = iCodeSnippets;
	}
	
	/**
	 * This is a method used to initialize the configuration variables used in 
	 * the class
	 */
	private void initConfigVar() {
		ConfigUtils configUtils = new ConfigUtils();
		greenStyle = configUtils.readConfig("GREEN");
		redStyle = configUtils.readConfig("RED");
		blueStyle = configUtils.readConfig("BLUE");
		customLogoStyle = configUtils.readConfig("CUSTOM_LOGO");
		superflow1 = configUtils.readConfig("SUPERFLOW1");
		superflow2 = configUtils.readConfig("SUPERFLOW2");
		reportValidExtn = configUtils.readConfig("REPORT_EXTN_VALID");
		reportValidExtnDisplay = configUtils.readConfig("REPORT_EXTN_VALID_DISPLAY");
	}
	
	/**
	 * This method runs on page load and initializes all components of the Compare.fxml page
	 *  ~ Initializes the snippets of the two students.
	 *  ~ Sets the label for the two students
	 *  ~ Sets the labels for file name and similarity percentage.
	 */
	@FXML
	protected void initialize() {
		applyStyle();
		snippetPairs = getSnippetPairs();
		reveal.setDisable(false);
		initializeSnippet();
		studentAName.setText("Student-" + codeSnippets.getStudent1Id());
		studentBName.setText("Student-" + codeSnippets.getStudent2Id());
		initializeFileNameLabels();
	}
	
	/**
	 * This method initializes the snippet text. It also highlights the plagiarized code,
	 * and gives the code above and below that in the students' files.
	 */
	private void initializeSnippet() {
		Text snippetAAbove = new Text(snippetPairs.get(currentSnippet).getSnippet1Above());
		Text snippetA = new Text(snippetPairs.get(currentSnippet).getSnippet1());
		Text snippetABelow = new Text(snippetPairs.get(currentSnippet).getSnippet1Below());
		Text snippetBAbove = new Text(snippetPairs.get(currentSnippet).getSnippet2Above());
		Text snippetB = new Text(snippetPairs.get(currentSnippet).getSnippet2());
		Text snippetBBelow = new Text(snippetPairs.get(currentSnippet).getSnippet2Below());
		snippetA.setFill(Color.RED);
		snippetB.setFill(Color.RED);
		studentACode.getChildren().clear();
		studentBCode.getChildren().clear();
		studentACode.getChildren().addAll(snippetAAbove, snippetA, snippetABelow);
		studentBCode.getChildren().addAll(snippetBAbove, snippetB, snippetBBelow);
	}
	
	/**
	 * This method initializes the Student names and labels the snippet.
	 * The labels initially show the student ID and the percentage match of this file's code
	 * to the other student's overall submission.
	 */
	private void initializeFileNameLabels() {
		DecimalFormat formatter = new DecimalFormat("#.##");
		String percentage1 = formatter.format(snippetPairs.get(currentSnippet).getPercentage1() * 100);
		String percentage2 = formatter.format(snippetPairs.get(currentSnippet).getPercentage2() * 100);

		filePath1.setText(snippetPairs.get(currentSnippet).getFileName1() + ": " + percentage1 + "% match");
		filePath2.setText(snippetPairs.get(currentSnippet).getFileName2() + ": " + percentage2 + "% match");
	}

	/**
	 * This method applies the CSS properties to the controls.
	 */
	private void applyStyle() {
		back.getStyleClass().add(blueStyle);
		prev.getStyleClass().add(redStyle);
		next.getStyleClass().add(redStyle);
		reportButton.getStyleClass().add(greenStyle);
		reveal.getStyleClass().add(greenStyle);
		studentAName.getStyleClass().add(customLogoStyle);
		studentBName.getStyleClass().add(customLogoStyle);
		filePath1.getStyleClass().add(customLogoStyle);
		filePath2.getStyleClass().add(customLogoStyle);
		studentACode.setId(superflow1);
		studentBCode.setId(superflow2);
		emailButton.getStyleClass().add(greenStyle);
	}

	/**
	 * This method handles the event when the previous button is clicked. It halts if the 
	 * index is at 0.
	 */
	@FXML public void onPreviousButtonClick() {
		if(currentSnippet == 0)
			return;
		currentSnippet--;
		initializeSnippet();
		initializeFileNameLabels();
	}

	/**
	 * This method handles the event when the next button is clicked. It halts if the index
	 * is maximum.
	 */
	@FXML public void onNextButtonClick() {
		if(currentSnippet >= (snippetPairs.size()-1))
			return;
		currentSnippet++;
		initializeSnippet();
		initializeFileNameLabels();
	}
	
	/**
	 * This function reveals the name of the students when the user clicks the reveal names
	 * button. It hides the button and replaces the labels with the student ID's with their names.
	 * If you hover over the name, you will be able to see the students' email id's
	 */
	@FXML public void revealNames() {
		reveal.setVisible(false);
		IDriver driver = Driver.getInstance();
		String studentName1 = driver.getNameById(codeSnippets.getStudent1Id());
		String studentName2 = driver.getNameById(codeSnippets.getStudent2Id());

		studentAName.setText(studentName1.length()  < 15 ? studentName1 : studentName1.substring(15) + ".");
		studentBName.setText(studentName2.length()  < 15 ? studentName2 : studentName2.substring(15) + ".");
		studentAName.setTooltip(new Tooltip(studentName1 + " <" + driver.getEmailById(codeSnippets.getStudent1Id()) + ">"));
		studentBName.setTooltip(new Tooltip(studentName2 + " <" + driver.getEmailById(codeSnippets.getStudent2Id()) + ">"));
	}
	
	/**
	 * This method takes the user back to the Summary screen
	 */
	@FXML public void goBack()
	{
		if(screenController != null) {
			screenController.activate("summary");
		}
	}

	/**
	 * This method gets a list of file pairs that contains the file names
	 * and the code snippets. It extracts the information for the computation task
	 * carried earlier on and stores it locally for easy access.
	 *
	 * @return a list of snippet pairs
	 */
	private List<SnippetPair> getSnippetPairs() {
		snippetPairs = new ArrayList<>();
		if(codeSnippets == null) {
			return snippetPairs;
		}
		List<driver.IFilePair> filePairs = codeSnippets.getFilePairList();
		filePairs.forEach(fp -> {
			File file1 = fp.getFile1();
			File file2 = fp.getFile2();
			String fileName1 = file1.getName();
			String fileName2 = file2.getName();
			double percentage1 = fp.getResult().getPercentagefile1();
			double percentage2 = fp.getResult().getPercentagefile2();
			Set<SimilaritySnippet> snippets = fp.getResult().generateSnippet();
			snippets.forEach(s -> {
				int start1 = s.getStart1();
				int end1 = s.getEnd1();
				int start2 = s.getStart2();
				int end2 = s.getEnd2();
				String snippet1Above = getFileString(file1, start1-10, start1-1);
				String snippet1 = getFileString(file1, start1, end1);
				String snippet1Below = getFileString(file1, end1+1, end1+10);
				String snippet2Above = getFileString(file2, start2-10, start2-1);
				String snippet2 = getFileString(file2, start2, end2);
				String snippet2Below = getFileString(file2, end2+1, end2+10);
				snippetPairs.add(new SnippetPair(fileName1, 
												fileName2, 
												snippet1Above, 
												snippet1, 
												snippet1Below, 
												snippet2Above, 
												snippet2, 
												snippet2Below, 
												percentage1, 
												percentage2));
			});
		});

		return snippetPairs;
	}
	
	/**
	 * This function is used to generate the report of the two student pair.
	 * It saves the file in txt format and the report contains information about the students,
	 * and their codes.
	 */
	@FXML public void generateReport() {
		final String report = FileUtils.getReport(codeSnippets);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter 
         	= new FileChooser.ExtensionFilter(reportValidExtnDisplay, reportValidExtn);
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);
        if(file != null){
            saveFile(report, file);
        }
    }
	
	/**
	 * This function prompts the user to save file
	 * 
	 * @param content The content the need to be saved
	 * @param file a file which needs to be saved
	 */
	private void saveFile(String content, File file) {
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        } catch (IOException e) {
        		logger.error(e); 
        } 
    }

	/**
	 * Send a mail asking to meet the professor. It has a basic template. This opens the default
	 * email app of the OS.
	 */
	public void onClickSendMail() {
		String recipient = Driver.getInstance().getEmailById(codeSnippets.getStudent1Id())
				+";"
				+ Driver.getInstance().getEmailById(codeSnippets.getStudent2Id());
		String subject = MailStrings.SUBJECT_FOR_STUDENTS;
		String body = MailStrings.BODY_FOR_STUDENTS;

		try {
			MailUtils.sendMail(recipient, subject, body);
		} catch(Exception e) {
			logger.error(e);
		}
	}
}
