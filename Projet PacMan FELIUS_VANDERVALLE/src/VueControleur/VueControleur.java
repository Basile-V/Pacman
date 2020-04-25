/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueControleur;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import Modele.Couloir;
import Modele.Fantome;
import Modele.Grille;
import Modele.PacMan;
import Modele.Mur;
import Modele.Porte;
import Modele.Jeu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class VueControleur extends Application {
    
    
   
    @Override
    public void start(Stage primaryStage) throws Exception {
	
    	//String buttonStyle2 = "-fx-padding: 8 15 15 15;-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%), #9d4024, #d86e3a, radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;";
    	
    	String buttonStyle = "-fx-background-color: \r\n" + 
    			"        linear-gradient(#ffd65b, #e68400),\r\n" + 
    			"        linear-gradient(#ffef84, #f2ba44),\r\n" + 
    			"        linear-gradient(#ffea6a, #efaa22),\r\n" + 
    			"        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\r\n" + 
    			"        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\r\n" + 
    			"    -fx-background-radius: 30; -fx-background-insets: 0,1,2,3,0; -fx-text-fill: #654b00; -fx-font-weight: bold; -fx-font-size: 30px; -fx-padding: 10 20 10 20;";
    	Font police = new Font("Impact",30);
	    Label topLabel = new Label();
	    topLabel.setFont(police);
	    topLabel.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid"); 
	    topLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 
	    topLabel.setMinHeight(100);
	    topLabel.setMinWidth(400);
	    
	    Label LabelVide = new Label("");
	    LabelVide.setFont(police);
	    LabelVide.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid"); 
	    LabelVide.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 
	    LabelVide.setMinHeight(100);
	    LabelVide.setMinWidth(400);
	    
	    Label LabelVideD = new Label("");
	    LabelVideD.setFont(police);
	    LabelVideD.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid"); 
	    LabelVideD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 
	    LabelVideD.setMinHeight(100);
	    LabelVideD.setMinWidth(400);
    	
    	Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setTitle("PACMAN LE JEU !");
    	
        PacMan joueur = new PacMan (2, 2, 1, 0);
        Fantome fantome1 = new Fantome (14, 15, 3);
        Fantome fantome2 = new Fantome (14, 14, 3);
        Fantome fantome3 = new Fantome (14, 14, 3);
        Fantome fantome4 = new Fantome (14, 15, 3);
        int[][] monde = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,1,1},
        {1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,4,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1,1},
        {1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
        {1,1,1,0,1,1,1,0,1,1,0,1,1,1,3,3,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,0,1,1,1,0,1,1,0,1,1,1,2,2,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,5,0,0,0,0,0,1,1,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0},
        {1,1,1,1,1,1,1,0,1,1,0,1,0,0,4,0,0,0,1,0,1,1,0,1,1,0,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,1,1},
        {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1}};
        
        
		Grille grille1 = new Grille (monde, joueur, fantome1, fantome2, fantome3, fantome4);
		
		int[][] monde2 = {
        {1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,0,0,5,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,4,0,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,4,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1,1},
        {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,0,1,1,1,3,3,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,0,1,1,1,2,2,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,5,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,1,1,1,0,1,1,0,1,0,0,4,0,0,0,1,0,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1},
        {1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1},
        {1,1,4,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,4,0,0,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
        
		Grille grille2 = new Grille (monde2, joueur, fantome1, fantome2, fantome3, fantome4);
		
		joueur.setGrille(grille1);
		joueur.setMaxScore();
		fantome1.setGrille(grille1);
		fantome2.setGrille(grille1);
		fantome3.setGrille(grille1);
		fantome4.setGrille(grille1);
		Jeu spm = new Jeu(joueur, fantome1, fantome2, fantome3, fantome4);
		joueur.setSPM(spm);
        GridPane grid = new GridPane();
        GridPane grid2 = new GridPane();
        
        
        
        File musicMenu = new File("Sons/musiqueMenu.wav"); 
	    Media musiqueMenu = new Media(musicMenu.toURI().toURL().toExternalForm()); 
	    MediaPlayer mediaPlayerM = new MediaPlayer(musiqueMenu);
        
	    File music1 = new File("Sons/musique1.wav"); 
	    Media musique1 = new Media(music1.toURI().toURL().toExternalForm()); 
	    MediaPlayer mediaPlayer1 = new MediaPlayer(musique1);
	    
	    File music2 = new File("Sons/musique2.wav"); 
	    Media musique2 = new Media(music2.toURI().toURL().toExternalForm()); 
	    MediaPlayer mediaPlayer2 = new MediaPlayer(musique2);
	    
	    File music3 = new File("Sons/musique3.wav"); 
	    Media musique3 = new Media(music3.toURI().toURL().toExternalForm()); 
	    MediaPlayer mediaPlayer3 = new MediaPlayer(musique3);
	    
	    BorderPane rootMenu = new BorderPane();
	    
	    Scene sceneMenu=new Scene(rootMenu, 400, 400);
	    Scene sceneJeu;
	    
	    Image imBravo = new Image("images/congratsr.png");
        Image imFANTMal = new Image("images/fantmal.jpg");
        Image imFANT11 = new Image("images/fant11.jpg");
        Image imFANT12 = new Image("images/fant12.jpg");
        Image imFANT21 = new Image("images/fant21.jpg");
        Image imFANT22 = new Image("images/fant22.jpg");
        Image imFANT31 = new Image("images/fant31.jpg");
        Image imFANT32 = new Image("images/fant32.jpg");
        Image imFANT41 = new Image("images/fant41.jpg");
        Image imFANT42 = new Image("images/fant42.jpg");
        Image imPMG = new Image("images/pmg.png");
        Image imBB = new Image("images/borneB.jpg");
        Image imBH = new Image("images/borneH.jpg");
        Image imPMD = new Image("images/pmd.png");
        Image imPMH = new Image("images/pmh.png");
        Image imPMB = new Image("images/pmb.png");
        Image imMur = new Image("images/mur.jpg");
        //Image imMur1 = new Image("images/mur1.png");
        Image imPorte = new Image("images/porte.jpg");
        //Image imFond = new Image("images/fond.jpg");
        Image imPerdu = new Image("images/gameover1.png");
        
        
        ImageView perdu = new ImageView();
        ImageView imgBoH = new ImageView();
        ImageView imgBoB = new ImageView();
        ImageView bravo = new ImageView();

        bravo.setImage(imBravo);
        perdu.setImage(imPerdu);
        imgBoB.setImage(imBB);
        imgBoH.setImage(imBH);
        grid2.setAlignment(Pos.CENTER);
        grid2.add(imgBoH, 1, 0);
        //grid2.add(imgBoG, 0, 0);
        grid2.add(grid, 1, 1);
        grid2.add(imgBoB, 1, 2);
        
        BorderPane root = new BorderPane();
        
        VBox boutonJeu = new VBox();
        boutonJeu.setMinWidth(300);
        boutonJeu.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid");
        boutonJeu.setSpacing(40);
        boutonJeu.setAlignment(Pos.CENTER);
        boutonJeu.setPadding(new Insets(10,10, 100,10));
        
        Button btnJeu1 = new Button("Restart");
        btnJeu1.setWrapText(true);
        btnJeu1.setMinHeight(45);
        btnJeu1.setStyle(buttonStyle);
        btnJeu1.setMinWidth(100);
        btnJeu1.setFont(police);
        btnJeu1.setOnMouseClicked((e) -> {
        	int difficulte=spm.getJoueur().getDifficulte();
        	
        	spm.setPartie(false);
        	int restart =JOptionPane.showConfirmDialog(null, "Votre score est de " + joueur.getScore() +"\nVoulez vous vraiment recommencer la partie ?","UNE PARTIE EST EN COURS !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        	
        	if (restart == JOptionPane.OK_OPTION) {
        		try {
                    mediaPlayer1.stop();
                    mediaPlayer2.stop();
                    mediaPlayer3.stop();
                    mediaPlayerM.stop();
                    spm.reset();
                    spm.start();
                    Platform.runLater(new Runnable() {
	                	public void run() {
	                		root.setCenter(grid2);
	                	}
	                });
                    if (difficulte==1)
                		mediaPlayer1.play();
                    if (difficulte==2)
                		mediaPlayer2.play();
                    if (difficulte==3)
                		mediaPlayer3.play();
        		}
        		catch (Exception f){
        			
        		}
        	}
        	else 
        		spm.start();
	 
		});
        
        HBox boutonMenu = new HBox();
        boutonMenu.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid; ");
        boutonMenu.setSpacing(40);
        boutonMenu.setAlignment(Pos.CENTER);
        boutonMenu.setPadding(new Insets(10,10, 100,10));
        
        Button btnJeu2 = new Button("Quitter");
        btnJeu2.setWrapText(true);
        btnJeu2.setMinHeight(45);
        btnJeu2.setStyle(buttonStyle);
        btnJeu2.setMinWidth(100);
        btnJeu2.setFont(police);
        btnJeu2.setOnMouseClicked((e) -> {
        	
        	spm.setPartie(false);
        	int quitter =JOptionPane.showConfirmDialog(null, "Votre score est de " + joueur.getScore() +"\nVoulez vous vraiment retourner au menu principal ?","UNE PARTIE EST EN COURS !", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        	
        	if (quitter == JOptionPane.OK_OPTION) {
        		try {
        			topLabel.setText("");
                	rootMenu.setBottom(boutonMenu);
                	primaryStage.setScene(sceneMenu);
                    primaryStage.show();
                    mediaPlayer1.stop();
                    mediaPlayer2.stop();
                    mediaPlayer3.stop();
                    mediaPlayerM.play();
                    spm.reset();
                    spm.interrupt();
                    
        		}
        		catch (Exception f){
        			
        		}
        	}
        	else 
        		spm.start();
        	
		});

        boutonJeu.getChildren().add(btnJeu1);
        boutonJeu.getChildren().add(btnJeu2);
        
        Label labelScore = new Label("Score : "+ spm.getJoueur().getScore() + " Points");
        labelScore.setFont(police);
        labelScore.setMinWidth(300);
        labelScore.setPadding(new Insets(10,10, 100,10));
        labelScore.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid"); 
        labelScore.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 
        labelScore.setMinHeight(50);
        
        
        Label labelVie = new Label("Vies restantes : "+ spm.getJoueur().getVies());
        labelVie.setFont(police);
        labelVie.setMinWidth(300);
        labelVie.setPadding(new Insets(10,10, 100,10));
        labelVie.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid"); 
        labelVie.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 
        labelVie.setMinHeight(50);
        
        
        
        VBox labelsJeu = new VBox();
        labelsJeu.setMinWidth(300);
        labelsJeu.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid");
        labelsJeu.setSpacing(40);
        labelsJeu.setAlignment(Pos.CENTER);
        labelsJeu.setPadding(new Insets(10,10, 100,10));
        labelsJeu.getChildren().add(labelScore);
        labelsJeu.getChildren().add(labelVie);
        
        
        
        root.setTop(topLabel);
        root.setBackground(new Background(new BackgroundFill(Color.INDIGO, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setCenter(grid2);
        root.setLeft(boutonJeu);
        root.setRight(labelsJeu);
        sceneJeu = new Scene(root, 400, 400);
        

        HBox boutonCarte = new HBox();
        boutonCarte.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid");
        boutonCarte.setSpacing(40);
        boutonCarte.setAlignment(Pos.CENTER);
        boutonCarte.setPadding(new Insets(10,10, 100,10));
        
        HBox boutonNiveau = new HBox();
        boutonNiveau.setStyle("-fx-alignment: center; -fx-background-color:MediumOrchid");
        boutonNiveau.setSpacing(40);
        boutonNiveau.setAlignment(Pos.CENTER);
        boutonNiveau.setPadding(new Insets(10,10, 100,10));
        

        
        Button btnNiveau1 = new Button("N00b");
        btnNiveau1.setWrapText(true);
        btnNiveau1.setMinHeight(45);
        btnNiveau1.setMinWidth(100);
        btnNiveau1.setStyle(buttonStyle);
        btnNiveau1.setFont(police);
        btnNiveau1.setOnMouseClicked((e) -> {
        	spm.setDifficulte(1);
        	spm.setTempspause(320);
            primaryStage.setScene(sceneJeu);
            primaryStage.show();
            spm.start();
            mediaPlayerM.stop();
            mediaPlayer1.play();
    		spm.setPartie(true);
    		root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
	            
	            @Override
	            public void handle(javafx.scene.input.KeyEvent event) {
	                
	                if (event.getCode() == KeyCode.D) {
	                	joueur.setDirection(1);
	                }
	                if (event.getCode() == KeyCode.S) {
	                	joueur.setDirection(2);
	                }
	                if (event.getCode() == KeyCode.Q) {
	                	joueur.setDirection(3);
	                }
	                if (event.getCode() == KeyCode.Z) {
	                	joueur.setDirection(4);
	                }
	            }
	        });
	        grid.requestFocus();
		
		});
        
        Button btnNiveau2 = new Button("\"Mon cousin a la Commodore 64\"");
        btnNiveau2.setWrapText(true);
        btnNiveau2.setMinHeight(45);
        btnNiveau2.setMinWidth(100);
        btnNiveau2.setStyle(buttonStyle);
        btnNiveau2.setFont(police);
        btnNiveau2.setOnMouseClicked((e) -> {
        	spm.setDifficulte(2);
        	spm.setTempspause(260);
        	primaryStage.setScene(sceneJeu);
            primaryStage.show();
            mediaPlayerM.stop();
            mediaPlayer2.play();
            spm.start();
            spm.setPartie(true);
    		root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
	            
	            @Override
	            public void handle(javafx.scene.input.KeyEvent event) {
	                
	                if (event.getCode() == KeyCode.D) {
	                	joueur.setDirection(1);
	                }
	                if (event.getCode() == KeyCode.S) {
	                	joueur.setDirection(2);
	                }
	                if (event.getCode() == KeyCode.Q) {
	                	joueur.setDirection(3);
	                }
	                if (event.getCode() == KeyCode.Z) {
	                	joueur.setDirection(4);
	                }
	            }
	        });
	        grid.requestFocus();
		});
        
        Button btnNiveau3 = new Button("Brutal !");
        btnNiveau3.setWrapText(true);
        btnNiveau3.setMinHeight(45);
        btnNiveau3.setMinWidth(100);
        btnNiveau3.setStyle(buttonStyle);
        btnNiveau3.setFont(police);
        btnNiveau3.setOnMouseClicked((e) -> {
    		spm.setDifficulte(3);
    		spm.setTempspause(200);
    		primaryStage.setScene(sceneJeu);
            primaryStage.show();
            mediaPlayerM.stop();
            mediaPlayer3.play();
            spm.start();
            spm.setPartie(true);
    		root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
	            
	            @Override
	            public void handle(javafx.scene.input.KeyEvent event) {
	                
	                if (event.getCode() == KeyCode.D) {
	                	joueur.setDirection(1);
	                }
	                if (event.getCode() == KeyCode.S) {
	                	joueur.setDirection(2);
	                }
	                if (event.getCode() == KeyCode.Q) {
	                	joueur.setDirection(3);
	                }
	                if (event.getCode() == KeyCode.Z) {
	                	joueur.setDirection(4);
	                }
	            }
	        });
	        grid.requestFocus();
		});
        
        Button btnNiveau4 = new Button("Retour");
        btnNiveau4.setWrapText(true);
        btnNiveau4.setMinHeight(45);
        btnNiveau4.setStyle(buttonStyle);
        btnNiveau4.setMinWidth(100);
        btnNiveau4.setFont(police);
        btnNiveau4.setOnMouseClicked((e) -> {
        	topLabel.setText("");
        	rootMenu.setBottom(boutonCarte);
		});
        
        
        boutonNiveau.getChildren().add(btnNiveau1);
        boutonNiveau.getChildren().add(btnNiveau2);
        boutonNiveau.getChildren().add(btnNiveau3);
        boutonNiveau.getChildren().add(btnNiveau4);
        
        
        Button btnCarte1 = new Button("Carte 1");
        btnCarte1.setWrapText(true);
        btnCarte1.setMinHeight(45);
        btnCarte1.setMinWidth(100);
        btnCarte1.setStyle(buttonStyle);
		btnCarte1.setFont(police);
        btnCarte1.setOnMouseClicked((e) -> {
        	spm.setGrille(grille1);
        	topLabel.setText("Carte choisie : Carte 1 \n Choix du niveau de difficulté :");
        	rootMenu.setBottom(boutonNiveau);
		});
        
        Button btnCarte2 = new Button("Carte 2");
        btnCarte2.setWrapText(true);
        btnCarte2.setMinHeight(45);
        btnCarte2.setMinWidth(100);
        btnCarte2.setStyle(buttonStyle);
		btnCarte2.setFont(police);
        btnCarte2.setOnMouseClicked((e) -> {
        	spm.setGrille(grille2);
        	topLabel.setText("Carte choisie : Carte 2 \n Choix du niveau de difficulté :");
        	rootMenu.setBottom(boutonNiveau);
		});
        
        Button btnCarte3 = new Button("Retour");
        btnCarte3.setWrapText(true);
        btnCarte3.setMinHeight(45);
        btnCarte3.setMinWidth(100);
        btnCarte3.setStyle(buttonStyle);
        btnCarte3.setFont(police);
        btnCarte3.setOnMouseClicked((e) -> {
        	topLabel.setText("");
        	rootMenu.setBottom(boutonMenu);
		});
        
        boutonCarte.getChildren().add(btnCarte1);
        boutonCarte.getChildren().add(btnCarte2);
        boutonCarte.getChildren().add(btnCarte3);
        
        Button btnQuitter = new Button();
		Image imQuit = new Image("images/btnquit.png");
		ImageView iconQuitter = new ImageView(imQuit);
		iconQuitter.setFitWidth(120);
		iconQuitter.setPreserveRatio(true);
		btnQuitter.setStyle(buttonStyle);
		btnQuitter.setGraphic(iconQuitter);
		btnQuitter.setOnMouseClicked((e) -> {
			Platform.exit();
			spm.setPartie(false);
		});
		Button btnJouer = new Button();
		Image imJouer = new Image("images/btnjouer.png");
		ImageView iconJouer = new ImageView(imJouer);
		btnJouer.setStyle(buttonStyle);
		iconJouer.setFitWidth(150);
		iconJouer.setPreserveRatio(true);
		btnJouer.setGraphic(iconJouer);
		btnJouer.setOnMouseClicked((e) -> {
			rootMenu.setBottom(boutonCarte);
		});
        boutonMenu.getChildren().add(btnJouer);
        boutonMenu.getChildren().add(btnQuitter);

	    ImageView imgMenu = new ImageView();
	    Image iconPM = new Image("images/pmgrand.png");
	    imgMenu.setImage(iconPM);
	    imgMenu.setFitWidth(600);
	    imgMenu.setPreserveRatio(true);
        
        rootMenu.setTop(topLabel);
        rootMenu.setLeft(LabelVide);
        rootMenu.setRight(LabelVideD);
        rootMenu.setBackground(new Background(new BackgroundFill(Color.INDIGO, CornerRadii.EMPTY, Insets.EMPTY)));
        rootMenu.setCenter(imgMenu);
        rootMenu.setBottom(boutonMenu);
        
        ImageView[][] tab = new ImageView[100+1][100];
        
        for (int i = 0; i < 100; i++) { 
            for (int j = 0; j < 100; j++) {
                ImageView img = new ImageView();
                
                tab[i][j] = img;
                
                grid.add(img, i, j);
            }
            
        }
        
        Observer o =  new Observer() {
            @Override
            public void update(Observable o, Object arg)  {
            	
            	
            	boolean entite;
            	if (joueur.testPerd()) 
            		Platform.runLater(new Runnable() {
	                	public void run() {
	                		root.setCenter(perdu);
	                		primaryStage.show();
	                		//spm.interrupt();
	                	}
	                });
            	if (joueur.testGagne()) 
            		Platform.runLater(new Runnable() {
	                	public void run() {
	                		root.setCenter(bravo);
	                		primaryStage.show();
	                		//spm.interrupt();
	                	}
	                });
            	
            	if (spm.getPartie()) {
            		
            		Platform.runLater(new Runnable() {
	                	public void run() {
	                		root.setCenter(grid2);
	                	}
	                });
            		
            		final int SIZE_X = 31;
            	    final int SIZE_Y = 31;
	                for (int i = 0; i < SIZE_X-1; i++) {
	                    for (int j = 0; j < SIZE_Y-1; j++) {
	                        entite=false;
	                    	if (fantome1.getX() == i && fantome1.getY() == j) {
	                    		entite=true;
	                    		if (joueur.getEtat())
	                    			tab[i][j].setImage(imFANTMal);
	                    		else if (fantome1.getDirection()==1 || fantome1.getDirection()==2)
	                    			tab[i][j].setImage(imFANT11);
	                    		else 
	                    			tab[i][j].setImage(imFANT12);
	                    	}
	                    	
	                    	if (fantome2.getX() == i && fantome2.getY() == j) {
	                    		entite=true;
	                    		if (joueur.getEtat())
	                    			tab[i][j].setImage(imFANTMal);
	                    		else if (fantome2.getDirection()==1 || fantome2.getDirection()==2)
	                    			tab[i][j].setImage(imFANT21);
	                    		else 
	                    			tab[i][j].setImage(imFANT22);
	                    	}
	                    	
	                    	if (fantome3.getX() == i && fantome3.getY() == j) {
	                    		entite=true;
	                    		if (joueur.getEtat())
	                    			tab[i][j].setImage(imFANTMal);
	                    		else if (fantome3.getDirection()==1 || fantome3.getDirection()==2)
	                    			tab[i][j].setImage(imFANT31);
	                    		else 
	                    			tab[i][j].setImage(imFANT32);
	                    	}
	                    	
	                    	if (fantome4.getX() == i && fantome4.getY() == j) {
	                    		entite=true;
	                    		if (joueur.getEtat())
	                    			tab[i][j].setImage(imFANTMal);
	                    		else if (fantome4.getDirection()==1 || fantome4.getDirection()==2)
	                    			tab[i][j].setImage(imFANT41);
	                    		else 
	                    			tab[i][j].setImage(imFANT42);
	                    	}
	                    	
	                        if (joueur.getX() == i && joueur.getY() == j) {
	                        	entite=true;
	                        	if (joueur.getDirection()==1)
	                        		tab[i][j].setImage(imPMD);
	                        	if (joueur.getDirection()==2)
	                        		tab[i][j].setImage(imPMB);
	                        	if (joueur.getDirection()==3)
	                        		tab[i][j].setImage(imPMG);
	                        	if (joueur.getDirection()==4)
	                        		tab[i][j].setImage(imPMH);
	                        	
	                        }
	                        if (!entite) {
	                            if (spm.getGrille().getCases()[i][j] instanceof Mur)
	                            	tab[i][j].setImage(imMur);
	                            if (spm.getGrille().getCases()[i][j] instanceof Porte)
	                            	tab[i][j].setImage(imPorte);
	                            else 
	                            	if (spm.getGrille().getCases()[i][j] instanceof Couloir) {
	                            		tab[i][j].setImage(((Couloir) spm.getGrille().getCases()[i][j]).getContenu().getImage());
	                            	}
	                        } 
	                    }
	                }
	                
	                Platform.runLater(new Runnable() {
	                	public void run() {
	            		labelScore.setText("Score  : "+ ((Integer)(spm.getJoueur().getScore())).toString() + " Points");
	                	labelVie.setText("Vies restantes : "+ spm.getJoueur().getVies());
	                	}
	                });

				}
            }			
        };
        
        
        spm.addObserver(o);
        joueur.addObserver(o);
        fantome1.addObserver(o);
        fantome2.addObserver(o);
        fantome3.addObserver(o);
        fantome4.addObserver(o);

        
        primaryStage.setScene(sceneMenu);
        primaryStage.show();
        mediaPlayerM.play();
        
    }
    

	public static void main(String[] args) {
    	
        launch(args);
    }
    
}
