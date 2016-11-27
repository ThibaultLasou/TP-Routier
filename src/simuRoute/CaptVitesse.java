package simuRoute;

/**
 * Un capteur de vitesse mesure la vitesse de la voiture qui passe dessus pendant l’unité de temps courante
 *
 */

public class CaptVitesse extends CaptPresence 
{
	int vitesse;
	
	@Override
	void getMesure() throws ErreurPositionVoiture
	{
		super.getMesure(); // initialise le capteur de présence
		if(this.presence)
		{
			try
			{
				vitesse = this.sonEmplacement.getVehicule(this.position, this.sens).sonEtat.getVitesse_inst();
			}
			catch (Exception e)
			{
			}
		}
	}
}
