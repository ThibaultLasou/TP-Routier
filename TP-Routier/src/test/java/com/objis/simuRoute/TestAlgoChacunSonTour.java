package com.objis.simuRoute;

import com.objis.simuRoute.AlgoRegulation.AlgoChacunSonTour;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Test pour AlgoChacunSonTour
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
        SemaphoreDynamique sem0 = new FeuBiCol(EnumSens.POSITIF, new Segment(1,"route N1"));
        SemaphoreDynamique sem1 = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N2"));
        SemaphoreDynamique sem2 = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N3"));
        arraySem.add(sem0);
        arraySem.add(sem1);
        arraySem.add(sem2);

        // on test un algo qui ignore les capteurs
        ArrayList<Capteur> arrayCapt = new ArrayList<Capteur>();
        arrayCapt.add(new CaptPresence(null, null, 0, null));
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
        SemaphoreDynamique sem0 = new FeuBiCol(EnumSens.POSITIF, new Segment(1,"route N4"));
        arraySem.add(sem0);

        // on test un algo qui ignore les capteurs
        ArrayList<Capteur> arrayCapt = new ArrayList<Capteur>();
        arrayCapt.add(new CaptPresence(null, null, 0, null));
        Regulateur regulateur = new Regulateur(arrayCapt, arraySem, algoChacunSonTour);

        for(int i = 0; i < COMPTEUR_TEST; i++){
            testAlgoEtape(regulateur);
        }
    }

    /**
     * Test sur un seul semaphore dynamique
     */
    public void testDeuxAlgoChacunSonTour() {
        // 1ere liste de semaphores dynamiques pour le premier algo
        ArrayList<SemaphoreDynamique> arraySem0 = new ArrayList<SemaphoreDynamique>();
        SemaphoreDynamique sem0 = new FeuBiCol(EnumSens.POSITIF, new Segment(1,"route N5"));
        arraySem0.add(sem0);

        // on test un algo qui ignore les capteurs donc on garde cette liste de capteur pour les deux algos
        ArrayList<Capteur> arrayCapt = new ArrayList<Capteur>();
        arrayCapt.add(new CaptPresence(null, null, 0, null));

        // deuxieme algo pour la deuxieme liste de semaphores dynamiques
        ArrayList<SemaphoreDynamique> arraySem1 = new ArrayList<SemaphoreDynamique>();
        SemaphoreDynamique sem1 = new FeuBiCol(EnumSens.POSITIF, new Segment(1,"route N6"));
        arraySem1.add(sem1);

        Regulateur regulateur0 = new Regulateur(arrayCapt, arraySem0, algoChacunSonTour);
        Regulateur regulateur1 = new Regulateur(arrayCapt, arraySem1, algoChacunSonTour);


        for(int i = 0; i < COMPTEUR_TEST; i++){
            testAlgoEtape(regulateur0);
            testAlgoEtape(regulateur1);
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
