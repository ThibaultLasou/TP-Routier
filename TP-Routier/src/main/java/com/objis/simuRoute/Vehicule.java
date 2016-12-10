package com.objis.simuRoute;
/**
 * Cette classe représente un véhicule circulant sur le réseau
 * @author tlasou
 *
 */
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
	
	/**
	 * Constructeur qui détermine aléatoirement la route précédente et suivante
	 * @param longueur - la longueur du véhiculé 
	 * @param vitesse_max - la vitesse maxiamle du véhicule
	 * @param routeDepart - la route sur laquelle le véhicule est postionné
	 * @param pos - sa position sur la route
	 * @param sensDepart - son sens sur la route
	 * @throws ErreurModeleException 
	 */
	public Vehicule(int longueur, int vitesse_max, Route routeDepart, int pos, 
			EnumSens sensDepart) throws ErreurModeleException
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
	
	/**
	 * Constructeur avec route précédente et suivante fournies
	 * @param longueur - la longueur du véhiculé 
	 * @param vitesse_max - la vitesse maxiamle du véhicule
	 * @param routeDepart - la route sur laquelle le véhicule est postionné
	 * @param routeAvant - la route précédente
	 * @param routeAprès - la route suivante
	 * @param pos - sa position sur la route
	 * @param sensDepart - son sens sur la route
	 */
	public Vehicule(int longueur, int vitesse_max, Route routeDepart, 
			Route routeAvant, Route routeAprès, int pos, EnumSens sensDepart)
	{
		this.id = Vehicule.nbVehicules;
		this.longueur = longueur;
		this.vitesse_max = vitesse_max;
		this.saRoute = routeDepart;
		this.routePrec = routeAvant;
		this.etapeSuiv = routeAprès;
		this.sens = sensDepart;
		this.position = pos;
		Vehicule.nbVehicules++;
	}

	/**
	 * Fait avancer la voiture du maximum qu'elle peut parcourir pendant 
	 * l'unité de temps
	 * @throws ErreurModeleException 
	 */
	public void avancer() throws ErreurModeleException
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
			/* ou si la route de devant est libre */
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
	
	/**
	 * Mets la vitesse instantanée au maximum
	 */
	public void accelerer()
	{
		this.vitesse_inst = vitesse_max;
	}
	
	/**
	 * Divise la vitesse instantanée par 2
	 */
	public void ralentir()
	{
		this.vitesse_inst = this.vitesse_inst/2;
	}
	
	/**
	 * Mets la vitesse instantanée à 0
	 */
	public void arreter()
	{
		this.vitesse_inst = 0;
	}
	
	/**
	 * Indique si la voiture est arrêtée
	 * @return - renvoie true si la voiture est arrêté, false sinon
	 */
	public boolean estArrete()
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