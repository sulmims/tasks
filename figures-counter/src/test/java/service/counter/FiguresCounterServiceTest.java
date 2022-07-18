package service.counter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.neighbour.NeighbourService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FiguresCounterServiceTest {

    private NeighbourService neighbourService;

    private LetterSource letterSource;

    private FiguresCounterService figuresCounterService;;

    @BeforeEach
    public void init() {
        neighbourService = new NeighbourService();
        letterSource = new RollingLetterSource();
        figuresCounterService = new FiguresCounterService(neighbourService, letterSource);
    }

    @Test
    public void countFiguresStandardMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]
                {{'1','1','0','0','0','0','0','0','0','0','0','1','1'},
                {'0','1','0','0','1','1','1','0','0','0','0','0','0'},
                {'0','0','0','0','0','1','1','1','0','0','0','1','1'},
                {'1','1','0','0','0','0','0','0','1','0','0','0','0'},
                {'0','0','0','0','1','1','1','0','0','0','0','1','1'},
                {'1','1','0','0','0','0','0','0','0','0','0','1','1'}});

        int figuresCount = figuresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(8, figuresCount);
    }

    @Test
    public void countFiguresOneRowMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]{{'1','0','1','1'}});
        int figuresCount = figuresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(2, figuresCount);
    }

    @Test
    public void countFiguresOneElementMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]{{'1'}});
        int figuresCount = figuresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(1, figuresCount);
    }

    @Test
    public void countFiguresEmptyMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]{{'0','0'},{'0','0'}});
        int figuresCount = figuresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(0, figuresCount);
    }

    @Test
    public void countFiguresNullMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[0][0]);
        int figuresCount = figuresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(0, figuresCount);
    }

    @Test
    public void countFiguresInvalidInputTest(){
        InvalidInputException thrown = Assertions.assertThrows(InvalidInputException.class, () -> {
            FiguresMap unevenFiguresMap = new FiguresMap(new char[][]{{'1','0','1','1'}, {'1','0','1','1','1'}});
            figuresCounterService.countFigures(unevenFiguresMap);
        });
        assertEquals(InvalidInputException.class, thrown.getClass());
        assertEquals("Number of columns in the input array is not the same in each row", thrown.getMessage());
    }
}