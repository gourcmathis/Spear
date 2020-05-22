
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FenetreDeJeu {
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Canvas canvas ;
	private Stage menuStage;
	private int largeur=Variables.Largeur();
	private int hauteur=Variables.Hauteur();
	private long elapsed_time;
	private long current_time;
	private long acc_time;
	private long last_time;
	
	
	
	public FenetreDeJeu() {
		initializeStage();


		
	}
	

	private void initializeStage() {
		gamePane=new AnchorPane();
		gameScene=new Scene(gamePane,1024,1024);
		gameStage=new Stage();
		gameStage.setScene(gameScene);
		canvas = new Canvas(1024,1024);
		gameStage.setTitle("Roguelike_game_Jeu");
		gamePane.getChildren().add(canvas);
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

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Personnage personnage = new Personnage(largeur/2,hauteur/2,56);
        Squelette squelette = new Squelette(500,100,4,"file:assets/Squelette.png",40);


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

                    salle.appCols(personnage);

                    acc_time = 0;
                }
                //gc.clearRect(0,0,largeur,hauteur);
                //gc.drawImage(sol,0,0);
                
                

                salle.dessinerMap(gc);
                personnage.render(gc);
                squelette.render(gc);


            }}.start();



	}
	
	public void createNewGame(Stage menuStage) {
		this.menuStage=menuStage;
		this.menuStage.hide();
		gameStage.show();
		
	}
	

}
