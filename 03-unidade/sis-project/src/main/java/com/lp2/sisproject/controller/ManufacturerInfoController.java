package com.lp2.sisproject.controller;

import com.lp2.sisproject.dao.BancoDAO;
import com.lp2.sisproject.model.Manufacturer;
import com.lp2.sisproject.util.RedirectWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for managing the Manufacturer Information screen.
 * Handles operations such as displaying, editing, and deleting manufacturer details.
 */
public class ManufacturerInfoController implements Initializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
    private final RedirectWindow redirectWindow = new RedirectWindow();

    BancoDAO bancoDAO = BancoDAO.getInstance();

    private Timeline timeline;
    private Manufacturer activeManufacturer;

    @FXML Label dateLabel = new Label();

    @FXML private Button btnCancel;
    @FXML private Button btnEdit;
    @FXML private Button btnManufacturers;

    @FXML private Button btnProducts;
    @FXML private Button btnRegisterManufacturer;
    @FXML private Button btnRegisterProduct;
    @FXML private Button btnSave;

    @FXML private TextField cepText;

    @FXML private TextField cidadeText;

    @FXML private Label cep;
    @FXML private Label cnpj;
    @FXML private Label cidade;
    @FXML private Label estado;
    @FXML private Label nome;
    @FXML private Label pais;
    @FXML private Label rua;

    @FXML private TextField estadoText;
    @FXML private TextField nomeText;
    @FXML private TextField paisText;
    @FXML private TextField ruaText;

    /**
     * Redirects the user to the Manufacturers screen.
     * @param event the event triggered by the button.
     */
    @FXML
    void toManufacturers(ActionEvent event) {
        String buttonId = btnManufacturers.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Redirects the user to the Products screen.
     * @param event the event triggered by the button.
     */
    @FXML
    void toProducts(ActionEvent event) {
        String buttonId = btnProducts.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Redirects the user to the Register Manufacturer screen.
     * @param event the event triggered by the button.
     */
    @FXML
    void toRegisterManufacturer(ActionEvent event) {
        String buttonId = btnRegisterManufacturer.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Redirects the user to the Register Product screen.
     * @param event the event triggered by the button.
     */
    @FXML
    void toRegisterProduct(ActionEvent event) {
        String buttonId = btnRegisterProduct.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Sets the active manufacturer and displays its details on the screen.
     * @param manufacturer the manufacturer to display.
     */
    public void setManufacturer(Manufacturer manufacturer) {
        cep.setText(cep.getText()+manufacturer.getAddress().getCEP());
        cidade.setText(cidade.getText()+manufacturer.getAddress().getCity());
        cnpj.setText(cnpj.getText()+manufacturer.getCNPJ());
        estado.setText(estado.getText()+manufacturer.getAddress().getState());
        rua.setText(rua.getText()+manufacturer.getAddress().getStreet());
        pais.setText(pais.getText()+manufacturer.getAddress().getCountry());
        nome.setText(nome.getText()+manufacturer.getName());

        activeManufacturer= manufacturer;
    }

    /**
     * Initializes the screen, setting up the timeline for the date label and hiding edit components.
     * @param url the location used to resolve relative paths for the root object.
     * @param resourceBundle the resources used to localize the root object.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), _ -> {
            String dateString = LocalDateTime.now().format(formatter);
            this.dateLabel.setText(dateString);
        }));

        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        cepText.setVisible(false);
        cidadeText.setVisible(false);
        estadoText.setVisible(false);
        ruaText.setVisible(false);
        paisText.setVisible(false);
        nomeText.setVisible(false);

    }

    /**
     * Updates the details of the active manufacturer based on user input.
     */
    public void updateManufacturer(){
        int temp = 0;

        for (Manufacturer m: bancoDAO.getManufacturers()) {
            if (Objects.equals(m.getName(), activeManufacturer.getName())) {
                temp = bancoDAO.getManufacturers().indexOf(m);
            }
        }

        if (isNotEmpty(nomeText.getText())) {
            activeManufacturer.setName(nomeText.getText());
        }

        if (isNotEmpty(cepText.getText())) {
            activeManufacturer.getAddress().setCEP(cepText.getText());
        }

        if (isNotEmpty(cidadeText.getText())) {
            activeManufacturer.getAddress().setCity(cidadeText.getText());
        }

        if (isNotEmpty(estadoText.getText())){
            activeManufacturer.getAddress().setState(estadoText.getText());
        }

        if (isNotEmpty(ruaText.getText())){
            activeManufacturer.getAddress().setStreet(ruaText.getText());
        }

        if(isNotEmpty(paisText.getText())){
            activeManufacturer.getAddress().setCountry(paisText.getText());
        }

        bancoDAO.getManufacturers().set(temp, activeManufacturer);

        toggleEdit();
    }

    /**
     * Checks if a text string is not null or empty.
     * @param text the text to check.
     * @return true if the text is not null or empty, false otherwise.
     */
    private boolean isNotEmpty(String text) {
        return text != null && !text.isEmpty();
    }

    /**
     * Deletes the active manufacturer after user confirmation.
     * @param event the event triggered by the delete button.
     */
    public void deleteManufacturer(ActionEvent event) {
        handleDelete();
        toManufacturers(event);
    }

    /**
     * Handles the deletion confirmation for the active manufacturer.
     */
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar deleção");
        alert.setHeaderText("Deseja deletar "+activeManufacturer.getName()+" ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Deletando fabricante...");
            bancoDAO.getManufacturers().remove(activeManufacturer);
            System.out.println("Fabricante deletado com sucesso!");
        } else {
            System.out.println("Saida cancelada.");
        }
    }

    /**
     * Toggles the edit mode, allowing the user to modify the manufacturer details.
     */
    public void toggleEdit() {
        btnEdit.setVisible(!btnEdit.isVisible());
        btnSave.setVisible(!btnSave.isVisible());
        btnCancel.setVisible(!btnCancel.isVisible());

        cepText.setVisible(!cepText.isVisible());
        cidadeText.setVisible(!cidadeText.isVisible());
        estadoText.setVisible(!estadoText.isVisible());
        ruaText.setVisible(!ruaText.isVisible());
        paisText.setVisible(!paisText.isVisible());
        nomeText.setVisible(!nomeText.isVisible());

        if (nome.getText().equals("Nome: ")) {
            cep.setText(cep.getText()+activeManufacturer.getAddress().getCEP());
            cidade.setText(cidade.getText()+activeManufacturer.getAddress().getCity());
            estado.setText(estado.getText()+activeManufacturer.getAddress().getState());
            rua.setText(rua.getText()+activeManufacturer.getAddress().getStreet());
            pais.setText(pais.getText()+activeManufacturer.getAddress().getCountry());
            nome.setText(nome.getText()+activeManufacturer.getName());


        } else {
            cep.setText("CEP: ");
            cidade.setText("Cidade: ");
            estado.setText("Estado: ");
            rua.setText("Rua: ");
            pais.setText("País: ");
            nome.setText("Nome: ");

            cepText.setText(activeManufacturer.getAddress().getCEP());
            cidadeText.setText(activeManufacturer.getAddress().getCity());
            estadoText.setText(activeManufacturer.getAddress().getState());
            ruaText.setText(activeManufacturer.getAddress().getStreet());
            paisText.setText(activeManufacturer.getAddress().getCountry());
            nomeText.setText(activeManufacturer.getName());
        }
    }
}
