package service.counter;

import service.Point;
import service.neighbour.NeighbourService;

import java.util.LinkedList;
import java.util.List;

public class FiguresCounterService {

    private static final char ONE = '1';

    private NeighbourService neighbourService;

    private LetterSource letterSource;

    public FiguresCounterService(NeighbourService neighbourService, LetterSource letterSource) {
        this.neighbourService = neighbourService;
        this.letterSource = letterSource;
    }

    public int countFigures(FiguresMap figuresMap){
        int figuresCount = 0;
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

    private void processFigureFromPoint(Point point, FiguresMap figuresMap, char letterToMark){
        LinkedList<Point> stack = new LinkedList<>();
        stack.addFirst(point);
        while(stack.size() != 0){
            Point pointFromStack = stack.getFirst();
            figuresMap.setMapPointData(pointFromStack, letterToMark);
            stack.removeFirst();
            List<Point> neighbourhood = neighbourService.getNeighbourhood(pointFromStack, figuresMap.getHeight(), figuresMap.getWidth());
            for(Point nPoint : neighbourhood){
                char nPointData = figuresMap.getFiguresMapData()[nPoint.getY()][nPoint.getX()];
                if(nPointData == ONE && !isOnStack(nPoint, stack)){
                    stack.addFirst(nPoint);
                }
            }
        }
    }

    private boolean isOnStack(Point pointToCheck, LinkedList<Point> stack){
        boolean isOnStack = false;
        for(Point point : stack){
            if(point.equals(pointToCheck)){
                isOnStack = true;
                break;
            }
        }
        return isOnStack;
    }

}
