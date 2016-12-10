package com.objis.simuRoute;

/**
 * Cette exception est levée à la détection d'une incohérence
 * dans le réseau routier
 * @author tlasou
 *
 */
public class ErreurModeleException extends Exception 
{
	String msg;
	
	public ErreurModeleException(String message) 
	{
		msg = message;
	}

}
