package src;

import java.util.List;

/**
* The object that stores information about current Node. 
* It stores the following information.
* The start index of the code. The end index of the code.
* The number of subnodes of the tree. The hashvalue of the present node.
* And the list of subnodes of the current node
* 
* 
* @author  team-107
* @version 1.0
* @since   2018-02-10 
*/

public interface Node {
    
    /**
     * Returns an Integer value which marks the starting index of the code.
     * 
     * @return the starting index
     */
    public int getStart();
    
    
    /**
     * 
     * Returns an Integer value which marks the ending index of the code.
     * 
     * @return the ending index
     */
    public int getEnd();
    
    /**
     * Sets the starting index of the code.
     * @param start value in int
     */
    
    public void setStart(int start);
    
    /**
     * Sets the ending index of the code.
     * @param end value in int
     */
    
    public void setEnd(int end);
    
    
    /**
     * Returns the number of subnodes to the present node.  
     *
     * @return the number of subnodes
     * 
     */
    public int getsize();
    
    /**
     * Sets the number of subnodes to the present node.  
     * @param size in int
     */
    public void setsize(int size);
    
    
    /**
     * Sets the computedHashValue of subnodes to the present node.  
     * @param hash value in int
     */
    public void setcomputedHash(int hash);
    
    /**
     * Returns the computedHashValue to the present node.  
     * @return the number of subnodes
     */
    public int getcomputedHash();
    
    /**
     * Returns a list of immediate child nodes to the node whose object is created.  
     * @return a List of immediate child nodes
     */
    public List<Node> getImmediateChildNodes();
    
    /**
     * Sets a list of immediate child nodes to the node whose object is created.  
     * @param List of nodes
     */
    public void setImmediateChildNodes(List<Node> nodes);
        
    
}
            