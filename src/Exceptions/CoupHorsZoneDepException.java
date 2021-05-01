package Exceptions;

public class CoupHorsZoneDepException extends Exception{
    public CoupHorsZoneDepException() {
        super("Coup illégal\nCoup hors de la zone de déplacement de la pièce\nou coup sans bouger");
    }
}
