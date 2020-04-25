package Modele;

public class Couloir extends Statique {

	private Contenu contenu;
	
	/**
	 * 
	 * @param x valeur de l'abscisse
	 * @param y valeur de l'ordonnée
	 * @param c le contenu du couloir
	 */
	public Couloir(int x, int y, Contenu c) {
		super(x, y);
		this.contenu=c;
	}
	
	/**
	 * 
	 * @param x valeur de l'abscisse
	 * @param y valeur de l'ordonnée
	 */
	public Couloir(int x, int y) {
		super(x, y);
		this.contenu= new Contenu (1);
	}
	
	/**
	 * 
	 * @param i definit le contenu (0 (sol), 1 (pacgum), 2 (super pacgum), 3 (sablier))
	 */
	public void setContenu (Contenu i) {
		this.contenu=i;
	}
	
	/**
	 * 
	 * @return le contenu du couloir
	 */
	public Contenu getContenu () {
		return contenu;
	}
}