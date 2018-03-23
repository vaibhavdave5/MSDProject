package controllers;

import algorithms.SimpleFilePair;
import algorithms.Result;
import algorithms.SimilaritySnippet;
import driver.CodeSnippets;
import driver.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static utils.FileUtils.getFileString;

/**
 * This Controller is responsible to load the Compare page of the application.
 * 
 * @author Shail Shah
 * @autho Samanjate Sood
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
	
	private CodeSnippets codeSnippets;
	private int currentSnippet = 0;
	private List<SimpleFilePair> simpleFilePairs;

	public CompareController(CodeSnippets codeSnippets) {
		this.codeSnippets = codeSnippets;
	}
	
	/**
	 * This method runs on page load and initializes all components of the Start.fxml page
	 */
	@FXML
	protected void initialize() {
		applyStyle();
		simpleFilePairs = getSimpleFilePairs();
		reveal.setDisable(false);
		initializeSnippet();
		initializeLabels();
	}
	
	/**
	 * This method initializes the snippet text
	 */
	private void initializeSnippet() {
		Text snippetA = new Text(simpleFilePairs.get(currentSnippet).getSnippet1());
		Text snippetB = new Text(simpleFilePairs.get(currentSnippet).getSnippet2());
		studentACode.getChildren().clear();
		studentBCode.getChildren().clear();
		studentACode.getChildren().addAll(snippetA);
		studentBCode.getChildren().addAll(snippetB);
	}
	
	/**
	 * This method initializes the Student names and labels the snippet
	 */
	private void initializeLabels() {
		studentAName.setText("Student-" + codeSnippets.getStudent1Id());
		studentBName.setText("Student-" + codeSnippets.getStudent2Id());
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
	}

	/**
	 * This method handles the event when the previous button is clicked.
	 */
	@FXML public void onPreviousButtonClick() {
		if(currentSnippet == 0)
			return;
		currentSnippet--;
		initializeSnippet();
	}

	/**
	 * This method handles the event when the next button is clicked.
	 */
	@FXML public void onNextButtonClick() {
		if(currentSnippet >= simpleFilePairs.size())
			return;
		currentSnippet++;
		initializeSnippet();
	}
	
	/**
	 * This function reveals the name of the students
	 */
	@FXML public void revealNames() {
		reveal.setDisable(true);
		studentAName.setText(Driver.getInstance().getNameById(codeSnippets.getStudent1Id()));
		studentBName.setText(Driver.getInstance().getNameById(codeSnippets.getStudent2Id()));
	}

	/**
	 * This method gets a list of file pairs that contains the file names
	 * and the code snippets
	 */
	private List<SimpleFilePair> getSimpleFilePairs() {
		simpleFilePairs = new ArrayList<>();
		if(codeSnippets == null) {
			return simpleFilePairs;
		}
		List<driver.FilePair> filePairs = codeSnippets.getFilePairList();
		filePairs.forEach(fp -> {
			File file1 = fp.getFile1();
			File file2 = fp.getFile2();
			String fileName1 = file1.getName();
			String fileName2 = file2.getName();
			Result result = fp.getResult();
			Set<SimilaritySnippet> snippets = result.generateSnippet();
			snippets.forEach(s -> {
				int start1 = s.getStart();
				int end1 = s.getEnd();
				int start2 = s.getStart2();
				int end2 = s.getEnd2();
				String snippet1 = getFileString(file1, start1, end1);
				String snippet2 = getFileString(file2, start2, end2);

				simpleFilePairs.add(new SimpleFilePair(fileName1, fileName2, snippet1, snippet2));
			});
		});

		return simpleFilePairs;
	}
}
