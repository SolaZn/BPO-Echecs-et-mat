package Jeu;

import Exceptions.CoupHorsZoneDepException; // TODO: 01/05/2021 Idée : organiser Exceptions en sous packages selon la scope de chaque Exception (exception pièces dans Exceptions.Piece)
import Pièces.Coordonnee;

public interface IPiece {
    String getCouleur();

    char dessiner();

    boolean move(Coordonnee coordArr) throws CoupHorsZoneDepException;

    Coordonnee getCoord();

    static boolean isMangeable(char typePiece){
        return Character.toUpperCase(typePiece) != 'R';
    }
}
