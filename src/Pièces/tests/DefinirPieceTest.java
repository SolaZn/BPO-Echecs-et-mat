package Pièces.tests;

import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Jeu.Interfaces.IPiece;
import org.junit.Assert;
import org.junit.Test;

import static Jeu.Jeu.creationCoordCoup;

public class DefinirPieceTest {

    @Test
    public void fabriquerPiece() throws FormatCoupIncorrectException {
        IPiece P = IPiece.getPiece('t', creationCoordCoup('b','7'),"blanc");
        IPiece P1 = IPiece.getPiece('r', creationCoordCoup('a','7'),"noir");

        Assert.assertEquals(P.getClass().getName(),"Pièces.Tour");
        Assert.assertEquals(P1.getClass().getName(),"Pièces.Roi");
    }
}