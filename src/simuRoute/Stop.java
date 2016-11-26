package simuRoute;

public class Stop extends Panneau 
{

	/**
	 * Arrete systematiquement le vehicule
	 */
	@Override
	void comportement() 
	{
        this.sonEmplacement.voitureEnTete().arreter();
	}
}
