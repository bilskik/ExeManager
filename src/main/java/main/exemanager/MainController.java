package main.exemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController extends FXMLinitializer implements Initializable  {
    @FXML
    Label nick;
    @FXML
    Label ExeManager;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nick.setText("");
    }
    public void setNick(String user) {
        nick.setText(user);
    }
    public void shoulder(ActionEvent event) {
        initalizeBigController(event,"BARKI");

    }
    public void chest(ActionEvent event) {
        initalizeBigController(event,"KLATKA PIERSIOWA");
    }
    public void bictric(ActionEvent event) {
        initalizeBigController(event,"BIC&TRIC");
    }
    public void back(ActionEvent event) {
        initalizeBigController(event,"PLECY");
    }
    public void abs(ActionEvent event) {
        initalizeBigController(event,"BRZUCH");
    }
    public void legs(ActionEvent event) {
        initalizeBigController(event,"NOGI");
    }
    public void logOut(ActionEvent event) {
        initalize(event, "login.fxml");
    }
    private void initalizeBigController(ActionEvent event, String exeName) {
        FXMLLoader loader = initalize_2(event,"exercisesPage.fxml");
        BigController bigController = (BigController)loader.getController();
        bigController.setLabel(exeName);
    }


}