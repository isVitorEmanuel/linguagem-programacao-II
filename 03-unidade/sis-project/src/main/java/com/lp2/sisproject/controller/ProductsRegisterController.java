package com.lp2.sisproject.controller;

import com.lp2.sisproject.dao.BancoDAO;
import com.lp2.sisproject.enums.Size;
import com.lp2.sisproject.enums.TypeProduct;
import com.lp2.sisproject.model.ClothingProduct;
import com.lp2.sisproject.model.EletronicProduct;
import com.lp2.sisproject.model.FoodstuffProduct;
import com.lp2.sisproject.model.Manufacturer;
import com.lp2.sisproject.util.CreateID;
import com.lp2.sisproject.util.NumberCheck;
import com.lp2.sisproject.util.RedirectWindow;
import com.lp2.sisproject.util.WarningsAlert;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Controller class for managing product registration.
 * Handles UI events and interactions for registering various types of products.
 */
public class ProductsRegisterController {

    /**
     * Formatter for displaying date and time.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

    /**
     * Utility for redirecting windows.
     */
    private final RedirectWindow redirectWindow = new RedirectWindow();

    /**
     * Data Access Object for interacting with the database.
     */
    BancoDAO bancoDAO = BancoDAO.getInstance();

    /**
     * Timeline for updating the date label.
     */
    private Timeline timeline;

    /**
     * The type of product currently being registered.
     */
    private TypeProduct typeProduct;

    @FXML private Label dateLabel;
    @FXML private Button btnManufacturers;
    @FXML private Button btnProducts;
    @FXML private Button btnRegisterManufacturer;
    @FXML private HBox hbManufacture;
    @FXML private HBox hbSize;
    @FXML private HBox hbSpecs;
    @FXML private HBox hbValidity;
    @FXML private ComboBox<Manufacturer> comboBoxManufac;
    @FXML private ComboBox<Size> comboBoxSize;
    @FXML private TextField inputName;
    @FXML private TextField inputQuantity;
    @FXML private TextField inputValue;
    @FXML private TextArea textAreaSpec;
    @FXML private Label sucessLabel;
    @FXML private DatePicker inputDataManufacturer;
    @FXML private DatePicker inputDataValidity;

    /**
     * Saves the product based on the selected type and input data.
     */
    @FXML
    void save() {
        if (this.typeProduct == TypeProduct.TECH_PRODUCT) {
            Manufacturer selectedManufacturer = comboBoxManufac.getValue();

            if (!this.validatedInputTech(selectedManufacturer)) return;

            EletronicProduct eletronicProduct = new EletronicProduct(inputName.getText(), CreateID.generateID(),
                    Double.parseDouble(inputValue.getText()),
                    Integer.parseInt(inputQuantity.getText()),
                    selectedManufacturer, textAreaSpec.getText());

            if (!eletronicProduct.isValid()) {
                WarningsAlert.invalidValues();
                return;
            }

            this.bancoDAO.getProdutos().add(eletronicProduct);
        }

        if (this.typeProduct == TypeProduct.CLOTHING_PRODUCT) {
            Manufacturer selectedManufacturer = comboBoxManufac.getValue();

            if (!this.validatedInputClothing(selectedManufacturer)) return;

            ClothingProduct clothingProduct = new ClothingProduct(inputName.getText(), CreateID.generateID(),
                    Double.parseDouble(inputValue.getText()),
                    Integer.parseInt(inputQuantity.getText()),
                    selectedManufacturer, comboBoxSize.getValue());

            if (!clothingProduct.isValid()) {
                WarningsAlert.invalidValues();
                return;
            }

            this.bancoDAO.getProdutos().add(clothingProduct);
        }

        if (this.typeProduct == TypeProduct.FOOD_PRODUCT) {
            Manufacturer selectedManufacturer = comboBoxManufac.getValue();

            if (!this.validatedInputFoods(selectedManufacturer)) return;

            FoodstuffProduct foodstuffProduct = new FoodstuffProduct(inputName.getText(), CreateID.generateID(),
                    Double.parseDouble(inputValue.getText()),
                    Integer.parseInt(inputQuantity.getText()),
                    selectedManufacturer,
                    inputDataValidity.getValue(),
                    inputDataManufacturer.getValue());

            if (!foodstuffProduct.isValid()) {
                WarningsAlert.invalidValues();
                return;
            }

            this.bancoDAO.getProdutos().add(foodstuffProduct);
        }

        sucessLabel.setText("Produto cadastrado com sucesso!");
        sucessLabel.setVisible(true);

        Timeline timelineSpan = new Timeline(new KeyFrame(
                Duration.seconds(3),
                _ -> sucessLabel.setVisible(false)
        ));

        timelineSpan.setCycleCount(1);
        timelineSpan.play();
    }

