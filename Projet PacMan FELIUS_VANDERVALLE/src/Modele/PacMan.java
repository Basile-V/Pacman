package Modele;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PacMan extends Entite{

	Jeu spm;
	int score;
	int scoreBrut;
	int a=1;
	int maxScore;
    
    Contenu vide = new Contenu (0);
    Contenu pacgum = new Contenu (1);
    Contenu superPacgum = new Contenu (2);
    Contenu sablier = new Contenu (3);
	int vies=3;
	
	
	/**
	 * 
	 * @param x la valeur de l'abscisse
	 * @param y la valeur de l'ordonnée
	 * @param direction valeur de la direction (1 (haut), 2 (droite), 3 (bas), 4 (gauche))
	 * @param tempspause temps de pause entre chaque action
	 */
	public PacMan(int x, int y, int direction, int tempspause) {
		super(x, y, direction);
		this.score=0;
		
	}
	
	
	/**
	 * 
	 * @param i valeur du son (1 (mange pacgum), 2 (mange super pacgum), 3 (pac man invincible), 4 (fantome mangé), 5 (pac man mangé))
	 * @throws Exception
	 */
	
	public void jouer(int i) throws Exception {
		
		if (i==1) {

			File file1 = new File("Sons/PelletEat2.wav"); 
		    Media media1 = new Media(file1.toURI().toURL().toExternalForm()); 
		    MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
		    mediaPlayer1.play();
		    
		}
		
		if (i==2) {
			
			File file2 = new File("Sons/PelletEat1.wav"); 
		    Media media2 = new Media(file2.toURI().toURL().toExternalForm()); 
		    MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
		    mediaPlayer2.play();
		    
		}
	    
		if (i==3) {
			
			File file3 = new File("Sons/Invincible.wav"); 
		    Media media3 = new Media(file3.toURI().toURL().toExternalForm()); 
		    MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
		    mediaPlayer3.play();
		    
		}
		
		if (i==4) {
			
			File file4 = new File("Sons/MonsterEaten.wav"); 
		    Media media4 = new Media(file4.toURI().toURL().toExternalForm()); 
		    MediaPlayer mediaPlayer4 = new MediaPlayer(media4);
		    mediaPlayer4.play();
		    
		}
		
		if (i==5) {
			
			File file5 = new File("Sons/PacmanEaten.wav"); 
		    Media media5 = new Media(file5.toURI().toURL().toExternalForm()); 
		    MediaPlayer mediaPlayer5 = new MediaPlayer(media5);
		    mediaPlayer5.play();
		    
		}
	}
	
	
	/**
	 * modification du score et de l'état du pac man en fonction de ce qui est mangé 
	 * 
	 * @throws Exception
	 */
	
	public void ramasser() throws Exception {
		if (this.getGrille().getCases()[this.getX()][this.getY()] instanceof Couloir) {
			Contenu contenu = ((Couloir) this.getGrille().getCases()[this.getX()][this.getY()]).getContenu();
			if (contenu.getType()!=0) {
				((Couloir) this.getGrille().getCases()[this.getX()][this.getY()]).setContenu(vide);
				score+=contenu.getScore();
				scoreBrut+=contenu.getScore();
				if (contenu.getType()==1) {
					if (a==1) {
						try {
							jouer(1);
							a=2;
						}
						catch (Exception e){
							
						}
						
					}
					else {
						try {
							jouer(2);
							a=1;
						}
						catch (Exception e){
							
						}
					}
					
				}
				else {
					try {
						jouer(3);
					}
					catch (Exception e){
						
					}
					//spm.setTempspause(spm.getTempspause()-5);
					
					if (contenu.getType()==2) {
						setTempsEtat(25);
						if (!this.getEtat())
							setEtat();
					}
					
					if (contenu.getType()==3) {
						spm.fantome1.setTempsEtat(20);
						spm.fantome2.setTempsEtat(20);
						spm.fantome3.setTempsEtat(20);
						spm.fantome4.setTempsEtat(20);
						if (!spm.fantome1.getEtat()) {
							spm.fantome1.setEtat();
							spm.fantome2.setEtat();
							spm.fantome3.setEtat();
							spm.fantome4.setEtat();
						}
					}
				}
			}
		}
	}

	
	/**
	 * regarde si le jeu est fini
	 */
	
	public boolean testGagne() {
		if(this.scoreBrut>=this.maxScore) {
			
			spm.setPartie(false);

			return true;
		}
		else
			return false;
	}
	
	/**
	 * regarde si le jeu est fini
	 */
	
	public boolean testPerd() {
		if(this.vies<=-1) {
			spm.setPartie(false);

			return true;
		}
		else
			return false;
	}
	
	
	/**
	 * instancie le score max pour gagner
	 */
	
	public void setMaxScore() {
		this.maxScore=this.getGrille().getMaxScore();
	}
	
	
	/**
	 * 
	 * @param spm la partie de pac man mise en place
	 */
	
	public void setSPM(Jeu spm) {
		this.spm=spm;
	}
	
	
	/**
	 * 
	 * @return le score max pour gagner
	 */
	
	public int getMaxScore() {
		return this.maxScore;
	}
	
	
	/**
	 * 
	 * @return le score de la partie
	 */
	
	public int getScore() {
		return this.score;
	}
	
	
	/**
	 * remet le score à 0
	 */
	
	public void resetScoreBrut() {
		this.scoreBrut=0;
	}

	
	/**
	 * arrete ou debute la partie
	 * @param 
	 */
	
	public void setPartie(boolean partie) {
		spm.setPartie(partie);
	}
	
	
	/**
	 * 
	 * @param change le score à i
	 */
	
	public void setScore(int i) {
		score=i;
	}
	
	
	/**
	 * 
	 * @return donnes le nombres de vie
	 */
	
	public String getVies() {
		return ((Integer)vies).toString();
	}
	
	
	/**
	 * 
	 * @param i modifie le nombre de vie
	 */
	
	public void setVies(int i) {
		vies=i;
		
	}

}
