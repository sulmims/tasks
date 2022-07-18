import service.counter.FiguresCounterService;
import service.counter.FiguresMap;
import service.counter.InvalidInputException;
import service.counter.RollingLetterSource;
import service.neighbour.NeighbourService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

public class FiguresCounter {

    private static final String FIGURES_MAP_FILE_NAME = "/home/compliance/development/tasks/figures-counter/build/resources/main/figures_map.txt";

    private static final Logger logger = System.getLogger(FiguresCounter.class.getName());

    public static void main(String[] args) {
        try {
            FiguresMap figuresMap = createFiguresMap();
            FiguresCounterService figuresCounterService = new FiguresCounterService(new NeighbourService(), new RollingLetterSource());
            int figuresCount = figuresCounterService.countFigures(figuresMap);
            logger.log(Level.INFO, String.format("FiguresCount : %s", figuresCount));
            printFiguresMap(figuresMap);
        }catch(InvalidInputException e){
            logger.log(Level.ERROR, String.format("ERROR : %s", e.getMessage()));
        }catch(IOException e){
            logger.log(Level.ERROR, String.format("ERROR : %s", e.toString()));
        }
    }

    private static void printFiguresMap(FiguresMap figuresMap){
        for(int y = 0; y < figuresMap.getHeight(); y++) {
            String line = "";
            for (int x = 0; x < figuresMap.getWidth(); x++) {
                line += (char) figuresMap.getFiguresMapData()[y][x];
            }
            logger.log(Level.DEBUG, line);
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
