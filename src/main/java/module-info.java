module main.exemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.exemanager to javafx.fxml;
    exports main.exemanager;
}