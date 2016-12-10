package com.objis.simuRoute;

/**
 * Les semaphores
 * @author Patrice Camousseigt
 */
public abstract class Semaphore 
{
	EnumSens sens;
	Segment sonEmplacement;
	EnumSemaphoreEtatCourant semaphoreEtatCourant;

    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param sonEmplacement l'emplacement sur le Segment
     */
	public Semaphore(EnumSens sens, Segment sonEmplacement) {
		this.sens = sens;
		this.sonEmplacement = sonEmplacement;
		this.semaphoreEtatCourant = EnumSemaphoreEtatCourant.INTERDICTION; // interdit par defaut
	}

    /**
     * Modifie le comportement du vehicule en tete en fionction de l'etat du semaphore
     */
	abstract void comportement();

	public EnumSens getSens() {
		return sens;
	}

	public Segment getSonEmplacement() {
		return sonEmplacement;
	}

	public EnumSemaphoreEtatCourant getSemaphoreEtatCourant() {
		return semaphoreEtatCourant;
	}
}
