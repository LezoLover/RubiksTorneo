package rbk.rbktimes.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import rbk.rbktimes.RubikApplication;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class RankingController implements Initializable {
    // ToggleButtons de las categorías
    @FXML
    private ToggleButton btn3x3, btn2x2, btn4x4, btn3x3OH, btnPyraminx;

    // Tabla Tiempos Single
    @FXML
    private TableView<Datos> tblSingle;
    @FXML
    private TableColumn<Datos, Integer> clmNumSingle;
    @FXML
    private TableColumn<Datos, String> clmNombreSingle;
    @FXML
    private TableColumn<Datos, Float> clmResultadoSingle;
    @FXML
    private TableColumn<Datos, String> clmPenalizacionSingle;
    @FXML
    private TableColumn<Datos, String> clmNacionalidadSingle;
    @FXML
    private TableColumn<Datos, String> clmFechaSingle;

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

    private void cargarTablaSingle(String cat) {

    }

    @FXML
    private void click3x3() {
        cargarTablaSingle("3x3");
    }

    @FXML
    private void click2x2() {
        cargarTablaSingle("2x2");
    }

    @FXML
    private void click4x4() {
        cargarTablaSingle("4x4");
    }

    @FXML
    private void click3x3OH() {
        cargarTablaSingle("3x3 OH");
    }

    @FXML
    private void clickPyraminx() {
        cargarTablaSingle("Pyraminx");
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

        cargarTablaSingle("3x3");
    }

    // Clase para llenar la tabla
    public static class Datos {
        private int conteo;
        private String nombre;
        private String tiempoReal;
        private float tiempo;
        private String nacionalidad;
        private String penalizacion;
        private String fecha;

        public Datos(int conteo, String nombre, String tiempoReal, float tiempo, String nacionalidad, String penalizacion, String fecha) {
            this.conteo = conteo;
            this.nombre = nombre;
            this.tiempoReal = tiempoReal;
            this.tiempo = tiempo;
            this.nacionalidad = nacionalidad;
            this.penalizacion = penalizacion;
            this.fecha = fecha;
        }

        // Setters
        public void setConteo(int conteo) {
            this.conteo = conteo;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setTiempoReal(String tiempoReal) {
            this.tiempoReal = tiempoReal;
        }

        public void setTiempo(float tiempo) {
            this.tiempo = tiempo;
        }

        public void setNacionalidad(String nacionalidad) {
            this.nacionalidad = nacionalidad;
        }

        public void setPenalizacion(String penalizacion) {
            this.penalizacion = penalizacion;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        // Getters
        public int getConteo() {
            return conteo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getTiempoReal() {
            return tiempoReal;
        }

        public float getTiempo() {
            return tiempo;
        }

        public String getNacionalidad() {
            return nacionalidad;
        }

        public String getPenalizacion() {
            return penalizacion;
        }

        public String getFecha() {
            return fecha;
        }
    }
}
