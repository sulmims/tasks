package service.neighbour;

public class NeighbourMappings {
    private final static Neighbour[] NEIGHBOUR_MAPPINGS = {
            new Neighbour(-1,-1),
            new Neighbour(-1,0),
            new Neighbour(-1,1),
            new Neighbour(0,1),
            new Neighbour(1,1),
            new Neighbour(1,0),
            new Neighbour(1,-1),
            new Neighbour(0,-1),
    };

    public static int getDy(int neighbourNumber){
        return NEIGHBOUR_MAPPINGS[neighbourNumber].getDeviationX();
    }

    public static int getDx(int neighbourNumber){
        return NEIGHBOUR_MAPPINGS[neighbourNumber].getDeviationY();
    }

    public static int getNeighbourSize(){
        return NEIGHBOUR_MAPPINGS.length;
    }
}