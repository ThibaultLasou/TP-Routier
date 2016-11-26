package simuRoute;



public abstract class Capteur 
{
	Segment sonEmplacement;
	Regulateur sonRegulateur;
	int position;
	Sens sens;
	
	abstract void getMesure()throws ErreurPositionVoiture ;
}
