package Jeu;

import Application.Appli;
import Joueurs.Joueur;
import Pièces.Coordonnee;

import java.util.Arrays;

public class Jeu {
    public enum positions {
        a, b, c, d, e, f, g;
    }

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    public static boolean coupJoué(Échiquier Echiquier, String coup) {//Coordonnee coordInit, Coordonnee coordArr){
        // Qui regarde les conditions de jeu dans quelle classe cette fonction doit etre mise

        String[] listePositions = getNames(positions.class);

        if (coup.length() != 4) {
            Appli.affichage("Coup illégal");
            return false;
        }

        String[] tab = coup.split("(?<=\\G..)");
        // remplaçable par coupCoord
        for (String s : tab) {
            String idligne = String.valueOf(s.charAt(0));
            for (String ch : listePositions) {
                if (ch.equals(idligne)) {
                    Appli.affichage(s);
                    if (coordExiste(new Coordonnee(positions.valueOf(idligne).ordinal() + 1, s.charAt(1)), Echiquier)) { // deplacer dans echiquier{
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
    }

    /* 1. on crée le coup en terme de coordonnees de depart, d'arrivée
        2. on verifie si le coup part de l'échiquier et reste dans l'échiquier (avec Coord)
        3. on verifie si il y a une pièce au depart, si il y a une piece à la fin (avec Coord et Echiquier)
        4. on verifie si on peut manger la pièce tout court (pour le Roi) (avec Echiquier)
        -> on transmet l'ordre de déplacement au joueur pour qu'il verifie selon ses règles de déplacement (on transmet Coord, Echiquier...)
            5. il regarde la liste des coups possibles pour son pion et le coup doit y être
            6. il verifie si il peut manger le pion d'arrivée le cas échéant
            7. "il vérifie si la partie est finie par son coup"
        <- il retourne vrai ou faux si le coup est fait ou pas
        <- coupJoue retourne le resultat et fait rejouer/arrête le jeu/continue le jeu selon les cas.
     */



    public static boolean coordExiste(Coordonnee coord, Échiquier Echiquier){
        // verifier si les coordonnes sont dans l'echiquier
        Appli.affichage(coord.toString());
        if(coord.getLigne() == 1)
            return true;
        return false;
    }

    public static void Partie(Joueur Blanc, Joueur Noir, Échiquier Echiquier){
        Appli.affichage(Echiquier.toString());
        Blanc.dessinerPositions(Echiquier.getPlateau());

        Appli.affichage(Echiquier.toString());
        Noir.dessinerPositions(Echiquier.getPlateau());

        Appli.affichage(Echiquier.toString());

        for(;;){
            String saisieJoueur;
            Appli.affichage("C'est au joueur blanc de saisir un coup :");
            saisieJoueur = Appli.saisie();
            Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement

            coupJoué(Echiquier, saisieJoueur);

            Appli.affichage(Echiquier.toString());

            Appli.affichage("C'est au joueur noir de saisir un coup :");
            saisieJoueur = Appli.saisie();
            Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement

            coupJoué(Echiquier, saisieJoueur);

            Appli.affichage(Echiquier.toString());
            // Ne pas oublier de rafraichir le tableau pour que le nouveau tour contienne seulement les nouvelles
            // positions de l'échiquier : Echiquier.rafraichir()
        }
    }

}
