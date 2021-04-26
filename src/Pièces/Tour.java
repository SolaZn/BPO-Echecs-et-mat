package Pièces;

public class Tour extends Piece {

    public Tour(int ligneInit, int colInit, String colr){
        super(ligneInit, colInit, colr);
    }

    public char getChar() {
        if(super.getCouleur().equals("blanc")){
            return 'T';
        }
        else{
            return 't';
        }
    }
}
