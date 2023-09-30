module com.example.rpsgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.rpsgame to javafx.fxml;
    exports com.example.rpsgame;
}