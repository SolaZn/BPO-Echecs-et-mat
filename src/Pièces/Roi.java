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
}
