package Exceptions.Coordonnees;

public class CoordInexistanteException extends Exception {
    public CoordInexistanteException() {
        super("Coup illégal\nCoordonnees inexistantes");
    }
}
