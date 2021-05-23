package Joueurs;

import Jeu.Interfaces.IJoueur;
import Jeu.Échiquier;

import static Application.Appli.saisie;

class Humain extends Joueur{

    /**
     * Construit un joueur humain comme sous-type d'un joueur
     * @param couleur la couleur des pièces du joueur
     * @param nomJoueur le nom du joueur
     */
    Humain(String couleur, String nomJoueur){
        super(couleur, nomJoueur);
    }

    @Override
    public String joueCoup(Échiquier Echiquier, IJoueur j2) {
        return saisie();
    }
}
