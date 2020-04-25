package Modele;

import java.util.Observable;
import java.util.Random;

public class Entite extends Observable {
	
	private int x, y;
	private Grille grille;
	private boolean etat;
	private int tempsEtat;
    Random r = new Random();
    int deltaX = 1;
	int deltaY = 1;
	private int direction;
	private int difficulte;
	int a=1;
    boolean j;

    /**
     * 
     * @param x valeur de l'abscisse
     * @param y valeur de l'ordonnée
     * @param direction valeur de la direction (1 (haut), 2 (droite), 3 (bas), 4 (gauche))
     */
	public Entite (int x, int y, int direction) {
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.etat=false;

	}
	
	/**definit la grille de jeu
	 * 
	 * @param grille la grille de jeu
	 */
	public void setGrille (Grille grille) {
		this.grille=grille;
	}
	
	/**
	 * 
	 * @return la grille de jeu
	 */
	public Grille getGrille () {
		return this.grille;
	}
	
	/**
	 * 
	 * @return l'abscisse de l'entité
	 */
    public int getX() {
        return x;
    }
    
    /**
     * 
     * @return l'ordonnée de l'entité
     */
    public int getY() {
        return y;
    }
    
    /**
     *  change l'etat de l'entité
     */
    public void setEtat() {
		this.etat=!etat;
	}
    
    /**
     * 
     * @param etat statut de l'etat
     */
    public void setEtat(boolean etat) {
		this.etat=etat;
	}
    
    /**
     * 
     * @param i change difficulté à i
     */
    public void setDifficulte (int i) {
    	difficulte=i;
    }
    
    /**
     * 
     * @param x definit la valeur de l'abscisse
     * @param y definit la valeur de l'ordonnée
     */
    public void initXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 
     * @param i 
     * @return
     */
	public boolean isCouloir(int i) {
		if (i==1) {
			if (x + deltaX > 0 && x + deltaX < grille.getSizeX()) 
				return (grille.getCases()[this.getX()+deltaX][this.getY()] instanceof Couloir);
		}
		
		if (i==2) {
			if (y + deltaY > -1 && y + deltaY < grille.getSizeY()) 
				return (grille.getCases()[this.getX()][this.getY()+deltaY] instanceof Couloir);
		}
		
		if (i==3) {
			if (x - deltaX > -1 && x - deltaX < grille.getSizeX()) 
				return (grille.getCases()[this.getX()-deltaX][this.getY()] instanceof Couloir);
		}
		
	    if (i==4) {
	    	if (y - deltaY > -1 && y - deltaY < grille.getSizeY()) 
	    		return (grille.getCases()[this.getX()][this.getY()-deltaY] instanceof Couloir);
	    }
	    
	    return false;	   
	}
	
	/**
	 * definit l'abscisse	 * 
	 * @param i 
	 */
	public void setX(int i) {
		this.x=i;
	}
	
	/**
	 * definit l'ordonnée
	 * @param i 
	 */
	public void setY(int i) {
		this.y=i;
	}
	
	/**
	 * definit la direction
	 * @param i 
	 */
	public void setDirection(int i) {
		if (isCouloir(i))
			this.direction=i;
	}
    
	/**
	 * la direction de l'entité
	 * @return l'int de la direction
	 */
	public int getDirection() {
		return this.direction;
	}
	
	/**
	 * retourne la difficulté de la partie
	 * @return le niveau de difficulté
	 */
	public int getDifficulte() {
		return difficulte;
	}
	
