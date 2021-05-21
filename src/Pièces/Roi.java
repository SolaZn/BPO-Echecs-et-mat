package Pièces;

import Exceptions.Coordonnees.*;
import Jeu.Échiquier;

import java.util.LinkedList;

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
    public boolean move(Coordonnee coordArr, etatPiece etat) throws CoupHorsZoneDepException {
        Coordonnee coordPiece = super.getCoord();
            if (!(coordPiece.equals(coordArr))) {
                if (coordArr.getColonne() == coordPiece.getColonne() + 1
                        || coordArr.getColonne() == coordPiece.getColonne() - 1
                        || coordArr.getColonne() == coordPiece.getColonne()) { // si il se déplace d'une seule case en colonne
                    if (coordArr.getLigne() == coordPiece.getLigne() - 1
                            || coordArr.getLigne() == coordPiece.getLigne() + 1
                            || coordArr.getLigne() == coordPiece.getLigne()) { // si il se déplace d'une seule case en ligne
                        if(etat == etatPiece.Jeu) {
                            deplacerA(coordArr);
                        }
                        return true;
                    }
                    throw new CoupHorsZoneDepException();
                }
                throw new CoupHorsZoneDepException();
            }
            return false;
    }

    @Override
    public boolean barreRoute(Coordonnee coordDepart, Coordonnee coordArr) {
        return false;
    }

    public LinkedList<Coordonnee> getCoupPossible(){
        Coordonnee coordPiece = this.getCoord();
        LinkedList<Coordonnee> listeCoups = new LinkedList<>();

        for(int variationCol = - 1; variationCol < 2; variationCol++){
            for(int variationLigne = - 1; variationLigne < 2; variationLigne++) {
                try {
                    Coordonnee posPossRoiAdv = new Coordonnee(coordPiece.getLigne() + variationLigne,
                            coordPiece.getColonne() + variationCol);
                    if(Échiquier.coordExiste(posPossRoiAdv) && !(coordPiece.equals(posPossRoiAdv)))
                        listeCoups.add(posPossRoiAdv);
                } catch (CoordInexistanteException ci) {
                    continue;
                }
            }
        }

        return listeCoups;
    }
}

