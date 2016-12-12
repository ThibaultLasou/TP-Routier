package com.objis.simuRoute;

import java.io.IOException;
import java.util.ArrayList;

import com.objis.simuRoute.AlgoRegulation.AlgoChacunSonTour;

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
        for(Vehicule v :vehicules)
        {
        	v.avancer();
        	System.out.println(v);
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
        segments.add(segment0);
        segments.add(segment1);
        segments.add(segment2);
        segments.add(segment3);
        System.out.println("Voici le réseau routier : ");
        for(int i =0;i<segments.size();i++)
        {
        	System.out.println(segments.get(i).toString());
        }
        // initialisation des jonctions
        jonctions = new ArrayList<Jonction>();
        
        Jonction jonctionB1 = new JonctionBarriere();
        segment0.addExtremite(jonctionB1, EnumSens.NEGATIF);
        jonctions.add(jonctionB1);
      // Affichage de la jonction
        
        Jonction jonctionB2 = new JonctionBarriere();
        segment3.addExtremite(jonctionB2, EnumSens.NEGATIF);
        jonctions.add(jonctionB2);
        // Affichage de la jonction
        
        Jonction jonctionB3 = new JonctionBarriere();
        segment1.addExtremite(jonctionB3, EnumSens.NEGATIF);
        jonctions.add(jonctionB3);
        // Affichage de la jonction
        
        Jonction jonctionS1 = new JonctionSimple();
        segment2.addExtremite(jonctionS1, EnumSens.POSITIF);
        segment3.addExtremite(jonctionS1, EnumSens.POSITIF);
        jonctions.add(jonctionS1);
        // Affichage de la jonction
        
        Jonction jonctionC1 = new JonctionComplexe();
        segment0.addExtremite(jonctionC1, EnumSens.POSITIF);
        segment1.addExtremite(jonctionC1, EnumSens.POSITIF);
        segment2.addExtremite(jonctionC1, EnumSens.NEGATIF);
        jonctions.add(3,jonctionC1);
        // Affichage de la jonction
        
        // initialisation des semaphores
        semaphores = new ArrayList<Semaphore>();
        FeuBiCol biColore1 = new FeuBiCol(EnumSens.POSITIF,segment0);
        semaphores.add(0,biColore1);
        // Affichage de la semaphore
        
        FeuBiCol biColore2 = new FeuBiCol(EnumSens.NEGATIF,segment1);
        semaphores.add(1,biColore2);
        // Affichage de la semaphore

        FeuBiCol biColore3 = new FeuBiCol(EnumSens.NEGATIF,segment2);
        semaphores.add(2,biColore3);
        // Affichage de la semaphore
        
        Feu triColor1 = new FeuTriCol(EnumSens.POSITIF, segment2);
        semaphores.add(3,triColor1);
        // Affichage de la semaphore
        
        Feu triColor2 = new FeuTriCol(EnumSens.POSITIF, segment3);
        semaphores.add(4,triColor2);
        // Affichage de la semaphore
        
        
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
        AlgoChacunSonTour algo = new AlgoChacunSonTour();
        ArrayList<SemaphoreDynamique> r1Sema = new ArrayList<SemaphoreDynamique>();
        r1Sema.add(triColor2);
        r1Sema.add(triColor1);
        Regulateur r1 = new Regulateur(null, r1Sema, algo);
        regulateurs.add(r1);
        // initialisation des vehicules
        vehicules = new ArrayList<Vehicule>();
        try 
        {
			Vehicule vehicule0 = new Vehicule(1, 2, segment0, 0, EnumSens.POSITIF);
			Vehicule vehicule1 = new Vehicule(1, 2, segment0, LONGUEUR_SEGMENT_0-1, EnumSens.NEGATIF);
	        Vehicule vehicule2 = new Vehicule(1, 2, segment2, 0, EnumSens.POSITIF);
	        Vehicule vehicule3 = new Vehicule(2, 2, segment2, 3, EnumSens.POSITIF);
	        
	        vehicules.add(vehicule0);
	        vehicules.add(vehicule1);
	        vehicules.add(vehicule2);
	        vehicules.add(vehicule3);
	    } catch (ErreurModele e1) 
		{
			System.out.println("Réseau incohérent : " + e1.getMessage());
        	return;
		}
        

        for(int compteur = 0; compteur < NB_TOUR; compteur++)
        {
        	System.out.println("Tour " + compteur);
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
            misAJourCapteurs();

            // mise a jour semaphores
            reguleSemaphores();
            for(Semaphore s : semaphores)
            {
            	System.out.println(s);
            }
            System.out.println("(Pour passer au tour suivant, appuyer sur Entrée)");
            try 
            {
				System.in.read();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
        }

    	
    }
}
