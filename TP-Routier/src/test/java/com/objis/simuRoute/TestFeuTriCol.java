package com.objis.simuRoute;


import junit.framework.TestCase;

public class TestFeuTriCol extends TestCase {

    public EnumColor testChangementVert(){
        Feu feu = new FeuTriCol(Sens.POSITIF, new Segment(1), new Regulateur(), EnumColor.VERT);
        feu.changement();
        return feu.couleur;
    }

    public EnumColor testChangementOrange(){
        Feu feu = new FeuTriCol(Sens.POSITIF, new Segment(1), new Regulateur(), EnumColor.ORANGE);
        feu.changement();
        return feu.couleur;
    }

    public void testChangement(){
        assertEquals(testChangementVert(), EnumColor.ORANGE);
        assertEquals(testChangementOrange(), EnumColor.ROUGE);
    }
}
