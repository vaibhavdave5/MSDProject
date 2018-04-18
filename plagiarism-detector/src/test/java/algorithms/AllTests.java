package algorithms;

import driver.DriverTests;
import driver.StudentPairTests;
import driver.StudentTests;
import driver.SummaryTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import utils.ConfigUtilsTest;
import utils.FileUtilsTest;
import machinelearning.MachineLearningTests.java;

/**
 * Test suite containing test classes to be run
 * @author Vaibhav Dave
 */
@RunWith(Suite.class)
@SuiteClasses({
		StudentPairTests.class,
		SummaryTests.class,
		DriverTests.class,
		StudentTests.class,
		LCSUnitTest.class,
		NeemanWalshAlgorithmTest.class,
		NodeTests.class,
		FileUtilsTest.class,
		SnippetPairTest.class,
		ConfigUtilsTest.class,
		MachineLearningTests.class
})
public class AllTests {

}
