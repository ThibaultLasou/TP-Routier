package com.objis.simuRoute;

/**
 * @author margauxdebure
 */
public class JonctionSimple extends Jonction
{

	@Override
	public Segment segSuivant(Vehicule v) throws ErreurModele //throws WTFException
	{
		for(Segment s : sesAcces)
		{
			if(s != v.getSaRoute())
			{
				return s;
			}
			else
			{
				throw new ErreurModele(this.toString() + ": Erreur segSuivant");
			}
		}
		return new Segment(1,"route N"); // a modifier
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
