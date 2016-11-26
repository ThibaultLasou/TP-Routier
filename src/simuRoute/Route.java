package simuRoute;

public abstract class Route 
{
	int longueur;
	Vehicule[][] sesVehicules; 
	
	public Route(int l)
	{
		this.longueur = l;
		sesVehicules = new Vehicule[2][l];
	}
	
	Vehicule getVehicule(int pos, Sens sens)
	{
		return sesVehicules[sens.ind][pos];
	}
	
	int placeLibre(Vehicule V)
	{
		return 0;
	}
}