package Joueurs;

import Jeu.IPiece;
import Pièces.Coordonnee;
import Pièces.Piece;
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

    public boolean deplacerPiece(char[][] Plateau, Coordonnee coordInit, Coordonnee coordArr, char typePieceArr){
        int idDepart = this.detientPiece(coordInit);
        int idArrivee = this.detientPiece(coordArr);
        if(idDepart != -1 && idArrivee == -1){
            if(IPiece.isMangeable(typePieceArr)){
                return true;
            }
        }
        return false;
    }

    private int detientPiece(Coordonnee coord) {
        for(IPiece p : listePieces){
            if(p.getCoord().equals(coord))
                return listePieces.indexOf(p);
            else
                continue;
        }
        return -1;
    }

    private void listerCoupsPossibles(char[][] Plateau, Coordonnee coordInit){

    }
}
