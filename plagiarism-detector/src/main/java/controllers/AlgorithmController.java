package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import algorithms.IAlgorithmFactory;
import algorithms.Algorithm;
import algorithms.AlgorithmFactory;
import algorithms.Enums;
import parser.Node;
import parser.ASTNodeListener;
import parser.CBaseListener;
import parser.CLexer;
import parser.CParser;

import java.util.*;

/**
 * The main controller of the application that can tell if two files are similar
 * @author Vaibhav Dave, Shail Shah
 *
 */
public class AlgorithmController implements IAlgorithmController {

	private File fileToBeParsed;
	private File fileToBeParsed2;

	/**
	 * Sets files for comparing 
	 * @param file1 the first file
	 * @param file2 the second file
	 */
	@Override
	public void setFiles(File file1, File file2) {
		this.fileToBeParsed = file1;
		this.fileToBeParsed2 = file2;
	}

	/**
	 * Compute the similarity between the set files
	 * @return a number that denotes the degree of similarity
	 * @throws IOException 
	 */
	@Override
	public double getAns(Enums.AlgorithmType algorithmType) throws IOException {
		List<Node> nodeList1 = new LinkedList<>();
		FileInputStream fis = null;
		CLexer lexer = null;
		fis = new FileInputStream(fileToBeParsed);
		lexer = new CLexer(CharStreams.fromStream(fis));

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CParser parser = new CParser(tokens);
		ParserRuleContext tree = parser.compilationUnit();
		ParseTreeWalker walker = new ParseTreeWalker();
		CBaseListener extractor = new ASTNodeListener(nodeList1);
		walker.walk(extractor, tree);

		CLexer lexer2 = null;
		List<Node> nodeList2 = new LinkedList<>();
		FileInputStream fis2;
			try {
				fis2 = new FileInputStream(fileToBeParsed2);
				lexer2 = new CLexer(CharStreams.fromStream(fis2));
			} catch (IOException e) {
				//doNothing
			}
			
		CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
		CParser parser2 = new CParser(tokens2);
		ParserRuleContext tree2 = parser2.compilationUnit();
		ParseTreeWalker walker2 = new ParseTreeWalker();
		CBaseListener extractor2 = new ASTNodeListener(nodeList2);
		walker2.walk(extractor2, tree2);

		IAlgorithmFactory factory = new AlgorithmFactory();
		Algorithm algo = factory.getAlgorithm(algorithmType);

		return algo.computeSimilarity(nodeList1, nodeList2);
	}

	/**
	 * Driver function
	 * @param args not used currently
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		IAlgorithmController start = new AlgorithmController();
		String file1path = "sample.c";
		String file2path = "sample2.c";
		File file1 = new File(file1path);
		File file2 = new File(file2path);
		
		start.setFiles(file1, file2);
		double ans = start.getAns(Enums.AlgorithmType.LCS);
		
		
		Logger logger = Logger.getLogger("logger");
		String strAns = "" + ans;
		logger.log(Level.FINE, strAns);
		System.out.println(strAns);
	}
}
