package com.objis.simuRoute;

/**
 * @author margauxdebure
 */
public class JonctionSimple extends Jonction
{

	@Override
	public Segment segSuivant(Vehicule v) throws ErreurModeleException //throws WTFException
	{
		for(Segment s : sesAcces)
		{
			if(s != v.getSaRoute())
			{
				return s;
			}
			else
			{
				throw new ErreurModeleException(this.toString() + ": Erreur segSuivant");
			}
		}
		return new Segment(1,"route N"); // a modifier
	}

	@Override
	public String toString() {
		return "JonctionSimple [sesAcces=" + sesAcces + ", sesVehicules="
				+ sesVehicules + "]";
	};
	
	
}
