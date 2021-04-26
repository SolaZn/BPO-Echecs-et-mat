package Jeu;

import Pièces.Coordonnee;

public interface IPiece {
    public String getCouleur();

    public char dessiner();

    public void deplacerA(Coordonnee coordArr);

    public Coordonnee getCoord();
}
