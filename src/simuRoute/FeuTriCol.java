package simuRoute;

public class FeuTriCol extends Feu 
{

    /**
     * Constructeur
     * @param couleur la couleur initiale
     */
    public FeuTriCol(EnumColor couleur) {
        super(couleur);
    }

    /**
     * Effectue le changement de couleur
     */
    @Override
    public void changement() {
		switch (this.couleur){
			case ORANGE:
				this.couleur = EnumColor.ROUGE;
				break;
			default: // si le feu n'est pas orange, il est rouge ou vert
				super.changement();
				break;
		}
	}

    /**
     * Modifie le fonctionnement du vehicule en tete en fonction de la couleur du feu
     */
    @Override
    public void comportement()
    {
        switch (this.couleur){
            case ORANGE:
                this.sonEmplacement.voitureEnTete().ralentir();
                break;
            default: // si le feu n'est pas orange, il est rouge ou vert
                super.comportement();
                break;
        }
    }


}
