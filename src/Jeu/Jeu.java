package Jeu;

import Application.Appli;
import Exceptions.Coordonnees.*;
import Exceptions.Pieces.*;
import Jeu.Interfaces.IJoueur;
import Jeu.Interfaces.IPiece;
import Pièces.Coordonnee;

import static Application.Appli.saisie;

public class Jeu {
    private static int nombreCoupsNonHostile = 0;
    enum etatTour{NORMAL, NULLE, ABANDON, YES, NO}

    /**
     * Empeche le developpeur d'instancier la classe jeu
     */
    private Jeu(){
        throw new UnsupportedOperationException("Cette classe n'est pas instantiable");
    }

    /**
     * Retourne une coordonnée initialisé a une ligne et une colonne donnée
     * @param c la colonne
     * @param l la ligne
     * @return la coordonnée
     * @throws FormatCoupIncorrectException
     */
    public static Coordonnee creationCoordCoup(char c, char l) throws FormatCoupIncorrectException {
        if(Character.isLetter(c) && Character.isDigit(l))
            return new Coordonnee((8 - Character.getNumericValue(l)), (Character.getNumericValue(Character.toLowerCase(c)) - 10));
        if(Character.isDigit(c) && Character.isDigit(l))
            return new Coordonnee(Character.getNumericValue(l), Character.getNumericValue(c));
        else
            throw new FormatCoupIncorrectException();
    }

    /**
     * Réinitialise le nombre de coups non hostile à 0
     */
    public static void setNbCoupsNonHostile(){
        nombreCoupsNonHostile = 0;
    }

    /**
     * Retourne vrai si le coup d'un joueur respecte les règles du jeu et reste dans les limites du plateau sinon faux
     * @param J le joueur qui joue le coup
     * @param J2 le joueur adverse
     * @param Echiquier le plateau de jeu
     * @param coordInit la coordonnée de départ du coup
     * @param coordArr la coordonnée d'arrivée du coup
     * @return vrai si le coup d'un joueur respecte les règles du jeu et reste dans les limites du plateau sinon faux
     * @throws CoordInexistanteException
     * @throws PieceNonMangeableException
     * @throws PieceNonDetenueException
     * @throws CoupHorsZoneDepException
     * @throws RoiEnSituationEchecException
     */
    private static boolean coupValide(IJoueur J, IJoueur J2, Échiquier Echiquier, Coordonnee coordInit, Coordonnee coordArr)
            throws CoordInexistanteException, PieceNonMangeableException, PieceNonDetenueException, CoupHorsZoneDepException,
            RoiEnSituationEchecException, RouteBarréeException {
        if(Échiquier.coordExiste(coordInit) && Échiquier.coordExiste(coordArr)) {
            //Les coordonnées initiale et d'arrivé existe bien dans l'échiquier
            int idDepart = J.detientPiece(coordInit); //
            if (idDepart != -1) {
                char typePieceArr = Echiquier.coordOccupé(coordArr);
                if (IPiece.isMangeable(typePieceArr)) {
                    // TODO: 01/05/2021 (UPDATE 02/05) => SEULE LA TOUR REGARDE LE RESULTAT DE CE CHECK, LES AUTRES NON - Un check pour si et seulement si une pièce est sur le chemin, vérifier si le coup ne passe pas par le chemin sans manger/s'arrêter avant
                    int idArrivee = J.detientPiece(coordArr);
                    if (idArrivee == -1) {
                        if(!J.barreRoute(coordInit, coordArr, Echiquier)) {
                            if (J.positionRoi().equals(coordInit)) {
                                if (!J2.essaiCoupHostile(coordArr)) {
                                    if (J.deplacerPiece(coordInit, coordArr)) {
                                        J2.perdrePiece(coordArr);
                                        return true;
                                    }
                                } else
                                    throw new RoiEnSituationEchecException();
                            } else {
                                if (!J2.essaiCoupHostile(J.positionRoi())) {
                                    if (J.deplacerPiece(coordInit, coordArr)) {
                                        J2.perdrePiece(coordArr);
                                        return true;
                                    }
                                } else
                                    throw new RoiEnSituationEchecException();
                            }
                        } else
                            throw new RouteBarréeException();
                    } else
                        throw new PieceNonDetenueException();
                } else
                    throw new PieceNonMangeableException();
            } else
                throw new PieceNonDetenueException();
        } else
            throw new CoordInexistanteException();
        return false;
    }

