package com.objis.simuRoute;

/**
 * @author margauxdebure
 */
public class JonctionSimple extends Jonction
{

	@Override
	public Segment segSuivant(Vehicule v) throws ErreurModele
	{
		for(Segment s : sesAcces)
		{
			if(s != v.getSaRoute())
			{
				return s;
			}
		}
		throw new ErreurModele(this.toString() + ": Erreur segSuivant");
	}

	@Override
	public String toString() {
		String retour="";
		for(int i=0;i<sesAcces.size();i++)
		{
			retour = retour+sesAcces.get(i).getNomSegment();
		}
		return "Jonction simple sur : "+retour;
	};
	
	
}
