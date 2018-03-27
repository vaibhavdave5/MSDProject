package driver;

import java.io.File;

import algorithms.IResult;

public class FilePair {
	private File file1;
	private File file2;
	private IResult result;
	
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
	
	public IResult getResult() {
		return result;
	}

	public void setResult(IResult result) {
		this.result = result;
	}
	
}
