package service.counter;

public class FixedLetterSource implements LetterSource{
    public char getNextLetter(){
        return 'P';
    }
}