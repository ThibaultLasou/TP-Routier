package com.objis.simuRoute;

/**
 * Un capteur de présence signale le passage d’une voiture pendant l’unité de temps courante
 * @author Yasmine Kertous
 */
public class CaptPresence extends Capteur
{
	boolean presence;

	public CaptPresence(Segment sonEmplacement, Regulateur sonRegulateur, int position, EnumSens sens) {
		super(sonEmplacement, sonRegulateur, position, sens);
	}

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

	/**
	 * Permet de tester plus facile
	 * @param presence active ou desactive la presence d'un vehicule
	 */
	public void setPresence(boolean presence) {
		this.presence = presence;
	}

	@Override
	public String toString() {
		return "Capteur de presence sur : "+ sonEmplacement.getNomSegment()+" (sens "+sens+")  ";
	}
}
