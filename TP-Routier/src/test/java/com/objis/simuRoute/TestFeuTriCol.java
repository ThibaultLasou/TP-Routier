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
}
