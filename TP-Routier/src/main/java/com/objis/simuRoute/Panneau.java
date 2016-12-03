package com.objis.simuRoute;

public abstract class Panneau extends SemaphoreStatique 
{
	/**
	 * Constructeur
	 * @param sens le sens choisi sur le Segment
	 * @param sonEmplacement l'emplacement sur le Segment
	 * @param sonRegulateur son Regulateur associe
	 */
	public Panneau(EnumSens sens, Segment sonEmplacement, Regulateur sonRegulateur) {
		super(sens, sonEmplacement, sonRegulateur);
	}

	/**
	 * Modifie le comportement du vehicule en tete en fonction du panneau
	 */
	abstract void comportement();
}
