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
	
	void entreeRoute(Vehicule v)
	{
		super.entreeRoute(v);
		int i = sesAcces.indexOf(v.sonEtat.etapeSuiv);
		if(sesAcces.get(i).sesExtremites[0] == this)
		{
			v.setSens(EnumSens.POSITIF);
		}
		else
		{
			v.setSens(EnumSens.NEGATIF);
		}
	}
	
	abstract Segment segSuivant(Vehicule v);
}
