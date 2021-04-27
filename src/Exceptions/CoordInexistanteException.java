package Exceptions;

public class CoordInexistanteException extends Exception {
    public CoordInexistanteException() {
        super("Coup illégal\nCoordonnees inexistantes");
    }
}
