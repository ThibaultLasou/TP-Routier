package com.objis.simuRoute;

import java.util.ArrayList;

public abstract class Jonction extends Route 
{
	static int Longueur = 1;
	ArrayList<Segment> sesAcces;
	
	public Jonction() 
	{
		super(Longueur);
	}
	
	@Override
	void finRoute(Vehicule v)
	{
		int i = sesAcces.indexOf(v.etapeSuiv);
		EnumSens nextSens;
		if(sesAcces.get(i).sesExtremites[0] == this) // change le sens pour entrer sur la prochaine route
		{
			nextSens = EnumSens.POSITIF;
		}
		else
		{
			nextSens = EnumSens.NEGATIF;
		}
		if(nextSens != v.getSens())
		{
			if(estLibre(nextSens))
			{
				this.sesVehicules.get(v.getSens().ind).poll();
				this.sesVehicules.get(nextSens.ind).offerLast(v);
				v.setSens(nextSens);
			}
		}
	}
	
	abstract Segment segSuivant(Vehicule v);
}
