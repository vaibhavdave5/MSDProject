package machinelearning;

public class TrainData {
	private double[][] x;
	private double[] y;
	
	public TrainData(double[][] x, double[] y) {
		this.x = x;
		this.y = y;
	}
	
	public TrainData() {
		
	}

	public double[][] getX() {
		return x;
	}

	public double[] getY() {
		return y;
	}
	
}
