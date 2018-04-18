package machinelearning;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * This class provides a learn function to learn the weights given a train-data.
 * @author darshan.panse
 *
 */
public class WeightLearner {
	private static Logger logger = Logger.getLogger(WeightLearner.class);
	
	/**
	 * This method learns the weights using moss results.
	 * @return double[]
	 */
	public double[] learn() {
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        URL url = this.getClass().getResource("/train-data.xlsx");
        File f = new File(url.getPath());
        TrainDataElicitor tdElicitor = new TrainDataElicitor(f);
        
        TrainData td = new TrainData();
        try {
        	td = tdElicitor.getTrainData();
		} catch (InvalidFormatException | IOException e) {
			logger.error(e.toString());
		}
        
        regression.newSampleData(td.getY(), td.getX());
        
        return regression.estimateRegressionParameters();
    }
}
