package Jeu.tests;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Application.IJoueur;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import org.junit.Test;
import org.junit.Assert;

public class ÉchiquierTest {

    @Test
    public void rafraichir() throws CoupHorsZoneDepException, FormatCoupIncorrectException {
        IJoueur J1 = IJoueur.getJoueur('h',"blanc","blanc");
        IJoueur J2 = IJoueur.getJoueur('h', "noir", "noir");
        Échiquier echiquier = new Échiquier();
        echiquier.rafraichir(J1, J2);
        Coordonnee coord = null;
        try {
            coord = Jeu.Jeu.creationCoordCoup('b', '7');
        } catch (FormatCoupIncorrectException e) {
            System.out.println("Cet affichage n'arrivera pas");
        }
        Assert.assertEquals( 'T', echiquier.coordOccupé(coord));

        Coordonnee coordInit;
        Coordonnee coordArr = null;

        coordInit = Jeu.Jeu.creationCoordCoup('b', '7');
        coordArr = Jeu.Jeu.creationCoordCoup('c', '7');
        J1.deplacerPiece(coordInit, coordArr);

        echiquier.rafraichir(J1,J2);

        Assert.assertEquals( ' ', echiquier.coordOccupé(coord));
        Assert.assertEquals( 'T', echiquier.coordOccupé(coordArr));
    }

}