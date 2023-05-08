module webweeg.start {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires jakarta.xml.bind;
    requires org.junit.jupiter.api;
    requires java.desktop;

    opens webweeg.start to javafx.fxml;
    exports webweeg.start;
    exports webweeg.start.controller;
    opens webweeg.start.controller to javafx.fxml;
    opens webweeg.start.model to javafx.fxml;
    exports webweeg.start.model;
}