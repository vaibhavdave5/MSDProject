package controllers;

import algorithms.AlgorithmContext;
import algorithms.AlgorithmStrategy;
import algorithms.IResult;
import database.Connect;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.log4j.Logger;
import parser.CASTNodeListener;
import parser.CLexer;
import parser.CParser;
import parser.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main controller of the application that can tell if two files are similar
 * 
 * @author Vaibhav Dave
 * 
 */
public class AlgorithmController {
	private static Logger logger = Logger.getLogger(AlgorithmController.class);
	
	/**
	 * Constructor for making a new AlgorithmController
	 * @param f1 the first file
	 * @param f2 the secocnd file
	 */
	public AlgorithmController() {}

	/**
	 * Gets the results from the type of algorithm passed in the strategy
	 * @param strategy the method to use to run the plagiarism detector
	 * @param nodeList1 list of nodes retrieved from file1
	 * @param nodeList2 list of nodes retrieved from file2
	 * @return Result a Result containing the outcome of the algorithm
	 */
	public IResult getResult(AlgorithmStrategy strategy, List<Node> nodeList1 ,List<Node> nodeList2) {
		return new AlgorithmContext(strategy).executeStrategy(nodeList1, nodeList2);
	}

	/**
	 * Get a list of Nodes representing a C file
	 * @param file a C file
	 * @return a list of Nodes that represent the given C file
	 */
	public List<Node> getNodeList(File file) {
		List<Node> nodeList = new ArrayList<>();
		try {
			CLexer cLexer = new CLexer(CharStreams.fromStream(new FileInputStream(file)));
			CParser cParser = new CParser(new CommonTokenStream(cLexer));
			new ParseTreeWalker().walk(new CASTNodeListener(nodeList), cParser.compilationUnit());		
		} catch(IOException e) {
			logger.error(e);  
		}
		return nodeList;
	}

	/**
	 * Gets the result and returns the answer in percentage
	 * @param strategy the strategy to empty
	 * @param nodeList1 list of nodes retrieved from file1
	 * @param nodeList2 list of nodes retrieved from file2
	 * @return the percentage of similarity using the given strategy
	 */
	public double getSimilarityPercentage(AlgorithmStrategy strategy,List<Node> nodeList1,List<Node> nodeList2){
		return getResult(strategy,nodeList1,nodeList2).getPercentage();
	}
	

	
}
