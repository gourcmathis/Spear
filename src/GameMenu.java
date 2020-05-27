import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
			root=new VBox(30);
			mainScene=new Scene(root,1024,1024);
			mainStage=new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("Roguelike_game_Menu");
			BackgroundImage BI=new BackgroundImage(new Image("file:assets/main-background.jpg"),BackgroundRepeat.REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			root.setBackground(new Background(BI));
			Text titre=new  Text("MENU");
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
			Text creditTitre=new  Text("Credit");
			creditTitre.setFont(Font.loadFont("file:assets/masoneer.ttf", 90));
  			creditTitre.setFill(Color.WHITE);
  			creditTitre.setTranslateX(370);
  			creditTitre.setTranslateY(-942);
  			creditTitre.setVisible(false);
  			Text creditText=new  Text("Bla bla bla bla bla");
			creditText.setFont(Font.loadFont("file:assets/masoneer.ttf", 20));
  			creditText.setFill(Color.BLACK);
  			creditText.setTranslateX(180);
  			creditText.setTranslateY(-880);
  			creditText.setVisible(false);
			CreditButton btnRetourCredit=new CreditButton("Retour");
			btnRetourCredit.setVisible(false);
			////////////////////////////////////////////////
  			

			
			MenuButton btnJouer=new MenuButton("Jouer");
			btnJouer.setOnMouseClicked(event->{
				//FenetreDeJeu fenetredeJeu=new FenetreDeJeu();
				//fenetredeJeu.createNewGame(mainStage);
				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(0);
  				ft.setOnFinished(evt->{
  					FenetreDeJeu fenetredeJeu=new FenetreDeJeu();
  					fenetredeJeu.createNewGame(mainStage);
  					String filepath = "assets/background.wav";
  					Audio musicObject = new Audio();
  					
  					musicObject.playMusic(filepath);
  					
  				});
  				ft.play();
  				
			
				
  				
  			});
	
			MenuButton btnQuitter=new MenuButton("Quitter");
			btnQuitter.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(0.5);
  				ft.setOnFinished(evt->System.exit(0));
  				ft.play();

				
			});
			
			
			MenuButton btnCredit=new MenuButton("Credit");
			btnCredit.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(1);
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
			
			btnRetourCredit.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),root);
  				ft.setFromValue(1);
  				ft.setToValue(1);
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
	