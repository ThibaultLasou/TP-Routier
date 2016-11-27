package com.objis.simuRoute;

public abstract class SemaphoreDynamique extends Semaphore
{
	/**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param sonEmplacement l'emplacement sur le Segment
     * @param sonRegulateur son Regulateur associe
	 */
	public SemaphoreDynamique(Sens sens, Segment sonEmplacement, Regulateur sonRegulateur) {
		super(sens, sonEmplacement, sonRegulateur);
	}

    /**
     * Modifie le comportement du vehicule en tete en fonction de l'etat de la semaphore
     */
	abstract void comportement();

	/**
	 * Modifie l'etat de la semaphore
	 */
	abstract void changement();
};