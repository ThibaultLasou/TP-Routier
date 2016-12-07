package com.objis.simuRoute;

import junit.framework.TestCase;

/**
 * Test pour le FeuBiCol
 * @author Patrice Camousseigt
 */
public class TestFeuBiCol extends TestCase {

    private FeuBiCol testChangementRouge(){
        FeuBiCol feu = new FeuBiCol(EnumSens.POSITIF, new Segment(1));
        feu.changement();
        return feu;
    }

    private FeuBiCol testChangementVert(){
        FeuBiCol feu = new FeuBiCol(EnumSens.POSITIF, new Segment(1));
        feu.changement();
        feu.changement();
        return feu;
    }

    private FeuBiCol testChangementRouge2(){
        FeuBiCol feu = new FeuBiCol(EnumSens.POSITIF, new Segment(1));
        feu.changement();
        feu.changement();
        feu.changement();
        return feu;
    }

    public void testChangement(){
        Feu feuVert = testChangementRouge();
        assertEquals(feuVert.semaphoreEtatCourant, EnumSemaphoreEtatCourant.AUTORISATION);
        assertEquals(feuVert.semaphoreEtatCourant.getEnumColor(), EnumColor.VERT);
        Feu feuRouge = testChangementVert();
        assertEquals(feuRouge.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        assertEquals(feuRouge.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        Feu feuVert2 = testChangementRouge2();
        assertEquals(feuVert2.semaphoreEtatCourant, EnumSemaphoreEtatCourant.AUTORISATION);
        assertEquals(feuVert2.semaphoreEtatCourant.getEnumColor(), EnumColor.VERT);
    }
}
