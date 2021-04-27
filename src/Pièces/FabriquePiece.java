package Pièces;

import Jeu.IPiece;

public class FabriquePiece {
    public IPiece FabriquePiece(Coordonnee coord, String couleur){
        if((coord.getLigne() == 8-6) && (coord.getColonne() == 8-6)){
            return new Roi(coord.getLigne(), coord.getColonne(), couleur);
        }
        return new Tour(1,1,"nlbnc");
    }
}
