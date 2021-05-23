package Exceptions.Pieces;

/**
 * Cette exception se déclenche lorsque le coup nécessiterait de manger une pièce
 * ne pouvant pas l'être
 */
public class PieceNonMangeableException extends Exception{
    public PieceNonMangeableException() {
        super("Coup illégal\nPiece non mangeable");
    }
}
