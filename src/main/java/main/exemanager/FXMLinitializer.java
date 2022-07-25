package main.exemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLinitializer {
    Parent root;
    Stage stage;
    Scene scene;
    public void initalize(ActionEvent event,String filename) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(filename));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            displayAlert(e);
        }
    }
    public FXMLLoader initalize_2(ActionEvent event, String filename) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(filename));
            Parent root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return loader;
        }
        catch(IOException e) {
            displayAlert(e);
        }
        return null;
    }
    public FXMLLoader initalize(String filename) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("specifiedExe.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            stage1.show();
            return fxmlLoader;
        }
        catch(IOException e) {
            displayAlert(e);
        }
        return null;
    }

    private void displayAlert(IOException e) {
        e.printStackTrace();
        Alerts alerts = new Alerts("FileNotFound!");
        alerts.displayError();
    }
}
