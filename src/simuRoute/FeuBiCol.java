package simuRoute;

public class FeuBiCol extends Feu 
{
    /**
     * Constructeur
     * @param couleur la couleur initiale
     */
    public FeuBiCol(EnumColor couleur) {
        super(couleur);
    }

    /**
     * Effectue le changement de couleur
     */
    @Override
    public void changement() {
        switch (this.couleur){
            case VERT:
                this.couleur = EnumColor.ROUGE;
                break;
            default:
                super.changement();
                break;
        }
    }

}
