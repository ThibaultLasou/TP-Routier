package com.objis.simuRoute;

import junit.framework.TestCase;

/**
 * Test pour le FeuBiCol
 * @author Patrice Camousseigt
 */
public class TestFeuBiCol extends TestCase {

    /**
     * Test du changement Interdiction->Autorisation
     * @return le feu en etat d'autorisation
     */
    private FeuBiCol testChangementRouge(){
        FeuBiCol feu = new FeuBiCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu.changement();
        return feu;
    }

    /**
     * Test du changement Autorisation->Interdiction
     * @return le feu en etat d'autorisation
     */
    private FeuBiCol testChangementVert(){
        FeuBiCol feu = new FeuBiCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu.changement();
        feu.changement();
        return feu;
    }

    /**
     * Test du changement Interdiction->Autorisation
     * Mais un ayant deja passe un cycle
     * @return le feu en etat d'autorisation
     */
    private FeuBiCol testChangementRouge2(){
        FeuBiCol feu = new FeuBiCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu.changement();
        feu.changement();
        feu.changement();
        return feu;
    }

    /**
     * Test des changement()
     */
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

    /**
     * Test des reinitialisation()
     */
    public void testReinitialisation(){
        Feu feu0 = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu0.changement();
        feu0.reinitialisation();
        assertEquals(feu0.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu0.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
        Feu feu1 = new FeuTriCol(EnumSens.POSITIF, new Segment(1,"route N"));
        feu0.changement();
        feu0.changement();
        feu1.reinitialisation();
        assertEquals(feu1.semaphoreEtatCourant.getEnumColor(), EnumColor.ROUGE);
        assertEquals(feu1.semaphoreEtatCourant, EnumSemaphoreEtatCourant.INTERDICTION);
    }
}
