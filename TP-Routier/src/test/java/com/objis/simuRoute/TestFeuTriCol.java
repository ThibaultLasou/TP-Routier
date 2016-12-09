package com.objis.simuRoute;

import junit.framework.TestCase;

/**
 * Test pour le FeuTriCol
 * @author Patrice Camousseigt
 */
public class TestFeuTriCol extends TestCase {


    public void testChangement(){
        Feu feu = new FeuTriCol(EnumSens.POSITIF, new Segment(1));
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        feu.changement();
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.VERT);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.AUTORISATION);
        feu.changement();
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.ORANGE);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.ATTENTION);
        feu.changement();
        assertEquals(feu.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        feu.changement();
    }

    public void testReinitialisation(){
        Feu feu0 = new FeuTriCol(EnumSens.POSITIF, new Segment(1));
        feu0.changement();
        feu0.reinitialisation();
        assertEquals(feu0.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu0.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        Feu feu1 = new FeuTriCol(EnumSens.POSITIF, new Segment(1));
        feu0.changement();
        feu0.changement();
        feu1.reinitialisation();
        assertEquals(feu1.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu1.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        Feu feu2 = new FeuTriCol(EnumSens.POSITIF, new Segment(1));
        feu2.changement();
        feu2.changement();
        feu2.changement();
        feu2.reinitialisation();
        assertEquals(feu2.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu2.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
    }
}
