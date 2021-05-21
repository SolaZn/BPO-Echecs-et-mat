package Pièces;

import Exceptions.Coordonnees.*;
import Jeu.Interfaces.IPiece;
import Jeu.Échiquier;
import java.util.LinkedList;

class Tour extends Piece {
    private static final int MTAILLE = 8;

    public Tour(int ligneInit, int colInit, String colr){
        super(ligneInit, colInit, colr);
    }

    /**
     * @see Piece#getChar()
     */
    @Override
    public char getChar() {
        if(super.getCouleur().equals("blanc")){
            return 'T';
        }
        else{
            return 't';
        }
    }

    /**
     * @see IPiece#move(Coordonnee, etatPiece)
     */
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

    /**
     * @see IPiece#getCoupsPossibles()
     */
    public LinkedList<Coordonnee> getCoupsPossibles(){
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

    /**
     * @see IPiece#barreRoute(Coordonnee, Coordonnee)
     */
    public boolean barreRoute(Coordonnee coordDepart, Coordonnee coordArr){
        boolean barre = false;
        if (coordDepart.getLigne() != coordArr.getLigne()){
            int pos = coordArr.getLigne() - coordDepart.getLigne();
            if(pos < 0){
                pos = Math.abs(pos);
                for(int i = 1; i < pos; ++i) {
                    Coordonnee coord = new Coordonnee(coordDepart.getLigne() - i, coordDepart.getColonne());
                    if (this.getCoord().equals(coord)){
                        return true;
                    }
                }
            }
            else{
                for(int i = 1; i < pos; ++i) {
                    Coordonnee coord = new Coordonnee(coordDepart.getLigne() + i, coordDepart.getColonne());
                    if (this.getCoord().equals(coord)){
                        return true;
                    }
                }
            }
        }
        else{
            int pos = coordArr.getColonne() - coordDepart.getColonne();
            if(pos < 0){
                pos = Math.abs(pos);
                for(int i = 1; i < pos; ++i) {
                    Coordonnee coord = new Coordonnee(coordDepart.getLigne(), coordDepart.getColonne() - i);
                    if (this.getCoord().equals(coord)){
                        return true;
                    }
                }
            }
            else{
                for(int i = 1; i < pos; ++i) {
                    Coordonnee coord = new Coordonnee(coordDepart.getLigne(), coordDepart.getColonne() + 1);
                    if (this.getCoord().equals(coord)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
