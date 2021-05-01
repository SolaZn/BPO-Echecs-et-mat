package Exceptions;

public class PieceNonDetenueException extends Exception {
    public PieceNonDetenueException() {
        super("Coup illégal\nPièce de départ non détenue\n et/ou Pièce d'arrivée détenue");
    }
}
