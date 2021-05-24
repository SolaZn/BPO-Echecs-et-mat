package Pièces;

import Jeu.IPiece;

/**
 * Cette classe représente une pièce dans le programme de finales d'échecs.
 * Cette pièce a une coordonnée ainsi que sa couleur d'affichage;
 * Couleur qui lui servira lors de son affichage.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony Zakani
 */
public abstract class Piece implements IPiece {
    private Coordonnee coord;
    private String couleur;

    /**
     * Renvoie la pièce initialisé a une ligne, une colonne et une couleur données
     * @param ligneInit la ligne a laquelle va être initialisé la pièce
     * @param colInit la colonne a laquelle va être initialisé la pièce
     * @param colrInit la couleur a laquelle va être initialisé la pièce
     */
    Piece(int ligneInit, int colInit, String colrInit){
        this.coord = new Coordonnee(ligneInit, colInit);
        this.couleur = colrInit;
    }

    /**
     * @see IPiece#getCouleur()
     */
    public String getCouleur() {
        return this.couleur;
    }

    /**
     * @see IPiece#dessiner()
     */
    public char dessiner(){
        return getChar();
    }

    /**
     * (abstraite -- chaque implémentation diffère)
     * Retourne le caratère correspondant au dessin de la pièce
     * @return le caractère
     */
    public abstract char getChar();

    /**
     * @see IPiece#getCoord()
     */
    public Coordonnee getCoord() {
        return coord;
    }

    /**
     * Mets à jour les coordonnées de la pièce
     * @param coordArr la coordonnée d'arrivée obtenue lors du coup précédent
     */
    void deplacerA(Coordonnee coordArr){
        this.coord.setLigne(coordArr.getLigne());
        this.coord.setColonne(coordArr.getColonne());
    }
}