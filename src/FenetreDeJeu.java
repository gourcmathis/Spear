import java.util.ArrayList;
import java.util.Random;

import com.test.GameOver;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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


        GraphicsContext gc = canvas.getGraphicsContext2D();
        Personnage personnage = new Personnage(largeur/2,hauteur/2,72);




        Image image = new Image("file:assets/crosshair.png");

        MapdeJeu m = new MapdeJeu(16,16,personnage);






		ath1=new Ath(personnage);
		ath1.setTranslateX(160);
		pauseMenu=new PauseMenu();
		Text text=new  Text("Press esc to pause");
		text.setFont(Font.loadFont("file:assets/masoneer.ttf", 15));
		text.setFill(Color.WHITE);
		text.setTranslateX(800);
		text.setTranslateY(990);
		pauseMenu.setVisible(false);
		context=new Context();
		pause=true;
		ath1.setVisible(false);
		text.setVisible(false);


		gameStage.setTitle("Roguelike_game_Jeu");
		gamePane.getChildren().addAll(canvas,ath1,pauseMenu,text,context);
		gameStage.setResizable(false);



        gameScene.setCursor(new ImageCursor(image,
                image.getWidth() / 2,
                image.getHeight() /2));

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


      
	        gameScene.setOnMouseClicked(
	                e -> {
	                    cursorX=e.getX();
	                    cursorY=e.getY();
	                    if(pause==false) {
	                    	personnage.shoot((int)cursorX,(int)cursorY,m.getSalleCourante());
	                    }

	                });


        last_time = 0;
        acc_time =0;
        


        
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

                    ath1.setfleche(personnage.getNbFleches());
                    ath1.setargent(personnage.getNbArgent());
                    ath1.setpv(personnage.getpV());
                    ath1.setcle(personnage.getNbCle());
                    //System.out.println(salle.getPosXSalle(personnage.getPosX()));
                    personnage.setDx(0);
                    personnage.setDy(0);



                    if (input.contains("Q")){
                        personnage.setDx(-4);
                    }
                    if (input.contains("D")){
                        personnage.setDx(4);
                    }
                    if (input.contains("Z")){
                        personnage.setDy(-4);
                   
                    }
                    if (input.contains("S")) {
                        personnage.setDy(4);

                    }

                    personnage.move();
					m.updateSalle();
					m.renderSalle(gc);

                    acc_time = 0;
                }




				m.changementSalle(m.getSalleCourante().getchange(),canvas);

				personnage.render(gc);




                ///////////////////PAUSE////////////////////////////////////:
                if (input.contains("ESCAPE")) {
                		pause=true;
      					stop();
      					pauseMenu.setVisible(true);
      					ath1.setVisible(false);
      					text.setVisible(false);

      			}
        
                
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
     					start();
     					ath1.setVisible(true);
     					context.setVisible(false);
     					pause=false;
     	
     					text.setVisible(true);

   			  });
   ////////////GAMEOVER///////////////////////////////////////////////
   				if (personnage.pV == 0) {
   					GameOver gameOver=new GameOver();
   					Stage window = new Stage();
   					stop();
   					window = gameOver.getMainStage();
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
