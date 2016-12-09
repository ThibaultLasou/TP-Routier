package com.objis.simuRoute;

public class Vehicule 
{
	static int nbVehicules = 0;
	int id;
	int longueur;
	int vitesse_max;
	
	Route saRoute;
	Route etapeSuiv;
	Route routePrec;
	int position;
	int vitesse_inst;
	EnumSens sens;
	
	public Vehicule(int longueur, int vitesse_max, Route routeDepart, int pos, EnumSens sensDepart)
	{
		this.id = Vehicule.nbVehicules;
		this.longueur = longueur;
		this.vitesse_max = vitesse_max;
		this.saRoute = routeDepart;
		this.sens = sensDepart.sensInverse();
		this.routePrec = saRoute.segSuivant(this); // on fait semblant d'aller en sens inverse
		this.sens = sensDepart;
		this.etapeSuiv = saRoute.segSuivant(this);
		this.position = pos;
		Vehicule.nbVehicules++;
	}

	void avancer() // boolean
	{
		/* soit vitesse max définie par une sémaphore, soit max du vehicule*/
		if(saRoute.estFin(getPosition(), getSens()))
		{
			saRoute.finRoute(this);
		}
		else
		{
			vitesse_inst = vitesse_max;
		}
		
		int i;
		for(i=1;i<vitesse_inst;)
		{
			/*si on est à la fin d'une route => vérifie sémaphore */
			if(saRoute.estFin(getPosition(), getSens()))
			{
				saRoute.finRoute(this);
				continue;//pour ne pas prendre les autres en compte
			}
			/* sinon on regarde si la route est libre devant */
			else if(saRoute.getVehicule(getPosition()+getSens().direction, getSens()) == null)
			{
				position++;
				i++;
			}
			/* ou si la route de devant est libre*/
			if(saRoute.estFin(getPosition(), getSens()))
			{
				if(etapeSuiv.estLibre(getSens()))
				{
					etapeSuiv.entreeRoute(this);
					i++;
				}
			}
		}
	}
	
	void accelerer()
	{
		this.vitesse_inst = vitesse_max;
	}
	
	void ralentir()
	{
		this.vitesse_inst = this.vitesse_inst/2;
	}
	
	void arreter()
	{
		this.vitesse_inst = 0;
	}
	
	boolean estArrete()
	{
		if(this.vitesse_inst == 0)
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
		this.etapeSuiv = r;
	}
	
	public Route getEtapeSuivante()
	{
		return this.etapeSuiv;
	}
	
	public Route getSaRoute() 
	{
		return saRoute;
	}
	
	public void setSaRoute(Route saRoute) 
	{
		this.saRoute = saRoute;
	}
	
	public Route getRoutePrec()
	{
		return this.routePrec;
	}
	
	public int getPosition() 
	{
		return position;
	}
	
	public void setPosition(int position) 
	{
		this.position = position;
	}
	
	public int getVitesse_inst() 
	{
		return vitesse_inst;
	}
	public void setVitesse_inst(int vitesse_inst) 
	{
		this.vitesse_inst = vitesse_inst;
	}
	
	public EnumSens getSens() 
	{
		return sens;
	}
	
	public void setSens(EnumSens sens) 
	{
		this.sens = sens;
	}
}