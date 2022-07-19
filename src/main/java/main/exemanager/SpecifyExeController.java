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
    public void setLabel(String exeName, String bodyPartName) {
        specExe.setText(exeName);
        textValues = setTextField();
        this.bodyPartName = bodyPartName;
        data = setInteger();
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
                    continue;
                data[i++] = Double.parseDouble(field.getText());
            }
            catch(NumberFormatException exception) {
                Alerts alerts = new Alerts("Write a number!");
                alerts.displayError();
                setTextFieldEditable(true);
                break;
            }
        }
    }
    public void quit(ActionEvent event) {
        FXMLLoader loader = initalize_2(event,"exercisesPage.fxml");
        BigController bigController = (BigController)loader.getController();
        bigController.setLabel("ThatExe");
    }
    @Override
    public BodyPartsData getBodyInstance() {
        DataManager dataManager = DataManager.getInstance();
        return null;
    }

    @Override
    public ThatExercise getThatExeInstance() {
        return null;
    }

    private TextField [] setTextField() {
        textValues = new TextField[4];
        textValues[0] = series;
        textValues[1] = repeat;
        textValues[2] = weight;
        textValues[3] = timeBreak;
        return textValues;
    }
    private Double [] setInteger() {
        data = new Double[4];
        return data;
    }
    private void setTextFieldEditable(Boolean b) {
        for(var field : textValues)
            field.setEditable(b);
    }


}
