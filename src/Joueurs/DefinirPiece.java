package Joueurs;

import Joueurs.IPiece;
import Pièces.Coordonnee;
import Pièces.Roi;
import Pièces.Tour;

/**
 * Cette classe est une fabrique d'IPièce. Elle sert à construire
 * tout objet de ce type à l'aide des caractéristiques renseignées par
 * l'utilisateur.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony Zakani
 */
public class DefinirPiece {
    /**
     * Construit une IPièce du type auquel correspond le char et
     * l'initialise avec une coordonnée et une couleur donnée
     * @param typePiece Le type de de la pièce
     * @param coord La coordonnée
     * @param couleur La couleur
     * @return la pièce (IPiece) ainsi définie
     */
    public static IPiece fabriquerPiece(char typePiece, Coordonnee coord, String couleur) {
        if(Character.toLowerCase(typePiece) == 'r'){
            return new Roi(coord.getLigne(), coord.getColonne(), couleur);
        }
        else if(Character.toLowerCase(typePiece) == 't'){
            return new Tour(coord.getLigne(), coord.getColonne(), couleur);
        }
        throw new UnsupportedOperationException("Cette pièce n'a pas été implémentée;\n" + typePiece + " n'existe pas");
    }

    /**
     * @see IPiece#isMangeable(char) 
     */
    static boolean isMangeable(char typePiece) {
        return Character.toUpperCase(typePiece) != 'R'; // => Point d'évolutivité citable dans le rapport : si on veut jouer avec des
        // => règles différentes, on peut changer. Cependant, réfléchir à la position dans le code
    }
}
