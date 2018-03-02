package algorithms;

/**
 * Classes implemeenting AbstractAlgorithmFactory 
 * can instantiate Algorithm instances
 * @author shail
 *
 */
public interface AbstractAlgorithmFactory {
	public Algorithm getAlgorithm(AlgorithmTypes type);
}
