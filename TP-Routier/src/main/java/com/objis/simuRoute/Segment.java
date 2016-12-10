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

	public String getNomSegment() {
		return nomSegment;
	}

	public void setNomSegment(String nomSegment) {
		this.nomSegment = nomSegment;
	}

	Vehicule voitureEnTete(EnumSens s)
	{
		return this.sesVehicules.get(s.ind).getFirst();	
	}
	
	public EnumSens getSensEntrée(Route r) throws ErreurModeleException
	{
		for(EnumSens sens : EnumSens.values())
		{
			if(sesExtremites[sens.ind]==r)
			{
				return sens.sensInverse();
			}
		}
		throw new ErreurModeleException(r.toString() +"n'est pas adjacente à" +this.toString());
	}

	@Override
	public Route segSuivant(Vehicule v) 
	{
		return sesExtremites[v.getSens().ind];
	}

	@Override
	public void finRoute(Vehicule v) 
	{
		this.saSignalisation[v.getSens().ind].comportement();
	}

	public Jonction[] getSesExtremites() {
		return sesExtremites;
	}

	public Semaphore[] getSaSignalisation() {
		return saSignalisation;
	}

    public void setSaSignalisation(Semaphore[] saSignalisation) {
        this.saSignalisation = saSignalisation;
    }
}
