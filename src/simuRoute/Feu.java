package simuRoute;

public abstract class Feu extends SemaphoreDynamique 
{
	EnumColor couleur;

	/**
	 * Constructuer
	 * @param couleur la couleur initiale
	 */
	public Feu(EnumColor couleur) {
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
	 * Modifie le fonctionnement du vehicule en tete en fonction de la couleur du feu
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
