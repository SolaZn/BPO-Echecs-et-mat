package Joueurs.tests;

import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Joueurs.IPiece;
import org.junit.Assert;
import org.junit.Test;

import static Jeu.Jeu.creationCoordCoup;
import static Joueurs.DefinirPiece.fabriquerPiece;

public class DefinirPieceTest {

    @Test
    public void fabriquerPieces() throws FormatCoupIncorrectException {
        IPiece P = fabriquerPiece('t', creationCoordCoup('b','7'),"blanc");
        IPiece P1 = fabriquerPiece('r', creationCoordCoup('a','7'),"noir");

        Assert.assertEquals(P.getClass().getName(),"Pièces.Tour");
        Assert.assertEquals(P1.getClass().getName(),"Pièces.Roi");
    }
}