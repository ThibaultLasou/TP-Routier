package simuRoute;

public class FeuBiCol extends Feu 
{

    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     * @param regulateur son Regulateur associe
     * @param couleur sa couleur initiale
     */
    public FeuBiCol(Sens sens, Segment segment, Regulateur regulateur, EnumColor couleur) {
        super(sens, segment, regulateur, couleur);
    }

    /**
     * Effectue le changement de couleur du feu
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
