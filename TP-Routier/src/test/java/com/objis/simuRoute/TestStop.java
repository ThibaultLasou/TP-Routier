package com.objis.simuRoute;

import junit.framework.TestCase;

public class TestStop extends TestCase {

    private final static Sens SENS_POSITIF = Sens.POSITIF;

    public Panneau testComportementStop(){
        return new Stop(SENS_POSITIF, new Segment(1), new Regulateur());
    }

    public void testComportement(){
        // Voiture dans le meme sens
        Vehicule vehicule = new Vehicule(); // TODO : implementer vehicule et EtatVehicule pour pouvoir tester
        //assertEquals(testComportementStop(), );
    }

    /*
     * Ici il faudra tester le comportement des vehicules dans les deux sens,
     * c a d que les vehicules dans son sens sarrete et dans lautre lignore
     */

}
