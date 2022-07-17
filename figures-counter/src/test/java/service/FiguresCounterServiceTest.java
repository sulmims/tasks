package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.counter.FiguresCounterService;
import service.counter.FiguresMap;
import service.counter.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class FiguresCounterServiceTest {

    @Test
    public void countFiguresStandardMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]
                {{'1','1','0','0','0','0','0','0','0','0','0','1','1'},
                        {'0','1','0','0','1','1','1','0','0','0','0','0','0'},
                        {'0','0','0','0','0','1','1','1','0','0','0','1','1'},
                        {'1','1','0','0','0','0','0','0','1','0','0','0','0'},
                        {'0','0','0','0','1','1','1','0','0','0','0','1','1'},
                        {'1','1','0','0','0','0','0','0','0','0','0','1','1'}});

        int figuresCount = FiguresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(8, figuresCount);
    }

    @Test
    public void countFiguresOneRowMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]{{'1','0','1','1'}});
        int figuresCount = FiguresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(2, figuresCount);
    }

    @Test
    public void countFiguresOneElementMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]{{'1'}});
        int figuresCount = FiguresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(1, figuresCount);
    }

    @Test
    public void countFiguresEmptyMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[][]{{'0','0'},{'0','0'}});
        int figuresCount = FiguresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(0, figuresCount);
    }

    @Test
    public void countFiguresNullMapTest() throws InvalidInputException {
        FiguresMap figuresMap = new FiguresMap(new char[0][0]);
        int figuresCount = FiguresCounterService.countFigures(figuresMap);
        Assertions.assertEquals(0, figuresCount);
    }

    @Test
    public void countFiguresInvalidInputTest(){
        InvalidInputException thrown = Assertions.assertThrows(InvalidInputException.class, () -> {
            FiguresMap unevenFiguresMap = new FiguresMap(new char[][]{{'1','0','1','1'}, {'1','0','1','1','1'}});
            FiguresCounterService.countFigures(unevenFiguresMap);
        });
        assertEquals(InvalidInputException.class, thrown.getClass());
        assertEquals("Number of columns in the input array is not the same in each row", thrown.getMessage());
    }
}