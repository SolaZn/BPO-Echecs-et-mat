package Jeu.tests;

import Exceptions.Coordonnees.CoordInexistanteException;
import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Exceptions.Coordonnees.RouteBarréeException;
import Exceptions.Pieces.PieceNonDetenueException;
import Exceptions.Pieces.PieceNonMangeableException;
import Exceptions.Pieces.RoiEnSituationEchecException;
import Application.IJoueur;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import org.junit.Assert;
import org.junit.Test;

public class JeuTest {

    @Test
    public void creationCoordCoup() throws FormatCoupIncorrectException {
        Coordonnee coordA = new Coordonnee(1,1);
        Coordonnee coordB = Jeu.Jeu.creationCoordCoup('b','7');

        Assert.assertTrue(coordA.equals(coordB));
    }

    @Test
    public void coupJoué() throws FormatCoupIncorrectException, CoupHorsZoneDepException,
            CoordInexistanteException, PieceNonDetenueException, PieceNonMangeableException,
            RoiEnSituationEchecException, RouteBarréeException {
        Échiquier Echiquier = new Échiquier();
        IJoueur J1 = IJoueur.getJoueur('h', "blanc", "blanc");
        IJoueur J2 = IJoueur.getJoueur('h', "noir", "noir");

        Jeu.Jeu.coupJoué(Echiquier, "e6f6", J1, J2);
    }
}