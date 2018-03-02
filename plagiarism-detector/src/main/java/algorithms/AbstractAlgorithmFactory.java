package algorithms;


public interface AbstractAlgorithmFactory {
	public Algorithm getAlgorithm(AlgorithmTypes type);
}
