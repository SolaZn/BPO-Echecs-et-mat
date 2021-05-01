package Joueurs;

import Exceptions.*;
import Jeu.IPiece;
import Pièces.Coordonnee;
import Pièces.Roi;
import Pièces.Tour;

import java.util.LinkedList;

public class Joueur implements Jeu.IJoueur {
    private final LinkedList<IPiece> listePieces;

    public Joueur(String couleur){
        listePieces = new LinkedList<>();
        if(couleur.equals("blanc")){
            listePieces.add(new Roi(2, 4, couleur));
            listePieces.add(new Tour(1,1, couleur));
        }
        else if (couleur.equals("noir")){
            listePieces.add(new Roi(0,4, couleur));
        }
    }

    public void dessinerPositions(char[][] Plateau){
        for(IPiece p : listePieces)
            Plateau[p.getCoord().getLigne()][p.getCoord().getColonne()] = p.dessiner();
    }

    public boolean deplacerPiece(Coordonnee coordInit, Coordonnee coordArr) throws CoupHorsZoneDepException {
        // TODO: 01/05/2021 Vérifier le respect du polym sur isMangeable puis passer les coordonnées et le type d'arrivée à la pièce instanciée
        int pieceJouee = this.detientPiece(coordInit);
        return listePieces.get(pieceJouee).move(coordArr);
    }

    public int detientPiece(Coordonnee coord) {
        for(IPiece p : listePieces){
            if(p.getCoord().equals(coord))
                return listePieces.indexOf(p);
            else
                continue;
        }
        return -1;
    }

    private void listerCoupsPossibles(char[][] Plateau, Coordonnee coordInit){
        // Implémentation à préparer
    }
}
