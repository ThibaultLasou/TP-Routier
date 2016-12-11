package com.objis.simuRoute;

import java.util.ArrayList;

public class Main
{
    // securite nombre de tours
    private static final int NB_TOUR = 10;

    private static final int LONGUEUR_SEGMENT_0 = 2;
    private static final int LONGUEUR_SEGMENT_1 = 2;
    private static final int LONGUEUR_SEGMENT_2 = 4;
    private static final int LONGUEUR_SEGMENT_3 = 3;

    private static ArrayList<Segment> segments; // list de segments
    private static ArrayList<Semaphore> semaphores; // list de semaphores
    private static ArrayList<Capteur> capteurs; // list de capteurs
    private static ArrayList<Regulateur> regulateurs; // list de regulateurs
    private static ArrayList<Vehicule> vehicules; // list de vehicules
    private static ArrayList<Jonction> jonctions; // list de jonctions
    
    private static void actionVehicules() throws ErreurModele{
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
        	System.out.println(segments.get(i).toString());
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
        // Affichage de la jonction
        System.out.println(jonctions.get(1).toString()); 
        
        Jonction jonctionS1 = new JonctionSimple();
        jonctionS1.sesAcces.add(0,segment2);
        jonctions.add(2,jonctionS1);
        // Affichage de la jonction
        System.out.println(jonctions.get(2).toString()); 
        
        Jonction jonctionC1 = new JonctionComplexe();
        jonctionC1.sesAcces.add(0,segment0);
        jonctionC1.sesAcces.add(1,segment1);
        jonctionC1.sesAcces.add(2,segment2);
        jonctions.add(3,jonctionC1);
        // Affichage de la jonction
        System.out.println(jonctions.get(3).toString()); 
        
        Jonction jonctionC2 = new JonctionComplexe();
        jonctionC2.sesAcces.add(0,segment2);
        jonctionC2.sesAcces.add(1,segment3);
        jonctions.add(4,jonctionC2);
        // Affichage de la jonction
        System.out.println(jonctions.get(4).toString()); 
        
        
        // initialisation des semaphores
        semaphores = new ArrayList<Semaphore>();
        FeuBiCol biColore1 = new FeuBiCol(EnumSens.POSITIF,segment0 );
        semaphores.add(0,biColore1);
        // Affichage de la semaphore
        System.out.println(semaphores.get(0).toString()); 
        
        FeuBiCol biColore2 = new FeuBiCol(EnumSens.NEGATIF,segment1 );
        semaphores.add(1,biColore2);
        // Affichage de la semaphore
        System.out.println(semaphores.get(1).toString()); 
        
        FeuBiCol biColore3 = new FeuBiCol(EnumSens.NEGATIF,segment2 );
        semaphores.add(2,biColore3);
        // Affichage de la semaphore
        System.out.println(semaphores.get(2).toString()); 
        
        Feu triColor1 = new FeuTriCol(EnumSens.POSITIF, segment2);
        semaphores.add(3,triColor1);
        // Affichage de la semaphore
        System.out.println(semaphores.get(3).toString()); 
        
        Feu triColor2 = new FeuTriCol(EnumSens.POSITIF, segment3);
        semaphores.add(4,triColor2);
        // Affichage de la semaphore
        System.out.println(semaphores.get(4).toString()); 
        
        
        // initialisation des capteurs
        capteurs = new ArrayList<Capteur>();
        Capteur captPresence0 = new CaptPresence(segment0, null, LONGUEUR_SEGMENT_0, EnumSens.POSITIF);
        Capteur captPresence1 = new CaptPresence(segment1, null, LONGUEUR_SEGMENT_1, EnumSens.POSITIF);
        Capteur captVitesse2 = new CaptVitesse(segment2, null, LONGUEUR_SEGMENT_2, EnumSens.POSITIF);
        Capteur captVitesse3 = new CaptVitesse(segment3, null, LONGUEUR_SEGMENT_3, EnumSens.POSITIF);
        capteurs.add(0, captPresence0);
        capteurs.add(1, captPresence1);
        capteurs.add(2, captVitesse2);
        capteurs.add(3, captVitesse3);
        //Affichage des capteurs
        for(int i=0;i<capteurs.size();i++)
        {
        	System.out.println(capteurs.get(i).toString());
        }

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
            catch(ErreurModele e)
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
