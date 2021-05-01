package Pièces;

public class Roi extends Piece {

    public Roi(int ligneInit, int colInit, String colr){
        super(ligneInit, colInit, colr);
    }

    public char getChar() {
        if(super.getCouleur().equals("blanc")){
            return 'R';
        }
        else{
            return 'r';
        }
    }

    @Override
    public boolean move(Coordonnee coordArr) {
        Coordonnee coordPiece = super.getCoord();
        if(!(coordPiece.equals(coordArr))){
            if (coordArr.getColonne() == coordPiece.getColonne() + 1 || coordArr.getColonne() == coordPiece.getColonne() - 1
                    || coordArr.getColonne() == coordPiece.getColonne()) {
                if (coordArr.getLigne() == coordPiece.getLigne() - 1 || coordArr.getLigne() == coordPiece.getLigne() + 1
                        || coordArr.getLigne() == coordPiece.getLigne()) {
                    deplacerA(coordArr);
                    return true;
                }
            }
        }
        return false;
    }
}
