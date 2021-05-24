package Pièces;

import Exceptions.Coordonnees.*;
import Joueurs.IPiece;
import Jeu.Échiquier;

import java.util.LinkedList;

/**
 * Cette classe représente un roi dans le programme de finales d'échecs.
 * Cette pièce a une coordonnée ainsi que sa couleur d'affichage;
 * Couleur qui lui servira lors de son affichage.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony Zakani
 */
public class Roi extends Piece {

    /**
     * Renvoie le roi initialisé a une ligne, une colonne et une couleur données
     * @param ligneInit la ligne a laquelle va être initialisé le Roi
     * @param colInit la colonne a laquelle va être initialisé le Roi
     * @param colr la couleur a laquelle va être initialisé le Roi
     */
    public Roi(int ligneInit, int colInit, String colr){
        super(ligneInit, colInit, colr);
    }

    /**
     * @see Piece#getChar()
     */
    @Override
    public char getChar() {
        if(super.getCouleur().equals("blanc")){
            return 'R';
        }
        else{
            return 'r';
        }
    }

    /**
     * @see IPiece#move(Coordonnee, etatPiece)
     */
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

    /**
     * @see IPiece#getCoupsPossibles()
     */
    public LinkedList<Coordonnee> getCoupsPossibles(){
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

    /**
     * @see IPiece#routeBarree(Coordonnee, Coordonnee, Échiquier) 
     */
    @Override
    public boolean routeBarree(Coordonnee coordDepart, Coordonnee coordArr, Échiquier echiquier) {
        return false;
    }
}

