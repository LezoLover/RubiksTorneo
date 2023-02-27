package rbk.rbktimes.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import rbk.rbktimes.RubikApplication;

import java.io.IOException;
import java.util.Objects;

public class AdministradorController {
    @FXML
    private Label lblTitle;

    @FXML
    private void adminCategorias() {

    }

    @FXML
    private void adminConcursantes() {
        openWindow("concursantes-view.fxml", "Concursantes", false);
        closeMe();
    }

    @FXML
    private void crearSingle() {
        openWindow("nuevostiempos-view.fxml", "Single", false);
        closeMe();
    }

    @FXML
    private void modoTorneo() {
        openWindow("torneo-view.fxml", "Torneo", true);
        closeMe();
    }

    @FXML
    private void salirMenu() {
        openWindow("main-view.fxml", "Rubiks Torneo", false);
        closeMe();
    }

    private void openWindow(String url, String title, boolean resizable) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(RubikApplication.class.getResource(url)));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(resizable);
            stage.setScene(scene);

            if (url.equals("torneo-view.fxml"))
                stage.setMaximized(true);

            stage.show();
        }
        catch (IOException e) {
            showAlert();
        }
    }

    private void closeMe() {
        Stage stage = (Stage) this.lblTitle.getScene().getWindow();
        stage.close();
    }

    private void showAlert() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("IOException");
        alerta.setHeaderText("Código: E001");
        alerta.setContentText("Comuníquese con el administrador");
        alerta.show();
    }
}
