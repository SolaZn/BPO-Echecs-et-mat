package Pièces;

public abstract class Piece {
    private int ligne, colonne;
    private String couleur;

    public Piece(int ligneInit, int colInit, String colrInit){
        this.ligne = ligneInit;
        this.colonne = colInit;
        this.couleur = colrInit;
    }

    public String getCouleur(){
        return this.couleur;
    }

    public void deplacer(int caseSrc, int caseDest){
        
    }

    abstract String getChar();

    public String dessiner(){
        return getChar();
    }
}