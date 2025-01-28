package com.lp2.sisproject.controller;

import com.lp2.sisproject.dao.BancoDAO;
import com.lp2.sisproject.model.Manufacturer;
import com.lp2.sisproject.util.RedirectWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controller for the manufacturers management screen.
 * Displays a list of registered manufacturers, allows navigation to other screens,
 * and updates the current date and time dynamically.
 */
public class ManufacturersController implements Initializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
    private final RedirectWindow redirectWindow = new RedirectWindow();

    private Timeline timeline;

    BancoDAO bancoDAO = BancoDAO.getInstance();
    public ObservableList<Manufacturer> manufacturers = FXCollections.observableArrayList( bancoDAO.getManufacturers());

    @FXML Label dateLabel = new Label();

    @FXML private Button btnProducts;
    @FXML private Button btnRegisterManufacturer;
    @FXML private Button btnRegisterProduct;

    @FXML private TableColumn<Manufacturer, String> manufacturerLocation;
    @FXML private TableColumn<Manufacturer, String> manufacturerCNPJ;
    @FXML private TableColumn<Manufacturer, String> manufacturerName;

    @FXML private TableView<Manufacturer> tableManufacturers;

    /**
     * Redirects the user to the products management screen.
     * Stops the current timeline before navigation.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void toProducts(ActionEvent event) {
        String buttonId = btnProducts.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Redirects the user to the manufacturer registration screen.
     * Stops the current timeline before navigation.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void toRegisterManufacturer(ActionEvent event) {
        String buttonId = btnRegisterManufacturer.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Redirects the user to the product registration screen.
     * Stops the current timeline before navigation.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void toRegisterProduct(ActionEvent event) {
        String buttonId = btnRegisterProduct.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Initializes the controller. Sets up the table view, populates it with manufacturers' data,
     * and configures the current date and time to update dynamically.
     * Also enables navigation by double-clicking a manufacturer entry.
     *
     * @param url the location of the FXML file used to set up the scene
     * @param resourceBundle the resources used for localization
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), _ -> {
            String dateString = LocalDateTime.now().format(formatter);
            this.dateLabel.setText(dateString);
        }));

        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();

        for (Manufacturer _ : bancoDAO.getManufacturers()) {
            manufacturerCNPJ.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
            manufacturerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            manufacturerLocation.setCellValueFactory(new PropertyValueFactory<>("address"));

            tableManufacturers.setItems(manufacturers);
        }

        tableManufacturers.setOnMouseClicked(event -> {
           if(event.getClickCount() == 2) {
               Manufacturer selectedManufacturer = tableManufacturers.getSelectionModel().getSelectedItem();
               if(selectedManufacturer != null) {
                   redirectWindow.toWindow(event,selectedManufacturer);
               }
           }
        });
    }
}
