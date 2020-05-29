import com.test.GameMenu;

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

//gere le cas où le joueur a perdu
public class GameOver extends Parent {
	private GameOver gameOver;

	
	
	private static VBox root;
	private  Scene mainScene;
	private Stage mainStage;
	

	
	public GameOver() {
		//Box contennat la fenetre gameOver
			root=new VBox(30);
			//Menu post defaite
			GameMenu gameMenu=new GameMenu();
			mainScene=new Scene(root,1024,1024);
			mainStage=new Stage();
			mainStage.setScene(mainScene);
			mainStage.setTitle("Spear");
			BackgroundImage BI=new BackgroundImage(new Image("file:assets/main-background.jpg"),BackgroundRepeat.REPEAT , BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			root.setBackground(new Background(BI));
			
			Text titre=new  Text("GAME OVER");
			titre.setFont(Font.loadFont("file:assets/masoneer.ttf", 90));
  			titre.setFill(Color.WHITE);
  			titre.setTranslateX(250);
  			titre.setTranslateY(450);
  			
  			
			root.getChildren().addAll(titre);
			FadeTransition ft=new FadeTransition(Duration.seconds(3),root);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.play();
				//Quand on perd la game over s'affiche puis la fenetre se ferme et un menu principale s'ouvre pour pouvoir rejouer
				ft.setOnFinished(evt->{ 
					mainStage.hide();
					mainStage=gameMenu.getMainStage();

					
				mainStage.show();
				
					
				});
	}
	
	
	
  			
  	



	public Stage getMainStage() {
		
		return mainStage;
	}
}
	