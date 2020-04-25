package Modele;

public class Grille {
    private int sizeX, sizeY;
    private Statique[][] cases;
    private PacMan joueur;
    private int maxScore;
    private Fantome fantome1, fantome2, fantome3, fantome4;
    Contenu vide = new Contenu (0);
    Contenu pacgum = new Contenu (1);
    Contenu superPacgum = new Contenu (2);
    Contenu sablier = new Contenu (3);
    int [][] monde;
    
    /**
     * 
     * @param monde 
     * 		la grille du monde
     * @param joueur
     * 		le pac-man
     * @param fantome1
     * 		le premier fantome
     * @param fantome2
     * 		le deuxieme fantome
     * @param fantome3
     * 		le troisieme fantome
     * @param fantome4
     * 		le quatrieme fantome
     */
    public Grille (int[][] monde, PacMan joueur, Fantome fantome1, Fantome fantome2, Fantome fantome3, Fantome fantome4) {
    	this.joueur=joueur;
    	this.fantome1=fantome1;
    	this.fantome2=fantome2;
    	this.fantome3=fantome3;
    	this.fantome4=fantome4;
    	this.sizeX=monde.length;
    	this.sizeY=monde[0].length;
    	this.monde=monde;
    	this.cases= new Statique[sizeX][sizeY];
    	int mS=0;
    	for (int i = 0; i < sizeX; i++) {
    		for (int j = 0; j < sizeY; j++) {
    			if (monde[i][j]==1)
    				this.cases[i][j]=new Mur(i, j);
    			if (monde[i][j]==2)
    				this.cases[i][j]=new Couloir(i, j, vide);
    			if (monde[i][j]==0) {
    				this.cases[i][j]=new Couloir(i, j, pacgum);
    				mS+=5;
    			}
    			if (monde[i][j]==3)
    				this.cases[i][j]=new Porte(i, j);
    			if (monde[i][j]==4) {
    				this.cases[i][j]=new Couloir(i, j, superPacgum);
    				mS+=50;
    			}
    			if (monde[i][j]==5) {
    				this.cases[i][j]=new Couloir(i, j, sablier);
    				mS+=50;
    			}
    		}
    	}
    	this.maxScore=mS;
    }
    
    /**
     * donne le score maximum
     * @return la valeur maximale de score
     */
    public int getMaxScore () {
		return maxScore;
	}
    
    /**
     * reinitialise la grille
     */
    public void reset() {
    	for (int i=1; i <= 4; i++) {
    		getFantome(i).setX(13);
			getFantome(i).setY(13);
			getFantome(i).setDirection(3);
    	}
    	getJoueur().setX(2);
    	getJoueur().setY(2);
    	getJoueur().setDirection(1);
    	for (int i = 0; i < sizeX; i++) {
    		for (int j = 0; j < sizeY; j++) {
    			if (monde[i][j]==1)
    				this.cases[i][j]=new Mur(i, j);
    			if (monde[i][j]==2)
    				this.cases[i][j]=new Couloir(i, j, vide);
    			if (monde[i][j]==0)
    				this.cases[i][j]=new Couloir(i, j, pacgum);
    			if (monde[i][j]==3)
    				this.cases[i][j]=new Porte(i, j);
    			if (monde[i][j]==4)
    				this.cases[i][j]=new Couloir(i, j, superPacgum);
    			if (monde[i][j]==5) 
    				this.cases[i][j]=new Couloir(i, j, sablier);
    		}
    	}
    	
    }
    
    /**
     * retourne la longueur de la grille
     * @return
     */
	public int getSizeX () {
		return sizeX;
	}
	
	/**
	 * retourn la hauteur de la grille
	 * @return
	 */
	public int getSizeY () {
		return sizeY;
	}
    
	/**
	 * retourne l'ensemble la grille des cases
	 * @return
	 */
    public Statique[][] getCases () {
    	return cases;
    }
    
    /**
     * retourne le pac man
     * @return
     */
    public PacMan getJoueur () {
    	return this.joueur;
    }
    
    /**
     * retourne le ième fantome
     * @param i
     * 		le numéto du fantome
     * @return
     */
    public Fantome getFantome (int i) {
    	if (i==1)
    		return this.fantome1;
    	if (i==2)
    		return this.fantome2;
    	if (i==3)
    		return this.fantome3;
    	else
    		return this.fantome4;
    }
}