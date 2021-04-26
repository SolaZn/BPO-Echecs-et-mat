package Application;

import Jeu.Joueur;
import Jeu.Échiquier;
import Pièces.Coordonnee;
import java.util.Scanner;

public class Appli {

    public void creationCoordCoup(){
        Scanner sc = new Scanner(System.in);
        String coup = sc.nextLine();
        Coordonnee coordInit = new Coordonnee((Character.getNumericValue(Character.toLowerCase(coup.charAt(0))) - 10), (Character.getNumericValue(coup.charAt(1)) - 1));
        Coordonnee coordArr = new Coordonnee((Character.getNumericValue(Character.toLowerCase(coup.charAt(2))) - 10), (Character.getNumericValue(coup.charAt(3)) - 1));
    }

    public static void main(String[] args) {
        Joueur Blanc = new Joueur();
        Joueur Noir = new Joueur();
        Échiquier Echiquier = new Échiquier();
    }

}
