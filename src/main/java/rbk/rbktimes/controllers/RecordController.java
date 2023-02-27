package rbk.rbktimes.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import rbk.rbktimes.RubikApplication;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RecordController implements Initializable {
    // ToggleButtons de las categorías
    @FXML
    private ToggleButton btn3x3, btn2x2, btn4x4, btn3x3OH, btnPyraminx;

    // Tabla Tiempos Single
    @FXML
    private TableView<?> tblSingle;
    @FXML
    private TableColumn<?, ?> clmNumSingle;
    @FXML
    private TableColumn<?, ?> clmNombreSingle;
    @FXML
    private TableColumn<?, ?> clmResultadoSingle;
    @FXML
    private TableColumn<?, ?> clmNacionalidadSingle;
    @FXML
    private TableColumn<?, ?> clmFechaSingle;

    // Tabla Tiempos AVG
    @FXML
    private TableView<?> tblAVG;
    @FXML
    private TableColumn<?, ?> clmNumAVG;
    @FXML
    private TableColumn<?, ?> clmNombreAVG;
    @FXML
    private TableColumn<?, ?> clmResultadoAVG;
    @FXML
    private TableColumn<?, ?> clmTiemposAVG;
    @FXML
    private TableColumn<?, ?> clmNacionalidadAVG;
    @FXML
    private TableColumn<?, ?> clmFechaAVG;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComponents();
    }

    @FXML
    private void regresarBtn() {
        openWindow("main-view.fxml", "Rubiks Torneo", false);
        closeMe();
    }

    // Métodos obligatorios
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
            showAlert("IOException", "Código: E001");
        }
    }

    private void closeMe() {
        Stage stage = (Stage) this.btn3x3.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String headText) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(title);
        alerta.setHeaderText(headText);
        alerta.setContentText("Comuníquese con el administrador");
        alerta.show();
    }

    private void initComponents() {
        // Añadimos los ToggleButtons a un mismo grupo
        ToggleGroup categorias = new ToggleGroup();
        btn3x3.setToggleGroup(categorias);
        btn2x2.setToggleGroup(categorias);
        btn4x4.setToggleGroup(categorias);
        btn3x3OH.setToggleGroup(categorias);
        btnPyraminx.setToggleGroup(categorias);
    }
}
