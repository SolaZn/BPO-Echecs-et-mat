package Exceptions.Coordonnees;

/**
 * Cette exception se déclenche lorsque le coup joué se situe hors de la zone de déplacement de la pièce
 * ou alors à la même position que la position actuelle
 */
public class CoupHorsZoneDepException extends Exception{
    public CoupHorsZoneDepException() {
        super("Coup illégal\nCoup hors de la zone de déplacement de la pièce\nou coup sans bouger");
    }
}
