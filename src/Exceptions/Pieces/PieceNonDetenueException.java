package Exceptions.Pieces;

/**
 * Cette exception se déclenche lorsque le coup nécessiterait de manipuler une pièce
 * qui n'est pas détenue ou alors de déplacer une pièce
 */
public class PieceNonDetenueException extends Exception {
    public PieceNonDetenueException() {
        super("Coup illégal\nPièce de départ non détenue\n et/ou Pièce d'arrivée détenue");
    }
}
