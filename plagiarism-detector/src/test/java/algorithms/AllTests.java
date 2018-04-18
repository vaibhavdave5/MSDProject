package algorithms;

import driver.DriverTests;
import driver.StudentPairTests;
import driver.StudentTests;
import driver.SummaryTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controllers.AlgorithmControllerTest;
import database.ConnectTest;
import utils.ConfigUtilsTest;
import utils.FileUtilsTest;
import utils.MailUtilsTest;
import machinelearning.MachineLearningTests;

/**
 * Test suite containing test classes to be run
 * @author Vaibhav Dave
 */
@RunWith(Suite.class)
@SuiteClasses({
		AlgorithmControllerTest.class,
		StudentPairTests.class,
		SummaryTests.class,
		DriverTests.class,
		StudentTests.class,
		LCSUnitTest.class,
		EditDistanceTest.class,
		NeemanWalshAlgorithmTest.class,
		NodeTests.class,
		FileUtilsTest.class,
		MailUtilsTest.class,
		SnippetPairTest.class,
		ConfigUtilsTest.class,
		MachineLearningTests.class,
		ConnectTest.class
})
public class AllTests {

}
