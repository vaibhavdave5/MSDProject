package driver;

import java.util.List;

/**
 * 
 * @author darshan.panse
 * This class holds the data to populate the similarity snippets of the homework
 * submissions of two students.
 *
 */
public class CodeSnippets {
	private Integer student1Id;
	private Integer student2Id;
	private List<FilePair> filePairList;
	
	public CodeSnippets(Integer student1Id, Integer student2Id) {
		this.student1Id = student1Id;
		this.student2Id = student2Id;
	}

	public Integer getStudent1Id() {
		return student1Id;
	}

	public void setStudent1Id(Integer student1Id) {
		this.student1Id = student1Id;
	}

	public Integer getStudent2Id() {
		return student2Id;
	}

	public void setStudent2Id(Integer student2Id) {
		this.student2Id = student2Id;
	}
	
	public List<FilePair> getFilePairList() {
		return filePairList;
	}

	public void setFilePairList(List<FilePair> filePairList) {
		this.filePairList = filePairList;
	}
	
}
