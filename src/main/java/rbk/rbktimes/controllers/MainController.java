package rbk.rbktimes.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rbk.rbktimes.RubikApplication;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MainController {
    @FXML
    private Label lblTitle;

    @FXML
    private void rankingsOpen() {
        openWindow("ranking-view.fxml", "Rankings", true);
        closeMe();
    }

    @FXML
    private void recordsOpen() {
        openWindow("record-view.fxml", "Records", true);
        closeMe();
    }

    @FXML
    private void adminOpen() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Administrador");
        dialog.setHeaderText("Por favor, introduce tu contraseña:");
        dialog.setContentText("Contraseña:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals("0000")) {
                openWindow("administrador-view.fxml", "Administrador", false);
                closeMe();
            }
        }
    }

    private void openWindow(String url, String title, boolean resizable) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(RubikApplication.class.getResource(url)));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(resizable);
            stage.setScene(scene);

            if (url.equals("ranking-view.fxml") || url.equals("record-view.fxml"))
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
