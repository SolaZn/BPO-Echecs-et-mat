package Application.tests;

import Application.DefinirJoueur;
import Application.IJoueur;
import org.junit.Assert;
import org.junit.Test;

public class DefinirJoueurTest {

    @Test
    public void fabriquerJoueur() {
        IJoueur J = DefinirJoueur.fabriquerJoueur('h',"blanc","blanc");
        IJoueur IA = DefinirJoueur.fabriquerJoueur('i',"noir","noir");

        Assert.assertEquals(J.getClass().getName(),"Joueurs.Humain");
        Assert.assertEquals(IA.getClass().getName(),"Joueurs.IA");
    }
}