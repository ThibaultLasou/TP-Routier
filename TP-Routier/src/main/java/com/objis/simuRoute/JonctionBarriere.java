package com.objis.simuRoute;

/**
 * @author margauxdebure
 */
public class JonctionBarriere extends Jonction
{

	public JonctionBarriere()
	{
		super();
	}
	@Override
	public Segment segSuivant(Vehicule v)
	{
		// TODO Auto-generated method stub
		return null;
	}
	public String toString()
	{
		
		return "Barriere sur : "+sesAcces.get(0).getNomSegment();
		
	}
}
