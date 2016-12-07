package com.objis.simuRoute;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * @author Patrice Camousseigt
 */
public class TestAlgoChacunSonTour extends TestCase {

    private final static boolean afficherTest = false;
    private final static int COMPTEUR_TEST = 50;

    private AlgoChacunSonTour algoChacunSonTour = new AlgoChacunSonTour();

    /**
     * Test sur trois semaphores dynamiques : 1 FeuBicol, 2 FeuTriCol
     */
    public void testAlgoChacunSonTour() {
        ArrayList<SemaphoreDynamique> arraySem = new ArrayList<SemaphoreDynamique>();
        // on cree 3 semaphores dynamiques avec des sens et segments au hasard
        SemaphoreDynamique sem0 = new FeuBiCol(EnumSens.POSITIF, new Segment(1));
        SemaphoreDynamique sem1 = new FeuTriCol(EnumSens.POSITIF, new Segment(1));
        SemaphoreDynamique sem2 = new FeuTriCol(EnumSens.POSITIF, new Segment(1));
        arraySem.add(sem0);
        arraySem.add(sem1);
        arraySem.add(sem2);

        // on test un algo qui ignore les capteurs
        ArrayList<Capteur> arrayCapt = new ArrayList<Capteur>();
        arrayCapt.add(new CaptPresence());
        Regulateur regulateur = new Regulateur(arrayCapt, arraySem, algoChacunSonTour);

        for(int i = 0; i < COMPTEUR_TEST; i++){
            testAlgoEtape(regulateur);
        }
    }

    /**
     * Test sur un seul semaphore dynamique
     */
    public void testAlgoUnSeulSemaphoreDynamique() {
        ArrayList<SemaphoreDynamique> arraySem = new ArrayList<SemaphoreDynamique>();
        // on cree 1 seul semaphore dynamique avec un sens et un segment au hasard
        SemaphoreDynamique sem0 = new FeuBiCol(EnumSens.POSITIF, new Segment(1));
        arraySem.add(sem0);

        // on test un algo qui ignore les capteurs
        ArrayList<Capteur> arrayCapt = new ArrayList<Capteur>();
        arrayCapt.add(new CaptPresence());
        Regulateur regulateur = new Regulateur(arrayCapt, arraySem, algoChacunSonTour);

        for(int i = 0; i < COMPTEUR_TEST; i++){
            testAlgoEtape(regulateur);
        }
    }


    /**
     * Test qu'il n'y a pas d'autres semaphores dynamiques que celui actuellement utilise en etat d'autorisation
     * @param regulateur le regulateur utilise
     */
    private void testAlgoEtape(Regulateur regulateur){
        int indiceSemaphoreCourant = algoChacunSonTour.getIndiceSemaphoreCourant();

        for(int i = 0; i < regulateur.sesSemaphoreDynamique.size(); i++){

            if(afficherTest) affichageSemaphore(regulateur, i);

            int valeur_attente = regulateur.sesSemaphoreDynamique.get(i).getValue();

            // dans cet algo, tous les autres semaphores autre que celle en train detre traite doivent etre en etat INTERDICTION
            if(i != indiceSemaphoreCourant){
                assertEquals(0, valeur_attente);
                assertEquals(EnumSemaphoreEtatCourant.INTERDICTION,
                        regulateur.sesSemaphoreDynamique.get(i).getKey().semaphoreEtatCourant);

            }
        }

        regulateur.regulation();
        System.out.println(" ");
    }

    private void affichageSemaphore(Regulateur regulateur, int i){
        System.out.print(regulateur.sesSemaphoreDynamique.get(i).getKey().semaphoreEtatCourant
                + " : "
                +  regulateur.sesSemaphoreDynamique.get(i).getValue() + " - ");
    }

}