	/**
	 * deplace l'entité
	 * @throws Exception
	 */
    public void deplacer() throws Exception {
    	j=false;
    	if (this.getEtat())
			this.setTempsEtat();
    	if (this instanceof PacMan) {
    		((PacMan)this).testGagne();
    		
			j=true;
    		if (x > -1 && x < grille.getSizeX() && y > -1 && y < grille.getSizeY()) {
    			for (int i = 1; i < 5; i++) {
    				if (detectCollision(i)) {
    					if (this.getEtat()) {
    						((PacMan)this).score+=200;
    						try {
    							((PacMan)this).jouer(4);
    						}
    						catch (Exception e){
	
    						}
    						grille.getFantome(i).setX(13);
    						grille.getFantome(i).setY(14);
    						grille.getFantome(i).setDirection(3);
    						grille.getFantome(i).setTempsEtat(20);
    						if (!grille.getFantome(i).getEtat()) {
    							grille.getFantome(i).setEtat();
    						}
    					}

    					else { 
    						((PacMan)this).jouer(5);
    						if (((PacMan)this).vies==0) {
    							((PacMan)this).vies--;
    							((PacMan)this).testPerd();
	    						try {
	    							((PacMan)this).spm.interrupt();
	    							((PacMan)this).setPartie(false);
	    						}
	    						catch (Exception e){
		
	    						}
    						}
    						else {
    							((PacMan)this).spm.replacer();
    						}
    					}
    				}
    			}
    			
    			((PacMan) this).ramasser();
    		}
    	}
    	if (!this.getEtat() || this instanceof PacMan) {
	    	if (direction==1) {
	    	   if (x + deltaX == grille.getSizeX()) {
	    		   x=0;
	    	   }
	    	   
	    	   else {
	    		   if (isCouloir(direction)) 
	        		   x += deltaX;
	    		   else if (grille.getCases()[this.getX()+deltaX][this.getY()] instanceof Porte && !j)
	        		   x += deltaX;
	    	   }
	       }
	       
	       if (direction==2) {
	    	   if (y + deltaY == grille.getSizeY()) {
	    		   y=0;
	    	   }
	    	   
	    	   else {
	    		   if (isCouloir(direction)) 
	    			   y += deltaY;
	    		   else if (grille.getCases()[this.getX()][this.getY()+deltaY] instanceof Porte && !j)
	        		   y += deltaY;
	    	   }
	       }
	       
	       if (direction==3) {
	    	   if (x - deltaX == -1) {
	    		   x=grille.getSizeX()-1;
	    	   }
	    	   else {
	    		   if (isCouloir(direction))
	    		   x -= deltaX;
	    		   else if (grille.getCases()[this.getX()-deltaX][this.getY()] instanceof Porte && !j)
	    		   x -= deltaX;
	    	   }
	       }
	       
	       if (direction==4) {
	    	   if (y - deltaY == -1) {
	    		   y=grille.getSizeY()-1;
	    	   }
	    	   else {
	    		   if (isCouloir(direction)) 
	    			   y -= deltaY;
	    		   else if (grille.getCases()[this.getX()][this.getY()-deltaY] instanceof Porte && !j)
	        		   y -= deltaY;
	    	   }
	       }
    	}
       
       
       if (this instanceof Fantome) {
    	   if (a<=3) {
    		   ((Fantome) this).choixDirection();
    		   a+=difficulte;
    	   }
    	   else {
    		   ((Fantome) this).choixDirection2();
    		   a=a-4+difficulte;
    	   }
       }      
    }
    
    private boolean detectCollision(int i) {
		if (this instanceof PacMan) {
			if ((grille.getFantome(i).getX()==this.getX() && grille.getFantome(i).getY()==this.getY()) || (grille.getFantome(i).getX()==this.getX()-1 && grille.getFantome(i).getY()==this.getY() && this.getDirection()==3 && grille.getFantome(i).getDirection()==1) || (grille.getFantome(i).getX()==this.getX()+1 && grille.getFantome(i).getY()==this.getY() && this.getDirection()==1 && grille.getFantome(i).getDirection()==3)|| (grille.getFantome(i).getX()==this.getX() && grille.getFantome(i).getY()==this.getY()+1 && this.getDirection()==2 && grille.getFantome(i).getDirection()==4)|| (grille.getFantome(i).getX()==this.getX() && grille.getFantome(i).getY()==this.getY()-1 && this.getDirection()==4 && grille.getFantome(i).getDirection()==2)) {
				return true;
			}
		}
		return false;
	}

	/**
     * retourne l'etat de l'entité 
     * @return 
     */
	public boolean getEtat() {
		return etat;
	}
	
	/**
	 * definit le temps de l'etat
	 * @param i
	 * 			le temps entre chaque état
	 */
	public void setTempsEtat(int i) {
		this.tempsEtat=i;
	}
	
	/**
	 * decremente le temps avant le prochain etat ou change l'etat si le temps arrive à 0
	 */
	public void setTempsEtat() {
		this.tempsEtat=this.getTempsEtat()-1;
		if (tempsEtat<=0)
			this.setEtat();
	}
	
	/**
	 * retourne le temps avant le changement du prochain etat
	 * @return temps avant le prochain état
	 */
	public int getTempsEtat() {
		return this.tempsEtat;
	}
	
	
	
}