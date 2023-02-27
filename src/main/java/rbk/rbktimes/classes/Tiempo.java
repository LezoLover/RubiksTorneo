package rbk.rbktimes.classes;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Tiempo {
    public Tiempo() {
        // void
    }

    public void guardarTiempo(String categoria, String nombre, String realTime, float tiempo, String nacionalidad, String penalizacion, String fecha) {
        try {
            File file = new File("src/main/resources/archivos/tiempos/ranking/single-" + categoria +".txt");
            FileWriter fw = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fw);

            writer.printf("%-25s %-30s %-10f %-10s %-10s %-15s%n", nombre, realTime, tiempo, nacionalidad, penalizacion, fecha);
            writer.close();
        } catch (Exception e) {
            showAlert(e.toString(),
                    "Código: E009",
                    "Error desconocido");
        }
    }

    public void guardarAVG(String categoria, String nombre, String realAVG, float avg, String ronda, String nacionalidad, String fecha) {
        try {
            File file = new File("src/main/resources/archivos/tiempos/ranking/avg-" + categoria +".txt");
            FileWriter fw = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fw);

            writer.printf("%-25s %-25s %-10f %-80s %-10s %-15s%n", nombre, realAVG, avg, ronda, nacionalidad, fecha);
            writer.close();
        } catch (Exception e) {
            showAlert(e.toString(),
                    "Código: E012",
                    "Error desconocido");
        }
    }

    private void showAlert(String title, String headerText, String text) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(title);
        alerta.setHeaderText(headerText);
        alerta.setContentText(text);
        alerta.show();
    }
}
