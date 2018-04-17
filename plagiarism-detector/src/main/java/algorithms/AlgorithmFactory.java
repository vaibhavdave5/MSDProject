package algorithms;

/**
 * A factory which returns the required Algorithm.
 * 
 * @author Vaibhav Dave
 *
 */
public class AlgorithmFactory {

	/**
	 * Returns the required algorithm
	 * 
	 * @param Algorithm
	 *            algo
	 * @return one of the Algorithm Strategy
	 */
	public AlgorithmStrategy getAlgo(Algorithm algo) {

		if (algo.equals(Algorithm.LCS)) {
			return new LCSAlgorithm();
		} 
		else if (algo.equals(Algorithm.NW))
			return new NeedlemanWunschAlgorithm();
		else
			return new LevenshteinDistance();
	}

}
