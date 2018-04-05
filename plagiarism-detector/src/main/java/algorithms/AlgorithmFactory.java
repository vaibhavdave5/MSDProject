package algorithms;

/**
 * A factory which returns the required Algorithm.
 * @author Vaibhav Dave
 *
 */
public class AlgorithmFactory {

	/**
	 * Returns the required algorithm
	 * @param Algorithm algo
	 * @return
	 */
	public AlgorithmStrategy getAlgo(Algorithm algo){
		
		if(algo.equals(Algorithm.LCS)){
			return new LCSAlgorithm();
		}
		
		return new NeedlemanWunschAlgorithm();
	}
	
}
