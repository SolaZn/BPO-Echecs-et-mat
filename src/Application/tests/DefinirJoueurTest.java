package Application.tests;

import Application.IJoueur;
import org.junit.Assert;
import org.junit.Test;

public class DefinirJoueurTest {

    @Test
    public void fabriquerJoueur() {
        IJoueur J = IJoueur.getJoueur('h',"blanc","blanc");
        IJoueur IA = IJoueur.getJoueur('i',"noir","noir");

        Assert.assertEquals(J.getClass().getName(),"Joueurs.Humain");
        Assert.assertEquals(IA.getClass().getName(),"Joueurs.IA");
    }
}