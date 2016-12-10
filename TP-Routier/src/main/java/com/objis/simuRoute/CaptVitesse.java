package com.objis.simuRoute;

/**
 * Un capteur de vitesse mesure la vitesse de la voiture qui passe dessus pendant l’unité de temps courante
 *
 */

public class CaptVitesse extends Capteur
{
	CaptPresence presence;
	int vitesse;

	public CaptVitesse(Segment sonEmplacement, Regulateur sonRegulateur, int position, EnumSens sens) {
		super(sonEmplacement, sonRegulateur, position, sens);
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

	/**
	 * Permet de tester plus facile
	 * @param presence active ou desactive la presence d'un vehicule
	 */
	public void setPresence(CaptPresence presence) {
		this.presence = presence;
	}
}
