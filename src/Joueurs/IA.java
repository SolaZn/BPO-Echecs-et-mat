package Joueurs;

import Jeu.Interfaces.IJoueur;
import Jeu.Jeu;

import Jeu.Échiquier;
import Pièces.*;

import java.util.LinkedList;
import java.util.Random;

public class IA extends Joueur implements IJoueur{

    public IA(String couleur, String nomJoueur) {
        super(couleur, nomJoueur);
    }

    public String joueCoup(Échiquier Echiquier, IJoueur J2){
        Random rdm = new Random();
        int idx = rdm.nextInt(this.listePieces.size());
        LinkedList<Coordonnee> listeCoups = listePieces.get(idx).getCoupPossible();

        StringBuilder coordonneeJouee = new StringBuilder();
        Coordonnee coordDeDepart = listePieces.get(idx).getCoord();

        for(;;) {
            Random r = new Random();
            if(listeCoups.isEmpty())
                throw new RuntimeException("C'est la merde");
            int idy = r.nextInt(listeCoups.size());
            Coordonnee coordAJouer = listeCoups.get(idy);

            if ((!J2.essaiCoupHostile(coordAJouer) && Character.toUpperCase(Echiquier.coordOccupé(coordDeDepart)) == 'R') ||
                    Character.toUpperCase(Echiquier.coordOccupé(coordDeDepart)) != 'R') {
                char colonneDep = Character.forDigit(coordDeDepart.getColonne() + 10, 36);
                int ligneDep = 8 - coordDeDepart.getLigne();

                char colonneArr = Character.forDigit(coordAJouer.getColonne() + 10, 36);
                int ligneArr = 8 - coordAJouer.getLigne();

                coordonneeJouee.append(colonneDep).append(ligneDep).append(colonneArr).append(ligneArr);
                break;
            }
            else{
                listeCoups.remove(idy);
            }
        }

        return String.valueOf(coordonneeJouee);
    }
}
