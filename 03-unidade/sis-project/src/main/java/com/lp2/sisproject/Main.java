package com.lp2.sisproject;

import com.lp2.sisproject.dao.BancoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static com.lp2.sisproject.handler.FileHandler.read;
import static com.lp2.sisproject.handler.FileHandler.write;


public class Main extends Application {
    BancoDAO banco = BancoDAO.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        read(banco.getProdutos(),banco.getManufacturers());

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("products-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SisCaixa");
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            event.consume();

            handleExit();
        });
        stage.show();
    }

    private void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar saída");
        alert.setHeaderText("Salvar e sair?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Salvando dados no arquivo...");
            write(banco.getProdutos(),banco.getManufacturers());
            System.exit(0);
        }else{
            System.out.println("Saida cancelada");
        }
    }

    public static void main(String[] args) {launch();}
}