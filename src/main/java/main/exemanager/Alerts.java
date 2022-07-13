package main.exemanager;

import javafx.scene.control.Alert;

public class Alerts {
    String description;
    String header = null;
    Alerts(String header, String description) {
        this.description = description;
        this.header = header;
    }
    Alerts(String description) {
        this.description = description;
    }
    public void displayError() {
        if(header == null)
            header = "Error occured!";
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(description);
        errorAlert.showAndWait();
    }
}
