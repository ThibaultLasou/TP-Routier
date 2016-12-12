package com.objis.simuRoute;

/**
 * Cette classe représente un feu bicolore
 * @author Patrice Camousseigt
 */
public class FeuBiCol extends Feu 
{

    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     */
    public FeuBiCol(EnumSens sens, Segment segment) {
        super(sens, segment);
    }

    /**
     * Effectue le changement de couleur du feu
     */
    @Override
    public void changement() {
        switch (this.semaphoreEtatCourant){
            case AUTORISATION:
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

    @Override
    public String toString() {
        return "Le feu BiColore sur la : "+ sonEmplacement.getNomSegment()+" (sens "+sens+") est " + this.semaphoreEtatCourant.getEnumColor().toString();
    }
}
