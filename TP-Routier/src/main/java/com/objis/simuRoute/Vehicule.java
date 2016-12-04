package com.objis.simuRoute;

public class Vehicule 
{
	int id;
	int longueur;
	int vitesse_max;
	EtatVehicule sonEtat;
	
	void avancer() // boolean
	{
		/* soit vitesse max définie par une sémaphore, soit max du vehicule*/
		if(sonEtat.saRoute.estFin(getPosition(), getSens()))
		{
			sonEtat.saRoute.finRoute(this);
		}
		else
		{
			sonEtat.vitesse_inst = vitesse_max;
		}
		
		for(int i=1;i<sonEtat.vitesse_inst;)
		{
			if(sonEtat.saRoute.estFin(getPosition(), getSens()))
			{
				sonEtat.saRoute.finRoute(this);
				continue;
			}
			else if(sonEtat.saRoute.getVehicule(getPosition()+(1*getSens().direction), 
					getSens()) == null)
			{
				sonEtat.position++;
				i++;
			}
			if(sonEtat.saRoute.estFin(getPosition(), getSens()))
			{
				if(sonEtat.etapeSuiv.estLibre(getSens()))
				{
					sonEtat.etapeSuiv.entreeRoute(this);
					i++;
				}
			}
		}
	}
	
	void accelerer()
	{
		this.sonEtat.vitesse_inst = vitesse_max;
	}
	
	void ralentir()
	{
		this.sonEtat.vitesse_inst = this.sonEtat.vitesse_inst/2;
	}
	
	void arreter()
	{
		this.sonEtat.vitesse_inst = 0;
	}
	
	boolean estArrete()
	{
		if(this.sonEtat.vitesse_inst == 0)
		{
			return true;
		}
		else
		{
			return false;	
		}
	}
	
	public void setEtapeSuivante(Route r)
	{
		this.sonEtat.etapeSuiv = r;
	}
	
	public Route getEtapeSuivante()
	{
		return this.sonEtat.etapeSuiv;
	}
	
	public Route getSaRoute() 
	{
		return sonEtat.saRoute;
	}
	
	public void setSaRoute(Route saRoute) 
	{
		this.sonEtat.saRoute = saRoute;
	}
	
	public int getPosition() 
	{
		return sonEtat.position;
	}
	
	public void setPosition(int position) 
	{
		this.sonEtat.position = position;
	}
	
	public int getVitesse_inst() 
	{
		return sonEtat.vitesse_inst;
	}
	public void setVitesse_inst(int vitesse_inst) 
	{
		this.sonEtat.vitesse_inst = vitesse_inst;
	}
	
	public EnumSens getSens() 
	{
		return sonEtat.sens;
	}
	
	public void setSens(EnumSens sens) 
	{
		this.sonEtat.sens = sens;
	}
}
