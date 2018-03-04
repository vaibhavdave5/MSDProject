package algorithms;

/**
 * An instance of AlgorithmFactory is able to create instances of the Algorithm class
 * @author Vaibhav Dave, Shail Shah
 *
 */
public class AlgorithmFactory implements IAlgorithmFactory {

	/**
	 * Creates an instance  an Algorithm of the given type
	 * @param type the type of algorithm to use
	 */
	@Override
	public Algorithm getAlgorithm(Enums.AlgorithmType type) {
		return type.equals(Enums.AlgorithmType.LCS) ? 
				new LCSAlgorithm() :
				new NeemanWalshAlgorithm();
	}
}
