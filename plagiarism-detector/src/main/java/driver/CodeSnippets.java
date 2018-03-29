package driver;

import java.util.List;

/**
 *
 * This class holds the data to populate the similarity snippets of the homework
 * submissions of two students.
 * @author Darshan Panse
 *
 */
public class CodeSnippets implements ICodeSnippets {
	private Integer student1Id;
	private Integer student2Id;
	private List<IFilePair> filePairList;

	/**
	 * Constructor for making a new object of this class
	 * @param student1Id the ID of the first student
	 * @param student2Id the ID of the second student
	 */
	public CodeSnippets(Integer student1Id, Integer student2Id) {
		this.student1Id = student1Id;
		this.student2Id = student2Id;
	}

	/**
	 * @return the ID of the first student
	 */
	public Integer getStudent1Id() {
		return student1Id;
	}


	/**
	 * Set the ID of the first student
	 * @param student1Id the ID of the first student
	 */
	public void setStudent1Id(Integer student1Id) {
		this.student1Id = student1Id;
	}

	/**
	 * @return the ID of the second student
	 */
	public Integer getStudent2Id() {
		return student2Id;
	}

	/**
	 * Set the ID of the second student
	 * @param student2Id the ID of the second student
	 */
	public void setStudent2Id(Integer student2Id) {
		this.student2Id = student2Id;
	}

	/**
	 * @return a list of FilePairs that represent the similar snippets
	 */
	public List<IFilePair> getFilePairList() {
		return filePairList;
	}

	/**
	 * Set the FilePair list
	 * @param fpList a list of FilePairs
	 */
	public void setFilePairList(List<IFilePair> fpList) {
		this.filePairList = fpList;
	}
	
}
