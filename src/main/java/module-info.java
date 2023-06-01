module com.emsi.Maven.JDBC {
    requires javafx.fxml;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires java.sql;
    //requires com.google.gson;
    requires gson;
    requires poi;
    requires poi.ooxml;
    opens com.emsi.Maven.JDBC to javafx.fxml;
    opens com.emsi.Maven.JDBC.entities to gson;
    exports com.emsi.Maven.JDBC;
    exports com.emsi.Maven.JDBC.entities;
    exports com.emsi.Maven.JDBC.controller;
    exports com.emsi.Maven.JDBC.service;
}
