package src;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
* It provides the contracts for implementing the similarity detector algorithm
* 
* @author  team-107
* @version 1.0
* @since   2018-02-10 
*/


public interface ASTCC {
	
	/**
	 * @param A list of file
	 * @return the computed hash value of a particular AST
	 */
	public Map<Integer, List<Node>> getMap(List<File> l);
	
	/**
	 * Takes in two hash maps with the hash of one AST with the value being 
	 * list of Node
	 * 
	 * @param map1
	 * @param map2
	 * 
	 * @return a number which represents the similarity between two AST representing the code.
	 */
	public Color computeSimilarity(Map<Integer, List<Node>> map1, Map<Integer, List<Node>> map2);
	
	
	 
}
