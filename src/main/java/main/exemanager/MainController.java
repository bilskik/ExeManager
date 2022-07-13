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

public class MainController implements Initializable {
    @FXML
    Label nick;
    @FXML
    Label ExeManager;
    Parent root;
    Stage stage;
    Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nick.setText("");
    }
    public void setNick(String user) {
        nick.setText(user);
    }
    public void shoulder(ActionEvent event) {
        nick.setText("siema");
        System.out.println("ramie");
    }
    public void chest(ActionEvent event) {
        ;
    }
    public void bictric(ActionEvent event) {
        ;
    }
    public void back(ActionEvent event) {
        ;
    }
    public void abs(ActionEvent event) {
        ;
    }
    public void legs(ActionEvent event) {
        ;
    }
    public void logOut(ActionEvent event) {
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