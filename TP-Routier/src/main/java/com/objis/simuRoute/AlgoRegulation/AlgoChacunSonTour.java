package com.objis.simuRoute.AlgoRegulation;

import com.objis.simuRoute.Algo;
import com.objis.simuRoute.Capteur;
import com.objis.simuRoute.SemaphoreDynamique;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Le principe de cet algorithme est que chaque semaphore dynamique est traite de la meme facon independemment du trafic
 * Tous les semaphores vont chacun leur tour autoriser les vehicules a passer et ensuite interdire
 * pour laisser le semaphore suivant faire de meme
 * Un seul semaphore dynamique a la fois laisse les vehicules passer
 * @author Patrice Camousseigt
 */
public class AlgoChacunSonTour implements Algo {

    // chaque temps d'attente pour chaque etat des semaphores dynamiques
    // pas d'attente particuliere pour l'interdiction car peu d'interet a ce qu'aucune voiture n'avance
    // si le cas devient interessant, pour des travaux par exemple, il suffit d'incrementer TEMPS_INTERDICTION
    private static final int TEMPS_AUTORISATION = 1;
    private static final int TEMPS_ATTENTION = 1;
    private static final int TEMPS_INTERDICTION = 0;

    private static final int UNITE_DE_TEMPS = 1;
    private static final int REINITIALISATION = 0;

    // l'indice actuelle du semaphore sur lequelle l'algo est concentre
    private int indiceSemaphoreCourant = 0;


    /**
     * Modifie l'etat des sempahores dynamiques en fonction des temps d'attente courant
     * @param capts les capteurs utilises
     * @param sems les semaphores dynamiques utilises
     */
    public void reguler(ArrayList<Capteur> capts, ArrayList<Pair<SemaphoreDynamique, Integer>> sems) {
        Pair<SemaphoreDynamique, Integer> pair = sems.get(indiceSemaphoreCourant);
        if(pair.getValue() > 0){ // si le semaphore doit encore attendre pour changer d'etat
            sems.set(indiceSemaphoreCourant, new Pair<SemaphoreDynamique, Integer>(pair.getKey(),
                    pair.getValue() - UNITE_DE_TEMPS));
            if(sems.get(indiceSemaphoreCourant).getKey().etatCourantIsInterdiction()
                    && sems.get(indiceSemaphoreCourant).getValue() == 0){ // si le semaphore dynamique a fait un cycle complet de ses etats
                indiceSemaphoreCourant = (indiceSemaphoreCourant + 1) % sems.size(); // si fin de liste on recommence au premier semaphore
            }
        }
        else{
            pair.getKey().changement(); // si le semaphores dynamiques a termine d'attendre pour cet etat
            sems.set(indiceSemaphoreCourant, new Pair<SemaphoreDynamique, Integer>(pair.getKey(),
                    tempsAttenteEtat(pair.getKey())));
        }
    }

    /**
     * Retourne le temps d'attente en fonction de l'etat du semaphore
     * @param sem le semaphore dynamique
     * @return le temps d'attente prevu par cet etat dans l'algo
     */
    public static int tempsAttenteEtat(SemaphoreDynamique sem){
        if(sem.etatCourantIsAttention()){
            return TEMPS_ATTENTION;
        }
        else if(sem.etatCourantIsAutorisation()){
            return TEMPS_AUTORISATION;
        }
        else if(sem.etatCourantIsInterdiction()){
            return TEMPS_INTERDICTION;
        }
        return REINITIALISATION;
    }

    /**
     * Getter sur l'indice du semaphore courant
     * @return l'indice du semaphore courant
     */
    public int getIndiceSemaphoreCourant() {
        return indiceSemaphoreCourant;
    }
}
