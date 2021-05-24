package Pièces;

/**
 * Cette classe représente une coordonnée dans l'échiquier,
 * une coordonnée consiste en une structure comportant à la fois
 * une ligne et une colonne
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony Zakani
 */
public class Coordonnee {
    private int ligne;
    private int colonne;

    /**
     * Construit une coordonnée initialisée à une ligne et une colonne spécifiés
     * @param ligne la ligne avec laquelle initialiser la coordonnée
     * @param colonne la colonne avec laquelle initialiser la coordonnée
     */
    public Coordonnee(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Donne la ligne de la coordonnée
     * @return la ligne de la coordonnée
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Donne la colonne de la coordonnée
     * @return la colonne de la coordonnée
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Modifie la ligne de la coordonnée
     * @param ligne la ligne
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * Modifie la colonne de la coordonnée
     * @param colonne la colonne
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * Étudie l'égalité entre deux coordonnées
     * @param o la coordonnée avec laquelle il faut comparer
     * @return vrai si les deux coordonnée sont égales sinon faux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordonnee that = (Coordonnee) o;
        return ligne == that.ligne && colonne == that.colonne;
    }
}
