package Pièces.tests;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Joueurs.IPiece;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import Pièces.etatPiece;
import org.junit.Assert;
import org.junit.Test;

import static Jeu.Jeu.creationCoordCoup;
import static Joueurs.DefinirPiece.fabriquerPiece;

public class RoiTest {

    @Test
    public void move() throws FormatCoupIncorrectException, CoupHorsZoneDepException {
        Échiquier echiquier = new Échiquier();
        Coordonnee coord = null;

        IPiece roi = fabriquerPiece('r', creationCoordCoup('b','7'),"blanc");
        coord = Jeu.Jeu.creationCoordCoup('b','7');
        Assert.assertTrue(roi.getCoord().equals(coord));
        coord = Jeu.Jeu.creationCoordCoup('c','7');
        roi.move(coord, etatPiece.Jeu);
        Assert.assertTrue(roi.getCoord().equals(coord));
    }

    @Test
    public void getCoupsPossibles() throws FormatCoupIncorrectException {
        Échiquier echiquier = new Échiquier();
        IPiece roi = fabriquerPiece('r', creationCoordCoup('b','7'),"blanc");

        Assert.assertEquals( 8, roi.getCoupsPossibles().size());
    }

}