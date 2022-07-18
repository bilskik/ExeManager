module main.exemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires json.simple;


    opens main.exemanager to javafx.fxml;
    exports main.exemanager;
    exports data;
    opens data to javafx.fxml;
}