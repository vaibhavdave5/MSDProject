package algorithms;

/**
 * Classes implemeenting AbstractAlgorithmFactory 
 * can instantiate Algorithm instances
 * @author shail
 *
 */
@FunctionalInterface
public interface IAlgorithmFactory {
	/**
	 * Create an instance of an Algorithm
	 * @param type the type of algorithm to instantiate
	 * @return an instance of the given algorithm type
	 */
	public Algorithm getAlgorithm(Enums.AlgorithmType type);
}
