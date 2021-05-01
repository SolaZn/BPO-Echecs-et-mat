package Pièces;

import Exceptions.CoupHorsZoneDepException;

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
    public boolean move(Coordonnee coordArr) throws CoupHorsZoneDepException {
        // TODO: 01/05/2021 Fonction à implémenter en prenant en compte les "pièces sur le chemin" (voir ajout d'une fonction) (voir todo Jeu.jeu si possible faire là)
        Coordonnee coordPiece = super.getCoord();
        if(!(coordPiece.equals(coordArr))){
            if (coordArr.getColonne() == coordPiece.getColonne() + 1 || coordArr.getColonne() == coordPiece.getColonne() - 1
                    || coordArr.getColonne() == coordPiece.getColonne()) { // si il se déplace d'une seule case en colonne
                if (coordArr.getLigne() == coordPiece.getLigne() - 1 || coordArr.getLigne() == coordPiece.getLigne() + 1
                        || coordArr.getLigne() == coordPiece.getLigne()) { // si il se déplace d'une seule case en ligne
                    deplacerA(coordArr);
                    return true;
                }
                throw new CoupHorsZoneDepException();
            }
            throw new CoupHorsZoneDepException();
        }
        return false;
    }
}
