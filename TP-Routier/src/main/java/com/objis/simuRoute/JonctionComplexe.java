package com.objis.simuRoute;
import java.util.Random;

/**
 * 
 */
public class JonctionComplexe extends Jonction
{
    @Override
    public Segment segSuivant(Vehicule v) throws ErreurModele 
    {
        if (sesAcces.size() == 1 && sesAcces.get(0) == v.getSaRoute()) 
        {
        	throw new ErreurModele("Le seul segment suivant possible est le segment actuel.");
        }//Pour éviter de tomber dans une boucle infnie

        int indice;

        do 
        {
            Random aleatoire = new Random();
            indice = aleatoire.nextInt(sesAcces.size() - 1);
        }
        while (sesAcces.get(indice) == v.getRoutePrec()); //On cherche un segment aléatoirement, mais différent du segment actuel sur lequel se trouve la voiture

        return sesAcces.get(indice);
    }

	@Override
	public String toString() 
	{
		String retour="";
		for(int i=0;i<sesAcces.size();i++)
		{
			retour = retour+sesAcces.get(i).getNomSegment()+", ";
		}
		retour = retour.substring(0, retour.length()-2); // pour supprimer la dernière virgule de la chaine de caracteres.
		
		return "Jonction Complexe entre : "+retour;
	}
}
