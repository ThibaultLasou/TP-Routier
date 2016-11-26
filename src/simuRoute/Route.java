package simuRoute;

public abstract class Route 
{
	int longueur;
	Vehicule[] sesVehicules; 
	
	public Route(int l)
	{
		this.longueur = l;
		sesVehicules = new Vehicule[l];
	}
	
	Vehicule getVehicule(int pos)
	{
		return sesVehicules[pos];
	}
	
	int placeLibre(Vehicule V)
	{
		return 0;
	}
}