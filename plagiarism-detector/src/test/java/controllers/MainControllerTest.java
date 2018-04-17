package controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import view.Main;

public class MainControllerTest  extends FxRobot {

	@Before public void setup() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @After public void cleanup() {}

    @Test public void test_one() {
    		//verifyThat("#dirName", LabeledMatchers.hasText(""));
    		clickOn("#excel");
    }
    @Test public void test_two() {}

}