    /**
     * Retourne vrai si le coup d'un joueur respecte les règles du jeu et reste dans les limites du plateau sinon faux
     * et rend le coup exploitable par l'algorithme (String to Coordonnée)
     * @param Echiquier Le plateau de jeu
     * @param coup le coup joué
     * @param J le joueur qui joue le coup
     * @param J2 le joueur adverse
     * @return vrai si le coup d'un joueur respecte les règles du jeu et reste dans les limites du plateau sinon faux
     * @throws CoordInexistanteException
     * @throws PieceNonMangeableException
     * @throws PieceNonDetenueException
     * @throws CoupHorsZoneDepException
     * @throws FormatCoupIncorrectException
     * @throws RoiEnSituationEchecException
     */
    public static boolean coupJoué(Échiquier Echiquier, String coup, IJoueur J, IJoueur J2)
            throws CoordInexistanteException, PieceNonMangeableException, PieceNonDetenueException, CoupHorsZoneDepException,
            FormatCoupIncorrectException, RoiEnSituationEchecException, RouteBarréeException {

        if (coup.length() != 4) {
            throw new FormatCoupIncorrectException();
        }

        Coordonnee coordInit = creationCoordCoup(coup.charAt(0), coup.charAt(1));
        Coordonnee coordArr = creationCoordCoup(coup.charAt(2), coup.charAt(3));

        return coupValide(J, J2, Echiquier, coordInit, coordArr);
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

    /**
     * Lance une partie en mode Nulle, propose au joueur adverse si il souhaite rester sur un match nul
     * @param J1 le joueur adverse
     * @return la réponse du joueur adverse
     */
    private static etatTour partieNulle(IJoueur J1){
        // TODO: 03/05/2021 Retourne NULLE pour remettre le jeu dans le bon sens dans 2 tours (donc un tour dans le vent) (mais à voir pour faire mieux) + NO inutile si fait comme ça
            Appli.affichage("Le joueur " + J1.toString() + " propose un match nul, accepter : YES or NO");
            String saisieJoueur = saisie();
            if(saisieJoueur.toUpperCase().compareTo("YES") == 0){
                return etatTour.YES;
            }
            else{
                return etatTour.NULLE;
            }
    }

    /**
     * Retourne vrai si le joueur met le joueur adverse en situation d'echec et mat sinon faux
     * @param J1 le joueur
     * @param J2 le joueur adverse
     * @param positionRoiAdverse la pasition du roi adverse
     * @param Echiquier le plateau de jeu
     * @return vrai si le joueur met le joueur adverse en situation d'echec et mat sinon faux
     */
    private static boolean situationEchecMat(IJoueur J1, IJoueur J2, Coordonnee positionRoiAdverse, Échiquier Echiquier){
        int positionsPossibles = 9;
        int nbSituationsEchec = 0;
        for(int variationCol = - 1; variationCol < 2; variationCol++){
           for(int variationLigne = - 1; variationLigne < 2; variationLigne++) {
               try {
                   Coordonnee posPossRoiAdv = new Coordonnee(positionRoiAdverse.getLigne() + variationLigne,
                           positionRoiAdverse.getColonne() + variationCol);
                   if(Échiquier.coordExiste(posPossRoiAdv)) {
                       if(J1.essaiCoupHostile(posPossRoiAdv)){
                           /*if(variationCol == 0 && variationLigne == 0){
                               ->
                               J2.essaiCoupHostile(posPieceQuiPeutMangerLeRoi);
                           }
                           else (contreEchec > nbpionsmettantenechec)*/

                           nbSituationsEchec++;
                       }
                   }
               } catch (CoordInexistanteException ci) {
                   positionsPossibles -= 1;
               }
           }
        }

        if(nbSituationsEchec == positionsPossibles){ // si toutes les échappatoires du roi sont épuisées
            Echiquier.rafraichir(J1,J2);
            Appli.affichage("Echec et mat. \nVictoire joueur " + J1.toString());
            return true;
        }
        return false;
    }

    /**
     * Retourne vrai si il y a situation de pat entre les deux joueurs sinon faux
     * @param J1 le premier joueur
     * @param J2 le second joueur
     * @param Echiquier le plateau de jeu
     * @return vrai si il y a situation de pat entre les deux joueurs sinon faux
     */
    private static boolean situationPat(IJoueur J1, IJoueur J2, Échiquier Echiquier){
        // -> après un tour, si le joueur qui va jouer va forcément se mettre en échec, alors il y a pat
        if(J1.nombrePieces() == 1 && J2.nombrePieces() == 1){
            Appli.affichage("Match nul. \nAucun joueur ne peut gagner dans cette situation");
            Appli.affichage(Echiquier.getNbAffichage() - 2 + " coups joués");
            return true;
        }
        if(nombreCoupsNonHostile >= 50){
            Appli.affichage("Match nul. \n"+ nombreCoupsNonHostile + " coups sans prise ont été joués");
            return true;
        }
        return false;
    }

    /**
     * Réalise le tour du joueur dans le mode voulu
     * @param J1 le joueur jouant
     * @param J2 le joueur adverse
     * @param Echiquier le plateau
     * @param mode le mode dans lequel lancer le tour
     * @return l'etat voulu pour le prochain tour
     */
    private static etatTour tourJoueur(IJoueur J1, IJoueur J2, Échiquier Echiquier, etatTour mode) {
        if(mode == etatTour.NORMAL){
            Appli.affichage("C'est au Joueur " + J1.toString() + " de saisir un coup :");
            String saisieJoueur;
            boolean etatCoup;

            do {
                saisieJoueur = J1.joueCoup(Echiquier,J2);
                Appli.affichage(saisieJoueur); // renvoyer le coup vers le traitement
                if(saisieJoueur.toUpperCase().compareTo("ABANDON") == 0){
                    return etatTour.ABANDON;
                }
                else if(saisieJoueur.toUpperCase().compareTo("NULLE") == 0){
                    return partieNulle(J1);
                }

                try {
                    etatCoup = coupJoué(Echiquier, saisieJoueur, J1, J2);
                }catch(CoordInexistanteException cd){
                    Appli.affichage("Coup illégal\nCoordonnées inexistantes");
                    etatCoup = false;
                    // cd.printStackTrace(); // à enlever
                }catch(PieceNonMangeableException pm){
                    Appli.affichage("Coup illégal\nPièce non mangeable");
                    etatCoup = false;
                    // pm.printStackTrace(); // à enlever
                }catch(PieceNonDetenueException pn){
                    Appli.affichage("Coup illégal\nPièce de départ non détenue\net/ou Pièce d'arrivée détenue");
                    etatCoup = false;
                    // pn.printStackTrace(); // à enlever
                }catch(CoupHorsZoneDepException cz){
                    Appli.affichage("Coup illégal\nCoup hors de la zone de déplacement de la pièce\nou coup sans bouger");
                    etatCoup = false;
                    // cz.printStackTrace(); // à enlever
                }catch(FormatCoupIncorrectException fci){
                    Appli.affichage("Coup illégal\nLe format du coup est incorrect");
                    etatCoup = false;
                }catch(RoiEnSituationEchecException rse) {
                    Appli.affichage("Le Roi " + J1.toString() + " serait\nen situation d'échec");
                    etatCoup = false;
                    // rse.printStackTrace();
                }catch (RouteBarréeException rb){
                    Appli.affichage("Coup invalide\nUne pièce adverse/alliée se trouve entre la coordonnée de départ et la coordonnée d'arrivée");
                    etatCoup = false;
                }
            }
            while(!etatCoup);

            Echiquier.rafraichir(J1,J2);
            return etatTour.NORMAL;
        }
        return etatTour.NORMAL;
    }

    /**
     * Réalise la partie entre les deux joueurs
     * @param Blanc le premier joueur
     * @param Noir le seconde joueur
     * @param Echiquier le plateau de jeu
     */
    public static void Partie(IJoueur Blanc, IJoueur Noir, Échiquier Echiquier){
        Appli.affichage(Echiquier.toString('a'));

        Blanc.dessinerPositions(Echiquier.getPlateau());
        Noir.dessinerPositions(Echiquier.getPlateau());
        Appli.affichage("C'est parti !");
        etatTour etatTour = Jeu.etatTour.NORMAL;
        for(;;){
            Appli.affichage(Echiquier.toString('a'));
            etatTour = tourJoueur(Blanc, Noir, Echiquier, etatTour);
            ++nombreCoupsNonHostile;
            if(etatTour == Jeu.etatTour.ABANDON || etatTour == Jeu.etatTour.YES){
                break;
            }
            if(situationEchecMat(Blanc, Noir, Noir.positionRoi(), Echiquier) || situationPat(Blanc, Noir, Echiquier)){
                Appli.affichage(Echiquier.toString('b'));
                break;
            }

            Appli.affichage(Echiquier.toString('b'));
            etatTour = tourJoueur(Noir, Blanc, Echiquier, etatTour);
            ++nombreCoupsNonHostile;
            if(etatTour == Jeu.etatTour.ABANDON || etatTour == Jeu.etatTour.YES){
                break;
            }
            if(situationEchecMat(Noir, Blanc, Blanc.positionRoi(), Echiquier) || situationPat(Noir, Blanc, Echiquier)){
                Appli.affichage(Echiquier.toString('a'));
                break;
            }
            // vérifier les conditions de fin de partie avant la fin de chaque tour
        }
    }

}
