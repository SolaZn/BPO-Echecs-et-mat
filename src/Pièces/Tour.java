package Pièces;

import Exceptions.Coordonnees.*;
import Jeu.Échiquier;
import java.util.LinkedList;

public class Tour extends Piece {
    private static final int MTAILLE = 8;

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

    public LinkedList<Coordonnee> getCoupPossible(){
        Coordonnee coordPiece = this.getCoord();
        LinkedList<Coordonnee> listeCoups = new LinkedList<>();


        for(int variationCol = - MTAILLE; variationCol < MTAILLE; variationCol++){
            try {
                Coordonnee posPossRoiAdv = new Coordonnee(coordPiece.getLigne(),
                        coordPiece.getColonne() + variationCol);
                if(Échiquier.coordExiste(posPossRoiAdv))
                    listeCoups.add(posPossRoiAdv);
            } catch (CoordInexistanteException ci) {
                continue;
            }
        }

        for(int variationLigne = - MTAILLE; variationLigne < MTAILLE; variationLigne++){
            try {
                Coordonnee posPossRoiAdv = new Coordonnee(coordPiece.getLigne() + variationLigne,
                        coordPiece.getColonne());
                if(Échiquier.coordExiste(posPossRoiAdv) && !(coordPiece.equals(posPossRoiAdv)))
                    listeCoups.add(posPossRoiAdv);
            } catch (CoordInexistanteException ci) {
                continue;
            }
        }

        return listeCoups;
    }

}
