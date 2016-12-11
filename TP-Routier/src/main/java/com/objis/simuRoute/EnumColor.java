package com.objis.simuRoute;

/**
 * Les couleurs des semaphores sur la route
 */
public enum EnumColor 
{
	VERT("VERT"),
	ORANGE("ORANGE"),
	ROUGE("ROUGE");

	private String name;

	EnumColor(String name) {
		this.name = name;
	}

	@Override
	public String toString() 
	{
		return name;
	}
}
