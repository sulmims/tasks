import service.counter.FiguresCounterService;
import service.counter.FiguresMap;
import service.counter.InvalidInputException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FiguresCounter {

    private static final String FIGURES_MAP_FILE_NAME = "/home/compliance/development/tasks/figures-counter/build/resources/main/figures_map.txt";

    public static void main(String[] args) {
        try {
            FiguresMap figuresMap = createFiguresMap();
            int figuresCount = FiguresCounterService.countFigures(figuresMap);
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
            figuresMapData[lineNumber++] = line.toCharArray();
        }
        return new FiguresMap(figuresMapData);
    }
}
