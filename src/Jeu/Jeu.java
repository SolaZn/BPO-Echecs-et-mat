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
        throws CoordInexistanteException, PieceNonMangeableException, PieceNonDetenueException, CoupHorsZoneDepException{
        if(Echiquier.coordExiste(coordInit) && Echiquier.coordExiste(coordArr)) {
            //Les coordonnées initiale et d'arrivé existe bien dans l'echiquier
            int idDepart = J.detientPiece(coordInit); //
            if(idDepart != -1) {
                char typePieceArr = Echiquier.coordOccupé(coordArr);
                if (IPiece.isMangeable(typePieceArr)) {
                    //La pièce d'arrivée est mangeable
                    // TODO: 01/05/2021 Un check pour si et seulement si une pièce est sur le chemin, vérifier si le coup ne passe pas par le chemin sans manger/s'arrêter avant
                    int idArrivee = J.detientPiece(coordArr);
                    if (idArrivee == -1)
                        return J.deplacerPiece(coordInit, coordArr);
                    else
                        throw new PieceNonDetenueException();
                }
                else
                    throw new PieceNonMangeableException();
            }
            else
                throw new PieceNonDetenueException();
        }
        return false;
    }

    public static boolean coupJoué(Échiquier Echiquier, String coup, Joueur J)
            throws CoordInexistanteException, PieceNonMangeableException, PieceNonDetenueException, CoupHorsZoneDepException {

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
            5. il regarde la liste des coups possibles pour son pion et le coup doit y être => TROP DUR pr l'instant -> voir juste deplacement possible => FAIT
            6. il verifie si il peut manger le pion d'arrivée le cas échéant => FAIT
            7. "il vérifie si la partie est finie par son coup" => A VOIR
        <- il retourne vrai ou faux si le coup est fait ou pas => FAIT
        <- coupJoue retourne le resultat et fait rejouer/arrête le jeu/continue le jeu selon les cas. => A VOIR, séparer en deux
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
                Appli.affichage("Coup illégal\nCoordonnées inexistantes");
                etatCoup = false;
                cd.printStackTrace(); // à enlever
            }catch(PieceNonMangeableException pm){
                Appli.affichage("Coup illégal\nPièce non mangeable");
                etatCoup = false;
                pm.printStackTrace(); // à enlever
            }catch(PieceNonDetenueException pn){
                Appli.affichage("Coup illégal\nPièce de départ non détenue\net/ou Pièce d'arrivée détenue");
                etatCoup = false;
                pn.printStackTrace(); // à enlever
            }catch(CoupHorsZoneDepException cz){
                Appli.affichage("Coup illégal\nCoup hors de la zone de déplacement de la pièce\nou coup sans bouger");
                etatCoup = false;
                cz.printStackTrace(); // à enlever
            }
        }
        while(!etatCoup);

        Echiquier.rafraichir(J1,J2);
    }

    public static void Partie(Joueur Blanc, Joueur Noir, Échiquier Echiquier){
        Appli.affichage(Echiquier.toString('a'));

        Blanc.dessinerPositions(Echiquier.getPlateau());
        Noir.dessinerPositions(Echiquier.getPlateau());
        Appli.affichage("C'est parti !");

        for(;;){
            Appli.affichage(Echiquier.toString('a'));
            Appli.affichage("C'est au Joueur BLANC de saisir un coup :");
            tourJoueur(Blanc, Noir, Echiquier);

            Appli.affichage(Echiquier.toString('b'));
            Appli.affichage("C'est au Joueur NOIR de saisir un coup :");
            tourJoueur(Noir, Blanc, Echiquier);

            // vérifier les conditions de fin de partie avant la fin de chaque tour
        }
    }

}
