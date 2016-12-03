package com.objis.simuRoute;

public class Stop extends Panneau 
{

	/**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     * @param regulateur son Regulateur associe
	 */
	public Stop(EnumSens sens, Segment segment, Regulateur regulateur) {
		super(sens, segment, regulateur);
	}

	/**
	 * Arrete systematiquement le vehicule
	 */
	@Override
	void comportement() 
	{
        this.sonEmplacement.voitureEnTete(sens).arreter();
	}
}
