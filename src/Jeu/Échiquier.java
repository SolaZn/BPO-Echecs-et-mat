package Jeu;

import Pièces.*;

public class Échiquier {
    private Piece[][] Plateau;

    public Échiquier(){
        Plateau = new Piece[8][8];
        for(int l = 0; l < Plateau.length; ++l){
            for(int c = 0; c < Plateau[0].length; ++c){
                // A completer je ne vois pas comment il faut declarer les pieces quand
                // la case est vide mais aussi quand la case est composé dd'une piéce.
                // C'est a dire comment sait on quelle piece declarer comment le jeu fait pour savoir ca
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("    a   b   c   d   e   f   g   h    \n");
        for(int l = 0; l < Plateau.length; ++l) {
            sb.append("   --- --- --- --- --- --- --- ---   \n");
            sb.append(Integer.toString(l+1));
            for (int c = 0; c < Plateau[0].length; ++c) {
                sb.append(" | ").append("a");
                //sb.append("| ").append(Plateau[l][c].dessiner());
            }
            sb.append(" | ").append(Integer.toString(8-l));
            sb.append("\n");
        }
        sb.append("   --- --- --- --- --- --- --- ---   \n");
        sb.append("    a   b   c   d   e   f   g   h    \n");
        return sb.toString();
    }
}
