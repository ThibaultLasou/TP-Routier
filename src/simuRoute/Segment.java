package simuRoute;

public class Segment extends Route
{
	Jonction[] sesExtremites;
	Semaphore[] saSignalisation;
	
	public Segment(int l) 
	{
		super(l);
		sesExtremites = new Jonction[2];
		saSignalisation = new Semaphore[2];
	}
	
	Vehicule voitureEnTete()
	{
		return null;	
	}
}