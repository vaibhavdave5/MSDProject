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
	
	private ICodeSnippets codeSnippets;
	private int currentSnippet = 0;
	private List<SnippetPair> snippetPairs;
	private ScreenController screenController;
	 
	private static Logger logger = Logger.getLogger(CompareController.class);

	public CompareController(ICodeSnippets iCodeSnippets) {
		this.screenController = ScreenController.getInstance();
		this.codeSnippets = iCodeSnippets;
	}
	
	/**
	 * This method runs on page load and initializes all components of the Start.fxml page
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
	 * This method initializes the snippet text
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
	 * This method initializes the Student names and labels the snippet
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
		back.getStyleClass().add("primary");
		prev.getStyleClass().add("danger");
		next.getStyleClass().add("danger");
		reportButton.getStyleClass().add("success");
		reveal.getStyleClass().add("success");
		studentAName.getStyleClass().add("logo");
		studentBName.getStyleClass().add("logo");
		filePath1.getStyleClass().add("logo");
		filePath2.getStyleClass().add("logo");
		studentACode.setId("supertextflow1");
		studentBCode.setId("supertextflow2");
		emailButton.getStyleClass().add("success");
	}

	/**
	 * This method handles the event when the previous button is clicked.
	 */
	@FXML public void onPreviousButtonClick() {
		if(currentSnippet == 0)
			return;
		currentSnippet--;
		initializeSnippet();
		initializeFileNameLabels();
	}

	/**
	 * This method handles the event when the next button is clicked.
	 */
	@FXML public void onNextButtonClick() {
		if(currentSnippet >= (snippetPairs.size()-1))
			return;
		currentSnippet++;
		initializeSnippet();
		initializeFileNameLabels();
	}
	
	/**
	 * This function reveals the name of the students
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
	 * This method takes the user back to the Start screen
	 */
	@FXML public void goBack()
	{
		if(screenController != null) {
			screenController.activate("summary");
		}
	}

	/**
	 * This method gets a list of file pairs that contains the file names
	 * and the code snippets
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
	 * This function is used to generate the report
	 */
	@FXML public void generateReport() {
		final String report = FileUtils.getReport(codeSnippets);
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter 
         	= new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
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
	 * Send a mail asking to meet the professor
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
