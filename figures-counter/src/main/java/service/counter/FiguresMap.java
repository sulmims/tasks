package service.counter;

import service.Point;
import service.Size;

public class FiguresMap {

    private static final String INPUT_DATA_INVALID = "Number of columns in the input array is not the same in each row";

    private char[][] figuresMapData;
    private Size size;

    public FiguresMap(char[][] figuresMapData) throws InvalidInputException{
        validateInput(figuresMapData);
        this.figuresMapData = figuresMapData;
        this.size = new Size(getFirstRowLength(figuresMapData), figuresMapData.length);
    }

    public void setMapPointData(Point point, char data){
        figuresMapData[point.getY()][point.getX()] = data;
    }

    public char[][] getFiguresMapData() {
        return figuresMapData;
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }

    private void validateInput(char[][] figuresMapData) throws InvalidInputException{
        int firstRowLength = getFirstRowLength(figuresMapData);
        for(char[] row : figuresMapData) {
            if(row.length != firstRowLength){
                throw new InvalidInputException(INPUT_DATA_INVALID);
            }
        }
    }

    private int getFirstRowLength(char[][] figuresMapData){
        return figuresMapData.length != 0 ? figuresMapData[0].length : 0;
    }
}