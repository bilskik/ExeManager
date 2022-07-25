package main.exemanager;

import data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class BigController extends FXMLinitializer implements Initializable, BodyPartsInstance {
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
    public void initialize(String thatPartOfBody, ActionEvent event) {
        exe.setText(thatPartOfBody);
        listInitalizer();
        BodyPartsData bodyPartsData = getBodyInstance();
        List<ThatExercise> list = bodyPartsData.getList();
        for(var element : list) {
            String name = element.getName();
            displayInitializer(name);
        }
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

            label.setOnMousePressed(mouseEvent -> {
                FXMLLoader loader = initalize( "specifiedExe.fxml");
                SpecifyExeController specifyExeController = (SpecifyExeController) loader.getController();
                specifyExeController.setLabel(exe.getText(), label.getText());
            });

            vBox.getChildren().add(label);
            vBox.setSpacing(5);
            RadioButton radioButton = new RadioButton();
            radioBox.getChildren().add(radioButton);
            radioBox.setSpacing(22);
            radioList.put(keyRadio++,radioButton);
            labelMap.put(key++,label);
            addData(label);

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
            removeFromData(iter);
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
    @Override
    public BodyPartsData getBodyInstance() {
        DataManager dataManager = DataManager.getInstance();
        BodyPartsData bodyPartsData = dataManager.chooseBodyPart(exe.getText());
        return bodyPartsData;
    }
    @Override
    public ThatExercise getThatExeInstance() {
        return null;
    }
    private void displayInitializer(String text) {
        Label label = labelSettings(text);
        label.setOnMousePressed(mouseEvent -> {
            FXMLLoader fxmlLoader =  initalize("specifiedExe.fxml");
            SpecifyExeController specifyExeController = fxmlLoader.getController();
            specifyExeController.setLabel(exe.getText(),label.getText());
        });
        vBox.getChildren().add(label);
        vBox.setSpacing(5);
        RadioButton radioButton = new RadioButton();
        radioBox.getChildren().add(radioButton);
        radioBox.setSpacing(22);
        radioList.put(keyRadio++,radioButton);
        labelMap.put(key++,label);
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
    private void addData(Label label) {
        BodyPartsData bodyPartsData = getBodyInstance();
        ThatExercise thatExercise = new ThatExercise(label.getText());
        bodyPartsData.add(thatExercise);
    }
    private void removeFromData(int index) {
        String value = labelMap.get(index).getText();
        BodyPartsData bodyPartsData = getBodyInstance();
        bodyPartsData.remove(value);
    }


}
