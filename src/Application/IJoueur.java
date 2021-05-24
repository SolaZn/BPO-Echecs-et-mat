package Application;

import Exceptions.Coordonnees.CoupHorsZoneDepException;
import Jeu.Échiquier;
import Pièces.Coordonnee;

/**
 * Cette interface représente l'ensemble des méthodes devant être implémentées
 * par des classes correspondant à des joueurs du programme.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony ZAKANI
 */
public interface IJoueur {
    static IJoueur getJoueur(char typeJoueur, String couleur, String nomJoueur) {
        return DefinirJoueur.fabriquerJoueur(typeJoueur, couleur, nomJoueur);
    }

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
     * @throws CoupHorsZoneDepException si le coup ne respecte pas les règles de déplacement de la pièce
     */
    boolean deplacerPiece(Coordonnee coordInit, Coordonnee coordArr) throws CoupHorsZoneDepException;

    /**
     * Donne le rang de la pièce du joueur correspondant aux coordonnées renseignées
     * @param coord la coordonnée où doit se situer la pièce recherchée
     * @return le rang (int) si la pièce est trouvée, -1 si aucune pièce ne correspond à la recherche
     */
    int detientPiece(Coordonnee coord);

    /**
     * Donne la position du Roi du joueur concerné
     * @return la coordonnée (Coordonnee) du Roi du joueur
     */
    Coordonnee positionRoi();

    /**
     * Teste pour une coordonnée donnée la possibilité pour le joueur d'atteindre cette position
     * @param coordRoi la coordonnée potentielle du Roi pouvant être attaqué par le joueur
     * @param Echiquier l'échiquier où se situe la pièce
     * @return vrai si la coordonnée est atteignable, faux si elle peut l'être
     */
    boolean essaiCoupHostile(Coordonnee coordRoi, Échiquier Echiquier);

    /**
     * Vérifie pour le joueur mentionné si le coup précédent doit provoquer la perte d'un pion
     * @param coordArr la coordonnée d'arrivée de la pièce adverse au coup précédent
     */
    void perdrePiece(Coordonnee coordArr);

    /**
     * Donne le nombre de pièces détenues par le joueur
     * @return le nombre (int) de pièces
     */
    int nombrePieces();

    /**
     * Teste le barrage de "route"
     * @param coordDepart la coordonnée d'où l'on commence à regarder
     * @param coordArrivée la coordonnée où l'on arrive
     * @param echiquier l'échiquier où se situe la pièce
     * @return vrai si la route est barrée, faux si elle ne l'est pas
     */
    boolean barreRoute(Coordonnee coordDepart, Coordonnee coordArrivée, Échiquier echiquier);
}
