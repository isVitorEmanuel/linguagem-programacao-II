package com.lp2.sisproject.controller;

import com.lp2.sisproject.dao.BancoDAO;
import com.lp2.sisproject.model.Address;
import com.lp2.sisproject.model.Manufacturer;
import com.lp2.sisproject.util.RedirectWindow;
import com.lp2.sisproject.util.WarningsAlert;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller for the manufacturer registration window.
 * Handles user interactions, manages input validation,
 * and integrates with the DAO layer to save new manufacturers.
 */
public class ManufacturerRegisterController {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
    private final RedirectWindow redirectWindow = new RedirectWindow();

    BancoDAO bancoDAO = BancoDAO.getInstance();
    private Timeline timeline;

    @FXML Label dateLabel = new Label();

    @FXML private Button btnManufacturers;
    @FXML private Button btnProducts;
    @FXML private Button btnRegisterProduct;

    @FXML private TextField fieldCEP;
    @FXML private TextField fieldCNPJ;
    @FXML private TextField fieldCity;
    @FXML private TextField fieldCountry;
    @FXML private TextField fieldName;
    @FXML private TextField fieldState;
    @FXML private TextField fieldStreet;

    @FXML private Label spanSuccess;

    /**
     * Redirects the user to the manufacturers management screen.
     * Stops the current timeline before navigation.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void toManufacturers(ActionEvent event) {
        String buttonId = btnManufacturers.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

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
     * Saves the manufacturer data provided in the input fields.
     * Validates all required fields before creating a new manufacturer.
     * Displays a success message and hides it after 3 seconds.
     */
    @FXML
    void save() {
        String name = fieldName.getText();
        String CNPJ = fieldCNPJ.getText();
        String state = fieldState.getText();
        String street = fieldStreet.getText();
        String city = fieldCity.getText();
        String country = fieldCountry.getText();
        String CEP = fieldCEP.getText();

        if (name.isBlank() || state.isBlank() ||
            street.isBlank() || city.isBlank() ||
            country.isBlank() || CEP.isBlank() || CNPJ.isBlank()) {
            WarningsAlert.blankValues();
        } else {
            Address newAddress = new Address(street, city, state, country, CEP);
            Manufacturer manufacturer = new Manufacturer(name, CNPJ, newAddress);

            spanSuccess.setText("Fabricante cadastrado com sucesso!");
            spanSuccess.setVisible(true);

            Timeline timelineSpan = new Timeline(new KeyFrame(
                    Duration.seconds(3),
                    _ -> spanSuccess.setVisible(false)
            ));

            timelineSpan.setCycleCount(1);
            timelineSpan.play();

            this.bancoDAO.getManufacturers().add(manufacturer);
        }
    }

    /**
     * Initializes the controller. Sets up a timeline to update the date and time label every millisecond.
     * Starts the timeline to display the current date and time.
     */
    public void initialize() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), _ -> {
            String dateString = LocalDateTime.now().format(formatter);
            this.dateLabel.setText(dateString);
        }));

        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }
}
