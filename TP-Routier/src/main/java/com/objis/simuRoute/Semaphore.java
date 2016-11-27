package com.objis.simuRoute;

public abstract class Semaphore 
{
	Sens sens;
	Segment sonEmplacement;
	Regulateur sonRegulateur;

    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param sonEmplacement l'emplacement sur le Segment
     * @param sonRegulateur son Regulateur associe
     */
	public Semaphore(Sens sens, Segment sonEmplacement, Regulateur sonRegulateur) {
		this.sens = sens;
		this.sonEmplacement = sonEmplacement;
		this.sonRegulateur = sonRegulateur;
	}

    /**
     * Modifie le comportement du vehicule en tete en fionction de l'etat du semaphore
     */
	abstract void comportement();
}
