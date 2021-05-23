package Joueurs.tests;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Jeu.Échiquier;
import Joueurs.Joueur;
import Pièces.Coordonnee;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoueurTest {

    @Test
    public void dessinerPositions() {
        // Sachant qu'on a un joueur et un échiquier sur lequel il joue
        Joueur J1 = new Joueur("blanc","blanc");
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
    public void deplacerPiece() {
        // Sachant qu'on a un joueur et un échiquier sur lequel il joue
        Joueur J1 = new Joueur("blanc","blanc");
        Échiquier Echiquier = new Échiquier();
        Echiquier.rafraichir(J1, J1);

        // Si le joueur BLANC vient de commencer à jouer, alors son roi se situe à l'emplacement
        // e6 sur l'échiquier du jeu (coordonnée 2,4) et la coordonnée 3,4 est vide
        Assert.assertEquals(Echiquier.coordOccupé(new Coordonnee(2,4)),'R');
        Assert.assertEquals(Echiquier.coordOccupé(new Coordonnee(3,4)),' ');

        // Alors, le joueur peut déplacer son pion de 2,4 vers 3,4 en tenant compte des règles
        // de déplacement
        try {
            J1.deplacerPiece(new Coordonnee(2,4), new Coordonnee(3,4));
        } catch (CoupHorsZoneDepException e) {
            System.out.println("Cet affichage n'arrivera pas");
        }

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
        Joueur J1 = new Joueur("blanc","blanc");
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
    public void essaiCoupHostile() {
    }

    @Test
    public void perdrePiece() {
    }

    @Test
    public void barreRoute() {
    }

}