package com.objis.simuRoute;

/**
 * Un capteur de présence signale le passage d’une voiture pendant l’unité de temps courante
 */
public class CaptPresence extends Capteur
{
	boolean presence; 
	
	/*l'attribut presence est à "vrai" s'il y a une voiture pendant l’unité de temps courante, "faux" sinon*/
	@Override
	public Boolean getMesure()
	{
		return presence;
	}

	@Override
	void mesurer() 
	{
		this.presence = this.sonEmplacement.getVehicule(this.position, this.sens) != null;
	}

	@Override
    public boolean isPresence() {
        return presence;
    }
}
