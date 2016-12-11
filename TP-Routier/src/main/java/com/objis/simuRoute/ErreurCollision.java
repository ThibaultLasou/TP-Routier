package com.objis.simuRoute;
/**
 * Cette exception est levée à la détection d'une collision
 * Les Vehicules concernés sont placés dans la variable crashed
 * @author tlasou
 *
 */
public class ErreurCollision extends Exception 
{
	public Vehicule[] crashed;
	
	public ErreurCollision(Vehicule v1, Vehicule v2)
	{
		super();
		crashed = new Vehicule[2];
		crashed[0]=v1;
		crashed[1]=v2;
	}
	
	public ErreurCollision(String msg, Vehicule v1, Vehicule v2)
	{		
		super(msg);
		crashed = new Vehicule[2];
		crashed[0]=v1;
		crashed[1]=v2;
	}
}
