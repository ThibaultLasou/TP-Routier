package simuRoute;

public abstract class Semaphore 
{
	Sens sens;
	Segment sonEmplacement;
	Regulateur sonRegulateur;
	
	abstract void comportement();
}
