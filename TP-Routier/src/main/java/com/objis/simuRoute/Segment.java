package com.objis.simuRoute;

public class Segment extends Route
{
	Jonction[] sesExtremites;
	Semaphore[] saSignalisation;
	
	public Segment(int l) 
	{
		super(l);
		sesExtremites = new Jonction[2];
		saSignalisation = new Semaphore[2];
	}

	Vehicule voitureEnTete(EnumSens s)
	{
		return this.sesVehicules.get(s.ind).getFirst();	
	}

	@Override
	Route segSuivant(Vehicule v) 
	{
		return sesExtremites[v.getSens().ind];
	}

	@Override
	void finRoute(Vehicule v) 
	{
		this.saSignalisation[v.getSens().ind].comportement();
	}

	public Jonction[] getSesExtremites() {
		return sesExtremites;
	}

	public Semaphore[] getSaSignalisation() {
		return saSignalisation;
	}
}
