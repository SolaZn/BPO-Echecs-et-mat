package Exceptions;

public class FormatCoupIncorrectException extends Exception{
    public FormatCoupIncorrectException() {
        super("Coup illégal\nLe format du coup n'est pas correct");
    }
}
