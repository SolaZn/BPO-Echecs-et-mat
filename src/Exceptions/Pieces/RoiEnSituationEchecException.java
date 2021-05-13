package Exceptions.Pieces;

public class RoiEnSituationEchecException extends Exception {
    public RoiEnSituationEchecException() {
        super("Le Roi se met en \nsituation d'échec par ce coup");
    }
}
