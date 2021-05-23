package Joueurs.tests;

import Exceptions.Coordonnees.CoordInexistanteException;
import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Jeu.*;
import Joueurs.IA;
import org.junit.Assert;
import org.junit.Test;

import static Jeu.Échiquier.coordExiste;

public class IATest {

    @Test
    public void joueCoup() {
        // Sachant qu'on a une IA et un échiquier sur lequel elle joue
        IA IA1 = new IA("blanc","blanc");
        IA IA2 = new IA("noir", "noir");
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

    @Test
    public void barreRoute() {
    }
}