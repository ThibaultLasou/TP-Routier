package com.objis.simuRoute;

import junit.framework.TestCase;

public class TestFeu extends TestCase {

    public EnumColor testChangementRouge(){
        Feu feu = new FeuBiCol(Sens.POSITIF, new Segment(1), new Regulateur(), EnumColor.ROUGE);
        feu.changement();
        return feu.couleur;
    }

    public void testChangement(){
        assertEquals(testChangementRouge(), EnumColor.VERT);

    }
}
