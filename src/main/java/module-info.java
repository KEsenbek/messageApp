module kg.megacom.smsmessage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kg.megacom.smsmessage to javafx.fxml;
    exports kg.megacom.smsmessage;
    exports kg.megacom.smsmessage.controller;
    exports kg.megacom.smsmessage.exception;
    exports kg.megacom.smsmessage.model;
    exports kg.megacom.smsmessage.service;
    exports kg.megacom.smsmessage.dao;
    opens kg.megacom.smsmessage.controller to javafx.fxml;
}