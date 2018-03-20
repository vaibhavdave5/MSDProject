package controllers;

import static org.testfx.api.FxAssert.verifyThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.matcher.control.LabeledMatchers;

import view.Main;

public class MainControllerTest  extends FxRobot {

	@Before public void setup() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @After public void cleanup() {}

    @Test public void test_one() {
    		verifyThat("#dirName", LabeledMatchers.hasText(""));
    		clickOn("#dir");
    }
    @Test public void test_two() {}

}
