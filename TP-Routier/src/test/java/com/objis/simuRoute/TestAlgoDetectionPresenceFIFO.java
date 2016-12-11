package com.objis.simuRoute;

import com.objis.simuRoute.AlgoRegulation.AlgoDetectionPresenceFIFO;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * @author Patrice Camousseigt
 */
public class TestAlgoDetectionPresenceFIFO extends TestCase {

    // pour afficher sur le terminal les etapes intermediaires
    private final static boolean afficherTest = false;

    // nombre de capteur teste + 1
    private final static int COMPTEUR_TEST = 4;

    private AlgoDetectionPresenceFIFO algoDetectionPresenceFIFO = new AlgoDetectionPresenceFIFO();
    private Regulateur regulateur;

    /**
     * Test sur le traitement de la file
     */
    public void testAjoutFile(){
        Segment segment0 = new Segment(1,"route A");
        Segment segment1 = new Segment(1,"route B");
        Segment segment2 = new Segment(1,"route C");

        ArrayList<SemaphoreDynamique> sems = new ArrayList<SemaphoreDynamique>();
        SemaphoreDynamique semaphoreDynamique0 = new FeuTriCol(EnumSens.POSITIF, segment0);
        SemaphoreDynamique semaphoreDynamique1 = new FeuBiCol(EnumSens.POSITIF, segment1);
        SemaphoreDynamique semaphoreDynamique2 = new FeuTriCol(EnumSens.POSITIF, segment2);
        sems.add(semaphoreDynamique0);
        sems.add(semaphoreDynamique1);
        sems.add(semaphoreDynamique2);

        Semaphore[] saSignalisation0 = new Semaphore[2];
        saSignalisation0[EnumSens.POSITIF.getInd()] = semaphoreDynamique0;
        saSignalisation0[EnumSens.NEGATIF.getInd()] = null;
        segment0.setSaSignalisation(saSignalisation0);
        Semaphore[] saSignalisation1 = new Semaphore[2];
        saSignalisation1[EnumSens.POSITIF.getInd()] = semaphoreDynamique1;
        saSignalisation1[EnumSens.NEGATIF.getInd()] = null;
        segment1.setSaSignalisation(saSignalisation1);
        Semaphore[] saSignalisation2 = new Semaphore[2];
        saSignalisation2[EnumSens.POSITIF.getInd()] = semaphoreDynamique2;
        saSignalisation2[EnumSens.NEGATIF.getInd()] = null;
        segment2.setSaSignalisation(saSignalisation2);

        ArrayList<Capteur> capts = new ArrayList<Capteur>();
        Capteur capteur0 = new CaptPresence(segment0, regulateur, 0, EnumSens.POSITIF);
        Capteur capteur1 = new CaptPresence(segment1, regulateur, 0, EnumSens.POSITIF);
        Capteur capteur2 = new CaptPresence(segment2, regulateur, 0, EnumSens.POSITIF);
        capts.add(capteur0);
        capts.add(capteur1);
        capts.add(capteur2);


        // tant que les capteurs ne detectent pas de vehicule, la file sera vide
        regulateur = new Regulateur(capts, sems, algoDetectionPresenceFIFO);
        assertEquals(true, algoDetectionPresenceFIFO.getSemDynFile().isEmpty());
        regulateur.regulation(); // et peut importe si on regule dans le vide
        assertEquals(true, algoDetectionPresenceFIFO.getSemDynFile().isEmpty());

        // on modifie la presence par un setter pour nos tests
        // on passe la presence en true
        // pour chaque nouveau capteur qui detecte une presence de vehicule
        // on s'attend a ce que la file s'accroit de un a chaque fois
        ((CaptPresence)(capteur0)).setPresence(true);
        regulateur.regulation();
        assertEquals(1, algoDetectionPresenceFIFO.getSemDynFile().size());

        ((CaptPresence)(capteur1)).setPresence(true);
        regulateur.regulation();
        assertEquals(2, algoDetectionPresenceFIFO.getSemDynFile().size());

        ((CaptPresence)(capteur2)).setPresence(true);
        regulateur.regulation();
        assertEquals(3, algoDetectionPresenceFIFO.getSemDynFile().size());

        // on repasse les capteurs a false

        ((CaptPresence)(capteur0)).setPresence(false);
        ((CaptPresence)(capteur1)).setPresence(false);
        ((CaptPresence)(capteur2)).setPresence(false);
        int tempsSemaphoreCycleComplet = algoDetectionPresenceFIFO.getTEMPS_AUTORISATION()
                + algoDetectionPresenceFIFO.getTEMPS_ATTENTION()
                + algoDetectionPresenceFIFO.getTEMPS_INTERDICTION();

        // on attend la duree COMPTEUR_TEST*tempsSemaphoreCycleComplet pour que les semaphores soient tous traites
        for(int i = 0; i < COMPTEUR_TEST*tempsSemaphoreCycleComplet; i++){
            if(afficherTest) print();
            regulateur.regulation();
        }

        // la file doit etre desormais vide
        assertEquals(0, algoDetectionPresenceFIFO.getSemDynFile().size());

    }


    /**
     * Affichage des resultats intermediaires
     */
    private void print(){
        for(Pair<SemaphoreDynamique, Integer> pair : algoDetectionPresenceFIFO.getSemDynFile()){
            System.out.print(pair.toString());
            System.out.print(" - ");
        }
        System.out.println();
    }
}
