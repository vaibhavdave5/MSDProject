package algorithms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import driver.Driver;
import driver.StudentTests;
import driver.SummaryTests;
import utils.FileUtilsTest;

@RunWith(Suite.class)
@SuiteClasses({ SummaryTests.class, Driver.class, StudentTests.class, LCSUnitTest.class, NeemanWalshAlgorithmTest.class, NodeTests.class, FileUtilsTest.class })

public class AllTests {

}
