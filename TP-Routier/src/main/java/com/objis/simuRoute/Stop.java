package com.objis.simuRoute;

/**
 * Un panneau stop qui indique au vehicule de s'arreter
 * @author Patrice Camousseigt
 */
public class Stop extends Panneau 
{

	/**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
	 */
	public Stop(EnumSens sens, Segment segment) {
		super(sens, segment);
	}

	/**
	 * Arrete systematiquement le vehicule
     * Si la voiture avance, le stop arrete le vehicule
     * Si la voiture est arretee, le stop lui redonne sa vitesse maximale
     * mais la voiture verifie si le segment suivant est disponible et avance que a cette condition
	 */
	@Override
	void comportement() {
        Vehicule vehiculeEnTete = this.sonEmplacement.voitureEnTete(sens);
        if (vehiculeEnTete.estArrete()) {
            vehiculeEnTete.accelerer();
        } else {
            vehiculeEnTete.arreter();
        }
    }

	@Override
	public String toString() {
		return "[Panneau Stop]";
	}
}
