package machinelearning;

import static org.junit.Assert.*;
import machinelearning.WeightLearner;

import org.junit.Test;

public class MachineLearningTests {

	@Test
	public void test() {
		WeightLearner wl = new WeightLearner();
		wl.learn();
	}

}
