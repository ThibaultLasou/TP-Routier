package simuRoute;

public class Stop extends Panneau 
{

	/**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     * @param regulateur son Regulateur associe
     * @param couleur sa couleur initiale
	 */
	public Stop(Sens sens, Segment segment, Regulateur regulateur, EnumColor couleur) {
		super(sens, segment, regulateur);
	}

	/**
	 * Arrete systematiquement le vehicule
	 */
	@Override
	void comportement() 
	{
        this.sonEmplacement.voitureEnTete().arreter();
	}
}
