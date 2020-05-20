




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
        Personnage personnage = new Personnage(0,0,56);


        Image sol = new Image ("file:assets/Sol.png",1024,1024, true,false);


        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
               //A CORRIGER : Vitesse du perso, il faut
                // game logic

               personnage.setDx(0);
               personnage.setDy(0);
                if (input.contains("LEFT"))
                    personnage.setDx(-1);
                if (input.contains("RIGHT"))
                    personnage.setDx(1);
                if (input.contains("UP"))
                    personnage.setDy(-1);
                if (input.contains("DOWN"))
                    personnage.setDy(1);

                personnage.move();

                gc.clearRect(0,0,1024,1024);
                gc.drawImage(sol,0,0);
                personnage.render(gc);



        }}.start();
	}
	
	public void createNewGame(Stage menuStage) {
		this.menuStage=menuStage;
		this.menuStage.hide();
		gameStage.show();
		
	}
	

}
