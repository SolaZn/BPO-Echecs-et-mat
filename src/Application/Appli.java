package Application;

import Jeu.Interfaces.IJoueur;
import Jeu.Jeu;
import Jeu.Échiquier;
import java.util.Scanner;

public class Appli {

    /**
     * Affiche la sortie voulue sur la console
     * @param output la sortie voulue
     */
    public static void affichage(String output){
        System.out.println(output);
    }

    /**
     * Demande à l'utilisateur de saisir une chaîne de caractères
     * @return la saisie de l'utilisateur
     */
    public static String saisie(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        return input;
    }



    public static void main(String[] args) {
        String modeJeu = Jeu.getModeJeu();

        IJoueur Blanc = null;
        IJoueur Noir = null;
        switch (modeJeu) {
            case "1":
                Blanc = IJoueur.getJoueur('h',"blanc","blanc");
                Noir = IJoueur.getJoueur('h',"noir","noir");
                break;
            case "2":
                Blanc = IJoueur.getJoueur('i',"blanc","blanc");
                Noir = IJoueur.getJoueur('i',"noir","noir");
                break;
            case "3":
                Blanc = IJoueur.getJoueur('h',"blanc","blanc");
                Noir = IJoueur.getJoueur('i',"noir","noir");
                break;
            default:
                Jeu.getModeJeu();
        }

        Échiquier Echiquier = new Échiquier();
        Jeu.Partie(Blanc, Noir, Echiquier);
    }
}