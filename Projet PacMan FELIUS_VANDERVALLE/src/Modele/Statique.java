package Modele;

public class Statique {
	
	private int x;
	private int y;
	
	
	/**Crée la super classe de porte, couloir et mur
	 * 
	 * @param x la valeur de l'abscisse
	 * @param y la valeur de l'ordonnée
	 */
	public Statique(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	
	/**
	 * 
	 * @return l'abscisse
	 */
	
	public int getX () {
		return x;
	}
	
	
	/**
	 * 
	 * @return l'ordonnée 
	 */
	
	public int getY () {
		return y;
	}
	
}
