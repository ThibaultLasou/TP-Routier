package simuRoute;

public class EtatVehicule 
{
	Route saRoute;
	int position;
	int vitesse_inst;
	Sens sens;
	public Route getSaRoute() {
		return saRoute;
	}
	public void setSaRoute(Route saRoute) {
		this.saRoute = saRoute;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getVitesse_inst() {
		return vitesse_inst;
	}
	public void setVitesse_inst(int vitesse_inst) {
		this.vitesse_inst = vitesse_inst;
	}
	public Sens getSens() {
		return sens;
	}
	public void setSens(Sens sens) {
		this.sens = sens;
	}
	
}
