package service.neighbour;

import service.Point;

import java.util.LinkedList;
import java.util.List;

public class NeighbourService {

    public List<Point> getNeighbourhood(Point point, int height, int width){
        List<Point> neighbourhood = new LinkedList<>();
        int neighbourSize = NeighbourMappings.getNeighbourSize();
        for(int neighbourNumber = 0; neighbourNumber < neighbourSize; neighbourNumber++){
            addNeighbour(point, height, width, neighbourNumber, neighbourhood);
        }
        return neighbourhood;
    }

    private void addNeighbour(Point point, int height, int width, int neighbourNumber, List<Point> neighbourhood){
        int dy = NeighbourMappings.getDy(neighbourNumber);
        int dx = NeighbourMappings.getDx(neighbourNumber);
        boolean isInBoundary = isInBoundary(point, height, width, dy, dx);
        if(isInBoundary){
            neighbourhood.add(new Point(point.getY() + dy, point.getX() + dx));
        }
    }

    private boolean isInBoundary(Point point, int height, int width, int dy, int dx){
        int y = point.getY() + dy;
        int x = point.getX() + dx;
        return x >= 0 && y >= 0 && x < width && y < height;
    }
}