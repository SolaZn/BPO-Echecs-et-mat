package Jeu;

import Application.Appli;
import Exceptions.*;
import Joueurs.Joueur;
import Pièces.Coordonnee;

import static Application.Appli.saisie;

public class Jeu {

    public static Coordonnee creationCoordCoup(char l, char c){
        return new Coordonnee((Character.getNumericValue(Character.toLowerCase(l)) - 10), (Character.getNumericValue(c) - 1));
        //Coordonnee coordInit = new Coordonnee((Character.getNumericValue(Character.toLowerCase(coup.charAt(0))) - 10), (Character.getNumericValue(coup.charAt(1)) - 1));
        //Coordonnee coordArr = new Coordonnee((Character.getNumericValue(Character.toLowerCase(coup.charAt(2))) - 10), (Character.getNumericValue(coup.charAt(3)) - 1));
    }

    public static boolean coupJoué(Échiquier Echiquier, String coup, Joueur joueur)
            throws CoordInexistanteException, PieceNonMangeableException {

        if (coup.length() != 4) {
            throw new CoordInexistanteException();
        }

        /*
        Pour qu'une coordonnée soit valide il suffit qu'elle soit presente dans l'echiquier c'est a dire
        qu'elle soit (0>= l < 8, 0>= c < 8)
         */
        Coordonnee coordInit = creationCoordCoup(coup.charAt(0), coup.charAt(1));
        Coordonnee coordArr = creationCoordCoup(coup.charAt(2), coup.charAt(3));
        Appli.affichage(coordArr.toString());
        if(Echiquier.coordExiste(coordInit) && Echiquier.coordExiste(coordArr)) {
            //Les coordonnées initiale et d'arrivé existe bien dans l'echiquier
            char typePieceArr = Echiquier.coordOccupé(coordArr);
            if (IPiece.isMangeable(typePieceArr)) {
                //La pièce d'arrivée est mangeable
                return joueur.deplacerPiece(Echiquier.getPlateau(), coordInit, coordArr, typePieceArr);
                //Le joueur a pu déplacer sa piece en toute securité
            }
            else
                throw new PieceNonMangeableException();
        }
        return false;
    }

    /* 1. on crée le coup en terme de coordonnees de depart, d'arrivée => Fait
        2. on verifie si le coup part de l'échiquier et reste dans l'échiquier (avec Coord) => Fait
        3. on verifie si il y a une pièce au depart, si il y a une piece à la fin (avec Coord et Echiquier) => Fait
        4. on verifie si on peut manger la pièce tout court (pour le Roi) (avec Echiquier) => Fait
        -> on transmet l'ordre de déplacement au joueur pour qu'il verifie selon ses règles de déplacement (on transmet Coord, Echiquier...)
            5. il regarde la liste des coups possibles pour son pion et le coup doit y être => TROP DUR pr l'instant -> voir juste deplacement possible
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
        Appli.affichage("C'est parti !");

        for(;;){
            String saisieJoueur;
            boolean etatCoup;

            Appli.affichage("C'est au Joueur BLANC de saisir un coup :");

            do {
                saisieJoueur = saisie();
                Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement
                try {
                    etatCoup = coupJoué(Echiquier, saisieJoueur, Blanc);
                }catch(CoordInexistanteException cd){
                    Appli.affichage("Coup illégal\nCoordonnees inexistantes");
                    etatCoup = false;
                    cd.printStackTrace();
                }
                catch(PieceNonMangeableException pm){
                    Appli.affichage("Coup illégal\nPiece non mangeable");
                    etatCoup = false;
                    pm.printStackTrace();
                }
            }
            while(!etatCoup);
            // Ne pas oublier de rafraichir le tableau pour que le nouveau tour contienne seulement les nouvelles
            // positions de l'échiquier : Echiquier.rafraichir()
            Echiquier.rafraichir();
            Blanc.dessinerPositions(Echiquier.getPlateau());

            Appli.affichage(Echiquier.toString());

            Appli.affichage("C'est au Joueur NOIR de saisir un coup :");
            do {
                saisieJoueur = saisie();
                Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement

                try {
                    etatCoup = coupJoué(Echiquier, saisieJoueur, Noir);
                }catch(CoordInexistanteException cd){
                    Appli.affichage("Coup illégal\nCoordonnees inexistantes");
                    etatCoup = false;
                    cd.printStackTrace();
                }
                catch(PieceNonMangeableException pm){
                    Appli.affichage("Coup illégal\nPiece non mangeable");
                    etatCoup = false;
                    pm.printStackTrace();
                }
            }
            while(!etatCoup);
            Noir.dessinerPositions(Echiquier.getPlateau());

            Appli.affichage(Echiquier.toString());

        }
    }

}
