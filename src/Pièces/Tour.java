package Pièces;

import Exceptions.Coordonnees.*;
import Jeu.IPiece;
import Jeu.Échiquier;
import java.util.LinkedList;

/**
 * Cette classe représente une Tour dans le programme de finales d'échecs.
 * Cette pièce a une coordonnée ainsi que sa couleur d'affichage;
 * Couleur qui lui servira lors de son affichage.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony Zakani
 */
public class Tour extends Piece {
    private static final int MTAILLE = 8;

    /**
     * Renvoie la tour initialisé a une ligne, une colonne et une couleur données
     * @param ligneInit la ligne a laquelle va être initialisé la Tour
     * @param colInit la colonne a laquelle va être initialisé la Tour
     * @param colr la couleur a laquelle va être initialisé la Tour
     */
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
     * @see IPiece#routeBarree(Coordonnee, Coordonnee, Échiquier)
     */
    public boolean routeBarree(Coordonnee coordDepart, Coordonnee coordArr, Échiquier echiquier){
        boolean barre = false;
        // TODO: 24/05/2021 : ne pas juger routeBarrée si la route à une case est barrée par la pièce qui est visée par le coup
        if (coordDepart.getLigne() != coordArr.getLigne()){
            int pos = coordArr.getLigne() - coordDepart.getLigne();
            if(pos < 0){
                pos = Math.abs(pos);
                for(int i = 1; i < pos; ++i) {
                    Coordonnee coord = new Coordonnee(coordDepart.getLigne() - i, coordDepart.getColonne());
                    if (echiquier.coordOccupé(coord) != ' '){
                        return true;
                    }
                }
            }
            else{
                for(int i = 1; i < pos; ++i) {
                    Coordonnee coord = new Coordonnee(coordDepart.getLigne() + i, coordDepart.getColonne());
                    if (echiquier.coordOccupé(coord) != ' '){
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
                    if (echiquier.coordOccupé(coord) != ' '){
                        return true;
                    }
                }
            }
            else{
                for(int i = 1; i < pos; ++i) {
                    Coordonnee coord = new Coordonnee(coordDepart.getLigne(), coordDepart.getColonne() + i);
                    if (echiquier.coordOccupé(coord) != ' '){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
