package com.objis.simuRoute;

import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Regule les semaphores dynamiques en fonction des capteurs en utilisant un algorithme de regulation
 * @author Patrice Camousseigt
 */
public class Regulateur 
{
	ArrayList<Pair<SemaphoreDynamique, Integer>> sesSemaphoreDynamique;
	ArrayList<Capteur> sesCapteurs;
	Algo sonAlgo;

	// compteur initial en unite de temps correspondant a l'attente pour un semaphore avant de changer d'etat
	private static final int INITIALISATION_COMPTEUR = 0;

    /**
     * Constructeur
     * @param sesCapteurs les capteurs utilises pour reguler
     * @param sesSemaphoreDynamique les semaphores dynamiques utilises pour reguler
     * @param sonAlgo l'algorithme qui utilise les capteurs et les semaphores dynamiques pour reguler
     */
    public Regulateur(ArrayList<Capteur> sesCapteurs, ArrayList<SemaphoreDynamique> sesSemaphoreDynamique, Algo sonAlgo) {
        this.sesSemaphoreDynamique = initialisation(sesSemaphoreDynamique);
        this.sesCapteurs = sesCapteurs;
        this.sonAlgo = sonAlgo;
    }

    /**
     * Initialise une liste de paire avec les semaphores dynamiques et leurs compteurs initialises
     * @param sesSemaphoreDynamique la liste des semaphores dynamiques
     * @return la liste de paire de semaphore dynamique avec leurs compteurs initialises
     */
    private ArrayList<Pair<SemaphoreDynamique, Integer>> initialisation(ArrayList<SemaphoreDynamique> sesSemaphoreDynamique){
        ArrayList<Pair<SemaphoreDynamique, Integer>> arrayPair = new ArrayList<Pair<SemaphoreDynamique, Integer>>();
        for(SemaphoreDynamique semDyn : sesSemaphoreDynamique){
            semDyn.reinitialisation(); // on reinitialise toutes les semaphores dynamiques
            Pair<SemaphoreDynamique, Integer> pair =
                    new Pair<SemaphoreDynamique, Integer>(semDyn, INITIALISATION_COMPTEUR);
            arrayPair.add(pair);
        }
        return arrayPair;
    }

    /**
     * Utilise l'algo pour reguler en fonction de l'etat courant des capteurs et des semaphores dynamiques
     */
    public void regulation() {
        sonAlgo.reguler(sesCapteurs, sesSemaphoreDynamique);
    }

}
