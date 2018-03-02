package algorithms;


public class AlgorithmFactory implements AbstractAlgorithmFactory {

	@Override
	public Algorithm getAlgorithm(AlgorithmTypes type) {

		if (type.equals(AlgorithmTypes.LCS)) {
			return new LCS();
		}

		return new NW();

	}

}
