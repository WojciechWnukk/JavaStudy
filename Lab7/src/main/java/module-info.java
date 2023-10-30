module com.example.lab7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.lab7.controllers to javafx.fxml;
    exports com.example.lab7;

}