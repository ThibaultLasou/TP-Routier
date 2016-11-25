package simuRoute;

import java.util.ArrayList;

public abstract class Jonction extends Route 
{
	static int Longueur = 1;
	ArrayList<Segment> sesAcces;
	
	abstract Segment getNext(Vehicule v);
}