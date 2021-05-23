package Joueurs.tests;

import Jeu.Interfaces.IJoueur;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefinirJoueurTest {

    @Test
    public void fabriquerJoueur() {
        IJoueur J = IJoueur.getJoueur('h',"blanc","blanc");
        IJoueur IA = IJoueur.getJoueur('i',"noir","noir");

        Assert.assertEquals(J.getClass().getName(),"Joueurs.Humain");
        Assert.assertEquals(IA.getClass().getName(),"Joueurs.IA");
    }
}