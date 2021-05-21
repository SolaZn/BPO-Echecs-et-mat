package Pièces;

import Jeu.Interfaces.IPiece;

public abstract class Piece implements IPiece {
    private Coordonnee coord;
    private String couleur;

    Piece(int ligneInit, int colInit, String colrInit){
        this.coord = new Coordonnee(ligneInit, colInit);
        this.couleur = colrInit;
    }


    /**
     * @see IPiece#getPiece(char, Coordonnee, String)
     */
    public static IPiece getPiece(char typePiece, Coordonnee coord, String couleur){
        return DefinirPiece.fabriquerPiece(typePiece, coord, couleur);
    }

    /**
     * @see IPiece#isMangeable(char)
     */
    public static boolean isMangeable(char typePiece){
        return DefinirPiece.isMangeable(typePiece);
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