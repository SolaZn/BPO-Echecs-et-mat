package Exceptions.Pieces;

public class PieceNonMangeableException extends Exception{
    public PieceNonMangeableException() {
        super("Coup illégal\nPiece non mangeable");
    }
}
