package main.exemanager;

import data.BodyPartsData;
import data.DataManager;
import data.ThatExercise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import data.JsonRead;
import java.net.URL;
import java.util.List;
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
        JsonRead.readFromJson();
        debugger();
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
        bigController.initialize(exeName,event);
    }
    private void debugger() {
        System.out.println("Readed JsonFile");
        DataManager dataManager = DataManager.getInstance();
        BodyPartsData[] bodyPartsData = dataManager.getArrBodyPart();
        for (int i = 0; i < bodyPartsData.length; i++) {
            System.out.println(bodyPartsData[i].getName());
            List<ThatExercise> list = bodyPartsData[i].getList();
            for (var iter : list) {
                System.out.println("Nazwa Cwiczenia " + iter.getName());
                System.out.println(iter.getSeries());
                System.out.println(iter.getAmount());
                System.out.println(iter.getWeight());
                System.out.println(iter.getTime());
            }
        }
    }
}