package com.objis.simuRoute;

import junit.framework.TestCase;

public class TestFeuBiCol extends TestCase {

    public EnumColor testChangementVert(){
        Feu feu = new FeuBiCol(Sens.POSITIF, new Segment(1), new Regulateur(), EnumColor.VERT);
        feu.changement();
        return feu.couleur;
    }

    public void testChangement(){
        assertEquals(testChangementVert(), EnumColor.ROUGE);

    }
}
