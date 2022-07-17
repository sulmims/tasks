package service.counter;

public class LetterSource{
    private static final String LETTERS = "ABCDE";
    private int letterNumber;

    public LetterSource(){
        letterNumber = 0;
    }

    public char getNextLetter(){
        char letter = LETTERS.charAt(letterNumber);
        letterNumber = letterNumber < LETTERS.length() - 1 ? letterNumber + 1 : 0;
        return letter;
    }
}