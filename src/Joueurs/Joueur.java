package Joueurs;

import Application.Appli;
import Exceptions.Coordonnees.*;
import Jeu.Interfaces.*;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import Pièces.etatPiece;

import java.util.LinkedList;
import java.util.Locale;

import static Application.Appli.saisie;

public class Joueur implements IJoueur {
    private int nbCoupsNonHostile = 0;
    private final String nomJoueur;
    protected final LinkedList<IPiece> listePieces;

    public Joueur(String couleur, String nomJoueur){
        listePieces = new LinkedList<>();
        this.nomJoueur = nomJoueur;
        if(couleur.equals("blanc")){
            listePieces.add(IPiece.getPiece('r', new Coordonnee(2,4), couleur));
            listePieces.add(IPiece.getPiece('t', new Coordonnee(1,1), couleur));
        }
        else if (couleur.equals("noir")){
             listePieces.add(IPiece.getPiece('r', new Coordonnee(0,4), couleur));
            // listePieces.add(IPiece.getPiece('t', new Coordonnee(3,2), couleur));
        }
    }

    @Override
    public String joueCoup(Échiquier Echiquier, IJoueur J2) {
        return saisie();
    }

    public void dessinerPositions(char[][] Plateau){
        for(IPiece p : listePieces)
            Plateau[p.getCoord().getLigne()][p.getCoord().getColonne()] = p.dessiner();
    }

    public boolean deplacerPiece(Coordonnee coordInit, Coordonnee coordArr) throws CoupHorsZoneDepException {
        int pieceJouee = this.detientPiece(coordInit);
        return listePieces.get(pieceJouee).move(coordArr, etatPiece.Jeu);
    }

    public int detientPiece(Coordonnee coord) {
        for(IPiece p : listePieces){
            if(p.getCoord().equals(coord))
                return listePieces.indexOf(p);
        }
        return -1;
    }

    public int nombrePieces(){
        return this.listePieces.size();
    }

    public boolean essaiCoupHostile(Coordonnee coordRoi){
        for(IPiece p : listePieces) {
                try {
                    if(p.move(coordRoi, etatPiece.Essai)){
                        return true;
                    }
                } catch (CoupHorsZoneDepException chz){
                    continue;
                }
        }
        return false;
    }

    @Override
    public String toString() {
        return nomJoueur.toUpperCase(Locale.ROOT);
    }

    private void listerCoupsPossibles(char[][] Plateau, Coordonnee coordInit){
        // Implémentation à préparer pour l'IA
    }

    public Coordonnee positionRoi() {
        for(IPiece p : listePieces) {
            if (Character.toUpperCase(p.dessiner()) == 'R') {
                return p.getCoord();
            }
        }
        throw new UnsupportedOperationException("Tous les joueurs ont un roi...");
    }

    public void perdrePiece(Coordonnee coordArr) {
        if(listePieces.removeIf(p -> p.getCoord().equals(coordArr))){
            Appli.affichage("Une pièce du joueur " + this.toString() + " a été mangée");
            Jeu.Jeu.setNbCoupsNonHostile();
        }
    }

    public boolean barreRoute(Coordonnee coordDepart, Coordonnee coordArrivé){
        for (IPiece piece: this.listePieces) {
            piece.barreRoute(coordDepart, coordArrivé);
        }
        return true;
    }
}
