package algorithms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import utils.FileUtils;

/**
 * This is the main test suite where in you should call all the tests
 * @author Vaibhav Dave
 * @since 3/23/2018
 */

@RunWith(Suite.class)
@SuiteClasses({ LCSUnitTest.class, NeemanWalshAlgorithmTest.class, NodeTests.class, FileUtils.class })
public class LCSAlgorithmTest {

}
