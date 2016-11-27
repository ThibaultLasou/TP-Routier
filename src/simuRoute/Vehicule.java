package simuRoute;

public class Vehicule 
{
	int id;
	int longueur;
	int vitesse_max;
	EtatVehicule sonEtat;
	
	void avancer() // boolean
	{
		
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
	
	public Sens getSens() 
	{
		return sonEtat.sens;
	}
	
	public void setSens(Sens sens) 
	{
		this.sonEtat.sens = sens;
	}
}