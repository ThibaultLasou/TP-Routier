package com.objis.simuRoute;

import junit.framework.TestCase;

/**
 * Test pour le FeuTriCol
 * @author Patrice Camousseigt
 */
public class TestFeuTriCol extends TestCase {


    /**
     * Test si les changements d'etat des FeuTriCol ont bien le comportement souhaite
     */
    public void testChangement(){
        Feu feu = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N"));
        // le feu est initialise en etat d'interdiction
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        feu.changement();
        // le feu est initialise en etat d'autorisation
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.VERT);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.AUTORISATION);
        feu.changement();
        // le feu est initialise en etat d'attention
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.ORANGE);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.ATTENTION);
        feu.changement();
        // le feu est initialise en etat d'interdiction a nouveau
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        feu.changement();
    }

    /**
     * Test la reinitialisation en tous les etats du FeuTriCol
     */
    public void testReinitialisation(){
        Feu feu0 = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu0.changement();
        feu0.reinitialisation();
        assertEquals(feu0.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu0.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        Feu feu1 = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu1.changement();
        feu1.changement();
        feu1.reinitialisation();
        assertEquals(feu1.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu1.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        Feu feu2 = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu2.changement();
        feu2.changement();
        feu2.changement();
        feu2.reinitialisation();
        assertEquals(feu2.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu2.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
    }
}
