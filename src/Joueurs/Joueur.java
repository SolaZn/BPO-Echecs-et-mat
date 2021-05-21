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

/**
 * Cette classe représente un joueur dans le programme de finales d'échecs.
 * Ce joueur a un nom et a à sa disposition la liste de ses pièces;
 * Pièces qu'il manipulera au cours de sa partie.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony Zakani
 */
public class Joueur implements IJoueur {
    private final String nomJoueur;
    private final LinkedList<IPiece> listePieces;

    /**
     * Construit un joueur humain comme sous-type d'un joueur
     * @param couleur la couleur des pièces du joueur
     * @param nomJoueur le nom du joueur
     */
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

    /**
     * @see IJoueur#joueCoup(Échiquier, IJoueur)
     */
    @Override
    public String joueCoup(Échiquier Echiquier, IJoueur J2) {
        return saisie();
    }

    /**
     * @see IJoueur#dessinerPositions(char[][])
     */
    @Override
    public void dessinerPositions(char[][] Plateau){
        for(IPiece p : listePieces)
            Plateau[p.getCoord().getLigne()][p.getCoord().getColonne()] = p.dessiner();
    }

    /**
     * @see IJoueur#detientPiece(Coordonnee)
     */
    @Override
    public boolean deplacerPiece(Coordonnee coordInit, Coordonnee coordArr) throws CoupHorsZoneDepException {
        int pieceJouee = this.detientPiece(coordInit);
        return listePieces.get(pieceJouee).move(coordArr, etatPiece.Jeu);
    }

    /**
     * @see IJoueur#detientPiece(Coordonnee)
     */
    @Override
    public int detientPiece(Coordonnee coord) {
        for(IPiece p : listePieces){
            if(p.getCoord().equals(coord))
                return listePieces.indexOf(p);
        }
        return -1;
    }

    /**
     * @see IJoueur#nombrePieces()
     */
    @Override
    public int nombrePieces(){
        return this.listePieces.size();
    }

    /**
     * @see IJoueur#essaiCoupHostile(Coordonnee)
     */
    @Override
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

    /**
     * @see IJoueur#positionRoi()
     */
    @Override
    public Coordonnee positionRoi() {
        for(IPiece p : listePieces) {
            if (Character.toUpperCase(p.dessiner()) == 'R') {
                return p.getCoord();
            }
        }
        throw new UnsupportedOperationException("Tous les joueurs ont un roi...");
    }

    /**
     * @see IJoueur#perdrePiece(Coordonnee)
     */
    @Override
    public void perdrePiece(Coordonnee coordArr) {
        if(listePieces.removeIf(p -> p.getCoord().equals(coordArr))){
            Appli.affichage("Une pièce du joueur " + this.toString() + " a été mangée");
            Jeu.Jeu.setNbCoupsNonHostile();
        }
    }

    /**
     * @see IJoueur#barreRoute(Coordonnee, Coordonnee)
     */
    public boolean barreRoute(Coordonnee coordDepart, Coordonnee coordArrivé){
        for (IPiece piece: this.listePieces) {
            piece.barreRoute(coordDepart, coordArrivé);
        }
        return true;
    }

    /**
     * Retourne la liste des pièces
     * @return la liste des pièces
     */
    LinkedList<IPiece> getListePieces(){
        return listePieces;
    }

    @Override
    public String toString() {
        return nomJoueur.toUpperCase(Locale.ROOT);
    }
}
