package Jeu;

import Exceptions.Coordonnees.*;
import Pièces.*;

public interface IPiece {
    static IPiece getPiece(char typePiece, Coordonnee coord, String couleur){
        return Piece.getPiece(typePiece, coord, couleur);
    }

    static boolean isMangeable(char typePiece){
        return Piece.isMangeable(typePiece);
    }

    String getCouleur();

    char dessiner();

    boolean move(Coordonnee coordArr) throws CoupHorsZoneDepException;

    Coordonnee getCoord();


}
