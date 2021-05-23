package Joueurs.tests;

import Exceptions.Coordonnees.CoordInexistanteException;
import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Jeu.*;
import Jeu.Interfaces.IJoueur;
import org.junit.Assert;
import org.junit.Test;

import static Jeu.Échiquier.coordExiste;

public class IATest {

    @Test
    public void joueCoup() {
        //Note : nous ne pouvons tester joueCoup pour un humain, cette implémentation
        // étant trop triviale.

        // Sachant qu'on a une IA et un échiquier sur lequel elle joue
        IJoueur IA1 = IJoueur.getJoueur('i',"blanc","blanc");
        IJoueur IA2 = IJoueur.getJoueur('i',"noir","noir");
        Échiquier Echiquier = new Échiquier();

        // Cette IA peut donner un coup à jouer en fonction de sa situation
        String coupJoué = IA1.joueCoup(Echiquier, IA2);

        // Ce coup sera forcément au format lettreCHIFFRElettreCHIFFRE
        Assert.assertTrue(Character.isLetter(coupJoué.charAt(0)));
        Assert.assertTrue(Character.isDigit(coupJoué.charAt(1)));
        Assert.assertTrue(Character.isLetter(coupJoué.charAt(2)));
        Assert.assertTrue(Character.isDigit(coupJoué.charAt(3)));

        try {
            // et pour chaque String coupJoué, on pourra vérifier que les coordonnées
            // correspondantes existent bien dans l'échiquier
            Assert.assertTrue(coordExiste(Jeu.creationCoordCoup(coupJoué.charAt(0), coupJoué.charAt(1))));
            Assert.assertTrue(coordExiste(Jeu.creationCoordCoup(coupJoué.charAt(2), coupJoué.charAt(3))));
        } catch (FormatCoupIncorrectException | CoordInexistanteException e) {
            throw new RuntimeException("Cela ne devrait pas arriver.");
        }

    }
}