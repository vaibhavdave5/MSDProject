package algorithms;


/**
 * An instance of AlgorithmFactory is able to create instances of the Algorithm class
 * @author Vaibhav Dave, Shail Shah
 *
 */
public class AlgorithmFactory implements AbstractAlgorithmFactory {

	/**
	 * Creates an instance  an Algorithm of the given type
	 * @param type the type of algorithm to use
	 */
	@Override
	public Algorithm getAlgorithm(AlgorithmTypes type) {
		return type.equals(AlgorithmTypes.LCS) ? 
				new LCS() :
				new NW();
	}
}
