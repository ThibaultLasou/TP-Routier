package com.objis.simuRoute;

public abstract class SemaphoreStatique extends Semaphore
{

	/**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param sonEmplacement l'emplacement sur le Segment
	 */
	public SemaphoreStatique(EnumSens sens, Segment sonEmplacement) {
		super(sens, sonEmplacement);
	}

	/**
	 * Modifie le comportement du vehicule en tete en fonction de l'etat de la semaphore
	 */
	abstract void comportement();
}
