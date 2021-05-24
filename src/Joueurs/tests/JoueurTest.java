package Joueurs.tests;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Exceptions.Coordonnees.FormatCoupIncorrectException;
import Application.IJoueur;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import org.junit.Assert;
import org.junit.Test;

public class JoueurTest {

    @Test
    public void dessinerPositions() {
        // Sachant qu'on a un joueur et un échiquier sur lequel il joue
        IJoueur J1 = IJoueur.getJoueur('h',"blanc","blanc");
        Échiquier Echiquier = new Échiquier();

        // Si le joueur n'a pas dessiné ses positions, l'échiquier est vide aux cases
        // correspondantes
        Assert.assertEquals(Echiquier.getPlateau()[2][4], ' ');
        Assert.assertEquals(Echiquier.getPlateau()[1][1], ' ');

        // Alors, le joueur dessine ses positions sur l'échiquier
        J1.dessinerPositions(Echiquier.getPlateau());

        // Donc, l'échiquier comporte désormais le nom des pièces du joueur aux
        // cases correspondantes
        Assert.assertEquals(Echiquier.getPlateau()[2][4], 'R');
        Assert.assertEquals(Echiquier.getPlateau()[1][1], 'T');
    }

    @Test
    public void deplacerPiece() throws CoupHorsZoneDepException {
        // Sachant qu'on a un joueur et un échiquier sur lequel il joue
        IJoueur J1 = IJoueur.getJoueur('h',"blanc","blanc");
        Échiquier Echiquier = new Échiquier();
        Echiquier.rafraichir(J1, J1);

        // Si le joueur BLANC vient de commencer à jouer, alors son roi se situe à l'emplacement
        // e6 sur l'échiquier du jeu (coordonnée 2,4) et la coordonnée 3,4 est vide
        Assert.assertEquals(Echiquier.coordOccupé(new Coordonnee(2,4)),'R');
        Assert.assertEquals(Echiquier.coordOccupé(new Coordonnee(3,4)),' ');

        // Alors, le joueur peut déplacer son pion de 2,4 vers 3,4 en tenant compte des règles
        // de déplacement
        J1.deplacerPiece(new Coordonnee(2,4), new Coordonnee(3,4));

        // En remettant donc à jour l'affichage de l'échiquier...
        Echiquier.rafraichir(J1, J1);

        // On peut DONC voir que la position du roi du joueur BLANC a été modifiée et est passée
        // de 2,4 à 3,4
        Assert.assertEquals(Echiquier.coordOccupé(new Coordonnee(2,4)),' ');
        Assert.assertEquals(Echiquier.coordOccupé(new Coordonnee(3,4)),'R');
    }

    @Test
    public void detientPiece() {
        // Sachant qu'on a un joueur et un échiquier sur lequel il joue
        IJoueur J1 = IJoueur.getJoueur('h',"blanc","blanc");
        Échiquier Echiquier = new Échiquier();
        Echiquier.rafraichir(J1, J1);

        // Si le joueur BLANC vient de commencer à jouer, alors il possède deux pièces, dont la
        // seconde est une pièce de type Tour, située en b7
        Assert.assertEquals(J1.detientPiece(new Coordonnee(1,1)), 1);
        // NDLR: detientPiece retourne l'id de la pièce dans la liste des pièces du joueur

        // En revanche, si le joueur BLANC vient de commencer à jouer, il ne possède pas la
        // pièce adverse située en e8
        Assert.assertEquals(J1.detientPiece(new Coordonnee(0,4)), -1);
        // NDLR: detientPiece retourne -1 si la pièce n'existe pas dans la liste du joueur
    }

    @Test
    public void essaiCoupHostile() throws FormatCoupIncorrectException {
        // Sachant qu'on a deux joueurs et un échiquier sur lequel ils jouent
        IJoueur J1 = IJoueur.getJoueur('h',"blanc","blanc");
        IJoueur J2 = IJoueur.getJoueur('h',"noir","noir");
        Échiquier Echiquier = new Échiquier();
        Echiquier.rafraichir(J1, J1);

        // Si le joueur NOIR et le joueur BLANC viennent d'être initialisés,
        // leurs rois ne peuvent s'attendre mutuellement
        Assert.assertFalse(J1.essaiCoupHostile(J2.positionRoi(), Echiquier));
        Assert.assertFalse(J2.essaiCoupHostile(J1.positionRoi(), Echiquier));

        // Cependant, supposons que le roi BLANC souhaite se déplacer en e7,
        // il se mettrait en position d'échec et DONC l'adverse pourrait l'y attaquer
        // essaiCoupHostile serait donc vrai
        Assert.assertTrue(J2.essaiCoupHostile(Jeu.Jeu.creationCoordCoup('e','7'), Echiquier));
    }

    @Test
    public void perdrePiece() throws FormatCoupIncorrectException, CoupHorsZoneDepException {
        IJoueur J1 = IJoueur.getJoueur('h',"blanc","blanc");
        IJoueur J2 = IJoueur.getJoueur('h',"noir","noir");
        Échiquier echiquier = new Échiquier();

        Coordonnee coordInit =  Jeu.Jeu.creationCoordCoup('b', '7');
        Coordonnee coordArr = Jeu.Jeu.creationCoordCoup('c', '7');
        J1.deplacerPiece(coordInit, coordArr);

        echiquier.rafraichir(J1,J2);

        coordInit =  Jeu.Jeu.creationCoordCoup('c', '5');
        coordArr = Jeu.Jeu.creationCoordCoup('c', '7');
        J1.perdrePiece(coordArr);
        Assert.assertEquals( -1, J1.detientPiece(coordArr));
        Assert.assertEquals(1, J1.nombrePieces());
    }

    @Test
    public void routeBarree() throws CoupHorsZoneDepException, FormatCoupIncorrectException {
        IJoueur J1 = IJoueur.getJoueur('h',"blanc","blanc");
        IJoueur J2 = IJoueur.getJoueur('h',"noir","noir");
        Échiquier echiquier = new Échiquier();

        Coordonnee coordInit =  Jeu.Jeu.creationCoordCoup('b', '7');
        Coordonnee coordArr = Jeu.Jeu.creationCoordCoup('c', '7');
        J1.deplacerPiece(coordInit, coordArr);

        echiquier.rafraichir(J1,J2);

        coordInit =  Jeu.Jeu.creationCoordCoup('c', '5');
        coordArr = Jeu.Jeu.creationCoordCoup('c', '8');
        Assert.assertTrue(J2.barreRoute(coordInit, coordArr, echiquier));
    }
}