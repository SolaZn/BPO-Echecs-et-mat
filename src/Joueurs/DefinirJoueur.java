package Joueurs;

import Jeu.Interfaces.IJoueur;

class DefinirJoueur {
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
