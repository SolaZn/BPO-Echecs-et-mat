package Pièces;

import Exceptions.Coordonnees.*;

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

    @Override
    public boolean move(Coordonnee coordArr, etatPiece etat) throws CoupHorsZoneDepException{
        // TODO: 01/05/2021 Fonction à implémenter en prenant en compte les "pièces sur le chemin" (voir ajout d'une fonction) (voir todo Jeu.jeu si possible faire là)
        Coordonnee coordPiece = super.getCoord();
        if(!(coordPiece.equals(coordArr))){
            if ((coordArr.getColonne() == coordPiece.getColonne() && coordArr.getLigne() != coordPiece.getLigne()) ||
                    (coordArr.getLigne() == coordPiece.getLigne() && coordArr.getColonne() != coordPiece.getColonne())) {
                // si il se déplace horizontalement ou verticalement
                if(etat == etatPiece.Jeu) {
                    deplacerA(coordArr);
                }
                return true;
            }
            throw new CoupHorsZoneDepException();
        }
        return false;
    }

}
