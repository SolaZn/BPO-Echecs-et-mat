package Jeu;

import Application.Appli;
import Joueurs.Joueur;
import Pièces.Coordonnee;

import java.util.Arrays;

import static Application.Appli.saisie;

public class Jeu {

    public static Coordonnee creationCoordCoup(char l, char c){
        return new Coordonnee((Character.getNumericValue(Character.toLowerCase(l)) - 10), (Character.getNumericValue(c) - 1));
        //Coordonnee coordInit = new Coordonnee((Character.getNumericValue(Character.toLowerCase(coup.charAt(0))) - 10), (Character.getNumericValue(coup.charAt(1)) - 1));
        //Coordonnee coordArr = new Coordonnee((Character.getNumericValue(Character.toLowerCase(coup.charAt(2))) - 10), (Character.getNumericValue(coup.charAt(3)) - 1));
    }

    public static boolean coupJoué(Échiquier Echiquier, String coup) {

        if (coup.length() != 4) {
            Appli.affichage("Coup illégal");
            return false;
        }

        /*String[] tab = coup.split("(?<=\\G..)");
        // remplaçable par coupCoord
        for (String s : tab) {
            String idligne = String.valueOf(s.charAt(0));
            for (String ch : listePositions) {
                if (ch.equals(idligne)) {
                    Appli.affichage(s);
                    if (Echiquier.coordExiste(new Coordonnee(positions.valueOf(idligne).ordinal() + 1, s.charAt(1)))) { // deplacer dans echiquier{
                        return true;
                        // verifier si une piece est dessus ou pas -> transmettre le type au joueur le cas échéant
                        // envoyer la requete au joueur de deplacer son pion selon ses règles
                        // si le coup a reussi (joueur retourne true), retourner true aussi
                    } else {
                        Appli.affichage("Coup illégal");
                        return false;
                    }
                }
            }
        }
        return false;

         */

        /*
        Pour qu'une coordonnée soit valide il suffit qu'elle soit presente dans l'echiquier c'est a dire
        qu'elle soit (0>= l < 8, 0>= c < 8)
         */
        Coordonnee coordInit = creationCoordCoup(coup.charAt(0), coup.charAt(1));
        Coordonnee coordArr = creationCoordCoup(coup.charAt(2), coup.charAt(3));
        if(Echiquier.coordExiste(coordInit) && Echiquier.coordExiste(coordArr)){
            //Les coordonnées initiale et d'arrivé existe bien dans l'echiquier
            return true;
        }
        return false;
    }

    /* 1. on crée le coup en terme de coordonnees de depart, d'arrivée => Fait
        2. on verifie si le coup part de l'échiquier et reste dans l'échiquier (avec Coord) => Fait
        3. on verifie si il y a une pièce au depart, si il y a une piece à la fin (avec Coord et Echiquier) => Fait
        4. on verifie si on peut manger la pièce tout court (pour le Roi) (avec Echiquier)
        -> on transmet l'ordre de déplacement au joueur pour qu'il verifie selon ses règles de déplacement (on transmet Coord, Echiquier...)
            5. il regarde la liste des coups possibles pour son pion et le coup doit y être
            6. il verifie si il peut manger le pion d'arrivée le cas échéant
            7. "il vérifie si la partie est finie par son coup"
        <- il retourne vrai ou faux si le coup est fait ou pas
        <- coupJoue retourne le resultat et fait rejouer/arrête le jeu/continue le jeu selon les cas.
     */

    public static void Partie(Joueur Blanc, Joueur Noir, Échiquier Echiquier){
        Appli.affichage(Echiquier.toString());
        Blanc.dessinerPositions(Echiquier.getPlateau());

        Appli.affichage(Echiquier.toString());
        Noir.dessinerPositions(Echiquier.getPlateau());

        Appli.affichage(Echiquier.toString());

        for(;;){
            String saisieJoueur;
            Appli.affichage("C'est au joueur blanc de saisir un coup :");
            saisieJoueur = saisie();
            Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement

            coupJoué(Echiquier, saisieJoueur);

            Appli.affichage(Echiquier.toString());

            Appli.affichage("C'est au joueur noir de saisir un coup :");
            saisieJoueur = saisie();
            Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement

            coupJoué(Echiquier, saisieJoueur);

            Appli.affichage(Echiquier.toString());
            // Ne pas oublier de rafraichir le tableau pour que le nouveau tour contienne seulement les nouvelles
            // positions de l'échiquier : Echiquier.rafraichir()
        }
    }

}
