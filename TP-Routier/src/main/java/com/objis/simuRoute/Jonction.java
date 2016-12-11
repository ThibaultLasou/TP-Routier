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
		sesAcces = new ArrayList<Segment>();
	}

	@Override
	public void finRoute(Vehicule v)
	{
		EnumSens nextSens;
		try {
			nextSens = v.getEtapeSuivante().getSensEntrée(this);
			if(nextSens != v.getSens())
			{
				if(estLibre(nextSens))
				{
					this.sesVehicules.get(v.getSens().ind).poll();
					this.sesVehicules.get(nextSens.ind).offerLast(v);
					v.setSens(nextSens);
				}
			}
		} catch (ErreurModele e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public abstract Segment segSuivant(Vehicule v) throws ErreurModele;
	
	public EnumSens getSensEntrée(Route r) throws ErreurModele
	{
			return r.getSensEntrée(this).sensInverse();
	}
}
