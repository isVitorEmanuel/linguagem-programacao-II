module com.lp2.sisproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jconsole;
    requires static lombok;
    requires jdk.jfr;


    opens com.lp2.sisproject to javafx.fxml;
    exports com.lp2.sisproject;
    exports com.lp2.sisproject.controller;
    exports com.lp2.sisproject.model;
    exports com.lp2.sisproject.enums;
    opens com.lp2.sisproject.controller to javafx.fxml;
}