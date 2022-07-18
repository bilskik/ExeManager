package main.exemanager;

import data.ThatExercise;
import data.UserName;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;

public class BigController extends FXMLinitializer implements Initializable {
    @FXML
    Label exe;
    @FXML
    VBox vBox;
    @FXML
    TextField textField;
    @FXML
    VBox radioBox;
    Map<Integer,Label> labelMap;
    Map<Integer,RadioButton> radioList;
    int key = 0;
    int keyRadio = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exe.setText("");
    }
    public void setLabel(String thatPartOfBody) {
        exe.setText(thatPartOfBody);
        listInitalizer();
    }
    public void addElements(ActionEvent event) {
        String text  = textField.getText();

        if(text.equals("")) {
            Alerts alerts = new Alerts("You have to specify the name of the exercise!");
            alerts.displayError();
        }
        else if(checkIfConsistsDuplicates(text)) {
            Alerts alerts = new Alerts("You cant have two exact the same exercises!");
            alerts.displayError();
        }
        else {
            Label label = labelSettings(text);
            label.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    FXMLLoader loader = initalize_2(event, "specifiedExe.fxml");
                    SpecifyExeController specifyExeController = (SpecifyExeController) loader.getController();
                    specifyExeController.setLabel(label.getText());
                }
            });
            vBox.getChildren().add(label);
            vBox.setSpacing(5);
            RadioButton radioButton = new RadioButton();
            radioBox.getChildren().add(radioButton);
            radioBox.setSpacing(22);
            radioList.put(keyRadio++,radioButton);
            labelMap.put(key++,label);
            ThatExercise thatExercise = new ThatExercise(label.getText());

        }
    }
    public void quit(ActionEvent event) {
        FXMLLoader loader = initalize_2(event,"main.fxml");
        UserName userName = UserName.getInstance();
        MainController controller = (MainController)loader.getController();
        controller.setNick(userName.getName());

    }
    public void removeElements(ActionEvent event) {
        int iter = -1;
        Map<Integer, RadioButton> tmpRadioList = new TreeMap<>();
        Map<Integer, Label> tmpLabelMap = new TreeMap<>();
        for(var i : radioList.keySet()) {
            RadioButton r = radioList.get(i);
            if(r.isSelected()) {
                iter = i;
                break;
            }
        }
        if(iter >= 0) {
            radioList.remove(iter);
            labelMap.remove(iter);
            radioBox.getChildren().remove(iter);
            vBox.getChildren().remove(iter);
            key--;
            keyRadio--;
            for (Integer i : radioList.keySet()) {
                RadioButton radioButton = radioList.get(i);
                Label label = labelMap.get(i);
                if (i == iter)
                    continue;
                else if (i > iter) {
                    tmpRadioList.put(i - 1, radioButton);
                    tmpLabelMap.put(i - 1, label);
                } else {
                    tmpRadioList.put(i, radioButton);
                    tmpLabelMap.put(i, label);
                }
            }
            radioList = tmpRadioList;
            labelMap = tmpLabelMap;
        }
        else {
            Alerts alerts = new Alerts("You have to specify which element you want to remove");
            alerts.displayError();
        }
    }
    private void listInitalizer() {
        radioList = new TreeMap<>();
        labelMap = new TreeMap<>();
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
    private boolean checkIfConsistsDuplicates(String name) {
        for(var i : labelMap.keySet()) {
            if(labelMap.get(i).getText().equals(name))
                return true;
        }
        return false;
    }
}
