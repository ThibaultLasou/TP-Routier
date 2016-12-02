package com.objis.simuRoute;

import junit.framework.TestCase;

/**
 * Test pour le FeuBiCol
 * @author Patrice Camousseigt
 */
public class TestFeuBiCol extends TestCase {

    private EnumColor testChangement(EnumColor enumColor){
        Feu feu = new FeuBiCol(Sens.POSITIF, new Segment(1), new Regulateur(), enumColor);
        feu.changement();
        return feu.couleur;
    }

    public void testChangement(){
        assertEquals(testChangement(EnumColor.VERT), EnumColor.ROUGE);
        assertEquals(testChangement(EnumColor.ROUGE), EnumColor.VERT);
        assertEquals(testChangement(EnumColor.ORANGE), EnumColor.ROUGE);
    }
}
