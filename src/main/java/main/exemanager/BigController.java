package main.exemanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class BigController extends FXMLinitializer implements Initializable {
    @FXML
    Label exe;
    @FXML
    VBox vBox;
    @FXML
    TextField textField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exe.setText("");
    }
    public void setLabel(String thatExercise) {
        exe.setText(thatExercise);
    }
    public void addElements(ActionEvent event) {
        String text  = textField.getText();
        if(text.equals("")) {
            Alerts alerts = new Alerts("You have to specify the name of the exercise!");
            alerts.displayError();
        } else {
            Label label = labelSettings(text);
            label.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    FXMLLoader loader = initalize_2(event,"specifiedExe.fxml");
                    SpecifyExeController specifyExeController = (SpecifyExeController) loader.getController();
                    specifyExeController.setLabel(vBox.getChildren().toString());
                }
            });
            vBox.getChildren().add(label);
            vBox.setSpacing(5);
        }
    }
    public void quit(ActionEvent event) {
        FXMLLoader loader = initalize_2(event,"main.fxml");
        UserName userName = UserName.getInstance();
        MainController controller = (MainController)loader.getController();
        controller.setNick(userName.getName());

    }
    public void removeElements(ActionEvent event) {
        vBox.getChildren().remove(0,1);
    }
    private Label labelSettings(String text) {
        Label label = new Label(text);
        label.setPrefHeight(35);
        label.setPrefWidth(345);
        label.setFont(new Font("System",20));
        label.setAlignment(Pos.CENTER_LEFT);
        label.setBackground(Background.fill(Color.web("0xF0F4C3")));
        Border b = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        label.setBorder(b);
        textField.clear();
        return label;
    }


}
