package rbk.rbktimes.classes;

import java.util.List;

public class AVG {
    private List<Solve> solves;
    private  float avg;

    public AVG(List<Solve> solves, float avg) {
        this.solves = solves;
        this.avg = avg;
    }

    // Setters
    public void setSolves(List<Solve> solves) {
        this.solves = solves;
    }
    public void setAvg(float avg) {
        this.avg = avg;
    }

    // Getters
    public List<Solve> getSolves() {
        return solves;
    }
    public float getAvg() {
        return avg;
    }
}
