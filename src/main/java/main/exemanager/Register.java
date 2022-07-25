package main.exemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Register {
    @FXML
    TextField log;
    @FXML
    TextField pass;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void sub(ActionEvent event) {
        String login = log.getText();
        String password = pass.getText();
        if(login.equals("")) {
            Alerts alerts = new Alerts("You haven't written your login!");
            alerts.displayError();
            pass.clear();
        }
        else if(password.equals("")) {
            Alerts alerts = new Alerts("You haven't written your password");
            alerts.displayError();
            log.clear();
        }
        Reader data = new Reader();
        Map<String,String> map = data.read();
        if(checkIfCanAdd(map,login,password) && !login.equals("") && !password.equals("")) {
            reader(login, password);
            openLogin(event);
        }
        else {
            Alerts alerts = new Alerts("That account already exists!");
            alerts.displayError();
        }

    }
    public void quit(ActionEvent event) {
        openLogin(event);
    }
    private void reader(String log, String pass) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\projekty_java\\ExeManager\\src\\main\\FIle's\\Logins\\login.txt",true);
            fileWriter.write(log + ":" + pass);
            fileWriter.write("\n");
            fileWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    private Boolean checkIfCanAdd(Map<String,String> map, String login, String password) {
        if(!map.isEmpty()) {
            for (var key : map.keySet()) {
                String value = map.get(key);
                if (key.equals(login) && value.equals(password))
                    return false;
            }
        }
        return true;
    }
    private void openLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
            Alerts alerts = new Alerts("FileNotFound!");
            alerts.displayError();
        }
    }
}
