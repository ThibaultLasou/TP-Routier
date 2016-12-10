package com.objis.simuRoute;

/**
 * Un capteur de vitesse mesure la vitesse de la voiture qui passe dessus pendant l’unité de temps courante
 *
 */

public class CaptVitesse extends Capteur
{
	CaptPresence presence;
	int vitesse;
	
	public CaptVitesse() 
	{
		presence = new CaptPresence();
	}
	
	@Override
	public Integer getMesure()
	{
		if(presence.getMesure())
		{
			return vitesse;
		}
		else
		{
			//TODO
			return -1;
		}
	}

	@Override
	void mesurer()
	{
		presence.mesurer(); // initialise le capteur de présence
		if(this.presence.getMesure())
		{
			vitesse = this.sonEmplacement.getVehicule(this.position, this.sens).getVitesse_inst();
		}
	}

	@Override
	public boolean isPresence() {
		return presence.isPresence();
	}
}
