package Pièces.tests;

import Pièces.Coordonnee;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordonneeTest {

    @Test
    public void testEquals() {
        Coordonnee coordA = new Coordonnee(0, 0);
        Coordonnee coordB = null;

        Assert.assertFalse(coordA.equals(coordB));
        coordB = new Coordonnee( 1, 0);
        Assert.assertFalse(coordA.equals(coordB));
        coordB = new Coordonnee( 0, 1);
        Assert.assertFalse(coordA.equals(coordB));
        coordB = new Coordonnee( 0, 0);
        Assert.assertTrue(coordA.equals(coordB));
    }
}