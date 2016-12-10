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
    private static ArrayList<Jonction> jonctions; // list de jonctions
    
    private static void actionVehicules() throws ErreurModeleException{
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
        Segment segment0 = new Segment(LONGUEUR_SEGMENT_0,"route N1");
        Segment segment1 = new Segment(LONGUEUR_SEGMENT_1,"route N2");
        Segment segment2 = new Segment(LONGUEUR_SEGMENT_2,"route N3");
        Segment segment3 = new Segment(LONGUEUR_SEGMENT_3,"route N4");
        segments.add(0, segment0);
        segments.add(1, segment1);
        segments.add(2, segment2);
        segments.add(3, segment3);
        System.out.println("Voici le réseau routier : ");
        for(int i =0;i<segments.size();i++)
        {
        	System.out.println(segments.get(i).getNomSegment()+" de longueur "+segments.get(i).getLongueur());
        }
        // initialisation des jonctions
        jonctions = new ArrayList<Jonction>();
        
        Jonction jonctionB1 = new JonctionBarriere();
        jonctionB1.sesAcces.add(0,segment0);
        jonctions.add(0,jonctionB1);
      // Affichage de la jonction
        System.out.println(jonctions.get(0).toString()); 
        
        Jonction jonctionB2 = new JonctionBarriere();
        jonctionB2.sesAcces.add(0,segment3);
        jonctions.add(1,jonctionB2);
        
        
        // initialisation des semaphores
        semaphores = new ArrayList<Semaphore>();

        // initialisation des capteurs
        capteurs = new ArrayList<Capteur>();
        Capteur captPresence0 = new CaptPresence(null, null, 0, null);
        Capteur captPresence1 = new CaptPresence(null, null, 0, null);
        Capteur captVitesse2 = new CaptVitesse(null, null, 0, null);
        Capteur captVitesse3 = new CaptVitesse(null, null, 0, null);
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
            try{
            	actionVehicules();
            }
            catch(ErreurModeleException e)
            {
            	System.out.println("Réseau incohérent : " + e.getMessage());
            	return;
            }
            // mise a jour capteurs
            //misAJourCapteurs();

            // mise a jour semaphores
            //reguleSemaphores();

        }

    	
    }
}
