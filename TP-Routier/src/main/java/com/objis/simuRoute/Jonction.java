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
	
	abstract Segment segSuivant(Vehicule v);
}