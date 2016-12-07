package com.objis.simuRoute;

public class JonctionSimple extends Jonction 
{
	@Override
	Segment segSuivant(Vehicule v) //throws WTFException
	{
		for(Segment s : sesAcces)
		{
			if(s != v.getSaRoute())
			{
				return s;
			}
			else
			{
				//TODO trouver un meilleur nom d'exception
				//throw new WTFException()
			}
		}
		return new Segment(1); // a modifier
	};
}
