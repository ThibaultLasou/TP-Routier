package simuRoute;

/**
 * Un capteur de présence signale le passage d’une voiture pendant l’unité de temps courante
 */
public class CaptPresence extends Capteur 
{
	boolean presence; 
	
	/*l'attribut presence est à "vrai" s'il y a une voiture pendant l’unité de temps courante, "faux" sinon*/
	@Override
	void getMesure() throws ErreurPositionVoiture
	{
		
		try
		{
			this.sonEmplacement.getVehicule(this.position, this.sens);
			presence = true;
		}
		catch (Exception e)
		{
			presence = false;
		}
	}
}
