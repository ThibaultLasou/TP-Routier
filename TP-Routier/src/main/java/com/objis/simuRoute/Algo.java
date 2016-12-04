package com.objis.simuRoute;

import java.util.Collection;

public interface Algo 
{
	void regulator(Collection<? extends Capteur> capts, 
			Collection<? extends SemaphoreDynamique> sems);
}
