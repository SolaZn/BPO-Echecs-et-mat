package Joueurs;

import Jeu.IPiece;
import Pièces.Roi;
import Pièces.Tour;

import java.util.LinkedList;

public class Joueur implements Jeu.IJoueur {
    private LinkedList<IPiece> listePieces;

    public Joueur(String couleur){
        listePieces = new LinkedList<>();
        if(couleur == "blanc"){
            listePieces.add(new Roi(8-6, 8-6, couleur)); // 8 - x parce que l'échiquier a le 8 en haut
            listePieces.add(new Tour(8-7,8-2, couleur));
        }
        else if (couleur == "noir"){
            listePieces.add(new Roi(8-8,8-5, couleur));
        }
    }

    public void dessinerPositions(char[][] Plateau){
        for(IPiece p : listePieces)
            Plateau[p.getCoord().getLigne()][p.getCoord().getColonne()] = p.dessiner();
    }


}
