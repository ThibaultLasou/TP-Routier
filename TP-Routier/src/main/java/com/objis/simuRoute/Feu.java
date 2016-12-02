package com.objis.simuRoute;

/**
 * Cette classe représente un feu
 * @author Patrice Camousseigt
 */
public abstract class Feu extends SemaphoreDynamique
{
	EnumColor couleur;

    /**
     * Constructeur
     * @param sens le sens choisi sur le Segment
     * @param segment l'emplacement sur le Segment
     * @param regulateur son Regulateur associe
     * @param couleur sa couleur initiale
     */
	public Feu(Sens sens, Segment segment, Regulateur regulateur, EnumColor couleur) {
		super(sens, segment, regulateur);
		this.couleur = couleur;
	}

    /**
	 * Effectue le changement de couleur
	 */
	@Override
	public void changement() {
		switch (this.couleur){
			case ROUGE:
				this.couleur = EnumColor.VERT;
				break;
			default: // Si un probleme, par securite, le feu passe au rouge
				this.couleur = EnumColor.ROUGE;
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
			case ROUGE:
				this.sonEmplacement.voitureEnTete().arreter();
				break;
			case VERT:
				this.sonEmplacement.voitureEnTete().avancer();
				break;
			default: // Si un probleme, par securite, la voiture s'arrete
				this.sonEmplacement.voitureEnTete().arreter();
				break;
		}
	}

}

