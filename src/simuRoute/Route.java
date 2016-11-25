package simuRoute;

import java.util.ArrayList;

public abstract class Route 
{
	int longueur;
	ArrayList<Vehicule> sesVehicules; 
	
	int placeLibre(Vehicule V)
	{
		return 0;
	}
}