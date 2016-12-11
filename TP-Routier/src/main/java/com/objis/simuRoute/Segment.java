package com.objis.simuRoute;

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
	
	public String getNomSegment()
	{
		return nomSegment;
	}
	
	public String toString()
	{
		return "Segment"+ nomSegment + " ("+longueur+" ul)";
	}

	public void setSaSignalisation(Semaphore[] saSignalisation) 
	{
		this.saSignalisation=saSignalisation;
	}
	
	public Semaphore[] getSaSignalisation() 
	{
		return saSignalisation;
	}
}
