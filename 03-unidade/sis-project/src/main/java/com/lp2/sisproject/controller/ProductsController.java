package com.lp2.sisproject.controller;

import com.lp2.sisproject.dao.BancoDAO;
import com.lp2.sisproject.model.Product;
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
 * Controller for the product management interface.
 * This controller manages the display and interaction with the product table,
 * as well as navigation to the product and manufacturer registration screens.
 */
public class ProductsController implements Initializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

    private final RedirectWindow redirectWindow = new RedirectWindow();

    BancoDAO banco = BancoDAO.getInstance();
    private Timeline timeline;

    public ObservableList<Product> products = FXCollections.observableArrayList(banco.getProdutos());

    @FXML Label dateLabel = new Label();

    @FXML private Button btnManufacturers;
    @FXML private Button btnRegisterManufacturer;
    @FXML private Button btnRegisterProduct;

    @FXML private TableColumn<Product, Long> productId;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, Integer> productQuantity;

    @FXML private TableView<Product> TableProducts;

    /**
     * Navigates to the product registration screen.
     * @param event the button click event.
     */
    @FXML
    private void toRegisterProduct(ActionEvent event) {
        String buttonId = btnRegisterProduct.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Navigates to the manufacturer registration screen.
     * @param event the button click event.
     */
    @FXML
    private void toRegisterManufacturer(ActionEvent event) {
        String buttonId = btnRegisterManufacturer.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Navigates to the manufacturer listing screen.
     * @param event the button click event.
     */
    @FXML
    private void toManufacturers(ActionEvent event) {
        String buttonId = btnManufacturers.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Initializes the controller. Configures continuous updating of date and time,
     * sets up the product table, and defines the double-click behavior on the table.
     * @param url location of the FXML file.
     * @param resourceBundle localized resources.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), _ -> {
            String dateString = LocalDateTime.now().format(formatter);
            this.dateLabel.setText(dateString);
        }));

        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();

        for(Product _: banco.getProdutos()){
            productId.setCellValueFactory(new PropertyValueFactory<>("id"));
            productName.setCellValueFactory(new PropertyValueFactory<>("name"));
            productQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            TableProducts.setItems(products);
        }

        TableProducts.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Product selectedProduct = TableProducts.getSelectionModel().getSelectedItem();
                if(selectedProduct != null){
                  redirectWindow.toWindow(event, selectedProduct);
                }
            }
        });
    }
}
