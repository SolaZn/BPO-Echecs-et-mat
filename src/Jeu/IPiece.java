package Jeu;

import Exceptions.Coordonnees.*;
import Pièces.*;

import java.util.LinkedList;

/**
 * Cette interface représente l'ensemble des méthodes devant être implémentées
 * par des classes correspondant à des pièces du jeu d'échecs du programme.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony ZAKANI
 */
public interface IPiece {

    /**
     * Génère la pièce demandée aux coordonnées demandées pour le joueur demandé
     * @param typePiece le type de la pièce demandée
     * @param coord les coordonnées où la placer
     * @param couleur la couleur d'appartenance de la pièce
     * @return la pièce (IPiece) ainsi définie
     */
    static IPiece getPiece(char typePiece, Coordonnee coord, String couleur){
        return DefinirPiece.fabriquerPiece(typePiece, coord, couleur);
    }

    /**
     * Édite la liste contenant les coups possibles compte-tenu des règles de
     * déplacement concernant le type de la pièce concernée
     * @return la liste (LinkedList) des coordonnées où la pièce peut se déplacer
     */
    LinkedList<Coordonnee> getCoupsPossibles();

    /**
     * Donne l'état de "mangeabilité" de la pièce demandée
     * @param typePiece le type de la pièce
     * @return vrai si elle est mangeable, faux si elle ne l'est pas
     */
    static boolean isMangeable(char typePiece){
        return DefinirPiece.isMangeable(typePiece);
    }

    /**
     * Donne la couleur de la pièce concernée
     * @return la couleur (String) de la pièce
     */
    String getCouleur();

    /**
     * Donne le "caractère" de dessin de la pièce sur l'échiquier
     * @return le caractère (char) correspondant à la pièce
     */
    char dessiner();

    /**
     * Effectue le déplacement de la pièce vers une coordonnée d'arrivée compte-tenu des règles de déplacement
     * concernant le type de la pièce concernée
     * @param coordArr la coordonnée d'arrivée de la pièce
     * @param etat le mode de déplacement (pour jouer ou juste pour tester)
     * @return vrai si le déplacement est possible/a été effectué, faux si il ne l'est pas/n'a pas été effectué
     * @throws CoupHorsZoneDepException si le coup ne respecte pas les règles de déplacement de la pièce
     */
    boolean move(Coordonnee coordArr, etatPiece etat) throws CoupHorsZoneDepException;

    /**
     * Donne les coordonnées actuelle de la pièce
     * @return les coordonnées de la pièce
     */
    Coordonnee getCoord();

    /**
     * Détermine si la pièce peut ou non barrer la route
     * @param coordDepart la coordonnée d'où l'on commence à regarder
     * @param coordArr la coordonnée où l'on arrive
     * @param echiquier l'échiquier où se situe la pièce
     * @return vrai si la route est barrée, faux si elle ne l'est pas
     */
    boolean routeBarree(Coordonnee coordDepart, Coordonnee coordArr, Échiquier echiquier);

}
