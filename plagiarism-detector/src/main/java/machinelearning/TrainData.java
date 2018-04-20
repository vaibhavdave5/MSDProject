package machinelearning;

/**
 * This class has the getters for the train data
 * i.e. the features and labels.
 * @author darsh
 *
 */
public class TrainData {
	private double[][] x;
	private double[] y;
	
	public TrainData(double[][] x, double[] y) {
		this.x = x;
		this.y = y;
	}
	
	public TrainData() {}

	/**
	 * This method returns the features.
	 * @return x double[][]
	 */
	public double[][] getX() {
		return x;
	}

	/**
	 * This method returns the labels.
	 * @return y double[]
	 */
	public double[] getY() {
		return y;
	}
	
}
