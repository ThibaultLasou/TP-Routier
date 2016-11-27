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
	Sens sens;
	
	abstract void getMesure()throws ErreurPositionVoiture ;
}
