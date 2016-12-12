package com.objis.simuRoute;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Route 
{
	protected int longueur;
	protected ArrayList<LinkedList<Vehicule>> sesVehicules;
	
	public Route(int l)
	{
		this.longueur = l;
		sesVehicules = new ArrayList<LinkedList<Vehicule>>();
		sesVehicules.add(EnumSens.NEGATIF.ind, new LinkedList<Vehicule>());
		sesVehicules.add(EnumSens.POSITIF.ind, new LinkedList<Vehicule>());
	}

	/* renvoie le véhicule à cette position dans ce sens */
	public Vehicule getVehicule(int pos, EnumSens sens)
	{
		for(Vehicule v : sesVehicules.get(sens.ind))
		{
			if(v.getPosition() == pos)
			{
				return v;
			}
		}
		Vehicule v = getVehiculeDevant(pos, sens);
		if(v != null)
		{
			if(pos*sens.direction >= (v.getPosition()+v.getLongueur()-1*v.getSens().sensInverse().direction)*sens.direction && pos*sens.direction <= v.getPosition()*sens.direction)
			{
				return v;
			}
		}
		return null;
	}

	/* renvoie le premier vehicule devant */
	public Vehicule getVehiculeDevant(int pos, EnumSens sens)
	{
		if(sesVehicules.get(sens.ind).size() == 0)
		{
			return null;
		}
		Vehicule v = sesVehicules.get(sens.ind).getLast();
		for(int i=sesVehicules.get(sens.ind).size()-1;i>0;i--)
		{
			if(v.getPosition()*sens.direction > pos*sens.direction)// pour gérer les deux sens
			{
				return v;
			}
			else
			{
				v = sesVehicules.get(sens.ind).get(i);
			}
		}
		return null;
	}
	
	/* indique si la position dans ce sens est libre */
	public abstract boolean estLibre(EnumSens sens, int pos);
	
	/* indique si l'extrémité entrante dans ce sens est libre */
	public boolean estLibre(EnumSens sens)
	{
		if(sens == EnumSens.POSITIF)
		{
			return estLibre(sens, 0);
		}
		else
		{
			return estLibre(sens, longueur);
		}
	}
	
	/* indique si la position est une extrémité sortante */
	public boolean estFin(int pos, EnumSens sens)
	{
		return (pos == 0 && sens == EnumSens.NEGATIF) || 
				(pos == longueur-1 && sens == EnumSens.POSITIF);
	}
	
	/* action à l'entrée d'une route */
	public void entreeRoute(Vehicule v) throws ErreurModele
	{
		v.getSaRoute().sesVehicules.get(v.getSens().ind).pollFirst(); // enlève la voiture de sa route actuelle
		this.sesVehicules.get(v.getSens().ind).addLast(v); // la met dans la nouvelle
		v.setSaRoute(this);
		if(v.getSens() == EnumSens.POSITIF) // règle la position
		{
			v.setPosition(0);
		}
		else
		{
			v.setPosition(longueur-1);
		}
		v.setEtapeSuivante(this.segSuivant(v)); // détermine sa prochaine étape
	}
	
	/* action à la sortie d'une route */
	public void finRoute(Vehicule v) {}
	
	public abstract Route segSuivant(Vehicule v) throws ErreurModele;
	
	public abstract EnumSens getSensEntrée(Route r) throws ErreurModele;

	public void addVehicule(Vehicule v)
	{
		int i = sesVehicules.get(v.getSens().ind).indexOf(getVehicule(v.getPosition(), v.getSens()));
		if(i==-1)
		{
			i = sesVehicules.get(v.getSens().ind).indexOf(getVehiculeDevant(v.getPosition(), v.getSens()))+1;
		}
		sesVehicules.get(v.getSens().ind).add(i, v);
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