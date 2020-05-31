import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenu extends Parent {
	private GameMenu gameMenu;
	private static VBox root;
	private  Scene mainScene;
	private Stage mainStage;

	public GameMenu() {
			//Box elements verticale
			root=new VBox(30);
			
			//Fenetre contenant le menu principale
			mainScene=new Scene(root,1024,1024);
			mainStage=new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("Spear");
			
			//Image de fond
			BackgroundImage BI=new BackgroundImage(new Image("file:assets/main-background.jpg"),BackgroundRepeat.REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			root.setBackground(new Background(BI));
			
			//Titre
			Text titre=new  Text("SPEAR");
			titre.setFont(Font.loadFont("file:assets/masoneer.ttf", 90));
  			titre.setFill(Color.WHITE);
  			titre.setTranslateX(380);
  			titre.setTranslateY(150);
  			
  			/////////////////////////////////CREDIT///////////////////////
			Rectangle credit=new Rectangle(700,600);
			credit.setFill(Color.WHITE);
			credit.setArcHeight(40);
			credit.setArcWidth(40);
			credit.setTranslateX(162);
			credit.setTranslateY(-50);
			credit.setOpacity(0.7);
			credit.setVisible(false);
			Text creditTitre=new  Text("Credits");
			creditTitre.setFont(Font.loadFont("file:assets/masoneer.ttf", 90));
  			creditTitre.setFill(Color.WHITE);
  			creditTitre.setTranslateX(370);
  			creditTitre.setTranslateY(-942);
  			creditTitre.setVisible(false);
  			Text creditText=new  Text("\n"+"\n"+"Realise par Chomel Louis, Gourc Mathis, Bernedo Hugo, JOLY Lisa.\n"+" \n"+"\n"+"\n"+"Ressources 2D libres de droit par 0x72 sur itch.io \n"+
  					"\n" + "\n"+
  					"Music Battle of the Creek by Alexander Nakarada www.serpentsoundstudios.com\n" + 
  					"Licensed under Creative Commons BY Attribution 4.0 License\n" + 
  					"\n"+ "\n"+"                  EISTI Pau Projet S2 ING 1");
			creditText.setFont(Font.loadFont("file:assets/masoneer.ttf", 20));
  			creditText.setFill(Color.BLACK);
  			creditText.setTranslateX(180);
  			creditText.setTranslateY(-880);
  			creditText.setWrappingWidth(700);
  			creditText.setVisible(false);
  			//revenir a la au menu
			CreditButton btnRetourCredit=new CreditButton("Retour");
			btnRetourCredit.setVisible(false);
			///////////////////////////////////////////////////////////////
  			

			//Creation du bouton
			MenuButton btnJouer=new MenuButton("Jouer");
			//Evenement lorsque l'utilisateur clique sur "Jouer"
			btnJouer.setOnMouseClicked(event->{
				//Transition 
				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(0);
  				ft.setOnFinished(evt->{
  					//creation d'une nouvelle fenetre  de jeu
  					FenetreDeJeu fenetredeJeu=new FenetreDeJeu();
  					fenetredeJeu.createNewGame(mainStage);
  					//Audio
  					String filepath = "assets/background.wav";
  					Audio musicObject = new Audio();
  					musicObject.playMusic(filepath);
  					
  				});
  				ft.play();
  				
			
				
  				
  			});
			
			//Creation du bouton
			MenuButton btnQuitter=new MenuButton("Quitter");
			//Evenement lorsque l'utilisateur clique sur "Quitter"
			btnQuitter.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(0.5);
  				//Fermeture de la fenetre
  				ft.setOnFinished(evt->System.exit(0));
  				ft.play();

				
			});
			
			//Creation du bouton
			MenuButton btnCredit=new MenuButton("Credits");
			//Evenement lorsque l'utilisateur clique sur "Credit"
			btnCredit.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(1);
  				//Affichage de la case contenant les credits et effacement du reste 
  				ft.setOnFinished(evt->{
  					credit.setVisible(true);
  					btnJouer.setVisible(false);
  					btnCredit.setVisible(false);
  					btnQuitter.setVisible(false);
					btnRetourCredit.setVisible(true);
					creditTitre.setVisible(true);
					creditText.setVisible(true);
					titre.setVisible(false);
  					
  				});
  				ft.play();
  				
  			});
			
			//Evenement lorsque l'utilisateur clique sur "retour"
			 btnRetourCredit.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(1);
  				//On efface les credits et on fait apparaitre le menu
  				ft.setOnFinished(evt->{
  					credit.setVisible(false);
  					btnJouer.setVisible(true);
  					btnCredit.setVisible(true);
  					btnQuitter.setVisible(true);
  					btnRetourCredit.setVisible(false);
  					creditTitre.setVisible(false);
  					creditText.setVisible(false);
  					titre.setVisible(true);
  					
  				});
  				ft.play();
  				
  			});
			
			

  		
			
			
			btnJouer.setTranslateY(300);
			btnQuitter.setTranslateY(300);
			btnCredit.setTranslateY(300);
			btnRetourCredit.setTranslateY(-180);
		
			root.getChildren().addAll(titre, btnJouer,btnQuitter,btnCredit,credit,btnRetourCredit,creditTitre,creditText);
	}
	
	//Boutons
	private static class MenuButton extends StackPane{
  		private Text text;
  		
  		public MenuButton(String name) {
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
	
	private static class CreditButton extends StackPane{
  		private Text text;
  		
  		public CreditButton(String name) {
  			text=new Text(name);
  			
  			//Police, couleur, taille ect du texte
  			text.setFont(Font.loadFont("file:assets/masoneer.ttf", 25));
  			text.setFill(Color.WHITE);
  		
  			
  			Rectangle bouton=new Rectangle(120,50);
  			
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



	public Stage getMainStage() {
		
		return mainStage;
	}
}
	