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
			root=new VBox(30);
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
			FadeTransition ft=new FadeTransition(Duration.seconds(5),root);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.play();
				ft.setOnFinished(evt->System.exit(0));
	}
	
	
	
  			
  	



	public Stage getMainStage() {
		
		return mainStage;
	}
}
	