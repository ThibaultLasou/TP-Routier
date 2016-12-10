package com.objis.simuRoute;

/**
 * @author margauxdebure
 */
public class JonctionBarriere extends Jonction
{
    @Override
    Segment segSuivant(Vehicule v)
    {
        return null; //On retourne null pour signaler que la voiture est arrivée à une barrière et doit disparaître
    }
}
