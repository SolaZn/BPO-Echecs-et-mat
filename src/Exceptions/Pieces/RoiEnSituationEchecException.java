package Exceptions.Pieces;

/**
 * Cette exception se déclenche lorsque le coup mettrait ou maintiendrait le Roi
 * en situation d'échec
 */
public class RoiEnSituationEchecException extends Exception {
    public RoiEnSituationEchecException() {
        super("Le Roi se met en \nsituation d'échec par ce coup");
    }
}
