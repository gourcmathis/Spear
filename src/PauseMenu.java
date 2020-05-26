package com.test;



import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PauseMenu extends Parent{
	private static VBox root;
	GameMenu gameMenu=new GameMenu();
	private PauseButton btnJouer;

	
	public PauseMenu() {
			root=new VBox(30);
			Text titre=new  Text("PAUSE");
			titre.setFont(Font.loadFont("file:assets/masoneer.ttf", 90));
  			titre.setFill(Color.WHITE);
  			titre.setTranslateX(360);
  			titre.setTranslateY(150);





		btnJouer=new PauseButton("Continuer Partie");
	
		
		
  				
			

			PauseButton btnQuitter=new PauseButton("Quitter");
			btnQuitter.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(0.5);
  				ft.setOnFinished(evt->System.exit(0));
  				ft.play();

				
			});
			
			
			
		
  				

			
		
			Rectangle arriere=new Rectangle(1024,1024);
			arriere.setFill(Color.DARKGREY);
			arriere.setOpacity(0.5);
		

			
			
			getBtnJouer().setTranslateY(300);
			btnQuitter.setTranslateY(300);

			getBtnJouer().setTranslateX(210);
			btnQuitter.setTranslateX(210);



		
			root.getChildren().addAll(titre, getBtnJouer() ,btnQuitter);
			getChildren().addAll(arriere,root);
	}
	
	
	public PauseButton getBtnJouer() {
		return btnJouer;
	}




	static class PauseButton extends StackPane{
  		private Text text;
  		
  		public PauseButton(String name) {
  			text=new Text(name);
  			
  			//Police, couleur, taille ect du texte
  			text.setFont(Font.loadFont("file:assets/masoneer.ttf", 25));
  			text.setFill(Color.WHITE);
  		
  			
  			Rectangle bouton=new Rectangle(600,50);
  			
  			//Police, couleur, taille ect du bouton
  			bouton.setOpacity(0.8);
  			bouton.setArcHeight(25);
  			bouton.setArcWidth(25);
  			bouton.setFill(Color.BLACK);
  		
  			
  			
  			getChildren().addAll(bouton,text);
  		//Esthetique des boutons quand la souris passe dessus
  			setOnMouseEntered(event->{
  				bouton.setFill(Color.WHITE);
  				text.setFill(Color.BLACK);
  			});
  			setOnMouseExited(event->{
  				bouton.setFill(Color.BLACK);
  				text.setFill(Color.WHITE);
  			});
  			
  		}
	}



	

}
