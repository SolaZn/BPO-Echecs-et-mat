package Joueurs;

import Application.IJoueur;
import Jeu.IPiece;

import Jeu.Échiquier;
import Pièces.*;

import java.util.LinkedList;
import java.util.Random;

/**
 * Cette classe représente une IA dans le programme de finales d'échecs.
 * Cette IA a un nom et a à sa disposition la liste de ses pièces;
 * Pièces qu'elle manipulera au cours de sa partie.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony ZAKANI
 */
public class IA extends Joueur implements IJoueur{

    /**
     * Construit une IA comme sous-type d'un joueur
     * @param couleur la couleur des pièces de l'IA
     * @param nomJoueur le nom de l'IA
     */
    public IA(String couleur, String nomJoueur) {
        super(couleur, nomJoueur);
    }

    /**
     * Cette implémentation génère, à l'aide d'un algorithme, un coup qui est
     * renvoyé sous forme de chaîne de caractères, dans le même format qu'un joueur
     * humain
     * @see IJoueur#joueCoup(Échiquier, IJoueur)
     */
    @Override
    public String joueCoup(Échiquier Echiquier, IJoueur J2){
        Random rdm = new Random();
        LinkedList<IPiece> listePieces= super.getListePieces();
        int idx = rdm.nextInt(listePieces.size());
        LinkedList<Coordonnee> listeCoups = listePieces.get(idx).getCoupsPossibles();

        StringBuilder coordonneeJouee = new StringBuilder();
        Coordonnee coordDeDepart = listePieces.get(idx).getCoord();

        for(;;) {
            Random r = new Random();
            if(listeCoups.isEmpty())
                throw new RuntimeException("Ceci n'est pas censé se produire");

            int idy = r.nextInt(listeCoups.size());
            Coordonnee coordAJouer = listeCoups.get(idy);

            if (!this.barreRoute(coordDeDepart, coordAJouer, Echiquier) && !coordDeDepart.equals(coordAJouer)) {
                if ((!J2.essaiCoupHostile(coordAJouer, Echiquier) && Character.toUpperCase(Echiquier.coordOccupé(coordDeDepart)) == 'R') ||
                        Character.toUpperCase(Echiquier.coordOccupé(coordDeDepart)) != 'R') {
                    char colonneDep = Character.forDigit(coordDeDepart.getColonne() + 10, 36);
                    int ligneDep = 8 - coordDeDepart.getLigne();

                    char colonneArr = Character.forDigit(coordAJouer.getColonne() + 10, 36);
                    int ligneArr = 8 - coordAJouer.getLigne();

                    coordonneeJouee.append(colonneDep).append(ligneDep).append(colonneArr).append(ligneArr);
                    break;
                } else {
                    listeCoups.remove(idy);
                }
            } else {
                listeCoups.remove(idy);
            }
        }

        return String.valueOf(coordonneeJouee);
    }
}