    /**
     * Initializes the controller.
     * Sets up the timeline for updating the date label and populates combo boxes.
     */
    public void initialize() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), _ -> {
            String dateString = LocalDateTime.now().format(formatter);
            this.dateLabel.setText(dateString);
        }));

        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();

        ArrayList<Manufacturer> manufacturers = bancoDAO.getManufacturers();
        comboBoxManufac.getItems().addAll(manufacturers);
        comboBoxSize.getItems().addAll(Size.values());
        this.disableAllSpecificFields();
    }

    /**
     * Disables all specific input fields for product types.
     */
    private void disableAllSpecificFields() {
        hbSpecs.setDisable(true);
        hbSize.setDisable(true);
        hbValidity.setDisable(true);
        hbManufacture.setDisable(true);
    }

    /**
     * Validates the input fields for electronic products.
     * @param manufacturer The selected manufacturer.
     * @return true if input is valid, false otherwise.
     */
    private boolean validatedInputTech(Manufacturer manufacturer) {
        if (manufacturer == null || this.inputName.getText().isBlank() ||
                this.inputQuantity.getText().isBlank() || this.inputValue.getText().isBlank()) {
            WarningsAlert.blankValues();
            return false;
        }

        if (this.textAreaSpec.getText().isBlank()) {
            WarningsAlert.blankValues();
            return false;
        }

        if (!NumberCheck.isDouble(inputValue.getText()) ||
                !NumberCheck.isInt(inputQuantity.getText())) {
            WarningsAlert.invalidValues();
            return false;
        }
        return true;
    }

    /**
     * Validates the input fields for clothing products.
     * @param manufacturer The selected manufacturer.
     * @return true if input is valid, false otherwise.
     */
    private boolean validatedInputClothing(Manufacturer manufacturer) {
        if (manufacturer == null || this.inputName.getText().isBlank() ||
                this.inputQuantity.getText().isBlank() || this.inputValue.getText().isBlank()) {
            WarningsAlert.blankValues();
            return false;
        }

        if (this.comboBoxSize.getValue() == null) {
            WarningsAlert.blankValues();
            return false;
        }

        if (!NumberCheck.isDouble(inputValue.getText()) ||
                !NumberCheck.isInt(inputQuantity.getText())) {
            WarningsAlert.invalidValues();
            return false;
        }

        return true;
    }

    /**
     * Validates the input fields for food products.
     * @param manufacturer The selected manufacturer.
     * @return true if input is valid, false otherwise.
     */
    private boolean validatedInputFoods(Manufacturer manufacturer) {
        if (manufacturer == null || this.inputName.getText().isBlank() ||
                this.inputQuantity.getText().isBlank() || this.inputValue.getText().isBlank()) {
            WarningsAlert.blankValues();
            return false;
        }

        if (inputDataManufacturer.getValue() == null ||
                inputDataValidity.getValue() == null) {
            WarningsAlert.blankValues();
            return false;
        }

        if (!NumberCheck.isDouble(inputValue.getText()) ||
                !NumberCheck.isInt(inputQuantity.getText())) {
            WarningsAlert.invalidValues();
            return false;
        }
        return true;
    }

    /**
     * Selects the electronics product type and enables the corresponding fields.
     */
    @FXML
    void selectElectronics() {
        disableAllSpecificFields();
        hbSpecs.setDisable(false);
        this.typeProduct = TypeProduct.TECH_PRODUCT;
    }

    /**
     * Selects the clothing product type and enables the corresponding fields.
     */
    @FXML
    void selectClothing() {
        disableAllSpecificFields();
        hbSize.setDisable(false);
        this.typeProduct = TypeProduct.CLOTHING_PRODUCT;
    }

    /**
     * Selects the food product type and enables the corresponding fields.
     */
    @FXML
    void selectFood() {
        disableAllSpecificFields();
        hbValidity.setDisable(false);
        hbManufacture.setDisable(false);
        this.typeProduct = TypeProduct.FOOD_PRODUCT;
    }

    /**
     * Navigates to the manufacturers window.
     * @param event The action event triggered by the user.
     */
    @FXML
    void toManufacturers(ActionEvent event) {
        String buttonId = btnManufacturers.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Navigates to the products window.
     * @param event The action event triggered by the user.
     */
    @FXML
    void toProducts(ActionEvent event) {
        String buttonId = btnProducts.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Navigates to the register manufacturer window.
     * @param event The action event triggered by the user.
     */
    @FXML
    void toRegisterManufacturer(ActionEvent event) {
        String buttonId = btnRegisterManufacturer.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }
}
