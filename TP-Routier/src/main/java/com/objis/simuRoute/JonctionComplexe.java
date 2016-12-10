package com.objis.simuRoute;
import java.util.Random;

/**
 * @author margauxdebure
 */
public class JonctionComplexe extends Jonction
{
    @Override
    Segment segSuivant(Vehicule v)
    {
        if (sesAcces.size()==1 && sesAcces.get(0)==v.getSaRoute()){
            //throw new ErreurJonction("Le seul segment suivant possible est le segment actuel.");
            return null;
        }//Pour éviter de tomber dans une boucle infnie

        int indice;

        do
        {
            Random aleatoire = new Random();
            indice = aleatoire.nextInt(sesAcces.size()-1);
            
        }
        while (sesAcces.get(indice) == v.getSaRoute()); //On cherche un segment aléatoirement, mais différent du segment actuel sur lequel se trouve la voiture
        
        return sesAcces.get(indice);
    }
}
