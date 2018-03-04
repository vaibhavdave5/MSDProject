package controllers;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import algorithms.Enums;

public class AlgorithmControllerTest {
	
	Logger logger = Logger.getLogger(this.getClass().toString());
	
	// Testing the simple small files 
	@Test
	public void test(){
		controllers.AlgorithmController ac = new controllers.AlgorithmController();

		String path1 = "sample3.c";
		String path2 = "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		ac.setFiles(file1, file2);
		double ans = -1;
		try {
			ans = ac.getAns(Enums.AlgorithmType.LCS);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		assertTrue(ans >= 0 && ans <= 1);
	}

	// Testing the large files 

	@Test
	public void test2(){
		controllers.AlgorithmController ac = new controllers.AlgorithmController();

		String path1 = "sample3.c";
		String path2 = "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		ac.setFiles(file1, file2);
		double ans=-1;
		try {
			ans = ac.getAns(Enums.AlgorithmType.NW);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		assertTrue(ans >= 0 && ans <= 1);
	}

	
}
