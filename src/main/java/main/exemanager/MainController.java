package main.exemanager;

import data.DataManager;
import data.JsonWrite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends FXMLinitializer implements Initializable  {
    @FXML
    Label nick;
    @FXML
    Label ExeManager;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nick.setText("");
        DataManager dataManager = DataManager.getInstance();

        dataManager.initialize();
    }
    public void setNick(String user) {
        nick.setText(user);

    }
    public void shoulder(ActionEvent event) {
        initalizeBigController(event,"SHOULDER");

    }
    public void chest(ActionEvent event) {
        initalizeBigController(event,"CHEST");
    }
    public void bictric(ActionEvent event) {
        initalizeBigController(event,"BIC&TRIC");
    }
    public void back(ActionEvent event) {
        initalizeBigController(event,"BACK");
    }
    public void abs(ActionEvent event) {
        initalizeBigController(event,"ABS");
    }
    public void legs(ActionEvent event) {
        initalizeBigController(event,"LEGS");
    }
    public void logOut(ActionEvent event) {
        JsonWrite.writeToJson();
        initalize(event, "login.fxml");
    }
    private void initalizeBigController(ActionEvent event, String exeName) {
        FXMLLoader loader = initalize_2(event,"exercisesPage.fxml");
        BigController bigController = (BigController)loader.getController();
        bigController.initialize(exeName,event);

    }

}