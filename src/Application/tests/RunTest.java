package Application.tests;

import Application.Appli;

public class RunTest
{
    public static void main(String[] args)
    {
        // test ne pouvant s'exécuter que si le String modeJeu a déjà été renseigné
        for(int i=1; i<=30; i++)
            Appli.main(args);
    }
}