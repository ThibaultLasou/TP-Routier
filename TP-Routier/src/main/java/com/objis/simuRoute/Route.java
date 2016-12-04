package com.objis.simuRoute;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Route 
{
	int longueur;
	ArrayList<LinkedList<Vehicule>> sesVehicules;
	
	public Route(int l)
	{
		this.longueur = l;
		sesVehicules = new ArrayList<LinkedList<Vehicule>>();
		sesVehicules.add(EnumSens.NEGATIF.ind, new LinkedList<Vehicule>());
		sesVehicules.add(EnumSens.POSITIF.ind, new LinkedList<Vehicule>());
	}

	Vehicule getVehicule(int pos, EnumSens sens)//throws ErreurPositionVoiture
	{
		for(Vehicule v : sesVehicules.get(sens.ind))
		{
			if(v.getPosition() == pos)
			{
				return v;
			}
		}
		return null;
		//throw new ErreurPositionVoiture("Pas de voiture a cette position");
	}

	int placeLibre(Vehicule vehic) throws ErreurPositionVoiture
	{
		int ind = sesVehicules.get(vehic.getSens().ind).indexOf(vehic)-1;
		if (ind<0)
		{
			return this.longueur-1 - vehic.getPosition();
		}
		Vehicule prec = sesVehicules.get(vehic.getSens().ind).get(ind);
		return vehic.getPosition() - (prec.getPosition()+prec.longueur-1);
	}
	
	boolean estLibre(EnumSens sens)
	{
		// le premier de la liste est-il à une extrémité ?
		return !estFin(sesVehicules.get(sens.ind).getFirst().getPosition(), sens);
	}
	
	boolean estFin(int pos, EnumSens sens)
	{
		return (pos == 0 && sens == EnumSens.NEGATIF) || 
				(pos == longueur-1 && sens == EnumSens.POSITIF);
	}
	
	void entreeRoute(Vehicule v)
	{
		v.getSaRoute().sesVehicules.get(v.getSens().ind).pollFirst();
		this.sesVehicules.get(v.getSens().ind).addLast(v);
		v.setSaRoute(this);
		if(v.getSens() == EnumSens.POSITIF)
		{
			v.setPosition(0);
		}
		else
		{
			v.setPosition(longueur-1);
		}
		v.setEtapeSuivante(this.segSuivant(v));
	}
	
	void finRoute(Vehicule v)
	{
		
	}
	
	abstract Route segSuivant(Vehicule v);
	
	
}
