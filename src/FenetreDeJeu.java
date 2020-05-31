import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FenetreDeJeu {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Canvas canvas ;
	private Stage menuStage;
	private Ath ath1;
	private int largeur=Variables.Largeur();
	private int hauteur=Variables.Hauteur();
	private long elapsed_time;
	private long current_time;
	private long acc_time;
	private long last_time;
	private double cursorX=largeur/2;
	private double cursorY=hauteur/2;
	private boolean pause;
	private PauseMenu pauseMenu;
	private Context context;

	
	public FenetreDeJeu() {
		initializeStage();
	}
	

	private void initializeStage() {
		gamePane=new AnchorPane();
		gameScene=new Scene(gamePane,1024,1024);
		gameStage=new Stage();
		gameStage.setScene(gameScene);
		canvas = new Canvas(1024,1024);
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Contexte graphique permettant de dessiner le jeu


		/*Initialisation des objets et salles*/
		Personnage personnage = new Personnage(largeur/2,hauteur/2,72); // initialisation du personnage
        MapdeJeu m = new MapdeJeu(16,16,personnage); // initialisation de toutes les salles du donjon


		/*Curseur en jeu*/
		Image image = new Image("file:assets/crosshair.png"); //Image du curseur
		gameScene.setCursor(new ImageCursor(image,
				image.getWidth() / 2,
				image.getHeight() /2));



        //Affichage ecran
		ath1=new Ath(personnage);
		ath1.setTranslateX(180);
		pauseMenu=new PauseMenu();
		Text text=new  Text("Press esc to pause");
		text.setFont(Font.loadFont("file:assets/masoneer.ttf", 15));
		text.setFill(Color.WHITE);
		text.setTranslateX(800);
		text.setTranslateY(1000);
		Text titre=new  Text("SPEAR");
		titre.setFont(Font.loadFont("file:assets/masoneer.ttf", 60));
		titre.setFill(Color.WHITE);
		titre.setTranslateX(385);
		titre.setTranslateY(1020);
		titre.setOpacity(0.5);
		pauseMenu.setVisible(false);
		context=new Context();
		pause=true;
		ath1.setVisible(false);
		text.setVisible(false);

		/*Ath bas de l'écran*/
		gameStage.setTitle("Spear");
		gamePane.getChildren().addAll(canvas,ath1,pauseMenu,text,titre,context);
		gameStage.setResizable(false);





		//INPUTS
        ArrayList<String> input = new ArrayList<>();
			//TOUCHE ENFONCEE
			gameScene.setOnKeyPressed(e -> {
				String code = e.getCode().toString();
				if ( !input.contains(code) )
					input.add( code );
			});

			//TOUCHE RELACHEE
			gameScene.setOnKeyReleased(e -> {
				String code = e.getCode().toString();
				input.remove(code);
			});


		  	/*Clic de la souris*/
			gameScene.setOnMouseClicked(
					e -> {
						cursorX=e.getX();
						cursorY=e.getY();
						if(pause==false) {
							personnage.shoot((int)cursorX,(int)cursorY,m.getSalleCourante()); // permet de tirer des fleches

						}
					});

		/*Initialisation des variables de temps*/
        last_time = 0;
        acc_time =0;
        

        /*Boucle de jeu*/
        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
            	if(context.isVisible()==true) {
            		stop();
            	}
                //A CORRIGER : Vitesse du perso, il faut
                // game logic
                current_time = System.nanoTime();
                elapsed_time = (current_time-last_time)/1000000;
                last_time = current_time;
                acc_time += elapsed_time;


                if (acc_time >=10) {
                //Perte de pv entre un lapse de temps specifique
              	  m.setTmp(m.getTmp()+1);

              	  	/*Liaison de l'ath aux informations du joueur*/
                    ath1.setfleche(personnage.getNbFleches());
                    ath1.setargent(personnage.getNbArgent());
                    ath1.setpv(personnage.getpV());
                    ath1.setcle(personnage.getNbCle());

                    /*réinitialisation de la vitesse du joueur*/
                    personnage.setDx(0);
                    personnage.setDy(0);



                    if (input.contains("Q")){
                        personnage.setDx(-4);//déplacement gauche
                    }
                    if (input.contains("D")){
                        personnage.setDx(4); // déplacement droit
                    }
                    if (input.contains("Z")){
                        personnage.setDy(-4); //déplacement haut
                   
                    }
                    if (input.contains("S")) { //déplacement bas
                        personnage.setDy(4);

                    }

                    personnage.move(); // déplacement du personnage
					m.updateSalle(); // mise à jour et calcul des évènements des salles
					m.renderSalle(gc); // affichage de la salle
                    acc_time = 0; // réinitialisation du compteur de temps
                }



				m.changementSalle(m.getSalleCourante().getDirectionSortie()); // changement de salle si le joueur passe une porte de la salle

				personnage.render(gc); // dessin de l'image du joueur




                ///////////////////PAUSE////////////////////////////////////:
                if (input.contains("ESCAPE")) {
                		pause=true;
      					stop();
      					pauseMenu.setVisible(true);
      					ath1.setVisible(false);
      					text.setVisible(false);

      			}
        
                /*Menu pause*/
               pauseMenu.getBtnJouer().setOnMouseClicked(event->{
      				FadeTransition ft=new FadeTransition(Duration.seconds(0.4),gamePane);
      				ft.setFromValue(1);
      				ft.setToValue(1);
      				ft.setOnFinished(evt->{
      					start();
      					pauseMenu.setVisible(false);
      					ath1.setVisible(true);
      					pause=false;
    					text.setVisible(true);
      				});
      				ft.play();
    			});


               context.getBtnExit().setOnMouseClicked(event->{
            	   FadeTransition ft=new FadeTransition(Duration.seconds(0.4),gamePane);
     				ft.setFromValue(1);
     				ft.setToValue(1);
     				ft.setOnFinished(evt->{
     					start();
     					pause=false;
     					ath1.setVisible(true);
     					context.setVisible(false);
     					text.setVisible(true);
     					
     				});
      				ft.play();
   			  });

   ////////////GAMEOVER///////////////////////////////////////////////

				if (personnage.getpV() == 0) {
   					GameOver gameOver=new GameOver();
   					Stage window = new Stage();
   					stop();
   					window = gameOver.getMainStage();
   					gameStage.hide();
   					window.show();
   				

   				}

////////////VICTOIRE///////////////////////////////////////////////

				if (personnage.getNbArgent()>=50) {
					Victoire victoire = new Victoire();
					Stage window = new Stage();
					stop();
					window = victoire.getMainStage();
					gameStage.hide();
					window.show();
				}





            }}.start();


	}

	
	public void createNewGame(Stage menuStage) {
		this.menuStage=menuStage;
		this.menuStage.hide();
		gameStage.show();
		
	}
}
