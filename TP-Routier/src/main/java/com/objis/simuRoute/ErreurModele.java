package com.objis.simuRoute;

/**
 * Cette exception est levée à la détection d'une incohérence
 * dans le réseau routier
 * @author tlasou
 *
 */
public class ErreurModele extends Exception 
{
	public ErreurModele() 
	{
		super();
	}
	
	public ErreurModele(String message) 
	{
		super(message);
	}

}
