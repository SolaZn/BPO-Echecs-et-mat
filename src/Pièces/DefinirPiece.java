package Pièces;

import Jeu.Interfaces.IPiece;

class DefinirPiece {
    public static IPiece fabriquerPiece(char typePiece, Coordonnee coord, String couleur) {
        if(Character.toLowerCase(typePiece) == 'r'){
            return new Roi(coord.getLigne(), coord.getColonne(), couleur);
        }
        else if(Character.toLowerCase(typePiece) == 't'){
            return new Tour(coord.getLigne(), coord.getColonne(), couleur);
        }
        throw new UnsupportedOperationException("Cette pièce n'a pas été implémentée;\n" + typePiece + " n'existe pas");
    }

    public static boolean isMangeable(char typePiece) {
        return Character.toUpperCase(typePiece) != 'R'; // => Point d'évolutivité citable dans le rapport : si on veut jouer avec des
                                                        // => règles différentes, on peut changer. Cependant, réfléchir à la position dans le code
    }
}
