package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import algorithms.Algorithm;
import algorithms.AlgorithmFactory;
import algorithms.Enums;
import parser.Node;
import parser.ASTNodeListener;

import parser.CLexer;
import parser.CListener;
import parser.CParser;

import java.util.*;

/**
 * The main controller of the application that can tell if two files are similar
 * @author Vaibhav Dave, Shail Shah
 *
 */
public class AlgorithmController implements IAlgorithmController {

	private File file1;
	private File file2;

	/**
	 * Sets files for comparing 
	 * @param file1 the first file
	 * @param file2 the second file
	 */
	@Override
	public void setFiles(File file1, File file2) {
		this.file1 = file1;
		this.file2 = file2;
	}

	/**
	 * Compute the similarity between the set files
	 * @return a number that denotes the degree of similarity
	 * @throws IOException 
	 */
	@Override
	public double getAns(Enums.AlgorithmType algorithmType) throws IOException {
		List<Node> nodeList1 = new LinkedList<>();
		List<Node> nodeList2 = new LinkedList<>();
				
		CLexer lexer1 = new CLexer(CharStreams.fromStream(new FileInputStream(file1)));
		CLexer lexer2 = new CLexer(CharStreams.fromStream(new FileInputStream(file2)));
	
		CParser parser1 = new CParser(new CommonTokenStream(lexer1));
		CParser parser2 = new CParser(new CommonTokenStream(lexer2));
				
		new ParseTreeWalker().walk(new ASTNodeListener(nodeList1), parser1.compilationUnit());
		new ParseTreeWalker().walk(new ASTNodeListener(nodeList2), parser2.compilationUnit());

		Algorithm algo = new AlgorithmFactory().getAlgorithm(algorithmType);
		return algo.computeSimilarity(nodeList1, nodeList2);
	}
}
