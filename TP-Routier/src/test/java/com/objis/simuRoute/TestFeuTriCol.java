package com.objis.simuRoute;

import junit.framework.TestCase;

/**
 * Test pour le FeuTriCol
 * @author Patrice Camousseigt
 */
public class TestFeuTriCol extends TestCase {

    private EnumColor testChangement(EnumColor enumColor){
        Feu feu = new FeuTriCol(Sens.POSITIF, new Segment(1), new Regulateur(), enumColor);
        feu.changement();
        return feu.couleur;
    }

    public void testChangement(){
        assertEquals(testChangement(EnumColor.VERT), EnumColor.ORANGE);
        assertEquals(testChangement(EnumColor.ORANGE), EnumColor.ROUGE);
        assertEquals(testChangement(EnumColor.ROUGE), EnumColor.VERT);
    }
}
