import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FiguresCounter {

    private static final String FIGURES_MAP_FILE_NAME = "/home/compliance/development/tasks/figures-counter/build/resources/main/figures_map.txt";

    private static final String INPUT_DATA_INVALID = "Number of columns in the input array is not the same in each row.";

    private static final char ONE = '1';

    public static void main(String[] args) throws Exception{
        try {
            FiguresMap figuresMap = createFiguresMap();
            int figuresCount = countFigures(figuresMap);
            System.out.println(String.format("FiguresCount : %s", figuresCount));
            printFiguresMap(figuresMap);
        }catch(InvalidInputException e){
            System.out.println(String.format("ERROR : %s", e.getMessage()));
        }catch(IOException e){
            System.out.println(String.format("ERROR : %s", e.toString()));
        }
    }

    private static void printFiguresMap(FiguresMap figuresMap){
        for(int y = 0; y < figuresMap.getHeight(); y++) {
            String line = "";
            for (int x = 0; x < figuresMap.getWidth(); x++) {
                line += (char) figuresMap.getFiguresMapData()[y][x];
            }
            System.out.println(line);
        }
    }

    private static FiguresMap createFiguresMap() throws IOException, InvalidInputException {
        List<String> lines = Files.readAllLines(Paths.get(FIGURES_MAP_FILE_NAME));
        int linesCount = lines.size();
        int lineLength = linesCount != 0 ? lines.get(0).length() : 0;
        char[][] figuresMapData = new char[linesCount][lineLength];
        int lineNumber = 0;
        for(String line : lines) {
            byte[] lineBytes = line.getBytes();
            if(lineBytes.length != lineLength){
                throw new InvalidInputException(INPUT_DATA_INVALID);
            }
            figuresMapData[lineNumber++] = line.toCharArray();
        }
        return new FiguresMap(figuresMapData, new Size(lineLength, linesCount));
    }

    private static int countFigures(FiguresMap figuresMap){
        int figuresCount = 0;
        LetterSource letterSource = new LetterSource();
        for(int y = 0; y < figuresMap.getHeight(); y++){
            for(int x = 0; x < figuresMap.getWidth(); x++) {
                if(figuresMap.getFiguresMapData()[y][x] == ONE){
                    figuresCount++;
                    char letter = letterSource.getNextLetter();
                    processFigureFromPoint(new Point(y, x), figuresMap, letter);
                }
            }
        }
        return figuresCount;
    }

    private static void processFigureFromPoint(Point point, FiguresMap figuresMap, char letter){
        LinkedList<Point> stack = new LinkedList<>();
        stack.addFirst(point);
        while(stack.size() != 0){
            Point pointFromStack = stack.getFirst();
            figuresMap.setMapPointData(pointFromStack, letter);
            stack.removeFirst();
            List<Point> neighbourhood = getNeighbourhood(pointFromStack, figuresMap);
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

    private static List<Point> getNeighbourhood(Point point, FiguresMap figuresMap){
        List<Point> neighbourhood = new LinkedList<>();
        boolean decX, decY, incX, incY;
        decX = point.getX() > 0;
        decY = point.getY() > 0;
        incX = point.getX() < figuresMap.getWidth() - 1;
        incY = point.getY() < figuresMap.getHeight() - 1;
        if(decX && decY) neighbourhood.add(new Point(point.getY() - 1, point.getX() - 1));      // -1, -1
        if(decY) neighbourhood.add(new Point(point.getY() - 1, point.getX()));                     // -1, 0
        if(incX && decY) neighbourhood.add(new Point(point.getY() - 1, point.getX() + 1));      // -1, 1
        if(incX) neighbourhood.add(new Point(point.getY(), point.getX() + 1));                     // 0, 1
        if(incX && incY) neighbourhood.add(new Point(point.getY() + 1, point.getX() + 1));      // 1, 1
        if(incY) neighbourhood.add(new Point(point.getY() + 1, point.getX()));                     // 1, 0
        if(incY && decX) neighbourhood.add(new Point(point.getY() + 1, point.getX() - 1));      // 1, -1
        if(decX) neighbourhood.add(new Point(point.getY(), point.getX() - 1));                     // 0, -1
        return neighbourhood;
    }

}
