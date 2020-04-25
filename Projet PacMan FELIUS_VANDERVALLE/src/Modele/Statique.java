package Modele;

public class Statique {
	
	private int x;
	private int y;
	
	
	/**Cr�e la super classe de porte, couloir et mur
	 * 
	 * @param x la valeur de l'abscisse
	 * @param y la valeur de l'ordonn�e
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
	 * @return l'ordonn�e 
	 */
	
	public int getY () {
		return y;
	}
	
}
