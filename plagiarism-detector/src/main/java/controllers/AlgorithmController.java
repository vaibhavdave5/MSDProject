package controllers;

import java.io.File;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;

import parser.Node;
import parser.*;
import java.util.*;
import java.util.logging.Level;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import algorithms.AlgorithmContext;
import algorithms.AlgorithmStrategy;
import algorithms.Result;

/**
 * The main controller of the application that can tell if two files are similar
 * 
 * @author Vaibhav Dave, Shail Shah
 *
 */
public class AlgorithmController {

	private File file1 = null;
	private File file2 = null;
	private static Logger logger = Logger.getLogger(AlgorithmController.class);
	
	public AlgorithmController(File file1, File file2) {
		this.file1 = file1;
		this.file2 = file2; 
	}

	/**
	 * Gets the results from the type of algorithm passed in the strategy
	 * @param strategy
	 * @return Result
	 */
	public Result getResult(AlgorithmStrategy strategy) {
		List<Node> nodeList1 = new ArrayList<>();
		List<Node> nodeList2 = new ArrayList<>();
				
		CLexer lexer1=null;
		CLexer lexer2=null;
		try { 
			lexer1 = new CLexer(CharStreams.fromStream(new FileInputStream(file1)));
			lexer2 = new CLexer(CharStreams.fromStream(new FileInputStream(file2)));
		} catch (IOException e) {
			logger.error(e.toString());
		} 
	
		CParser parser1 = new CParser(new CommonTokenStream(lexer1));
		CParser parser2 = new CParser(new CommonTokenStream(lexer2));
				
		new ParseTreeWalker().walk(new CASTNodeListener(nodeList1), parser1.compilationUnit());
		new ParseTreeWalker().walk(new CASTNodeListener(nodeList2), parser2.compilationUnit());
		
		return new AlgorithmContext(strategy).executeStrategy(nodeList1, nodeList2);
	}

	/**
	 * Gets the result and returns the answer in percentage
	 * @param strategy
	 * @return double : Percentage
	 */
	public double getAns(AlgorithmStrategy strategy){
		return getResult(strategy).getPercentage();
	}
	
}
