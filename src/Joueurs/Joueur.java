package Joueurs;

import Application.Appli;
import Application.IJoueur;
import Exceptions.Coordonnees.*;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import Pièces.etatPiece;

import java.util.LinkedList;
import java.util.Locale;

/**
 * Cette classe représente un joueur dans le programme de finales d'échecs.
 * Ce joueur a un nom et a à sa disposition la liste de ses pièces;
 * Pièces qu'il manipulera au cours de sa partie.
 *
 * @author Slim BEN DAALI, Yacine BETTAYEB et Anthony ZAKANI
 */
abstract class Joueur implements IJoueur {
    private final String nomJoueur;
    private final LinkedList<IPiece> listePieces;

    /**
     * Construit un joueur
     * @param couleur la couleur des pièces du joueur
     * @param nomJoueur le nom du joueur
     */
    public Joueur(String couleur, String nomJoueur){
        listePieces = new LinkedList<>();
        this.nomJoueur = nomJoueur;
        if(couleur.equals("blanc")){
            listePieces.add(DefinirPiece.fabriquerPiece('r', new Coordonnee(2,4), couleur));
            listePieces.add(DefinirPiece.fabriquerPiece('t', new Coordonnee(1,1), couleur));
        }
        else if (couleur.equals("noir")){
             listePieces.add(DefinirPiece.fabriquerPiece('r', new Coordonnee(0,4), couleur));
             // listePieces.add(IPiece.getPiece('t', new Coordonnee(3,2), couleur));
        }
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
     * @see IJoueur#essaiCoupHostile(Coordonnee, Échiquier)
     */
    @Override
    public boolean essaiCoupHostile(Coordonnee coordRoi, Échiquier Echiquier){
        for(IPiece p : listePieces) {
                try {
                    if(!p.routeBarree(p.getCoord(), coordRoi, Echiquier)) {
                        if (p.move(coordRoi, etatPiece.Essai)) {
                            return true;
                        }
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
     * @see IJoueur#barreRoute(Coordonnee, Coordonnee, Échiquier)
     */
    public boolean barreRoute(Coordonnee coordDepart, Coordonnee coordArrivé, Échiquier echiquier){
        for (IPiece piece: this.listePieces) {
            if(piece.getCoord().equals(coordDepart)) {
                return piece.routeBarree(coordDepart, coordArrivé, echiquier);
            }
        }
        return true;
    }

    /**
     * Donne la liste des pièces
     * @return la liste des pièces
     */
    LinkedList<IPiece> getListePieces(){
        return listePieces;
    }

    /**
     * Donne la manière d'afficher un joueur
     * @return la manière d'afficher un joueur
     */
    @Override
    public String toString() {
        return nomJoueur.toUpperCase(Locale.ROOT);
    }
}
