package com.objis.simuRoute;

/**
 * Cette classe représente un feu tricolore
 * @author Patrice Camousseigt
 */
public class FeuTriCol extends Feu 
{

    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     */
    public FeuTriCol(EnumSens sens, Segment segment) {
        super(sens, segment);
    }

    /**
     * Effectue le changement de couleur du feu
     */
    @Override
    public void changement() {
		switch (this.semaphoreEtatCourant){
            case AUTORISATION:
                this.semaphoreEtatCourant = EnumSemaphoreEtatCourant.ATTENTION;
                break;
            case ATTENTION:
                this.semaphoreEtatCourant = EnumSemaphoreEtatCourant.INTERDICTION;
				break;
            case INTERDICTION:
                this.semaphoreEtatCourant = EnumSemaphoreEtatCourant.AUTORISATION;
                break;
			default:
				super.changement();
				break;
		}
	}

    /**
     * Modifie le comportement du vehicule en tete en fonction de la couleur du feu
     */
    @Override
    public void comportement()
    {
        switch (this.semaphoreEtatCourant){
            case ATTENTION:
                this.sonEmplacement.voitureEnTete(this.sens).ralentir();
                break;
            default:
                super.comportement();
                break;
        }
    }


}
