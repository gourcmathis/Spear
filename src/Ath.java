

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
	Data pv;
	Data fleche;
	Data cle;
	Data money;

	public Ath(Personnage p){
		HBox ath= new HBox(150);
		
		
		//Ajout des informations utilent au joueur pendant le jeu
		pv=new Data("file:assets/Joueur.png", Data.datatype.DATAPV,p);
		money=new Data("file:assets/Argent.png", Data.datatype.DATAARGENT,p);
		cle=new Data("file:assets/Cle.png", Data.datatype.DATACLE,p);
		fleche=new Data("file:assets/Fleche.png", Data.datatype.DATAFLECHE,p);
	 	 //Valeur a determiner en fonction des données du jeu =>trouver un moyen de le lier au classe au cas par cas
	
		Rectangle arriere=new Rectangle(700,60);
		arriere.setFill(Color.DARKGREY);
		arriere.setOpacity(0.5);
		arriere.setTranslateX(-20);
	 	arriere.setTranslateY(10);
	 	arriere.setArcHeight(25);
		arriere.setArcWidth(25);
		
		ath.getChildren().addAll(pv,money,cle, fleche);
		getChildren().addAll(arriere,ath);
		
	}



	//Création des informations avec icones+info
	private static class Data extends StackPane{
		protected enum datatype {DATAPV,DATAFLECHE,DATAARGENT,DATACLE};
		private Text valeur;
		

		public Data(String name, datatype dt,Personnage p){
	
		
	 	 Image img=new Image(name,0 , 0, false, false);
	 	 //Valeur a determiner en fonction des données du jeu =>trouver un moyen de le lier au classe au cas par cas
			switch(dt){
				case DATAPV:
					valeur=new Text(Integer.toString(p.getpV()));
					break;
				case DATACLE:
					valeur=new Text("0");
					break;
				case DATAFLECHE:
					valeur=new Text(Integer.toString(p.getNbFleches()));
					break;

				case DATAARGENT:
					valeur=new Text("0");
					break;
			}

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
		public void setText(int value){
			valeur.setText(Integer.toString(value));
		}
	}
	public void setcle(int value){
		cle.setText(value);
	}
	public void setargent(int value){
		money.setText(value);
	}
	public void setpv(int value){
		pv.setText(value);
	}
	public void setfleche(int value){
		fleche.setText(value);
	}





}