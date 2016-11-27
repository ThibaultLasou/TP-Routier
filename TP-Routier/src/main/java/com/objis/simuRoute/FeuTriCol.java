package com.objis.simuRoute;

public class FeuTriCol extends Feu 
{

    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     * @param regulateur son Regulateur associe
     * @param couleur sa couleur initiale
     */
    public FeuTriCol(Sens sens, Segment segment, Regulateur regulateur, EnumColor couleur) {
        super(sens, segment, regulateur, couleur);
    }

    /**
     * Effectue le changement de couleur du feu
     */
    @Override
    public void changement() {
		switch (this.couleur){
            case VERT:
                this.couleur = EnumColor.ORANGE;
                break;
			case ORANGE:
				this.couleur = EnumColor.ROUGE;
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
        switch (this.couleur){
            case ORANGE:
                this.sonEmplacement.voitureEnTete().ralentir();
                break;
            default:
                super.comportement();
                break;
        }
    }


}
