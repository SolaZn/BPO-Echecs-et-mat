package Application;

import Joueurs.Humain;
import Joueurs.IA;

/**
 * Cette classe est une fabrique d'IJoueur. Elle sert à construire
 * tout objet de ce type à l'aide des caractéristiques renseignées par
 * l'utilisateur.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony Zakani
 */
class DefinirJoueur {
    /**
     * Construit une IPièce du type auquel correspond le char et
     * l'initialise avec une coordonnée et une couleur donnée
     * @param typeJoueur le type de joueur à créer
     * @param couleur la couleur des pièces du joueur
     * @param nomJoueur le nom du joueur
     * @return une IPièce du type spécifié
     */
    public static IJoueur fabriquerJoueur(char typeJoueur, String couleur, String nomJoueur) {
        if(Character.toLowerCase(typeJoueur) == 'h'){
            return new Humain(couleur, nomJoueur);
        }
        else if(Character.toLowerCase(typeJoueur) == 'i'){
            return new IA(couleur, nomJoueur);
        }
        throw new UnsupportedOperationException("Ce type de joueur n'a pas été implémenté;\n" + typeJoueur + " n'existe pas");
    }
}
