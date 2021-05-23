package Exceptions.Coordonnees;

/**
 * Cette exception se déclenche lorsque le format lettre-chiffre-lettre-chiffre pour un coup joué
 * n'est pas respecté par la saisie obtenue
 */
public class FormatCoupIncorrectException extends Exception{
    public FormatCoupIncorrectException() {
        super("Coup illégal\nLe format du coup est incorrect");
    }
}
