package com.lp2.sisproject.controller;

import com.lp2.sisproject.dao.BancoDAO;
import com.lp2.sisproject.enums.Size;
import com.lp2.sisproject.model.ClothingProduct;
import com.lp2.sisproject.model.EletronicProduct;
import com.lp2.sisproject.model.FoodstuffProduct;
import com.lp2.sisproject.model.Product;
import com.lp2.sisproject.util.RedirectWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for managing the detailed view and operations for individual products.
 * Handles the display, editing, updating, and deletion of product details, as well as navigation
 * between different views (e.g., product list, manufacturers, etc.).
 */
public class ProductsInfoController implements Initializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");
    private final RedirectWindow redirectWindow = new RedirectWindow();

    BancoDAO bancoDAO = BancoDAO.getInstance();
    private Timeline timeline;

    private Product activeProduct;

    @FXML Label dateLabel = new Label();

    @FXML private Button btnCancel;
    @FXML private Button btnEdit;
    @FXML private Button btnProducts;
    @FXML private Button btnRegisterManufacturer;
    @FXML private Button btnManufacturers;
    @FXML private Button btnRegisterProduct;
    @FXML private Button btnSave;

    @FXML private VBox clothingProductDetails;
    @FXML private VBox details;
    @FXML private VBox eletronicProductDetails;
    @FXML private VBox foodProductDetails;
    @FXML private VBox productDetails;

    @FXML private DatePicker dataDeFabricacaoDate;
    @FXML private DatePicker dataDeValidadeDate;
    @FXML private TextField informacaoTecnicaText;
    @FXML private TextField nomeText;
    @FXML private TextField quantidadeText;
    @FXML private TextField valorText;

    @FXML private ChoiceBox<Size> tamanhoChoice;

    @FXML private Label quantidade;
    @FXML private Label tamanho;
    @FXML private Label valor;
    @FXML private Label nome;
    @FXML private Label dataDeValidade;
    @FXML private Label id;
    @FXML private Label informacaoTecnica;
    @FXML private Label dataDeFabricacao;

    /**
     * Redirects to the manufacturers view.
     * @param event ActionEvent triggered by button click.
     */
    @FXML
    void toManufacturers(ActionEvent event) {
        String buttonId = btnManufacturers.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Redirects to the products view.
     * @param event ActionEvent triggered by button click.
     */
    @FXML
    void toProducts(ActionEvent event) {
        String buttonId = btnProducts.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }
    /**
     * Redirects to the register manufacturer view.
     * @param event ActionEvent triggered by button click.
     */
    @FXML
    void toRegisterManufacturer(ActionEvent event) {
        String buttonId = btnRegisterManufacturer.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }
    /**
     * Redirects to the register product view.
     * @param event ActionEvent triggered by button click.
     */
    @FXML
    void toRegisterProduct(ActionEvent event) {
        String buttonId = btnRegisterProduct.getId();
        this.timeline.stop();
        this.redirectWindow.toWindow(event, buttonId);
    }

    /**
     * Deletes the currently active product and redirects to the products list view.
     * @param event ActionEvent triggered by button click.
     */
    public void deleteProduct(ActionEvent event) {
        handleDelete();
        toProducts(event);
    }

    /**
     * Function to call an alert of confirmation and delete the product in the bancoDAO's array.
     * Calls an Alert type object of type CONFIRMATION to prompt the user about deleting or not the product
     * if positive, the product is deleted from the database.
     * If negative, the operation is cancelled.
     * */
    private void handleDelete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar deleção");
        alert.setHeaderText("Deseja deletar "+ activeProduct.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Deletando produto...");
            bancoDAO.getProdutos().remove(activeProduct);
            System.out.println("Produto deletado com sucesso!");
        } else {
            System.out.println("Saida cancelada");
        }
    }

    /**
     * Sets the details of the given product into the corresponding UI fields.
     * @param product The product to be displayed.
     */
    public void setProduct(Product product) {
        quantidade.setText(quantidade.getText() + product.getQuantity());
        valor.setText(valor.getText() + product.getPrice());
        nome.setText(nome.getText()+product.getName());
        id.setText(id.getText()+product.getId());

        switch (product) {
            case FoodstuffProduct fp -> {
                dataDeValidade.setText(dataDeValidade.getText() + fp.getDateValidity());
                dataDeFabricacao.setText(dataDeFabricacao.getText() + fp.getManufactureDate());

                eletronicProductDetails.setVisible(false);
                clothingProductDetails.setVisible(false);

                details.getChildren().setAll(productDetails, foodProductDetails);
            }
            case EletronicProduct e -> {
                informacaoTecnica.setText(informacaoTecnica.getText() + e.getTechnicalInfo());

                foodProductDetails.setVisible(false);
                clothingProductDetails.setVisible(false);

                details.getChildren().setAll(productDetails, eletronicProductDetails);
            }
            case ClothingProduct c -> {
                tamanho.setText(tamanho.getText() + c.getSize().toString());

                foodProductDetails.setVisible(false);
                eletronicProductDetails.setVisible(false);

                details.getChildren().setAll(productDetails, clothingProductDetails);
            }
            default -> {
            }
        }
        activeProduct = product;
    }

    /**
     * Updates the details of the currently active product in the product list.
     */
    public void updateProduct() {
        int temp = 0;

        for (Product p: bancoDAO.getProdutos()) {
            if (p.getId() == activeProduct.getId()) {
                temp = bancoDAO.getProdutos().indexOf(p);
            }
        }

        if (activeProduct instanceof FoodstuffProduct fp) {
            updateFoodStuffProduct(fp);
        } else if (activeProduct instanceof EletronicProduct e) {
            updateEletronicProduct(e);
        } else if (activeProduct instanceof ClothingProduct c) {
            updateClothingProduct(c);
        }

        bancoDAO.getProdutos().set(temp,activeProduct);
        toggleEdit();
    }

    /**
     * Updates the details of a FoodstuffProduct.
     * @param fp The FoodstuffProduct to be updated.
     */
    private void updateFoodStuffProduct(FoodstuffProduct fp) {
        if (isNotEmpty(quantidadeText.getText())) {
            fp.setQuantity(Integer.parseInt(quantidadeText.getText()));
        }
        if (isNotEmpty(valorText.getText())) {
            fp.setPrice(Double.parseDouble(valorText.getText()));
        }
        if (isNotEmpty(nomeText.getText())) {
            fp.setName(nomeText.getText());
        }
        if (isNotEmpty(dataDeValidadeDate.getValue().toString())) {
            fp.setDateValidity(dataDeValidadeDate.getValue());
        }
        if (isNotEmpty(dataDeFabricacaoDate.getValue().toString())) {
           fp.setManufactureDate(dataDeFabricacaoDate.getValue());
        }
    }

    /**
     * Updates the details of an EletronicProduct.
     * @param e The EletronicProduct to be updated.
     */
    private void updateEletronicProduct(EletronicProduct e) {
        if (isNotEmpty(quantidadeText.getText())) {
            e.setQuantity(Integer.parseInt(quantidadeText.getText()));
        }
        if (isNotEmpty(valorText.getText())) {
            e.setPrice(Double.parseDouble(valorText.getText()));
        }
        if (isNotEmpty(nomeText.getText())) {
            e.setName(nomeText.getText());
        }
        if (isNotEmpty(informacaoTecnicaText.getText())) {
            e.setTechnicalInfo(informacaoTecnicaText.getText());
        }
    }

    /**
     * Updates the details of a ClothingProduct.
     * @param c The ClothingProduct to be updated.
     */
    private void updateClothingProduct(ClothingProduct c) {
        if (isNotEmpty(quantidadeText.getText())) {
            c.setQuantity(Integer.parseInt(quantidadeText.getText()));
        }
        if (isNotEmpty(valorText.getText())) {
            c.setPrice(Double.parseDouble(valorText.getText()));
        }
        if (isNotEmpty(nomeText.getText())) {
            c.setName(nomeText.getText());
        }
        if (isNotEmpty(tamanhoChoice.getValue().toString())) {
            c.setSize(tamanhoChoice.getValue());
        }
    }

    /**
     * Checks if a given text is not empty or null.
     * @param text The text to be checked.
     * @return true if the text is not empty or null, false otherwise.
     */
    private boolean isNotEmpty(String text) {
        return text != null && !text.isEmpty();
    }

    /**
     * Initializes the controller class and sets up initial configurations.
     * @param url The location used to resolve relative paths for the root object, or null if not known.
     * @param resourceBundle The resources used to localize the root object, or null if not known.
     */    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), _ -> {
            String dateString = LocalDateTime.now().format(formatter);
            this.dateLabel.setText(dateString);
        }));

        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        quantidadeText.setVisible(false);
        valorText.setVisible(false);
        nomeText.setVisible(false);
        dataDeValidadeDate.setVisible(false);
        dataDeFabricacaoDate.setVisible(false);
        informacaoTecnicaText.setVisible(false);
        tamanhoChoice.setVisible(false);
    }

    /**
     * Toggles the edit mode for product details.
     */
    public void toggleEdit(){
        btnEdit.setVisible(!btnEdit.isVisible());
        btnSave.setVisible(!btnSave.isVisible());
        btnCancel.setVisible(!btnCancel.isVisible());

        quantidadeText.setVisible(!quantidadeText.isVisible());
        valorText.setVisible(!valorText.isVisible());
        nomeText.setVisible(!nomeText.isVisible());
        dataDeValidadeDate.setVisible(!dataDeValidadeDate.isVisible());
        dataDeFabricacaoDate.setVisible(!dataDeFabricacaoDate.isVisible());
        informacaoTecnicaText.setVisible(!informacaoTecnicaText.isVisible());
        tamanhoChoice.setVisible(!tamanhoChoice.isVisible());

        if (quantidade.getText().equals("Quantidade:")) {
            quantidade.setText(quantidade.getText() + activeProduct.getQuantity());
            valor.setText(valor.getText() + activeProduct.getPrice());
            nome.setText(nome.getText()+activeProduct.getName());

            if (activeProduct instanceof FoodstuffProduct fp) {
                dataDeValidade.setText(dataDeValidade.getText() + fp.getDateValidity());
                dataDeFabricacao.setText(dataDeFabricacao.getText() + fp.getManufactureDate());
            } else if (activeProduct instanceof EletronicProduct e) {
                informacaoTecnica.setText(informacaoTecnica.getText()+e.getTechnicalInfo());
            } else if (activeProduct instanceof ClothingProduct c) {
                tamanho.setText(tamanho.getText()+c.getSize().toString());
            }
        } else {
            quantidade.setText("Quantidade:");
            valor.setText("Valor:");
            nome.setText("Nome:");

            quantidadeText.setText(Integer.toString(activeProduct.getQuantity()));
            valorText.setText(Double.toString(activeProduct.getPrice()));
            nomeText.setText(activeProduct.getName());

            if (activeProduct instanceof FoodstuffProduct fp) {
                dataDeValidade.setText("Data de Validade:");
                dataDeFabricacao.setText("Data de Fabricacao:");
                dataDeValidadeDate.setValue(fp.getDateValidity());
                dataDeFabricacaoDate.setValue(fp.getManufactureDate());
            } else if (activeProduct instanceof EletronicProduct e) {
                informacaoTecnica.setText("Informação técnica:");
                informacaoTecnicaText.setText(e.getTechnicalInfo());
            } else if (activeProduct instanceof ClothingProduct c) {
                tamanho.setText("Tamanho:");
                tamanhoChoice.setValue(c.getSize());
            }
        }
    }
}
