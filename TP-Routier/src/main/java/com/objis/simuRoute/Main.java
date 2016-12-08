package com.objis.simuRoute;

import java.util.ArrayList;

public class Main
{
    // securite nombre de tours
    private static final int NB_TOUR = 10;

    private static final int LONGUEUR_SEGMENT_0 = 1;
    private static final int LONGUEUR_SEGMENT_1 = 1;
    private static final int LONGUEUR_SEGMENT_2 = 1;
    private static final int LONGUEUR_SEGMENT_3 = 1;

    private static ArrayList<Segment> segments; // list de segments
    private static ArrayList<Semaphore> semaphores; // list de semaphores
    private static ArrayList<Capteur> capteurs; // list de capteurs
    private static ArrayList<Regulateur> regulateurs; // list de regulateurs
    private static ArrayList<Vehicule> vehicules; // list de vehicules

    private static void actionVehicules(){
        for(Vehicule vehicule : vehicules){
            vehicule.avancer();
        }
    }

    private static void misAJourCapteurs(){
        for(Capteur capteur : capteurs){
            capteur.mesurer();
        }
    }

    private static void reguleSemaphores(){
        for(Regulateur regulateur : regulateurs){
            regulateur.regulation();
        }
    }


    public static void main(String args[]) 
    {
        // initialisation des segments
        segments = new ArrayList<Segment>();
        Segment segment0 = new Segment(LONGUEUR_SEGMENT_0);
        Segment segment1 = new Segment(LONGUEUR_SEGMENT_1);
        Segment segment2 = new Segment(LONGUEUR_SEGMENT_2);
        Segment segment3 = new Segment(LONGUEUR_SEGMENT_3);
        segments.add(0, segment0);
        segments.add(1, segment1);
        segments.add(2, segment2);
        segments.add(3, segment3);

        // initialisation des semaphores
        semaphores = new ArrayList<Semaphore>();

        // initialisation des capteurs
        capteurs = new ArrayList<Capteur>();
        Capteur captPresence0 = new CaptPresence();
        Capteur captPresence1 = new CaptPresence();
        Capteur captVitesse2 = new CaptVitesse();
        Capteur captVitesse3 = new CaptVitesse();
        capteurs.add(0, captPresence0);
        capteurs.add(1, captPresence1);
        capteurs.add(2, captVitesse2);
        capteurs.add(3, captVitesse3);

        // initialisation des regulateurs
        regulateurs = new ArrayList<Regulateur>();

        // initialisation des vehicules
        vehicules = new ArrayList<Vehicule>();
        //Vehicule vehicule0 = new Vehicule()
        // TODO : faire vehicule pour pouvoir faire l'initialisation


        for(int compteur = 0; compteur < NB_TOUR; compteur++){

            // mise a jour vehicules
            //actionVehicules();

            // mise a jour capteurs
            //misAJourCapteurs();

            // mise a jour semaphores
            //reguleSemaphores();

        }

    	
    }
}
