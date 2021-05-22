package Exceptions.Coordonnees;

public class RouteBarréeException extends Exception{
    public RouteBarréeException(){
        super("Coup invalide\nUne pièce adverse/alliée se trouve entre la coordonnée de départ et la coordonnée d'arrivée");
    }
}
