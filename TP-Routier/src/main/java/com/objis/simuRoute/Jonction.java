package com.objis.simuRoute;

import java.util.ArrayList;

/**
 * @author margauxdebure
 */
public abstract class Jonction extends Route 
{
	private static int Longueur = 1;
	protected ArrayList<Segment> sesAcces;

	public Jonction() 
	{
		super(Longueur);
	}

	@Override
	public void finRoute(Vehicule v)
	{
		EnumSens nextSens;
		try {
			nextSens = v.etapeSuiv.getSensEntrée(this);
			if(nextSens != v.getSens())
			{
				if(estLibre(nextSens))
				{
					this.sesVehicules.get(v.getSens().ind).poll();
					this.sesVehicules.get(nextSens.ind).offerLast(v);
					v.setSens(nextSens);
				}
			}
		} catch (ErreurModeleException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public abstract Segment segSuivant(Vehicule v) throws ErreurModeleException;
	
	public EnumSens getSensEntrée(Route r) throws ErreurModeleException
	{
			return r.getSensEntrée(this).sensInverse();
	}
}
