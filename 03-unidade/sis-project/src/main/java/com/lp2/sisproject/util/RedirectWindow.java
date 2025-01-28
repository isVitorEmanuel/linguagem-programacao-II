package com.lp2.sisproject.util;

import com.lp2.sisproject.controller.ManufacturerInfoController;
import com.lp2.sisproject.controller.ProductsInfoController;
import com.lp2.sisproject.model.Manufacturer;
import com.lp2.sisproject.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

/**
 * Utility class for redirecting to different windows (FXML views) based on user actions.
 */
public class RedirectWindow {

    /**
     * Maps button IDs to their corresponding FXML file paths.
     *
     * @param buttonId the ID of the button
     * @return the FXML file path associated with the button ID
     */
    private static String getWindowURL(String buttonId){
    String defaultPath = "/com/lp2/sisproject/";
        Map<String, String> buttonIdToFXMLMap = Map.of(
                "btnRegisterManufacturer", defaultPath + "manufacturer-register-view.fxml",
                "btnRegisterProduct", defaultPath+"products-register-view.fxml",
                "btnProducts", defaultPath+"products-view.fxml",
                "btnManufacturers", defaultPath+"manufacturers-view.fxml",
                "btnProductInfo",defaultPath+"product-info-view.fxml",
                "btnManufacturerInfo",defaultPath+"manufacturer-info-view.fxml"
        );

        return buttonIdToFXMLMap.getOrDefault(buttonId, null);
    }

    /**
     * Redirects to a new window (FXML view) based on the provided button ID.
     *
     * @param event    the ActionEvent that triggered the window redirection
     * @param buttonId the ID of the button used for redirection
     */
    public void toWindow(ActionEvent event, String buttonId) {
        try {
            String url = getWindowURL(buttonId);

            if (url == null) { throw new Exception(); }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Redirects to a new window (FXML view) based on the provided MouseEvent and entity type.
     *
     * @param event  the MouseEvent that triggered the window redirection
     * @param entity the entity (Product or Manufacturer) used to determine the correct FXML view
     */
    public void toWindow(MouseEvent event, Object entity) {
        try{
            FXMLLoader loader;
            String url;

            if (entity instanceof Product){
                url = getWindowURL("btnProductInfo");
                loader = new FXMLLoader(getClass().getResource(url));
            } else if (entity instanceof Manufacturer) {
                url = getWindowURL("btnManufacturerInfo");
                loader = new FXMLLoader(getClass().getResource(url));
            } else {
                throw new IllegalArgumentException("Unsupported entity type.");
            }

            Parent root = loader.load();

            if (entity instanceof Product) {
                ProductsInfoController controller = loader.getController();
                controller.setProduct((Product) entity);
            } else{
                ManufacturerInfoController controller = loader.getController();
                controller.setManufacturer((Manufacturer) entity);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
