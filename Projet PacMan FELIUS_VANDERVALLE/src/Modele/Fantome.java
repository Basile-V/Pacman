package Modele;

public class Fantome extends Entite {
	
	/**
	 * 
	 * @param x la valeur de l'abscisse
	 * @param y la valeur de l'ordonnée
	 * @param direction la valeur de la direction (1 (haut), 2 (droite), 3 (bas), 4 (gauche))
	 */
	public Fantome(int x, int y, int direction) {
		super(x, y, direction);
	}
	
	
	/**
	 * deplace le fantome en fonction de la difficulté
	 */
	public void choixDirection () {
		int nbChoix = 1;
		int [] choix = new int [4];
		if (isCouloir(this.getDirection())) {
			choix[0]=this.getDirection();
			nbChoix+=1;
		}
		if (this.getX()>0 && this.getX()<this.getGrille().getSizeX() && this.getY()>0 && this.getY()<this.getGrille().getSizeY()) {
			if (this.getDirection()==1 || this.getDirection()==3) {
				if (isCouloir(2)) {
					choix[nbChoix]=2;
					nbChoix+=1;
				}
				
				if (isCouloir(4)) {
					choix[nbChoix]=4;
					nbChoix+=1;
				}
			}
			
			if (this.getDirection()==2 || this.getDirection()==4) {
				if (isCouloir(1)) {
					choix[nbChoix]=1;
					nbChoix+=1;
				}
				
				if (isCouloir(3)) {
					choix[nbChoix]=3;
					nbChoix+=1;
				}
			}
	
			int resultat=(int) (Math.random() * ( nbChoix ));
			this.setDirection(choix[resultat]);
		}
	}
	
	
	/**
	 * deplace le fantome en focntion de la difficulté
	 */
	public void choixDirection2 () {
		
		if (getGrille().getJoueur().getEtat()) {
			
			if (this.getX()>0 && this.getX()<this.getGrille().getSizeX() && this.getY()>0 && this.getY()<this.getGrille().getSizeY()) {
				if (this.getDirection()==1 || this.getDirection()==3) {
					if (isCouloir(2) && Math.abs(getGrille().getJoueur().getY()-getY()-1) > Math.abs(getGrille().getJoueur().getY()-getY())) {
						setDirection(2);
					}
					
					if (isCouloir(4) && Math.abs(getGrille().getJoueur().getY()-getY()+1) > Math.abs(getGrille().getJoueur().getY()-getY())) {
						setDirection(4);
					}
				}
				
				if (this.getDirection()==2 || this.getDirection()==4) {
					if (isCouloir(1) && Math.abs(getGrille().getJoueur().getX()-getX()-1) > Math.abs(getGrille().getJoueur().getX()-getX())) {
						setDirection(1);
					}
					
					if (isCouloir(3) && Math.abs(getGrille().getJoueur().getX()-getX()+1) > Math.abs(getGrille().getJoueur().getX()-getX())) {
						setDirection(3);
					}
				}
			}
			
		}
		
		else {

			if (this.getX()>0 && this.getX()<this.getGrille().getSizeX() && this.getY()>0 && this.getY()<this.getGrille().getSizeY()) {
				if (this.getDirection()==1 || this.getDirection()==3) {
					if (isCouloir(2) && Math.abs(getGrille().getJoueur().getY()-getY()-1) <= Math.abs(getGrille().getJoueur().getY()-getY())) {
						setDirection(2);
					}
					
					if (isCouloir(4) && Math.abs(getGrille().getJoueur().getY()-getY()+1) <= Math.abs(getGrille().getJoueur().getY()-getY())) {
						setDirection(4);
					}
				}
				
				if (this.getDirection()==2 || this.getDirection()==4) {
					if (isCouloir(1) && Math.abs(getGrille().getJoueur().getX()-getX()-1) <= Math.abs(getGrille().getJoueur().getX()-getX())) {
						setDirection(1);
					}
					
					if (isCouloir(3) && Math.abs(getGrille().getJoueur().getX()-getX()+1) <= Math.abs(getGrille().getJoueur().getX()-getX())) {
						setDirection(3);
					}
				}
			}
		}
	}


	

}