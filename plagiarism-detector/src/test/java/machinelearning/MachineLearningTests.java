package machinelearning;

import static org.junit.Assert.*;
import machinelearning.WeightLearner;

import org.junit.Test;

/**
 * This class tests the WeightLearner classes method. 
 * @author darshan.panse
 *
 */
public class MachineLearningTests {

	@Test
	public void testWeightLearner() {
		WeightLearner wl = new WeightLearner();
		double[] actual = wl.learn();
		double[] expected = new double[] {0.1755, 0.6079, 0.0594};
		assertArrayEquals(expected, actual, 0.0001);
		
	}

}
