package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.log4j.Logger;

import algorithms.AlgorithmContext;
import algorithms.AlgorithmStrategy;
import parser.CASTNodeListener;
import parser.CLexer;
import parser.CParser;
import parser.Node;
import algorithms.IResult;

/**
 * The main controller of the application that can tell if two files are similar
 * 
 * @author Vaibhav Dave
 * 
 */
public class AlgorithmController {

	private File file1 = null;
	private File file2 = null;
	private static Logger logger = Logger.getLogger(AlgorithmController.class);
	
	public AlgorithmController(File f1, File f2) {
		file1 = f1;
		file2 = f2; 
	}

	/**
	 * Gets the results from the type of algorithm passed in the strategy
	 * @param strategy
	 * @return Result
	 */
	public IResult getResult(AlgorithmStrategy strategy) {
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
	 * @param Strategy strategy
	 * @return double : Percentage
	 */
	public double getAns(AlgorithmStrategy strategy){
		return getResult(strategy).getPercentage();
	}
	
}
