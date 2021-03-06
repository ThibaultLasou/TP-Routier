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
 * Un seul semaphore dynamique a la fois autorise les vehicules a passer
 * Cet algorithme de regulation ne prend pas en compte les mesures des capteurs
 * @author Patrice Camousseigt
 */
public class AlgoChacunSonTour implements Algo {

    // temps d'attente pour chaque etat des semaphores dynamiques
    // pas d'attente particuliere pour l'interdiction car peu d'interet a ce qu'aucune voiture n'avance
    // si le cas devient interessant, pour des travaux par exemple, il suffit d'incrementer TEMPS_INTERDICTION
    private final int TEMPS_AUTORISATION = 1;
    private final int TEMPS_ATTENTION = 1;
    private final int TEMPS_INTERDICTION = 0;

    // la seule utilite de ces constantes est une meilleure lisibilite du code
    private static final int UNITE_DE_TEMPS = 1;
    private static final int REINITIALISATION = 0;

    // l'indice actuelle du semaphore sur lequel l'algo travaille
    private int indiceSemaphoreCourant;

    /**
     * Constructeur
     */
    public AlgoChacunSonTour() {
        this.indiceSemaphoreCourant = 0;
    }

    /**
     * Modifie l'etat des sempahores dynamiques en fonction des temps d'attente courant
     * @param capts les capteurs utilises
     * @param sems les semaphores dynamiques utilises
     */
    public void reguler(ArrayList<? extends Capteur> capts, ArrayList<Pair<SemaphoreDynamique, Integer>> sems) {

        // si le semaphore est en etat d'interdiction
        // et si le semaphore dynamique a fait un cycle complet de ses etats, cad compteur = 0
        if(sems.get(indiceSemaphoreCourant).getKey().etatCourantIsInterdiction()
                && sems.get(indiceSemaphoreCourant).getValue() == 0){

            // si fin de liste on recommence au premier semaphore
            indiceSemaphoreCourant = (indiceSemaphoreCourant + 1) % sems.size();
        }

        Pair<SemaphoreDynamique, Integer> pair = sems.get(indiceSemaphoreCourant);

        // si le semaphore doit encore attendre pour changer d'etat
        if(pair.getValue() > 0){

            sems.set(indiceSemaphoreCourant, new Pair<SemaphoreDynamique, Integer>(pair.getKey(),
                    pair.getValue() - UNITE_DE_TEMPS)); // on retire une unite de temps a son compteur
        }
        else{
            // si le semaphore dynamique a termine d'attendre pour cet etat
            pair.getKey().changement();
            // on change l'etat du semaphore dynamique et lui attribue le compteur associe a son etat
            sems.set(indiceSemaphoreCourant, new Pair<SemaphoreDynamique, Integer>(pair.getKey(),
                    tempsAttenteEtat(pair.getKey())));
        }
    }

    /**
     * Retourne le temps d'attente en fonction de l'etat du semaphore
     * @param sem le semaphore dynamique
     * @return le temps d'attente prevu par cet etat dans l'algo
     */
    private int tempsAttenteEtat(SemaphoreDynamique sem){
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

    /* Getter */

    /**
     * Getter sur l'indice du semaphore courant
     * @return l'indice du semaphore courant
     */
    public int getIndiceSemaphoreCourant() {
        return indiceSemaphoreCourant;
    }
}
