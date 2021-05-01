package Pièces;

import Jeu.IPiece;

public class FabriquePiece {
    public IPiece FabriquePiece(Coordonnee coord, String couleur){
        // TODO: 01/05/2021 Implémenter la fabrique à pièces pour éviter au joueur de manipuler les pièces par leur type mais passer par à IPiece pour créer
        if((coord.getLigne() == 8-6) && (coord.getColonne() == 8-6)){
            return new Roi(coord.getLigne(), coord.getColonne(), couleur);
        }
        return new Tour(1,1,"nlbnc");
    }
}
