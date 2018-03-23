package controllers;

import algorithms.SimpleFilePair;
import algorithms.Result;
import algorithms.SimilaritySnippet;
import driver.CodeSnippets;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static utils.FileUtils.getFileString;

/**
 * Controller for the UI that shows comparision between two submissions
 * @author Shail Shah
 */
public class CompareController {
	
	// Controller injectors
	@FXML private TextFlow studentACode;
	@FXML private TextFlow studentBCode;
	@FXML private Button reportButton;
	@FXML private Label studentAName;
	@FXML private Label studentBName;
	
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
		simpleFilePairs = getSimpleFilePairs();
		Text snippetA = new Text(simpleFilePairs.get(currentSnippet).getSnippet1());
		Text snippetB = new Text(simpleFilePairs.get(currentSnippet).getSnippet2());

		studentACode.getChildren().addAll(snippetA);
		studentBCode.getChildren().addAll(snippetB);
	}

	protected void onPreviousButtonClick() {
		if(currentSnippet == 0)
			return;
		currentSnippet--;

		refreshSnippets();
	}

	protected void onNextButtonClick() {
		if(currentSnippet >= simpleFilePairs.size())
			return;

		currentSnippet++;

		refreshSnippets();
	}

	private void refreshSnippets() {
		Text snippetA = new Text(simpleFilePairs.get(currentSnippet).getSnippet1());
		Text snippetB = new Text(simpleFilePairs.get(currentSnippet).getSnippet2());

		studentACode.getChildren().addAll(snippetA);
		studentBCode.getChildren().addAll(snippetB);
	}

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
