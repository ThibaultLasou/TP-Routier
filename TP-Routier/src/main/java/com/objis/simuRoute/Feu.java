package com.objis.simuRoute;

/**
 * Cette classe représente un feu
 * @author Patrice Camousseigt
 */
public abstract class Feu extends SemaphoreDynamique
{
    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     */
	public Feu(EnumSens sens, Segment segment) {
		super(sens, segment);
	}

    /**
	 * Effectue le changement de couleur
	 */
	@Override
	public void changement() {
		switch (this.semaphoreEtatCourant){
			default: // Si un probleme, par securite, le feu passe au rouge
                this.semaphoreEtatCourant = EnumSemaphoreEtatCourant.INTERDICTION;
				break;
		}
	}

	/**
	 * Modifie le comportement du vehicule en tete en fonction de la couleur du feu
	 */
	@Override
	public void comportement() {
		switch (this.semaphoreEtatCourant){
            case INTERDICTION:
				this.sonEmplacement.voitureEnTete(this.sens).arreter();
				break;
            case AUTORISATION:
				this.sonEmplacement.voitureEnTete(this.sens).accelerer();
				break;
			default: // Si un probleme, par securite, la voiture s'arrete
				this.sonEmplacement.voitureEnTete(this.sens).arreter();
				break;
		}
	}

    /**
     * Reinitialise le feu en le passant le feu au rouge
     */
    @Override
    void reinitialisation() {
        this.semaphoreEtatCourant = EnumSemaphoreEtatCourant.INTERDICTION;
    }


}
