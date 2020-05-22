package com.test;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Ath extends Parent{
	private Text valeur;
	public Ath(){
		HBox ath= new HBox(150);
		
		
		//Ajout des informations utilent au joueur pendant le jeu
		Data pV=new Data("file:assets/Joueur.png");
		Data money=new Data("file:assets/Argent.png");
		Data cle=new Data("file:assets/Cle.png");
		Data fleche=new Data("file:assets/Fleche.png");
	 	 //Valeur a determiner en fonction des données du jeu =>trouver un moyen de le lier au classe au cas par cas
	
		Rectangle arriere=new Rectangle(700,60);
		arriere.setFill(Color.DARKGREY);
		arriere.setOpacity(0.5);
		arriere.setTranslateX(-20);
	 	arriere.setTranslateY(10);
	 	arriere.setArcHeight(25);
		arriere.setArcWidth(25);
		
		ath.getChildren().addAll(pV,money,cle, fleche);
		getChildren().addAll(arriere,ath);
		
	}

	//Création des informations avec icones+info
	private static class Data extends StackPane{
		private Text valeur;
		
		
		

	
	
		public Data(String name){
	
		
	 	 Image img=new Image(name,0 , 0, false, false);
	 	 //Valeur a determiner en fonction des données du jeu =>trouver un moyen de le lier au classe au cas par cas
	 	 valeur=new Text("1"/*+Personnage.getNbFleches()*/);
	 	 valeur.setFont(Font.loadFont("file:assets/masoneer.ttf", 35));
	 	 valeur.setFill(Color.WHITE);
	 	 valeur.setTranslateX(50);
	 	 valeur.setTranslateY(20);
	  
	 	 //Icone
	 	 ImageView imgView = new ImageView(img); 
	 	 imgView.setFitHeight(40);
	 	 imgView.setPreserveRatio(true);
	 	 imgView.setTranslateY(15);
	
	 	 getChildren().addAll(imgView,valeur);
		 
		}
	}
}