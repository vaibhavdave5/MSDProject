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

import algorithms.AbstractAlgorithmFactory;
import algorithms.Algorithm;
import algorithms.AlgorithmFactory;
import algorithms.AlgorithmTypes;
import parser.Node;
import parser.ASTNodeListener;
import parser.CBaseListener;
import parser.CLexer;
import parser.CParser;

import java.util.*;

public class AlgorithmController implements IAlgorithmController {

	private File fileToBeParsed;
	private File fileToBeParsed2;

	@Override
	public void setFiles(File file1, File file2) {
		this.fileToBeParsed = file1;
		this.fileToBeParsed2 = file2;
	}

	@Override
	public double getAns() {
		List<Node> nodeList1 = new LinkedList<>();
		FileInputStream fis = null;
		CLexer lexer = null;
		try {
			fis = new FileInputStream(fileToBeParsed);
			lexer = new CLexer(CharStreams.fromStream(fis));
		} catch (IOException e) {
			//doNothing
		}

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

		AbstractAlgorithmFactory factory = new AlgorithmFactory();
		Algorithm algo = factory.getAlgorithm(AlgorithmTypes.NW);

		return algo.computeSimilarity(nodeList1, nodeList2);
	}

	public static void main(String[] args) {
		IAlgorithmController start = new AlgorithmController();
		String file1path = "C:"+File.separator+"Javalib"+File.separator+"Test"+File.separator+"sample.c";
		String file2path = "C:"+File.separator+"Javalib"+File.separator+"Test"+File.separator+"sample2.c";
		File file1 = new File(file1path);
		File file2 = new File(file2path);
		
		start.setFiles(file1, file2);
		Logger logger = Logger.getLogger("logger");
		logger.log(Level.FINE, start.getAns()+"");

	}
}
