package com.objis.simuRoute;

/**
 * Panneau
 * @author Patrice Camousseigt
 */
public abstract class Panneau extends SemaphoreStatique 
{
	/**
	 * Constructeur
	 * @param sens le sens choisi sur le Segment
	 * @param sonEmplacement l'emplacement sur le Segment
	 */
	public Panneau(EnumSens sens, Segment sonEmplacement) {
		super(sens, sonEmplacement);
	}

	/**
	 * Modifie le comportement du vehicule en tete en fonction du panneau
	 */
	abstract void comportement();
}
