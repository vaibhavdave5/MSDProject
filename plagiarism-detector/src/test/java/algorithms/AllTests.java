package algorithms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import driver.DriverTests;
import driver.StudentPairTests;
import driver.StudentTests;
import driver.SummaryTests;
import utils.FileUtilsTest;

@RunWith(Suite.class)
@SuiteClasses({ StudentPairTests.class, SummaryTests.class, DriverTests.class, StudentTests.class, LCSUnitTest.class, NeemanWalshAlgorithmTest.class, NodeTests.class, FileUtilsTest.class })

public class AllTests {

}
