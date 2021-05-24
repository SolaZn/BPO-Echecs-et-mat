package Joueurs;

import Application.IJoueur;
import Jeu.Échiquier;

import static Application.Appli.saisie;

/**
 * Cette classe représente un joueur humain dans le programme de finales d'échecs.
 * Cette IA a un nom et a à sa disposition la liste de ses pièces;
 * Pièces qu'elle manipulera au cours de sa partie.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony ZAKANI
 */
public class Humain extends Joueur{

    /**
     * Construit un joueur humain comme sous-type d'un joueur
     * @param couleur la couleur des pièces du joueur
     * @param nomJoueur le nom du joueur
     */
    public Humain(String couleur, String nomJoueur){
        super(couleur, nomJoueur);
    }

    /**
     * @see IJoueur#joueCoup(Échiquier, IJoueur) 
     */
    @Override
    public String joueCoup(Échiquier Echiquier, IJoueur j2) {
        return saisie();
    }
}
