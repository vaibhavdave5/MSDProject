package controllers;

import algorithms.IResult;
import algorithms.SimilaritySnippet;
import algorithms.SnippetPair;
import constants.MailStrings;
import driver.Driver;
import driver.ICodeSnippets;
import driver.IDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import org.apache.log4j.Logger;
import utils.FileUtils;
import utils.MailUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static utils.FileUtils.getFileString;

/**
 * This Controller is responsible to load the Compare page of the application.
 * 
 * @author Shail Shah
 * @author Samanjate Sood
 */
public class CompareController {
	
	// Controller injectors
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
		Text snippetA = new Text(snippetPairs.get(currentSnippet).getSnippet1());
		Text snippetB = new Text(snippetPairs.get(currentSnippet).getSnippet2());
		studentACode.getChildren().clear();
		studentBCode.getChildren().clear();
		studentACode.getChildren().addAll(snippetA);
		studentBCode.getChildren().addAll(snippetB);
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
		Integer studentId1 = codeSnippets.getStudent1Id();
		Integer studentId2 = codeSnippets.getStudent2Id();
		String text1 = driver.getNameById(studentId1) + " (" + driver.getEmailById(studentId1) + ")";
		String text2 = driver.getNameById(studentId2) + " (" + driver.getEmailById(studentId2) + ")";
		studentAName.setText(text1);
		studentBName.setText(text2);
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
			double percentage1 = fp.getResult2().getPercentagefile1();
			double percentage2 = fp.getResult2().getPercentagefile2();
			Set<SimilaritySnippet> snippets = fp.getResult1().generateSnippet();
			snippets.forEach(s -> {
				int start1 = s.getStart1();
				int end1 = s.getEnd1();
				int start2 = s.getStart2();
				int end2 = s.getEnd2();
				String snippet1 = getFileString(file1, start1, end1);
				String snippet2 = getFileString(file2, start2, end2);
				snippetPairs.add(new SnippetPair(fileName1, fileName2, snippet1, snippet2, percentage1, percentage2));
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
