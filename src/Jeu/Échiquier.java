package Jeu;

import Exceptions.CoordInexistanteException;
import Pièces.Coordonnee;
import Joueurs.Joueur;

public class Échiquier {
    private char[][] Plateau;
    private final static int MTAILLE = 8;

    public Échiquier(){
        Plateau = new char[MTAILLE][MTAILLE];
        for(int l = 0; l < Plateau.length; ++l){
            for(int c = 0; c < Plateau[0].length; ++c){
                // A completer je ne vois pas comment il faut declarer les pieces quand
                // la case est vide mais aussi quand la case est composé d'une piéce.
                // C'est a dire comment sait on quelle piece declarer comment le jeu fait pour savoir ca
                this.Plateau[l][c] = ' ';
            }
        }
    }

    public char[][] getPlateau() {
        return Plateau;
    }

    public boolean coordExiste(Coordonnee coord) throws Exceptions.CoordInexistanteException{
        // verifier si les coordonnes sont dans l'echiquier
        if(coord.getLigne() >= 0 && coord.getLigne() < MTAILLE && coord.getColonne() >= 0 && coord.getColonne() < MTAILLE)
            return true;
        throw new CoordInexistanteException();
    }

    public char coordOccupé(Coordonnee coord){
        return this.Plateau[coord.getLigne()][coord.getColonne()];
    }

    public void effacer(){
        for(int l = 0; l < Plateau.length; ++l) {
            for (int c = 0; c < Plateau[0].length; ++c) {
                this.Plateau[l][c] = ' ';
            }
        }
    }

    public void rafraichir(Joueur J1, Joueur J2){
        this.effacer();
        J1.dessinerPositions(this.getPlateau());
        J2.dessinerPositions(this.getPlateau());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("    a   b   c   d   e   f   g   h    \n");
        for(int l = 0; l < Plateau.length; ++l) {
            sb.append("   --- --- --- --- --- --- --- ---   \n");
            sb.append(Integer.toString(MTAILLE-l));
            for (int c = 0; c < Plateau[0].length; ++c) {
                if((Character.toUpperCase(Plateau[l][c]) != ' '))
                    sb.append(" | ").append(Plateau[l][c]);
                else
                    sb.append(" | ").append(" ");
            }
            sb.append(" | ").append(Integer.toString(MTAILLE-l));
            sb.append("\n");
        }
        sb.append("   --- --- --- --- --- --- --- ---   \n");
        sb.append("    a   b   c   d   e   f   g   h    \n");
        return sb.toString();
    }
}
