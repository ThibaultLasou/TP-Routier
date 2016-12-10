package com.objis.simuRoute;



/**
 *Les capteurs comprennent notamment les capteurs de présence et les capteurs de vitesse. 
 *Un capteur est placé sur un segment dans un sens donné, est associé à un régulateur,et a une position .
 *
 */
public abstract class Capteur 
{
	Segment sonEmplacement;
	Regulateur sonRegulateur;
	int position;
	EnumSens sens;

    public Capteur(Segment sonEmplacement, Regulateur sonRegulateur, int position, EnumSens sens) {
        this.sonEmplacement = sonEmplacement;
        this.sonRegulateur = sonRegulateur;
        this.position = position;
        this.sens = sens;
    }

    abstract void mesurer();
	
	public abstract Object getMesure();

	public abstract boolean isPresence();

    public Segment getSonEmplacement() {
        return sonEmplacement;
    }

    public Regulateur getSonRegulateur() {
        return sonRegulateur;
    }

    public int getPosition() {
        return position;
    }

    public EnumSens getSens() {
        return sens;
    }


}
