package driver;

import java.io.File;

import algorithms.Result;

public class FilePair {
	private File file1;
	private File file2;
	private Result result;
	
	public FilePair(File file1, File file2) {
		this.file1 = file1;
		this.file2 = file2;
	}
	
	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}
	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
}
