package Modele;

import javafx.scene.image.Image;

public class Contenu {

	Image imVide = new Image("images/sol.jpg");
    Image imPacgum = new Image("images/pacgum.jpg");
    Image imSablier = new Image("images/Sablier.jpg");
    Image imPouvoir = new Image("images/pouvoir.jpg");
    
    private Image image;
	private int type;
	//0 = vide
	//1 = pacgum (par défaut)
	//2 = SUPER pacgum 
	//3 = sablier 
	
	private int score;
	
	/**
	 * 
	 * @param i definit le contenu (0 (sol), 1 (pacgum), 2 (super pacgum), 3 (sablier))
	 */
	public Contenu(int i) {
		this.type=i;
		if (i==0) {
			this.score=0;
			this.image=imVide;
		}
		if (i==1) {
			this.score=5;
			this.image=imPacgum;
		}
		if (i==2) {
			this.score=50;
			this.image=imPouvoir;
		}
		if (i==3) {
			this.score=50;
			this.image=imSablier;
		}
	}
	
	/**
	 * 
	 * @return le score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * 
	 * @return le contenu du couloir
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * 
	 * @return l'image
	 */
	public Image getImage() {
		return image;
	}
	
}