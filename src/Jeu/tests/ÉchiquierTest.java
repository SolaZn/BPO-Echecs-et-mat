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

    @Test
    public void toStringTest(){
        Échiquier Echiquier = new Échiquier();
        StringBuilder sb = new StringBuilder();
        sb.append("    a   b   c   d   e   f   g   h    \n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("8 |   |   |   |   |   |   |   |   | 8\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("7 |   |   |   |   |   |   |   |   | 7\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("6 |   |   |   |   |   |   |   |   | 6\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("5 |   |   |   |   |   |   |   |   | 5\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("4 |   |   |   |   |   |   |   |   | 4\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("3 |   |   |   |   |   |   |   |   | 3\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("2 |   |   |   |   |   |   |   |   | 2\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("1 |   |   |   |   |   |   |   |   | 1\n")
                .append("   --- --- --- --- --- --- --- ---   \n")
                .append("    a   b   c   d   e   f   g   h    \n");
        Assert.assertTrue(Echiquier.toString('a').equals(sb.toString()));
    }



}