package Pièces.tests;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Application.IJoueur;
import Jeu.IPiece;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import Pièces.etatPiece;
import org.junit.Assert;
import org.junit.Test;

import static Jeu.Jeu.creationCoordCoup;

public class TourTest {

    @Test
    public void move() throws FormatCoupIncorrectException, CoupHorsZoneDepException {
        Échiquier echiquier = new Échiquier();
        Coordonnee coord = null;

        IPiece tour = IPiece.getPiece('t', creationCoordCoup('b','7'),"blanc");
        coord = Jeu.Jeu.creationCoordCoup('b','7');
        Assert.assertTrue(tour.getCoord().equals(coord));
        coord = Jeu.Jeu.creationCoordCoup('f','7');
        tour.move(coord, etatPiece.Jeu);
        Assert.assertTrue(tour.getCoord().equals(coord));
    }

    @Test
    public void getCoupsPossibles() throws FormatCoupIncorrectException{
        Échiquier echiquier = new Échiquier();
        IPiece tour = IPiece.getPiece('t', creationCoordCoup('b','7'),"blanc");

        Assert.assertEquals( 15, tour.getCoupsPossibles().size());
    }

    @Test
    public void barreRoute() throws FormatCoupIncorrectException, CoupHorsZoneDepException{
        Échiquier Echiquier = new Échiquier();
        IJoueur J1 = IJoueur.getJoueur('h', "blanc", "blanc");
        IJoueur J2 = IJoueur.getJoueur('h', "noir", "noir");

        Coordonnee coordInit = Jeu.Jeu.creationCoordCoup('b','7');
        Coordonnee coordArr = Jeu.Jeu.creationCoordCoup('c','7');

        J1.deplacerPiece(coordInit, coordArr);
        Echiquier.rafraichir(J1, J2);

        coordInit = Jeu.Jeu.creationCoordCoup('c','5');
        coordArr = Jeu.Jeu.creationCoordCoup('c','8');
        Assert.assertTrue(J2.barreRoute(coordInit, coordArr, Echiquier));
    }
}