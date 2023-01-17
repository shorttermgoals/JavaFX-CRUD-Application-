module Nebrija.Javafx1erTrimestre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Nebrija.Javafx1erTrimestre to javafx.fxml;
    exports Nebrija.Javafx1erTrimestre;
    exports Nebrija.Javafx1erTrimestre.dao;
}
