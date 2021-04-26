package Pièces;

public abstract class Piece {
    private Coordonnee coord;
    private String couleur;

    public Piece(int ligneInit, int colInit, String colrInit){
        this.coord = new Coordonnee(ligneInit, colInit);
        this.couleur = colrInit;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public abstract String getChar();

    public String dessiner(){
        return getChar();
    }

    public void deplacerA(Coordonnee coordArr){
        this.coord.setLigne(coordArr.getLigne());
        this.coord.setColonne(coordArr.getColonne());
    }
}