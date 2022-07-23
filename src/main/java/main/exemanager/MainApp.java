package main.exemanager;

import data.JsonWrite;
import data.UserName;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() {
        UserName userName = UserName.getInstance();
        if(!userName.getName().equals(""))
            JsonWrite.writeToJson();
    }

    public static void main(String[] args) {
        launch();
    }
}