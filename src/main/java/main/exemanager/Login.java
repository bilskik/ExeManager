package main.exemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {
    @FXML
    private TextField login;
    @FXML
    private TextField passwd;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void submit(ActionEvent event) {
        String log = login.getText();
        String pass = passwd.getText();
        if(log == "") {
            Alerts alerts = new Alerts("You haven't written your login!");
            alerts.displayError();
            login.clear();
        }
        else if(pass == "") {
            Alerts alerts = new Alerts("You haven't written your password");
            alerts.displayError();
            passwd.clear();
        }
        if(checkifExists(log,pass)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                Parent root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                MainController controller = (MainController)loader.getController();
                controller.setNick(log);

            }
            catch(IOException e) {
                Alerts alert = new Alerts("FileNotFound!");
                alert.displayError();
                e.printStackTrace();
            }
        }
        else {
            Alerts alert = new Alerts("No account!", "Account doesn't exists!");
            alert.displayError();
            login.clear();
            passwd.clear();
        }
    }
    public void noacc(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            Alerts alert = new Alerts("FileNotFound!");
            alert.displayError();
            e.printStackTrace();
        }
        finally {
            Register register = new Register();
        }

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

}
