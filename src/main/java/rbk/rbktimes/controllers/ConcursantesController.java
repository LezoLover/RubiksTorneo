package rbk.rbktimes.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rbk.rbktimes.RubikApplication;
import rbk.rbktimes.classes.Concursante;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConcursantesController implements Initializable {
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cmbNacionalidad;
    @FXML
    private RadioButton btnMasculino, btnFemenino;
    @FXML
    private Label lblEstatus;

    @FXML
    private void agregarClick() {
        try {
            String[] parts = txtNombre.getText().split(" ");
            Concursante concursante = new Concursante(parts[1]);

            concursante.setNombre(txtNombre.getText());
            concursante.setNacionalidad(cmbNacionalidad.getValue());
            concursante.setSexo(btnMasculino.isSelected() ? "Masculino" : "Femenino");

            concursante.registrarConcursante(concursante);

            LocalTime horaActual = LocalTime.now();
            LocalTime hora = horaActual.withNano(0);
            lblEstatus.setText("Registro exitoso (" + hora + ")");

            txtNombre.setText("");
            cmbNacionalidad.setValue(null);
            btnMasculino.setSelected(true);
        } catch (NullPointerException e) {
            showAlert("NullPointerException",
                    "Código: E005",
                    "Rellene todos los campos");
        }
    }

    @FXML
    private void regresarClick() {
        openWindow("administrador-view.fxml", "Administrador", false);
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

            stage.show();
        }
        catch (IOException e) {
            showAlert("IOException",
                    "Código: E006",
                    "No se encontró el archivo");
        }
    }

    private void closeMe() {
        Stage stage = (Stage) this.lblEstatus.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String headerText, String text) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(title);
        alerta.setHeaderText(headerText);
        alerta.setContentText(text);
        alerta.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComponents();
    }

    private void initComponents() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Mexicana");

        cmbNacionalidad.setItems(items);

        // Grupo de RadioButtons
        ToggleGroup sexGroup = new ToggleGroup();
        btnMasculino.setToggleGroup(sexGroup);
        btnFemenino.setToggleGroup(sexGroup);
    }
}
