package com.objis.simuRoute;

import java.util.LinkedList;

public class Segment extends Route
{
	private Jonction[] sesExtremites;
	private Semaphore[] saSignalisation;
	private String nomSegment;
	
	public Segment(int l,String nom) 
	{
		super(l);
		sesExtremites = new Jonction[2];
		saSignalisation = new Semaphore[2];
		nomSegment = nom;
	}

	Vehicule voitureEnTete(EnumSens s)
	{
		return this.sesVehicules.get(s.ind).getFirst();	
	}
	
	public EnumSens getSensEntrée(Route r) throws ErreurModele
	{
		for(EnumSens sens : EnumSens.values())
		{
			if(sesExtremites[sens.ind]==r)
			{
				return sens.sensInverse();
			}
		}
		throw new ErreurModele(r.toString() +"n'est pas adjacente à" +this.toString());
	}

	@Override
	public Route segSuivant(Vehicule v) 
	{
		return sesExtremites[v.getSens().ind];
	}

	@Override
	public void finRoute(Vehicule v) 
	{
		if(this.saSignalisation[v.getSens().ind] != null)
		{
			this.saSignalisation[v.getSens().ind].comportement();
		}
	}
	
	public void addExtremite(Jonction j, EnumSens sens)
	{
		if(sesExtremites[sens.ind] != null)
		{
			System.out.println("Attention : remplacement de jonction ("+nomSegment+", "+ sens);
		}
		sesExtremites[sens.ind] = j;
		j.sesAcces.add(this);
	}
	
	public String getNomSegment()
	{
		return nomSegment;
	}
	
	public String toString()
	{
		return "Segment "+ nomSegment + " ("+longueur+" ul)";
	}

	public void setSaSignalisation(Semaphore[] saSignalisation) 
	{
		this.saSignalisation=saSignalisation;
	}
	
	public Semaphore[] getSaSignalisation() 
	{
		return saSignalisation;
	}

	@Override
	public boolean estLibre(EnumSens sens, int pos) 
	{
		
		if(getVehicule(pos, sens) == null)
		// pas de vehic à cet endroit
		{/*
			if(sesExtremites[sens.ind].debordement(this) == 0 || sesExtremites[sens.ind].debordement(this)*sens.sensInverse().direction + longueur * sens.ind != pos)
			// pas de voiture d'une autre route arrivant ici
			{
				Vehicule v = getVehiculeDevant(pos, sens);
				if(v == null || v.getPosition() + (v.getLongueur()*sens.direction) != pos) 
				// pas de voiture sur ce segment débordant sur pos
				{*/
					return true;
				}/*
			}
		}*/
		return false;
	}
	
	/* calcule si une voiture dépasse de cette route déborde sur r*/
	public int debordement(Route r)
	{
		for(LinkedList<Vehicule> a : sesVehicules)
		{
			if(a.size() != 0)
			{
				Vehicule v = a.getLast();
				if(v.getRoutePrec() == r)
				{
					int finVoiture = v.getPosition()+(v.getLongueur()-1*v.getSens().direction);//trouve la position du dernier "bout" de la voiture
					if(v.getSens() == EnumSens.POSITIF)
					{
						if(finVoiture < 0)
						{
							return 0-finVoiture;
						}
						else
						{
							return 0;
						}
					}
					else
					{
						if(finVoiture >= longueur)
						{
							return finVoiture-longueur-1;
						}
						else
						{
							return 0;
						}
					}
				}
				else
				{
					continue;
				}
			}
		}
		return 0;
	}
}
