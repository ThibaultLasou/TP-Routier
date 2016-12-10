package com.objis.simuRoute;

/**
 * @author margauxdebure
 */
public class JonctionSimple extends Jonction
{
    @Override
    Segment segSuivant(Vehicule v)
    {
        for(Segment s : sesAcces)
        {
            if(s != v.getSaRoute())
            {
                return s;
            }
        }
        //throw new ErreurJonction();
        return null;
    };
}
