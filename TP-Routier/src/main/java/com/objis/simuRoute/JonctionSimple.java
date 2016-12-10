package com.objis.simuRoute;

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
		return new Segment(1); // a modifier
	};
}
