package main.exemanager;

import data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class Login extends FXMLinitializer {
    @FXML
    private TextField login;
    @FXML
    private TextField passwd;

    public void submit(ActionEvent event) {
        String log = login.getText();
        String pass = passwd.getText();
        if(log.equals("")) {
            Alerts alerts = new Alerts("You haven't written your login!");
            alerts.displayError();
            login.clear();
        }
        else if(pass.equals("")) {
            Alerts alerts = new Alerts("You haven't written your password");
            alerts.displayError();
            passwd.clear();
        }
        if(checkifExists(log,pass)) {
            FXMLLoader loader = initalize_2(event,"main.fxml");
            UserName userName = UserName.getInstance();
            userName.setName(log);
            userName.setSurename(pass);
            JsonRead.readFromJson();
            MainController controller = (MainController)loader.getController();
            controller.setNick(userName.getName());
        }
        else {
            Alerts alert = new Alerts("No account!", "Account doesn't exists!");
            alert.displayError();
            login.clear();
            passwd.clear();
        }
    }
    public void noacc(ActionEvent event) {
        initalize(event,"register.fxml");
        Register register = new Register();
    }
    private boolean checkifExists(String log, String pass) {
        Reader reader = new Reader();
        Map<String, String> data = reader.read();
        if(!data.isEmpty()) {
            for (var key : data.keySet()) {
                String value = data.get(key);
                if (key.equals(log) && value.equals(pass))
                    return true;
            }
        }
            return false;
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
