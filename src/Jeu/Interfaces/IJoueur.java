package Jeu.Interfaces;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Jeu.Échiquier;
import Pièces.Coordonnee;

public interface IJoueur {
    // TODO: 01/05/2021 Commencer à choisir les fonctions communes et à l'IA pour construire l'interface à utiliser dans Jeu
    String joueCoup(Échiquier Echiquier, IJoueur j2);

    void dessinerPositions(char[][] Plateau);

    boolean deplacerPiece(Coordonnee coordInit, Coordonnee coordArr) throws CoupHorsZoneDepException;

    int detientPiece(Coordonnee coord);

    Coordonnee positionRoi();

    boolean essaiCoupHostile(Coordonnee coordRoi);

    void perdrePiece(Coordonnee coordArr);

    int nombrePieces();
}
