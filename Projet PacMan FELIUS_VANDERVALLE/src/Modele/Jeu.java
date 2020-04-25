/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Jeu extends Observable implements Runnable {

	public Thread worker;
	boolean partie=true;
	int tempspause=200;
    PacMan joueur;
    Fantome fantome1, fantome2, fantome3, fantome4;
    Random r = new Random();
	boolean running=true;
    
	
	/**
	 * 
	 * @param j
	 * 		le pac man
	 * @param f1
	 * 		le premier fantome
	 * @param f2
	 * 		le deuxieme fantome
	 * @param f3
	 *  	le troisieme fantome
	 * @param f4
	 *  	le quatrieme fantome
	 */
	
    public Jeu(PacMan j, Fantome f1, Fantome f2, Fantome f3, Fantome f4) {
        joueur=j;
        fantome1=f1;
        fantome2=f2;
        fantome3=f3;
        fantome4=f4;
    }
    
    
    /**
     * stop la partie
     */
    
    public void interrupt() {
    	partie=false;
        worker.interrupt();
        setChanged(); 
	    notifyObservers();
    }

    
    /**
     *  retourne true si la partie est en marche sinon false
     * @return
     */
    
    public boolean isRunning() {
    	setChanged(); 
	    notifyObservers();
        return partie;
    }

    
    /**
     * retourne true si la partie est en stop sinon false
     * @return
     */
    
    boolean isStopped() {
        return !partie;
    }


    /**
     * demarre la partie
     */
    
    public void start() {
        Thread worker = new Thread(this);
        worker.start();
        partie=true;
        setChanged(); 
	    notifyObservers();
    }
    
    
    /**
     * actionne une etape du jeu
     */
    
    @Override
    public void run() {

    	while (partie) {
        	try {
				joueur.deplacer();
				fantome1.deplacer();
				fantome2.deplacer();
				fantome3.deplacer();
				fantome4.deplacer();
				joueur.testPerd();
				setChanged(); 
			    notifyObservers();
				//setPartie(!(joueur.testGagne()) && joueur.estTouche());
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		   
		    try {
		        Thread.sleep(tempspause);
		    } catch (InterruptedException ex) {
		        Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    
		    try {
		    	if (joueur.getEtat()) {
		    		joueur.deplacer();
		    		joueur.testPerd();
		    		setChanged(); 
				    notifyObservers();
				    try {
				        Thread.sleep(tempspause);
				    } catch (InterruptedException ex) {
				        Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
				    }
		    	}
		    	joueur.deplacer();
				fantome1.deplacer();
				fantome2.deplacer();
				fantome3.deplacer();
				fantome4.deplacer();
				setChanged(); 
			    notifyObservers();
				joueur.testGagne();
				joueur.testPerd();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		   
		    try {
		        Thread.sleep(tempspause);
		    } catch (InterruptedException ex) {
		        Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    
		    try {
				joueur.deplacer();
				fantome1.deplacer();
				fantome2.deplacer();
				fantome3.deplacer();
				fantome4.deplacer();
				setChanged(); 
			    notifyObservers();
				joueur.testGagne();
				joueur.testPerd();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		   
		    try {
		        Thread.sleep(tempspause);
		    } catch (InterruptedException ex) {
		        Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
		    }
    	}

    }

    
    /**
     * definit le temps de pause
     * @param i
     * 		le temps entre chaque action
     */
    
	public void setTempspause(int i) {
		this.tempspause=i;
	}
	
	
	/**
	 * retourne le temps de pause
	 * @return
	 */
    public int getTempspause() {
        return tempspause;
    }
    
    /**
     * reourne le pac man
     * @return
     */
    public PacMan getJoueur() {
        return joueur;
    }
    
    /**
     * retourne la partie
     * @return
     */
	public boolean getPartie() {
		return partie;
	}
	
	/**
	 * definit si la partie est en cours (true) sinon False
	 * @param partie
	 */
	public void setPartie(boolean partie) {
		this.partie = partie;
	}
	
	/**
	 * replace les fantomes dans la prison, le pac man à l'endroit initial et enlève une vie
	 */
	
	public void replacer() {
		joueur.setVies(joueur.vies-1);
		joueur.setTempsEtat(0);
		joueur.setDirection(1);
		joueur.setEtat(false);
		joueur.setX(2);
		joueur.setY(2);
		joueur.setDirection(1);
		for (int i = 1; i <= 4; i++) {
			getFantome(i).setX(13);
			getFantome(i).setY(13);
			getFantome(i).setDirection(3);
			getFantome(i).setEtat(false);
			getFantome(i).setTempsEtat(0);
		}
		setChanged(); 
	    notifyObservers();
		try {
	        Thread.sleep(3*tempspause);
	    } catch (InterruptedException ex) {
	        Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
	    }
		partie=true;
	}
	
	
	/**
	 * relance une nouvelle partie
	 */
	
	public void reset() {
		joueur.setVies(3);
		joueur.setScore(0);
		joueur.resetScoreBrut();
		joueur.setTempsEtat(0);
		joueur.setDirection(1);
		joueur.setEtat(false);
		for (int i = 1; i <= 4; i++) {
			getFantome(i).setEtat(false);
			getFantome(i).setTempsEtat(0);
		}
		
		joueur.getGrille().reset();
	}

	/**
	 * met le jeu en pose
	 */
	public void sleep() {
		running = false;
		//primaryStage.setScene(sceneMenu);
		//primaryStage.show();
	}

	/**
	 * retourne la grille
	 * @return
	 */
	public Grille getGrille() {
		return joueur.getGrille();
	}
	
	
	/**
	 * retourn le ième fantome
	 * @param i
	 * 		le numéro du fantome
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
	
	
	/**
	 * definit la grille du jeu
	 * @param grille
	 */
	
	public void setGrille(Grille grille) {
		joueur.setGrille(grille);
		joueur.setMaxScore();
		joueur.resetScoreBrut();
		for (int i = 1; i <= 4; i++)
			getFantome(i).setGrille(grille);
	}
	
	
	/**
	 * definit la difficulté de la partie
	 * @param j
	 * 		niveau de difficulté
	 */
	
	public void setDifficulte(int j) {
		joueur.setDifficulte(j);
		for (int i = 1; i < 4; i++)
			getFantome(i).setDifficulte(j);;
	}
}
