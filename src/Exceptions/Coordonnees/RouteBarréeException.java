package Exceptions.Coordonnees;

/**
 * Cette exception se déclenche lorsque le coup joué nécessiterait un saut de pièce par une pièce
 * ne possédant pas cet habilité
 */
public class RouteBarréeException extends Exception{
    public RouteBarréeException(){
        super("Coup invalide\nUne pièce adverse/alliée se trouve entre la coordonnée de départ et la coordonnée d'arrivée");
    }
}
