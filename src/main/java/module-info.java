module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens Client to javafx.fxml;
    exports Client;
}