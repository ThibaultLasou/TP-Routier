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

	Vehicule getVehicule(int pos, EnumSens sens)
	{
		for(Vehicule v : sesVehicules.get(sens.ind))
		{
			if(v.getPosition() == pos)
			{
				return v;
			}
		}
		return null;
	}

	Vehicule getVehiculeDevant(int pos, EnumSens sens)
	{
		Vehicule v = sesVehicules.get(sens.ind).getLast();
		int i = sesVehicules.get(sens.ind).size();
		for(i=i;i>0;i--)
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

	/* calcule si une voiture dépasse de cette route déborde sur r*/
	int debordement(Route r)
	{
		for(LinkedList<Vehicule> a : sesVehicules)
		{
			Vehicule v = a.getLast();
			if(v.getRoutePrec() == r)
			{
				int finVoiture = v.getPosition()+(v.longueur-1*v.getSens().direction);//trouve la position du dernier "bout" de la voiture
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
		return 0;
	}
	
	/* indique si la position dans ce sens est libre */
	boolean estLibre(EnumSens sens, int pos)
	{
		//voir si une voiture devant arrive pas jusque ici
		if(getVehicule(pos, sens) == null)
		{
			Vehicule v = getVehiculeDevant(pos,sens);
			if(v != null)
			{
				if(v.getPosition() + (v.longueur*sens.direction) != pos)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/* indique si l'extrémité entrante dans ce sens est libre */
	boolean estLibre(EnumSens sens)
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
	boolean estFin(int pos, EnumSens sens)
	{
		return (pos == 0 && sens == EnumSens.NEGATIF) || 
				(pos == longueur-1 && sens == EnumSens.POSITIF);
	}
	
	/* action à l'entrée d'une route */
	void entreeRoute(Vehicule v)
	{
		v.getSaRoute().sesVehicules.get(v.getSens().ind).pollFirst(); // enlève la voiture de sa route actuelle
		this.sesVehicules.get(v.getSens().ind).addLast(v); // la met dans la nouvelle
		v.setSaRoute(this); // idem mais du point de vue de route
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
	void finRoute(Vehicule v) {}
	
	abstract Route segSuivant(Vehicule v);
}
