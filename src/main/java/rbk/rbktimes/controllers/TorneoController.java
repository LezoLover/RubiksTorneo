package rbk.rbktimes.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import rbk.rbktimes.RubikApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TorneoController implements Initializable {
    @FXML
    private ToggleButton btnAVG;
    @FXML
    private ComboBox<String> cmbConcursante;
    @FXML
    private ComboBox<String> cmbCategoria;
    @FXML
    private DatePicker dtpFecha;
    @FXML
    private TextField txtSolve1, txtSolve2, txtSolve3, txtSolve4, txtSolve5;
    @FXML
    private RadioButton btnSP1, btnSP2, btnSP3, btnSP4, btnSP5;
    @FXML
    private RadioButton btnPlus1, btnPlus2, btnPlus3, btnPlus4, btnPlus5;
    @FXML
    private RadioButton btnDNF1, btnDNF2, btnDNF3, btnDNF4, btnDNF5;
    @FXML
    private HBox paneSolve4, paneSolve5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComponents();
    }

    private void initComponents() {
        agruparPenalizaciones();
        llenarComboBox();

        // Desaparecemos las solves 4 y 5
        paneSolve4.setVisible(false);
        paneSolve5.setVisible(false);
    }

    @FXML
    private void registrarAVG() {
        // Guardar Tiempo nuevo
        limpiarCampos();
    }

    private float obtenerSegundos(String entrada) {
        float segundos = 0;

        if (entrada.contains(":")) {
            // Caso 1: formato mm:ss.sss
            String[] minutosSegundos = entrada.split(":");

            int minutos = Integer.parseInt(minutosSegundos[0]);
            float segundosFraccion = Float.parseFloat(minutosSegundos[1]);

            segundos = minutos * 60 + segundosFraccion;
        } else {
            // Caso 2: formato ss.sss
            segundos = Float.parseFloat(entrada);
        }

        return segundos;
    }

    @FXML
    private void salirClick() {
        openWindow("administrador-view.fxml", "Administrador", false);
        closeMe();
    }

    @FXML
    private void cambioAVG() {
        paneSolve4.setVisible(!paneSolve4.isVisible());
        paneSolve5.setVisible(!paneSolve5.isVisible());

        if (!paneSolve4.isVisible()) btnAVG.setText("AVG 3");
        else btnAVG.setText("AVG 5");
    }

    // Métodos de initComponents()
    private void agruparPenalizaciones() {
        // Grupos para las penalizaciones
        ToggleGroup penal1 = new ToggleGroup();
        btnSP1.setToggleGroup(penal1);
        btnPlus1.setToggleGroup(penal1);
        btnDNF1.setToggleGroup(penal1);

        ToggleGroup penal2 = new ToggleGroup();
        btnSP2.setToggleGroup(penal2);
        btnPlus2.setToggleGroup(penal2);
        btnDNF2.setToggleGroup(penal2);

        ToggleGroup penal3 = new ToggleGroup();
        btnSP3.setToggleGroup(penal3);
        btnPlus3.setToggleGroup(penal3);
        btnDNF3.setToggleGroup(penal3);

        ToggleGroup penal4 = new ToggleGroup();
        btnSP4.setToggleGroup(penal4);
        btnPlus4.setToggleGroup(penal4);
        btnDNF4.setToggleGroup(penal4);

        ToggleGroup penal5 = new ToggleGroup();
        btnSP5.setToggleGroup(penal5);
        btnPlus5.setToggleGroup(penal5);
        btnDNF5.setToggleGroup(penal5);
    }

    private void llenarComboBox() {
        // Listas para los ComboBox
        ObservableList<String> categorias = FXCollections.observableArrayList();
        ObservableList<String> concursantes = FXCollections.observableArrayList();

        // Leer el archivo de las categorías
        List<String> cats = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/archivos/categorias/categorias.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Procesar cada línea del archivo
                cats.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo de los concursantes
        File carpeta = new File("src/main/resources/archivos/concursantes");
        File[] archivos = carpeta.listFiles(); // Obtener la lista de archivos en la carpeta
        List<String> participantes = new ArrayList<>();

        assert archivos != null;
        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().endsWith(".txt")) { // Verificar si es un archivo de texto
                participantes.add(archivo.getName().substring(0, archivo.getName().length() - 4)); // Obtener el nombre sin la extensión);
            }
        }

        categorias.addAll(cats);
        concursantes.addAll(participantes);

        cmbConcursante.setItems(concursantes);
        cmbCategoria.setItems(categorias);
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
            showAlert();
        }
    }

    private void closeMe() {
        Stage stage = (Stage) this.btnAVG.getScene().getWindow();
        stage.close();
    }

    private void showAlert() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("IOException");
        alerta.setHeaderText("Código: E001");
        alerta.setContentText("Comuníquese con el administrador");
        alerta.show();
    }

    private void limpiarCampos() {
        cmbConcursante.setValue(null);
        cmbCategoria.setValue(null);
        dtpFecha.setValue(null);

        txtSolve1.setText("");
        txtSolve2.setText("");
        txtSolve3.setText("");
        txtSolve4.setText("");
        txtSolve5.setText("");

        btnSP1.setSelected(true);
        btnSP2.setSelected(true);
        btnSP3.setSelected(true);
        btnSP4.setSelected(true);
        btnSP5.setSelected(true);
    }
}
