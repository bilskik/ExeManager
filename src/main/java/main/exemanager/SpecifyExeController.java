package main.exemanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SpecifyExeController implements Initializable {
    @FXML
    Label specExe;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specExe.setText("");
    }
    public void setLabel(String exeName) {
        specExe.setText(exeName);
    }
}
