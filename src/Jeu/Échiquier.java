package Jeu;

import Exceptions.Coordonnees.*;
import Jeu.Interfaces.IJoueur;
import Pièces.Coordonnee;

public class Échiquier {
    private char[][] Plateau;
    private static final int MTAILLE = 8;
    private static final String LIGNES = "   --- --- --- --- --- --- --- ---   \n";
    private static final String CASESALPH = "    a   b   c   d   e   f   g   h    \n";
    private static final String CASESREV = "    h   g   f   e   d   c   b   a    \n";
    private int nbAffichage = 0;

    /**
     * Renvoie le plateau de jeu initialisé avec des cases vide
     */
    public Échiquier(){
        Plateau = new char[MTAILLE][MTAILLE];
        for(int l = 0; l < Plateau.length; ++l){
            for(int c = 0; c < Plateau[0].length; ++c){
                this.Plateau[l][c] = ' ';
            }
        }
    }

    /**
     * Retourne le plateau de jeu
     * @return le plateau de jeu
     */
    public char[][] getPlateau() {
        return Plateau;
    }

    /**
     * Vérifie si la coordonnée fait partie de l'échiquier
     * @param coord la coordonnée
     * @return vrai si la coordonnée existe dans le plateau sinon faux
     * @throws CoordInexistanteException
     */
    public static boolean coordExiste(Coordonnee coord) throws CoordInexistanteException {
        // verifier si les coordonnes sont dans l'echiquier
        if(coord.getLigne() >= 0 && coord.getLigne() < MTAILLE && coord.getColonne() >= 0 && coord.getColonne() < MTAILLE)
            return true;
        throw new CoordInexistanteException();
    }

    /**
     * Donne le caractère définissant la pièce à la coordonnée spécifiée
     * @param coord la coordonnée
     * @return le type de la pièce qui occupe la coordonnée
     */
    public char coordOccupé(Coordonnee coord){
        return this.Plateau[coord.getLigne()][coord.getColonne()];
    }

    /**
     * Vide le tableau de l'échiquier
     */
    void effacer(){
        for(int l = 0; l < Plateau.length; ++l) {
            for (int c = 0; c < Plateau[0].length; ++c) {
                this.Plateau[l][c] = ' ';
            }
        }
    }

    /**
     * Place les pièces des deux joueurs à leur nouvelle position
     * @param J1 le premier joueur
     * @param J2 le second joueur
     */
    public void rafraichir(IJoueur J1, IJoueur J2){
        this.effacer();
        J1.dessinerPositions(this.getPlateau());
        J2.dessinerPositions(this.getPlateau());
    }

    /**
     * Définit la manière d'afficher le plateau dans le bon sens
     * @return la manière d'afficher le plateau dans le bon sens
     */
    private String affBonSens(){
        StringBuilder sb = new StringBuilder();
        sb.append(CASESALPH);
        for (int l = 0; l < Plateau.length; ++l) {
            sb.append(LIGNES);
            sb.append((MTAILLE - l));
            for (int c = 0; c < Plateau[0].length; ++c) {
                if ((Character.toUpperCase(Plateau[l][c]) != ' '))
                    sb.append(" | ").append(Plateau[l][c]);
                else
                    sb.append(" | ").append(" ");
            }
            sb.append(" | ").append((MTAILLE - l));
            sb.append("\n");
        }
        sb.append(LIGNES);
        sb.append(CASESALPH);
        return sb.toString();
    }

    /**
     * Définit la manière d'afficher le plateau dans le sens inverse
     * @return la manière d'afficher le plateau dans le sens inverse
     */
    private String affReverse(){
        StringBuilder sb = new StringBuilder();
        sb.append(CASESREV);
        for (int l = Plateau.length - 1; l >= 0; --l) {
            sb.append(LIGNES);
            sb.append((MTAILLE - l));
            for (int c = Plateau[0].length - 1; c >= 0; --c) {
                if ((Character.toUpperCase(Plateau[l][c]) != ' '))
                    sb.append(" | ").append(Plateau[l][c]);
                else
                    sb.append(" | ").append(" ");
            }
            sb.append(" | ").append((MTAILLE - l));
            sb.append("\n");
        }
        sb.append(LIGNES);
        sb.append(CASESREV);
        return sb.toString();
    }

    /**
     * Affiche le plateau de jeu selon le mode d'affichage
     * @param modeAffichage le mode d'affichage
     * @return la manière d'afficher le plateau de jeu selon le mode d'affichage
     */
    public String toString(char modeAffichage) {
        nbAffichage++;
        if (modeAffichage == 'a') {
            return affBonSens();
        }
        else
            return affReverse();
    }


    public int getNbAffichage() {
        return this.nbAffichage;
    }
}
