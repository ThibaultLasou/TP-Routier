package com.objis.simuRoute;

import junit.framework.TestCase;

/**
 * Test pour Stop
 * @author Patrice Camousseigt
 */
public class TestStop extends TestCase {

    private final int VITESSE_MAX = 10;
    private final int VITESSE_NULLE = 0;

    /**
     * Test l'effet du Stop sur le comportement du vehicule
     */
    public void testComportement(){
        try {
            Segment segment = new Segment(10,"Route N");
            Vehicule vehicule = new Vehicule(1, VITESSE_MAX, segment, 5, EnumSens.NEGATIF);
            segment.addVehicule(vehicule); ;
            Stop stop = new Stop(EnumSens.NEGATIF, segment);

            Semaphore[] semaphores = new Semaphore[2];
            semaphores[EnumSens.NEGATIF.getInd()] = stop;
            semaphores[EnumSens.POSITIF.getInd()] = stop;
            segment.setSaSignalisation(semaphores);

            // le vehicule est a l'arret donc le stop doit lui redonner sa vitesse max
            stop.comportement();
            assertEquals(VITESSE_MAX, vehicule.getVitesse_inst());

            // le vehicule a une vitesse superieure a 0 donc le stop doit lui donner une vitesse nulle
            vehicule.accelerer();
            stop.comportement();
            assertEquals(VITESSE_NULLE, vehicule.getVitesse_inst());

        } catch (ErreurModele erreurModele) {
            erreurModele.printStackTrace();
        }
    }
}
