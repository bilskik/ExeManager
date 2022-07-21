package main.exemanager;

import data.BodyPartsData;
import data.BodyPartsInstance;
import data.DataManager;
import data.ThatExercise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SpecifyExeController extends FXMLinitializer implements Initializable, BodyPartsInstance {
    @FXML
    Label specExe;
    @FXML
    TextField series;
    @FXML
    TextField repeat;
    @FXML
    TextField weight;
    @FXML
    TextField timeBreak;
    TextField [] textValues;
    Double [] data;
    String bodyPartName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specExe.setText("");
    }
    public void setLabel(String bodyPartName, String exeName) {
        System.out.println("exeName : " + exeName + " bodyPartName " + bodyPartName);
        specExe.setText(exeName);
        textValues = setTextField();
        this.bodyPartName = bodyPartName;
        data = setDouble();
    }
    public void edit(ActionEvent event) {
       setTextFieldEditable(true);
    }
    public void submit(ActionEvent event) {
        setTextFieldEditable(false);

        int i = 0;
        for(var field : textValues) {
            try {
                if(field.getText().equals(""))
                    data[i] = 0.0;
                else
                    data[i] = Double.parseDouble(field.getText());
                i++;
            }
            catch(NumberFormatException exception) {
                Alerts alerts = new Alerts("Write a number!");
                alerts.displayError();
                setTextFieldEditable(true);
                break;
            }
        }
        ThatExercise thatExercise = getThatExeInstance();
        setThatExe(thatExercise);
        debugger();
    }
    public void quit(ActionEvent event) {
        FXMLLoader loader = initalize_2(event,"exercisesPage.fxml");
        BigController bigController = (BigController)loader.getController();
        bigController.setLabel("ThatExe");
    }
    @Override
    public BodyPartsData getBodyInstance() {
        DataManager dataManager = DataManager.getInstance();
        System.out.println("BODY PART NAME: " + bodyPartName);
        BodyPartsData bodyPartsData = dataManager.chooseBodyPart(bodyPartName);
        return bodyPartsData;
    }

    @Override
    public ThatExercise getThatExeInstance() {
        BodyPartsData bodyPartsData = getBodyInstance();
        List<ThatExercise> exeList = bodyPartsData.getList();
        for(var exe : exeList) {
            if(exe.name.equals(specExe.getText()))
                return exe;
        }
        return null;
    }
    private void setThatExe(ThatExercise thatExercise) {
        thatExercise.setSeries(data[0]);
        thatExercise.setAmount(data[1]);
        thatExercise.setWeight(data[2]);
        thatExercise.setTime(data[3]);
    }
    private TextField [] setTextField() {
        textValues = new TextField[4];
        textValues[0] = series;
        textValues[1] = repeat;
        textValues[2] = weight;
        textValues[3] = timeBreak;
        return textValues;
    }
    private Double [] setDouble() {
        data = new Double[4];
        for(var i : data)
            i = 0.0;
        return data;
    }
    private void setTextFieldEditable(Boolean b) {
        for(var field : textValues)
            field.setEditable(b);
    }
    private void debugger() {
        DataManager dataManager = DataManager.getInstance();
        BodyPartsData [] bodyPartsData = dataManager.getArrBodyPart();
        for(int i=0; i<bodyPartsData.length; i++) {
            System.out.println(bodyPartsData[i].getName());
            List<ThatExercise> list = bodyPartsData[i].getList();
            for(var iter : list) {
                System.out.println(iter.getName());
                System.out.println(iter.getAmount());
                System.out.println(iter.getSeries());
                System.out.println(iter.getWeight());
            }
        }

    }


}
