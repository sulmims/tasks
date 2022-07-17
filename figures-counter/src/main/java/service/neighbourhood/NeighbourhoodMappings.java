package service.neighbourhood;

public class NeighbourhoodMappings{
    private static int[][] NEIGHBOURHOOD_MAPPINGS = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};

    public static int getDy(int neighbourNumber){
        return NEIGHBOURHOOD_MAPPINGS[neighbourNumber][0];
    }

    public static int getDx(int neighbourNumber){
        return NEIGHBOURHOOD_MAPPINGS[neighbourNumber][1];
    }
}