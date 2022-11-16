module com.example.tp03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.tp03 to javafx.fxml;
    exports com.example.tp03;
}