package service.neighbour;

public class Neighbour {
    int deviationX;
    int deviationY;

    public int getDeviationX() {
        return deviationX;
    }

    public int getDeviationY() {
        return deviationY;
    }

    public Neighbour(int deviationX, int deviationY) {
        this.deviationX = deviationX;
        this.deviationY = deviationY;
    }
}
