package controllers;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class AlgorithmControllerTest {

	
	// Testing the simple small files 
	@Test
	public void test(){
		controllers.AlgorithmController ac = new controllers.AlgorithmController();

		String path1 = "sample.c";
		String path2 = "sample2.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		ac.setFiles(file1, file2);
		double ans = ac.getAns();
		assertTrue(ans >= 0 && ans <= 1);
	}

	// 
	@Test
	public void test2(){
		controllers.AlgorithmController ac = new controllers.AlgorithmController();

		String path1 = "sample3.c";
		String path2 = "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		ac.setFiles(file1, file2);
		double ans = ac.getAns();
		System.out.println(ans);
		
		assertTrue(ans >= 0 && ans <= 1);
	}

	
}
