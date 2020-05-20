
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class ApplicationJeu extends Application {
	private GameMenu gameMenu;
    private final int hauteur = 1024;
    private final int largeur = 1024;
    private long current_time;
    private long elapsed_time;
    private long last_time;
    private long acc_time;

    @Override
     public void start (Stage theStage)throws Exception{
         theStage.setTitle("Roguelike_game");
         Group root = new Group(); // noeud racine
         Canvas canvas = new Canvas(hauteur,largeur);
         gameMenu=new GameMenu();//Menu

         root.getChildren().addAll(canvas,gameMenu);

         Scene theScene = new Scene(root); // conteneur du graphe
         theStage.setScene(theScene);

 		 gameMenu.setVisible(true);



         //INPUTS
         ArrayList<String> input = new ArrayList<>();

         //TOUCHE ENFONCEE
         theScene.setOnKeyPressed(e -> {
             String code = e.getCode().toString();
             if ( !input.contains(code) )
                 input.add( code );
         });

         //TOUCHE RELACHEE
         theScene.setOnKeyReleased(e -> {
             String code = e.getCode().toString();
             input.remove(code);
         });

         GraphicsContext gc = canvas.getGraphicsContext2D();
         Personnage personnage = new Personnage(largeur/2,hauteur/2,56);


         Image sol = new Image ("file:assets/Sol.png",largeur,hauteur,true,false);

         Salle salle = new Salle(16,16);

         last_time = 0;
         acc_time =0;
         new AnimationTimer() {
             public void handle(long currentNanoTime)
             {
                //A CORRIGER : Vitesse du perso, il faut
                 // game logic
                current_time = System.nanoTime();
                elapsed_time = (current_time-last_time)/1000000;
                last_time = current_time;
                acc_time += elapsed_time;

                if (acc_time >=10) {
                    //System.out.println(salle.getPosXSalle(personnage.getPosX()));
                    personnage.setDx(0);
                    personnage.setDy(0);

                    if (input.contains("LEFT")){
                        personnage.setDx(-4);
                     }
                    if (input.contains("RIGHT")){
                        personnage.setDx(4);
                        }
                    if (input.contains("UP")){
                        personnage.setDy(-4);
                        }
                    if (input.contains("DOWN")) {
                        personnage.setDy(4);

                    }
                    personnage.move();

                    salle.appCols(personnage);

                    acc_time = 0;
                }
                    //gc.clearRect(0,0,largeur,hauteur);
                    //gc.drawImage(sol,0,0);

                    salle.dessinerMap(gc);
                    personnage.render(gc);


         }}.start();
         theStage.show();
         }
  //Contenu du menu
  	public class GameMenu extends Parent{
  		public GameMenu() {
  			VBox menu=new VBox(15);

  	  //Boutons
  			//Bouton Jouer
  			MenuButton btnJouer=new MenuButton("Jouer");

  			//Quand on clique sur Jouer ca enleve le menu pour laisser le joueur jouer
  			btnJouer.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),gameMenu);
  				ft.setFromValue(1);
  				ft.setToValue(0);
  				ft.setOnFinished(evt->gameMenu.setVisible(false));
  				ft.play();
  				//gameMenu.setVisible(false);

  			});


  			//Bouton Quitter
  			MenuButton btnQuitter=new MenuButton("Quitter");
  			//Quand on clique sur Quitter ca quitte le jeu
  			btnQuitter.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),gameMenu);
  				ft.setFromValue(1);
  				ft.setToValue(0.5);
  				ft.setOnFinished(evt->System.exit(0));
  				ft.play();


  			});

  			//Bouton Credit
  			MenuButton btnCredit=new MenuButton("Credit");
  			//Quand on clique sur Credit on affiche les credits
  			btnCredit.setOnMouseClicked(event->{
  				FadeTransition ft=new FadeTransition(Duration.seconds(0.75),gameMenu);
  				ft.setFromValue(1);
  				ft.setToValue(0.5);
  				//ft.setOnFinished(evt->"a definir");
  				ft.play();


  			});

  			//Centrage des boutons
  			menu.setTranslateX(405);
  			menu.setTranslateY(430);


  			menu.getChildren().addAll(btnJouer,btnQuitter, btnCredit);


  			Rectangle arriere=new Rectangle(512,512);
  			arriere.setFill(Color.BLACK);
  			arriere.setTranslateX(256);
  			arriere.setTranslateY(256);

  			//
  			arriere.setOpacity(0.3);
  			getChildren().addAll(arriere,menu);

  		}


  	}


  //Boutons du menu sous forme de rectangle
  	private static class MenuButton extends StackPane{
  		private Text text;

  		public MenuButton(String name) {
  			text=new Text(name);

  			//Police, couleur, taille ect du texte
  			text.setFont(Font.font(20));
  			text.setFill(Color.WHITE);

  			Rectangle bouton=new Rectangle(200,30);

  			//Police, couleur, taille ect du bouton
  			bouton.setOpacity(0.7);
  			bouton.setFill(Color.BLACK);
  			setAlignment(Pos.CENTER);


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

  	MenuButton btnOption=new MenuButton("Quitter");
    public static void main(String[] args) {
        launch(args);
     }





     }

