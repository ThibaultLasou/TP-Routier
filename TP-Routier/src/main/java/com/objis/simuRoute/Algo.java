package com.objis.simuRoute;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Cette interface permet d'utiliser differents algorithme
 */
public interface Algo 
{
	/**
	 * Permet de reguler le trafic en utilisant les capteurs et les semaphores dynamiques dans leurs etats courants
	 * @param capts les capteurs utilises
	 * @param sems les semaphores dynamiques utilises
	 */
	void reguler(ArrayList<Capteur> capts, ArrayList<Pair<SemaphoreDynamique, Integer>> sems);
}
