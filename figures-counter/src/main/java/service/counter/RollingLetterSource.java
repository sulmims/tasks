package service.counter;

public class RollingLetterSource implements LetterSource{
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int letterNumber;

    public RollingLetterSource(){
        letterNumber = 0;
    }

    public char getNextLetter(){
        char letter = LETTERS.charAt(letterNumber);
        letterNumber = letterNumber < LETTERS.length() - 1 ? letterNumber + 1 : 0;
        return letter;
    }
}