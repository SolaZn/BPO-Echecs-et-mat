package Jeu;

import Application.Appli;
import Exceptions.*;
import Joueurs.Joueur;
import Pièces.Coordonnee;

import static Application.Appli.saisie;

public class Jeu {

    private Jeu(){
        throw new UnsupportedOperationException("Cette classe n'est pas instanciable");
    }

    public static Coordonnee creationCoordCoup(char c, char l){
        return new Coordonnee((8 - Character.getNumericValue(l)), (Character.getNumericValue(Character.toLowerCase(c)) - 10));
    }

    private static boolean coupValide(Joueur J, Échiquier Echiquier, Coordonnee coordInit, Coordonnee coordArr)
        throws CoordInexistanteException, PieceNonMangeableException, PieceNonDetenueException{
        if(Echiquier.coordExiste(coordInit) && Echiquier.coordExiste(coordArr)) {
            //Les coordonnées initiale et d'arrivé existe bien dans l'echiquier
            char typePieceArr = Echiquier.coordOccupé(coordArr);
            if (IPiece.isMangeable(typePieceArr)) {
                //La pièce d'arrivée est mangeable
                int idDepart = J.detientPiece(coordInit);
                int idArrivee = J.detientPiece(coordArr);
                if(idDepart != -1 && idArrivee == -1)
                    return J.deplacerPiece(coordInit, coordArr);
                //Le joueur a pu déplacer sa piece en toute securité
                else
                    throw new PieceNonDetenueException();
            }
            else
                throw new PieceNonMangeableException();
        }
        return false;
    }

    public static boolean coupJoué(Échiquier Echiquier, String coup, Joueur J)
            throws CoordInexistanteException, PieceNonMangeableException, PieceNonDetenueException {

        if (coup.length() != 4) {
            throw new CoordInexistanteException();
        }

        Coordonnee coordInit = creationCoordCoup(coup.charAt(0), coup.charAt(1));
        Coordonnee coordArr = creationCoordCoup(coup.charAt(2), coup.charAt(3));

        return coupValide(J, Echiquier, coordInit, coordArr);
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

    private static void tourJoueur(Joueur J1, Joueur J2, Échiquier Echiquier) {
        String saisieJoueur;
        boolean etatCoup;
        do {
            saisieJoueur = saisie();
            Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement

            try {
                etatCoup = coupJoué(Echiquier, saisieJoueur, J1);
            }catch(CoordInexistanteException cd){
                Appli.affichage("Coup illégal\nCoordonnees inexistantes");
                etatCoup = false;
                cd.printStackTrace(); // à enlever
            }catch(PieceNonMangeableException pm){
                Appli.affichage("Coup illégal\nPiece non mangeable");
                etatCoup = false;
                pm.printStackTrace(); // à enlever
            }catch(PieceNonDetenueException pn){
                Appli.affichage("Coup illégal\nPièce de départ non détenue\net/ou Pièce d'arrivée détenue");
                etatCoup = false;
                pn.printStackTrace();
            }
        }
        while(!etatCoup);

        Echiquier.rafraichir(J1,J2);
        Appli.affichage(Echiquier.toString());
    }

    public static void Partie(Joueur Blanc, Joueur Noir, Échiquier Echiquier){
        Appli.affichage(Echiquier.toString());

        Blanc.dessinerPositions(Echiquier.getPlateau());
        Noir.dessinerPositions(Echiquier.getPlateau());
        Appli.affichage(Echiquier.toString());
        Appli.affichage("C'est parti !");

        for(;;){
            Appli.affichage("C'est au Joueur BLANC de saisir un coup :");
            tourJoueur(Blanc, Noir, Echiquier);

            Appli.affichage("C'est au Joueur NOIR de saisir un coup :");
            tourJoueur(Noir, Blanc, Echiquier);
        }
    }

}
