package algorithms;

public class ContextFactory {

	public AlgorithmContext getLCSContext(){
		return new AlgorithmContext(new LCSAlgorithm());
	}
	
	public AlgorithmContext getNWContext(){
		return new AlgorithmContext(new NeemanWalshAlgorithm());
	}
}
