package com.lp2.sisproject.util;

import javafx.scene.control.Alert;

/**
 * Utility class for showing alert warnings.
 */
public class WarningsAlert {
    /**
     * Displays an alert when required fields are left blank.
     */
    public static void blankValues() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alerta");
        alert.setHeaderText("Campos obrigatórios");
        alert.setContentText("Preencha todos os campos");
        alert.show();
    }

    /**
     * Displays an alert when invalid values are entered.
     */
    public static void invalidValues() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alerta");
        alert.setHeaderText("Valores inválidos");
        alert.setContentText("Os valores informados não são válidos");
        alert.show();
    }
}
