package rbk.rbktimes.classes;

import javafx.scene.control.Alert;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class Concursante implements Serializable {
    private String ID;
    private String nombre;
    private String nacionalidad;
    private String sexo;
    private List<Solve> solves;

    public Concursante() {
        // void
    }
    public Concursante(String apellido) {
        setID(apellido);
    }

    public void registrarConcursante(Concursante conc) {
        try {
            String ruta = "src/main/resources/archivos/concursantes/" + conc.getID() + ".obj";
            FileOutputStream fileOut = new FileOutputStream(ruta);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(conc);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            showAlert("IOException", "Código: E015", "No existe el archivo");
        }
    }

    public Concursante obtenerConcursante(String id) {
        try {
            String ruta = "src/main/resources/archivos/concursantes/" + id + ".obj";
            FileInputStream fileIn = new FileInputStream(ruta);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Concursante c = (Concursante) in.readObject();
            in.close();
            fileIn.close();

            return c;
        } catch (IOException e) {
            showAlert("IOException", "Código: E016", "No existe el archivo");
        } catch (ClassNotFoundException e) {
            showAlert("ClassNotFoundException", "Código: E017", "No existe la clase Concursante");
        }

        return null;
    }

    public void agregarSolve(Concursante conc, Solve s) {
        conc.getSolves().add(s);
    }

    private void showAlert(String title, String headerText, String text) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(title);
        alerta.setHeaderText(headerText);
        alerta.setContentText(text);
        alerta.show();
    }

    // Setters
    public void setID(String apellido) {
        ID = LocalDate.now().getYear() + apellido.toUpperCase();
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setSolves(List<Solve> solves) {
        this.solves = solves;
    }

    // Getters
    public String getID() {
        return ID;
    }
    public String getNombre() {
        return nombre;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public String getSexo() {
        return sexo;
    }
    public List<Solve> getSolves() {
        return solves;
    }
}
