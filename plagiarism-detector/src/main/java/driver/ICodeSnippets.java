package driver;

import java.util.List;

/**
 * Classes implementing this interface provide methods for the frontend
 * to get the snippet data.
 * @author darshan.panse
 *
 */
public interface ICodeSnippets {
	/**
	 * @return the ID of the first student
	 */
	Integer getStudent1Id();
	
	/**
	 * Set the ID of the first student
	 * @param student1Id the ID of the first student
	 */
	void setStudent1Id(Integer student1Id);
	
	/**
	 * @return the ID of the second student
	 */
	Integer getStudent2Id();
	
	/**
	 * Set the ID of the second student
	 * @param student2Id the ID of the second student
	 */
	void setStudent2Id(Integer student2Id);
	
	/**
	 * @return a list of FilePairs that represent the similar snippets
	 */
	List<IFilePair> getFilePairList();
	
	/**
	 * Set the FilePair list
	 * @param filePairList a list of FilePairs
	 */
	void setFilePairList(List<IFilePair> filePairList);
}
