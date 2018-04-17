package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class LoadController implements Initializable {

    private FileSorter fileSorter = new FileSorter();

    @FXML
    private VBox mainVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    public void testOnly(){
        if(mainVBox==null){
            System.out.println("NULL VBOX");
        }else{
            System.out.println("NON-NULL VBOX");
        }
    }

    public static class FileSorter {}
}
