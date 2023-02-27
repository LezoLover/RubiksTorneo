package rbk.rbktimes.classes;

import java.util.Date;

public class Solve {
    private float tiempo;
    private String penalizacion;
    private String scramble;
    private Date fecha;

    public Solve(float tiempo, String penalizacion, String scramble, Date fecha) {
        this.tiempo = tiempo;
        this.penalizacion = penalizacion;
        this.scramble = scramble;
        this.fecha = fecha;
    }

    // Setters
    public void setTiempo(Float tiempo) {
        this.tiempo = tiempo;
    }
    public void setPenalizacion(String penalizacion) {
        this.penalizacion = penalizacion;
    }
    public void setScramble(String scramble) {
        this.scramble = scramble;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Getters
    public float getTiempo() {
        return tiempo;
    }
    public String getPenalizacion() {
        return penalizacion;
    }
    public String getScramble() {
        return scramble;
    }
    public Date getFecha() {
        return fecha;
    }
}
