package com.objis.simuRoute;

import static com.objis.simuRoute.EnumColor.ORANGE;
import static com.objis.simuRoute.EnumColor.ROUGE;
import static com.objis.simuRoute.EnumColor.VERT;

/**
 * Represente les etats des semaphores ainsi que les couleurs representatives
 * @author Patrice Camousseigt
 */
public enum EnumSemaphoreEtatCourant {

    INTERDICTION(ROUGE),
    ATTENTION(ORANGE),
    AUTORISATION(VERT);

    private EnumColor enumColor;

    EnumSemaphoreEtatCourant(EnumColor color) {
        enumColor = color;
    }

    public EnumColor getEnumColor() {
        return enumColor;
    }
}
