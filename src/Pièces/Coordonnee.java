package Pièces;

public class Coordonnee {
    private int ligne;
    private int colonne;

    /**
     * Retourne une coordonnée initialisé a une ligne et une colonne donnée
     * @param ligne la ligne a laquelle initialiser la coordonnée
     * @param colonne la colonne a laquelle initialiser la coordonnée
     */
    public Coordonnee(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * @return la ligne de la coordonnée
     */
    public int getLigne() {
        return ligne;
    }

    /**
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
     * Retourne vrai si une coordonnée est égale à une autre
     * @param o la coordonnée a laquel comparé
     * @return vrai si les deux coordonnée sont égaux sinon faux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordonnee that = (Coordonnee) o;
        return ligne == that.ligne && colonne == that.colonne;
    }
}
