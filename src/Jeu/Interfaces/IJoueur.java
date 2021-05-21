package Jeu.Interfaces;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Jeu.Échiquier;
import Pièces.Coordonnee;

/**
 * Cette interface représente l'ensemble des méthodes devant être implémentées
 * par des classes correspondant à des joueurs du programme.
 */
public interface IJoueur {
    /**
     * Demande à l'utilisateur de renseigner le coup qu'il souhaite jouer
     * @param Echiquier l'échiquier sur lequel le coup sera joué
     * @param j2 le joueur adverse
     * @return la chaîne de caractères (String) correspondant au coup joué
     */
    String joueCoup(Échiquier Echiquier, IJoueur j2);

    /**
     * Permet à un joueur de renseigner la position de ses pièces sur l'échiquier
     * @param Plateau la représentation en tableau de caractères de l'échiquier
     */
    void dessinerPositions(char[][] Plateau);

    /**
     * Effectue le déplacement d'une pièce d'une coordonnée A à une coordonnée B
     * @param coordInit la coordonnée de départ du déplacement
     * @param coordArr la coordonnée d'arrivée du déplacement
     * @return vrai si le déplacement est effectué, faux si il n'a pu l'être
     * @throws CoupHorsZoneDepException
     */
    boolean deplacerPiece(Coordonnee coordInit, Coordonnee coordArr) throws CoupHorsZoneDepException;

    /**
     * Retourne le rang de la pièce du joueur correspondant aux coordonnées renseignées
     * @param coord la coordonnée où doit se situer la pièce recherchée
     * @return le rang (int) si la pièce est trouvée, -1 si aucune pièce ne correspond à la recherche
     */
    int detientPiece(Coordonnee coord);

    /**
     * Retourne la position du Roi du joueur concerné
     * @return la coordonnée (Coordonnee) du Roi du joueur
     */
    Coordonnee positionRoi();

    /**
     * Teste pour une coordonnée donnée la possibilité pour le joueur d'atteindre cette position
     * @param coordRoi la coordonnée potentielle du Roi pouvant être attaqué par le joueur
     * @return vrai si la coordonnée est atteignable, faux si elle peut l'être
     */
    boolean essaiCoupHostile(Coordonnee coordRoi);

    /**
     * Vérifie pour le joueur mentionné si le coup précédent doit provoquer la perte d'un pion
     * @param coordArr la coordonnée d'arrivée de la pièce adverse au coup précédent
     */
    void perdrePiece(Coordonnee coordArr);

    /**
     * Retourne le nombre de pièces détenues par le joueur
     * @return le nombre (int) de pièces
     */
    int nombrePieces();

    /**
     * Teste le barrage de "route"
     * @param coordDepart
     * @param coordArrivée
     * @return
     */
    boolean barreRoute(Coordonnee coordDepart, Coordonnee coordArrivée);
}
