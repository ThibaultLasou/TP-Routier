package com.objis.simuRoute;

/**
 * Les semaphores dynamiques
 * @author Patrice Camousseigt
 */
public abstract class SemaphoreDynamique extends Semaphore
{
	/**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param sonEmplacement l'emplacement sur le Segment
	 */
	public SemaphoreDynamique(EnumSens sens, Segment sonEmplacement) {
		super(sens, sonEmplacement);
	}

    /**
     * Modifie le comportement du vehicule en tete en fonction de l'etat de la semaphore
     */
	abstract void comportement();

	/**
	 * Modifie l'etat de la semaphore
	 */
	public abstract void changement();

    /**
     * Remet la semaphore a son état d'originie souhaitee
     */
	abstract void reinitialisation();

	/**
	 * Retourne si l'etat courant est INTERDICTION ou non
	 * @return true si l'etat courant est INTERDICTION
	 */
	public boolean etatCourantIsInterdiction(){
	    return this.semaphoreEtatCourant == EnumSemaphoreEtatCourant.INTERDICTION;
    }

    /**
     * Retourne si l'etat courant est ATTENTION ou non
     * @return true si l'etat courant est ATTENTION
     */
    public boolean etatCourantIsAttention(){
        return this.semaphoreEtatCourant == EnumSemaphoreEtatCourant.ATTENTION;
    }

    /**
     * Retourne si l'etat courant est AUTORISATION ou non
     * @return true si l'etat courant est AUTORISATION
     */
    public boolean etatCourantIsAutorisation(){
        return this.semaphoreEtatCourant == EnumSemaphoreEtatCourant.AUTORISATION;
    }
};
