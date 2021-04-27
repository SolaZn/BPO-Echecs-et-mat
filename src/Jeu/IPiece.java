package Jeu;

import Pièces.Coordonnee;

public interface IPiece {
    String getCouleur();

    char dessiner();

    void deplacerA(Coordonnee coordArr);

    Coordonnee getCoord();

    static boolean isMangeable(char typePiece){
        return Character.toUpperCase(typePiece) != 'R';
    }
}
