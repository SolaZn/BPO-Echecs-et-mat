package Exceptions.Coordonnees;

/**
 * Cette exception se déclenche lorsque la coordonnée obtenue est inexistante
 */
public class CoordInexistanteException extends Exception {
    public CoordInexistanteException() {
        super("Coup illégal\nCoordonnees inexistantes");
    }
}
