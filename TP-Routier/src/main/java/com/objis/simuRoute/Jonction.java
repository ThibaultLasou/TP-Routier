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

	public boolean estLibre(EnumSens sens, int pos) 
	{
		
		if(getVehicule(pos, sens) == null)
		// pas de vehic à cet endroit
		{
			/*Vehicule v = getVehiculeDevant(pos, sens);
			if(v != null && v.getPosition() + (v.getLongueur()*sens.direction) != pos)
			{
				int d = 0;
				for(Segment s : sesAcces)
				{
					d = Math.max(d, s.debordement(this));
				}
				if(d*sens.sensInverse().direction + longueur * sens.ind != pos)
					// pas de voiture d'une autre route arrivant ici
					{
						 
						// pas de voiture sur ce segment débordant sur pos
						{*/
							return true;
						}
					/*}
			}
		}*/
		return false;
	}
}
