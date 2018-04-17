package controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javafx.scene.layout.VBox;

@RunWith(MockitoJUnitRunner.class)
public class LoadControllerTest {

    @Mock
    private LoadController.FileSorter fileSorter;
    @Mock
    private VBox mainVBox;
    @InjectMocks
    private LoadController loadController;

    @Test
    public void testTestOnly(){
        loadController.testOnly();
    }
}