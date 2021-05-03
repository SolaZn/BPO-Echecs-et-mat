package Exceptions.Coordonnees;

public class FormatCoupIncorrectException extends Exception{
    public FormatCoupIncorrectException() {
        super("Coup illégal\nLe format du coup est incorrect");
    }
}
