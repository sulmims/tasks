package service.counter;

import service.neighbourhood.NeighbourhoodMappings;
import service.neighbourhood.NeighbourhoodParams;
import service.Point;

import java.util.LinkedList;
import java.util.List;

public class FiguresCounterService {

    private static final char ONE = '1';

    public static int countFigures(FiguresMap figuresMap){
        int figuresCount = 0;
        LetterSource letterSource = new LetterSource();
        for(int y = 0; y < figuresMap.getHeight(); y++){
            for(int x = 0; x < figuresMap.getWidth(); x++) {
                if(figuresMap.getFiguresMapData()[y][x] == ONE){
                    figuresCount++;
                    processFigureFromPoint(new Point(y, x), figuresMap, letterSource.getNextLetter());
                }
            }
        }
        return figuresCount;
    }

    private static void processFigureFromPoint(Point point, FiguresMap figuresMap, char letterToMark){
        LinkedList<Point> stack = new LinkedList<>();
        stack.addFirst(point);
        while(stack.size() != 0){
            Point pointFromStack = stack.getFirst();
            figuresMap.setMapPointData(pointFromStack, letterToMark);
            stack.removeFirst();
            List<Point> neighbourhood = getNeighbourhood(pointFromStack, figuresMap.getHeight(), figuresMap.getWidth());
            for(Point nPoint : neighbourhood){
                char nPointData = figuresMap.getFiguresMapData()[nPoint.getY()][nPoint.getX()];
                if(nPointData == ONE && !isOnStack(nPoint, stack)){
                    stack.addFirst(nPoint);
                }
            }
        }
    }

    private static boolean isOnStack(Point pointToCheck, LinkedList<Point> stack){
        boolean isOnStack = false;
        for(Point point : stack){
            if(point.equals(pointToCheck)){
                isOnStack = true;
                break;
            }
        }
        return isOnStack;
    }

    private static List<Point> getNeighbourhood(Point point, int height, int width){
        List<Point> neighbourhood = new LinkedList<>();
        NeighbourhoodParams neighbourhoodParams = NeighbourhoodParams.builder()
                .decX(point.getX() > 0)
                .decY(point.getY() > 0)
                .incX(point.getX() < width - 1)
                .incY(point.getY() < height - 1)
                .build();
        for(int neighbourNumber = 0; neighbourNumber < 8; neighbourNumber++){
            addNeighbour(point, neighbourhoodParams, neighbourNumber, neighbourhood);
        }
        return neighbourhood;
    }

    private static void addNeighbour(Point point,
                                     NeighbourhoodParams neighbourhoodParams,
                                     int neighbourNumber,
                                     List<Point> neighbourhood){
        boolean isInBoundary = isInBoundary(neighbourhoodParams, neighbourNumber);
        if(isInBoundary){
            int dy = NeighbourhoodMappings.getDy(neighbourNumber);
            int dx = NeighbourhoodMappings.getDx(neighbourNumber);
            neighbourhood.add(new Point(point.getY() + dy, point.getX() + dx));
        }
    }

    private static boolean isInBoundary(NeighbourhoodParams neighbourhoodParams, int neighbourNumber){
        int dy = NeighbourhoodMappings.getDy(neighbourNumber);
        int dx = NeighbourhoodMappings.getDx(neighbourNumber);
        if(dy == -1 && dx == -1 && neighbourhoodParams.getDecX() && neighbourhoodParams.getDecY()) return true;
        if(dy == -1 && dx == 0 && neighbourhoodParams.getDecY()) return true;
        if(dy == -1 && dx == 1 && neighbourhoodParams.getIncX() && neighbourhoodParams.getDecY()) return true;
        if(dy == 0 && dx == 1 && neighbourhoodParams.getIncX()) return true;
        if(dy == 1 && dx == 1 && neighbourhoodParams.getIncX() && neighbourhoodParams.getIncY()) return true;
        if(dy == 1 && dx == 0 && neighbourhoodParams.getIncY()) return true;
        if(dy == 1 && dx == -1 && neighbourhoodParams.getIncY() && neighbourhoodParams.getDecX()) return true;
        if(dy == 0 && dx == -1 && neighbourhoodParams.getDecX()) return true;
        return false;
    }

}
