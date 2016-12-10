package com.objis.simuRoute;

import com.objis.simuRoute.AlgoRegulation.AlgoDectectionPresenceFIFO;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * @author Patrice Camousseigt
 */
public class TestAlgoDetectionPresenceFIFO extends TestCase {

    private final static boolean afficherTest = false;
    private final static int COMPTEUR_TEST = 50;

    private AlgoDectectionPresenceFIFO algoDectectionPresenceFIFO = new AlgoDectectionPresenceFIFO();
    private Regulateur regulateur;

    public void testAjoutFile(){
        Segment segment0 = new Segment(1,"route N");
        Segment segment1 = new Segment(1,"route N");
        Segment segment2 = new Segment(1,"route N");

        ArrayList<SemaphoreDynamique> sems = new ArrayList<SemaphoreDynamique>();
        SemaphoreDynamique semaphoreDynamique0 = new FeuBiCol(EnumSens.POSITIF, segment0);
        SemaphoreDynamique semaphoreDynamique1 = new FeuBiCol(EnumSens.POSITIF, segment1);
        SemaphoreDynamique semaphoreDynamique2 = new FeuBiCol(EnumSens.POSITIF, segment2);
        sems.add(semaphoreDynamique0);
        sems.add(semaphoreDynamique1);
        sems.add(semaphoreDynamique2);

        Semaphore[] saSignalisation0 = new Semaphore[2];
        saSignalisation0[EnumSens.POSITIF.getInd()] = semaphoreDynamique0;
        saSignalisation0[EnumSens.NEGATIF.getInd()] = null;
        segment0.setSaSignalisation(saSignalisation0);

        ArrayList<Capteur> capts = new ArrayList<Capteur>();
        Capteur capteur0 = new CaptPresence(segment0, regulateur, 0, EnumSens.POSITIF);
        Capteur capteur1 = new CaptPresence(segment1, regulateur, 0, EnumSens.POSITIF);
        Capteur capteur2 = new CaptPresence(segment2, regulateur, 0, EnumSens.POSITIF);
        capts.add(capteur0);
        capts.add(capteur1);
        capts.add(capteur2);


        regulateur = new Regulateur(capts, sems, algoDectectionPresenceFIFO);
        assertEquals(true, algoDectectionPresenceFIFO.getSemDynFile().isEmpty());

        regulateur.regulation();
        assertEquals(true, algoDectectionPresenceFIFO.getSemDynFile().isEmpty());

        ((CaptPresence)(capteur0)).setPresence(true);
        regulateur.regulation();
        assertEquals(1, algoDectectionPresenceFIFO.getSemDynFile().size());

        ((CaptPresence)(capteur1)).setPresence(true);
        regulateur.regulation();
        assertEquals(2, algoDectectionPresenceFIFO.getSemDynFile().size());

        ((CaptPresence)(capteur2)).setPresence(true);
        regulateur.regulation();
        assertEquals(3, algoDectectionPresenceFIFO.getSemDynFile().size());

        ((CaptPresence)(capteur0)).setPresence(false);
        ((CaptPresence)(capteur1)).setPresence(false);
        ((CaptPresence)(capteur2)).setPresence(false);
        int tempsSemaphoreCycleComplet = algoDectectionPresenceFIFO.getTEMPS_AUTORISATION()
                + algoDectectionPresenceFIFO.getTEMPS_ATTENTION()
                + algoDectectionPresenceFIFO.getTEMPS_INTERDICTION();
        for(int i = 0; i < 10*tempsSemaphoreCycleComplet; i++){}
        //assertEquals(0, algoDectectionPresenceFIFO.getSemDynFile().size());

    }
}
