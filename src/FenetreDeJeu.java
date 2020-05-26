
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
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
import javafx.stage.Stage;


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

        Salle1 salle = new Salle1(16,16);
        salle.creergrid();
        salle.gridprint();

		ath1=new Ath(personnage);
		ath1.setTranslateX(160);
		gameStage.setTitle("Roguelike_game_Jeu");
		gamePane.getChildren().addAll(canvas,ath1);
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
                    personnage.shoot((int)cursorX,(int)cursorY,m.getSalleCourante());

                });
        




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


                    //fleche.moveTo((int)cursorX,(int)cursorY);
                    //fleche.move();
                    /*salle.updateProjectiles();
                    salle.appCols(personnage);
                    //salle.appCols(squelette);
                    salle.pickupArrow(personnage);
                    salle.pickupMoney(personnage);
                    salle.pickupPotion(personnage);
                    salle.pickupCle(personnage);
                    salle.pickupCoffre(personnage);
                    salle.ennemiesTakingDammage();
                    //salle.appCols(fleche);
                    salle.updateEnnemis(personnage);
                    */
                    m.updateSalle();

                    m.changementSalle(m.getSalleCourante().getchange(),canvas);

                    acc_time = 0;
                }




                
                m.renderSalle(gc);
                /*salle.dessinerMap(gc);
                salle.renderProjectiles(gc);
                    */
                personnage.render(gc);
                /*
                salle.renderEnnemis(gc);
                salle.renderArgent(gc);
                salle.renderPotion(gc);
                salle.renderCle(gc);
                salle.renderCoffre(gc);
                */




            }}.start();



	}
	
	public void createNewGame(Stage menuStage) {
		this.menuStage=menuStage;
		this.menuStage.hide();
		gameStage.show();
		
	}
	

}
