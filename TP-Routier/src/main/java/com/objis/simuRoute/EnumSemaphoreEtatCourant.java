package com.objis.simuRoute;

import static com.objis.simuRoute.EnumColor.ORANGE;
import static com.objis.simuRoute.EnumColor.ROUGE;
import static com.objis.simuRoute.EnumColor.VERT;

/**
 * Represente les etats des semaphores ainsi que les couleurs representatives
 * @author Patrice Camousseigt
 */
public enum EnumSemaphoreEtatCourant 
{
    INTERDICTION("INTERDICTION", ROUGE),
    ATTENTION("ATTENTION", ORANGE),
    AUTORISATION("AUTORISATION", VERT);

    private String name;
    private EnumColor enumColor;


    EnumSemaphoreEtatCourant(String name, EnumColor enumColor) 
    {
        this.name = name;
        this.enumColor = enumColor;
    }

    /* Getter */

    public String getName() 
    {
        return name;
    }

    public EnumColor getEnumColor() {
        return enumColor;
    }

    @Override
    public String toString() {
        return name;
    }
}
