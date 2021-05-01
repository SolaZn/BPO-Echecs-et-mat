package Pièces;

public abstract class Piece implements Jeu.IPiece{
    private Coordonnee coord;
    private String couleur;

    Piece(int ligneInit, int colInit, String colrInit){
        this.coord = new Coordonnee(ligneInit, colInit);
        this.couleur = colrInit;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public char dessiner(){
        return getChar();
    }

    void deplacerA(Coordonnee coordArr){
        this.coord.setLigne(coordArr.getLigne());
        this.coord.setColonne(coordArr.getColonne());
    }

    public Coordonnee getCoord() {
        return coord;
    }

    public abstract char getChar();;
}