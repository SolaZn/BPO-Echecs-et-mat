package Joueurs;

import Jeu.IPiece;

import java.util.LinkedList;

public class Joueur {
    private LinkedList<IPiece> listePieces

    public Joueur(String couleur){
        listePieces = new LinkedList<IPiece>();
        if(couleur == "blanc"){
            listePieces.add(new Roi(7, 0, couleur)) {
            }
        }
    }


}
