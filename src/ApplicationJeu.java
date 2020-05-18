import com.sun.jdi.LongValue;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ApplicationJeu extends Application {
    private final int hauteur = 1024;
    private final int largeur = 1024;
    public static void main(String[] args) {
        launch(args);
     }
     public void start (Stage theStage){
         theStage.setTitle("Roguelike_game");
         Group root = new Group(); // noeud racine
         Scene theScene = new Scene(root); // conteneur du graphe
         theStage.setScene(theScene);
         Canvas canvas = new Canvas(hauteur,largeur);
         root.getChildren().add(canvas);


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
         Personnage personnage = new Personnage(0,0,56);


         Image sol = new Image ("file:assets/Sol.png",largeur,hauteur,true,false);


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

                 gc.clearRect(0,0,largeur,hauteur);
                 gc.drawImage(sol,0,0);
                 personnage.render(gc);



         }}.start();


         theStage.show();
         }

     }

