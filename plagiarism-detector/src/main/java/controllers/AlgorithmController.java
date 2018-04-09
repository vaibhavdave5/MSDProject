package controllers;

import algorithms.Algorithm;
import algorithms.AlgorithmContext;
import algorithms.AlgorithmFactory;
import algorithms.AlgorithmStrategy;
import algorithms.IResult;

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
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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
	 * @return the array of percentages of similarity using the given strategy
	 */
	public double[] getSimilarityPercentage(AlgorithmStrategy strategy,List<Node> nodeList1,List<Node> nodeList2){
		double[] arr = new double[2];
		IResult result = getResult(strategy,nodeList1,nodeList2);
		arr[0] = result.getPercentagefile1();
		arr[1] = result.getPercentagefile2(); 
		return arr;
	}
	

	/**
	 * Gets the results from the all types of algorithm
	 * 
	 * @param nodeList1 list of nodes retrieved from file1
	 * @param nodeList2 list of nodes retrieved from file2
	 * @return Map<Algorithm,IResult> map with key as the Algorithm and value is its corresponding result
	 */
	public Map<Algorithm,IResult> getAllResults(List<Node> nodeList1 ,List<Node> nodeList2) {
		Map<Algorithm, IResult> map = new EnumMap<> (Algorithm.class);
		map.put(Algorithm.LCS, new AlgorithmContext(new AlgorithmFactory().getAlgo(Algorithm.LCS)).executeStrategy(nodeList1, nodeList2));
		map.put(Algorithm.NW, new AlgorithmContext(new AlgorithmFactory().getAlgo(Algorithm.NW)).executeStrategy(nodeList1, nodeList2));
		return map;
	}
	
}
