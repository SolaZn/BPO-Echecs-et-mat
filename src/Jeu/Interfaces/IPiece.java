package Jeu.Interfaces;

import Exceptions.Coordonnees.*;
import Pièces.*;

import java.util.LinkedList;

public interface IPiece {
    static IPiece getPiece(char typePiece, Coordonnee coord, String couleur){
        return Piece.getPiece(typePiece, coord, couleur);
    }

    LinkedList<Coordonnee> getCoupPossible();

    static boolean isMangeable(char typePiece){
        return Piece.isMangeable(typePiece);
    }

    String getCouleur();

    char dessiner();

    boolean move(Coordonnee coordArr, etatPiece etat) throws CoupHorsZoneDepException;

    Coordonnee getCoord();


}
