package com.objis.simuRoute.AlgoRegulation;

import com.objis.simuRoute.Algo;
import com.objis.simuRoute.Capteur;
import com.objis.simuRoute.Semaphore;
import com.objis.simuRoute.SemaphoreDynamique;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Le principe de cet algorithme est que chaque semaphore dynamiques est traite en fonction de son capteur associe.
 * Cad que lorsque le capteur detecte la presence d'un vehicule, le semaphore associe s'ajoute a la file
 * A chaque tour on traite le premier element de la file et quand on a termine on le retire de la file
 * On part du principe que tous les capteurs de la liste ont une correspondance avec un semaphore de la liste des semaphores
 * De toute facon, on teste ici quand meme si c'est le cas
 * @author Patrice Camousseigt
 */
public class AlgoDetectionPresenceFIFO implements Algo {

    // temps d'attente pour chaque etat des semaphores dynamiques
    // pas d'attente particuliere pour l'interdiction car peu d'interet a ce qu'aucune voiture n'avance
    // si le cas devient interessant, pour des travaux par exemple, il suffit d'incrementer TEMPS_INTERDICTION
    private final int TEMPS_AUTORISATION = 5;
    private final int TEMPS_ATTENTION = 1;
    private final int TEMPS_INTERDICTION = 0;

    private static final int UNITE_DE_TEMPS = 1;
    private static final int REINITIALISATION = 0;

    // l'indice utlise de la methode peek() de java
    private final int INDEX_FILE_PEEK = 0;
    // une file pour l'ordre de traitement en fonction de la présence
    private LinkedList<Pair<SemaphoreDynamique, Integer>> semDynFile;

    /**
     * Constructeur
     */
    public AlgoDetectionPresenceFIFO() {
        this.semDynFile = new LinkedList<Pair<SemaphoreDynamique, Integer>>();
    }

    /**
     * Ajoute les semaphores dynamiques a la file pour etre traite dans l'ordre
     * Le premier capteur ayant detecte une presence permettra de traiter son semaphore en premier
     * Dans le cas ou les capteurs detectent au meme tour une presence de vehicule,
     * le premier rencontre dans la liste aura la priorite
     * @param capts les capteurs captant la presence des vehicules
     * @param sems les semaphores reguler grace aux capteurs
     */
    public void addFile(ArrayList<? extends Capteur> capts, ArrayList<Pair<SemaphoreDynamique, Integer>> sems){
        for(Capteur capteur : capts){
            // si le capteur detecte la presence d'un vehicule
            if(capteur.isPresence()){
                Semaphore semaphore = capteur.getSonEmplacement().getSaSignalisation()[capteur.getSens().getInd()];
                // si le semaphore est bien dans la liste des semaphores initialisee
                // et si le semaphore n'est pas deja dans la file des semaphores a traiter
                // alors il faut l'ajouter a la file a la suite des autres
                if(contains(sems, semaphore) && !contains(semDynFile, semaphore)){
                    semDynFile.add(new Pair<SemaphoreDynamique, Integer>((SemaphoreDynamique) semaphore, 0));
                }
            }
        }
    }


    /**
     *
     * @param capts les capteurs utilises
     * @param sems les semaphores dynamiques utilises
     */
    public void reguler(ArrayList<? extends Capteur> capts, ArrayList<Pair<SemaphoreDynamique, Integer>> sems) {

        // ajoute les semaphores a traiter car leurs capteurs a detecte la presence d'un vehicule
        addFile(capts, sems);

        // on traite le premier semaphore de la liste
        Pair<SemaphoreDynamique, Integer> pair = semDynFile.peek();

        // si la file est vide on n'a rien a traiter
        // les vehicules aux semaphores statiques peuvent en profiter pour passer sans risque
        if(pair == null) return;

        if(pair.getValue() > 0){
            // si le semaphore doit encore attendre pour changer d'etat
            semDynFile.set(INDEX_FILE_PEEK, new Pair<SemaphoreDynamique, Integer>(pair.getKey(),
                    pair.getValue() - UNITE_DE_TEMPS)); // on retire une unite de temps a son compteur
        }
        else{
            pair.getKey().changement(); // si le semaphores dynamiques a termine d'attendre pour cet etat
            semDynFile.set(INDEX_FILE_PEEK, new Pair<SemaphoreDynamique, Integer>(pair.getKey(),
                    tempsAttenteEtat(pair.getKey())));
        }

        // si le semaphore est en etat d'interdiction et
        // si le semaphore dynamique a fait un cycle complet de ses etats, cad compteur = 0
        // on le retire de la file
        if(pair.getKey().etatCourantIsInterdiction() && pair.getValue() == 0){
            semDynFile.poll();
        }
    }

    /**
     * Verification qu'un semaphore est bien dans la liste des semaphores initialises
     * @param sems les semaphores initialises
     * @param semaphore la semaphore dont la presence doit etre verifiee
     * @return si le semaphore est bien dans la liste des paires
     */
    private static boolean contains(List<Pair<SemaphoreDynamique, Integer>> sems, Semaphore semaphore){
        for(Pair<SemaphoreDynamique, Integer> paire : sems){
            if(paire.getKey() == semaphore){
                return true;
            }
        }
        return false;
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

    public LinkedList<Pair<SemaphoreDynamique, Integer>> getSemDynFile() {
        return semDynFile;
    }

    public int getTEMPS_AUTORISATION() {
        return TEMPS_AUTORISATION;
    }

    public int getTEMPS_ATTENTION() {
        return TEMPS_ATTENTION;
    }

    public int getTEMPS_INTERDICTION() {
        return TEMPS_INTERDICTION;
    }

    @Override
    public String toString() {
        return "AlgoDetectionPresenceFIFO{" +
                "TEMPS_AUTORISATION=" + TEMPS_AUTORISATION +
                ", TEMPS_ATTENTION=" + TEMPS_ATTENTION +
                ", TEMPS_INTERDICTION=" + TEMPS_INTERDICTION +
                ", INDEX_FILE_PEEK=" + INDEX_FILE_PEEK +
                ", semDynFile=" + semDynFile +
                '}';
    }
}
