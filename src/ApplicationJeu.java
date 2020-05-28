import javafx.application.Application;
import javafx.stage.Stage;
public class ApplicationJeu extends Application{

	@Override
	public void start(Stage theStage) {
       
		GameMenu gameMenu=new GameMenu();
		theStage.setTitle("Roguelike_game");

        theStage=gameMenu.getMainStage();
        theStage.setResizable(false);
        
       
		theStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}


}
