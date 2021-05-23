package Application;

import Jeu.Jeu;
import Joueurs.IA;
import Joueurs.Joueur;
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
     * @return la saisie de l'utilisateur
     */
    public static String saisie(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        return input;
    }



    public static void main(String[] args) {
        String modeJeu = getModeJeu();

        Joueur Blanc = null;
        Joueur Noir = null;
        switch (modeJeu) {
            case "1":
                Blanc = new Joueur("blanc", "blanc");
                Noir = new Joueur("noir", "noir");
                break;
            case "2":
                Blanc = new IA("blanc", "blanc");
                Noir = new IA("noir", "noir");
                break;
            case "3":
                Blanc = new Joueur("blanc", "blanc");
                Noir = new IA("noir", "noir");
                break;
            default:
                getModeJeu();
        }

        Échiquier Echiquier = new Échiquier();
        Jeu.Partie(Blanc, Noir, Echiquier);
    }

    /**
     * @return le mode de jeu choisi par l'utilisateur
     */
    private static String getModeJeu() {
        System.out.println("Veuillez choisir un mode de jeu :");
        System.out.println("Tapez 1 pour une partie entre Humains");
        System.out.println("Tapez 2 pour une partie entre IA");
        System.out.println("Tapez 3 pour une partie mixte Humains/IA");
        String modeJeu;

        do{
            modeJeu = saisie();

        }while(!(modeJeu.equals("1") || modeJeu.equals("2") || modeJeu.equals("3")));

        return modeJeu;
    }

}