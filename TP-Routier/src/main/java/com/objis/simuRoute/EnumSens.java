package com.objis.simuRoute;

public enum EnumSens
{
	NEGATIF(0, -1), 
	POSITIF(1, 1);
	
	int ind;
	int direction;
	
	private EnumSens(int i, int d)
	{
		this.ind = i;
		this.direction = d;
	}
	
	public EnumSens sensInverse() 
	{
		if(this == EnumSens.POSITIF)
		{
			return EnumSens.NEGATIF;
		}
		else
		{
			return EnumSens.POSITIF;
		}
	}
}
