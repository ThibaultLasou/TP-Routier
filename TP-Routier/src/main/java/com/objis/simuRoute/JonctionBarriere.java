package com.objis.simuRoute;

/**
 * @author margauxdebure
 */
public class JonctionBarriere extends Jonction
{
	@Override
	public Segment segSuivant(Vehicule v)
	{
		return null;
	}
	public String toString()
	{
		return "Barriere sur : "+sesAcces.get(0).getNomSegment();
		
	}
}
