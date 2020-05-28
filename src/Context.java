
import javafx.scene.Parent;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Context extends Parent{
	private static VBox root;
	private ContextButton btnExit;
	
	
	public Context() {
		//Box contenant les elements les uns a la suite des autre de maniere verticale
		root=new VBox(15);
	
		//Arriere noir
		Rectangle arriere2=new Rectangle(1024,1024);
		arriere2.setFill(Color.DARKGREY);
		arriere2.setOpacity(0.8);
		
		//Paragraphe de texte
		Text text=new  Text("Vous vous reveillez sur un sol froid et humide avec comme l'impression d'avoir deja vecu cela ... ");
		text.setFont(Font.loadFont("file:assets/masoneer.ttf", 23));
		text.setFill(Color.WHITE);
		text.setWrappingWidth(700);
		text.setTranslateX(162);
		text.setTranslateY(310);

		Text text2=new  Text("De nombreux dangers vous entourent, allez vous reussir a sortir en vie ? Heuresement, trois lances se trouvent a cote de vous. Faites en bon usage !");
		text2.setFont(Font.loadFont("file:assets/masoneer.ttf", 23));
		text2.setFill(Color.WHITE);
		text2.setWrappingWidth(700);
		text2.setTranslateX(162);
		text2.setTranslateY(325);
		
		
		//Fenetre de jeu foncé en transparence
		Rectangle arriere=new Rectangle(800,550);
		arriere.setFill(Color.BLACK);
		arriere.setTranslateX(100);
		arriere.setTranslateY(200);
		arriere.setArcHeight(35);
		arriere.setArcWidth(25);



		//Bouton pour lancer le jeu apres que l'utilisateur ai lu le contexte
	    btnExit=new ContextButton("Commencer");
		getBtnExit().setTranslateX(162);
		getBtnExit().setTranslateY(400);
		
		root.getChildren().addAll(text,text2,getBtnExit());
		getChildren().addAll(arriere2,arriere,root);
		
		
	}
	public ContextButton getBtnExit() {
		return btnExit;
	}
	static class ContextButton extends StackPane{
  		private Text text;
  		
  		public ContextButton(String name) {
  			text=new Text(name);
  			
  			//Police, couleur, taille ect du texte
  			text.setFont(Font.loadFont("file:assets/masoneer.ttf", 15));
  			text.setFill(Color.BLACK);
  			text.setTranslateY(1);
  		
  			
  			Rectangle bouton=new Rectangle(120,35);
  			
  			//Police, couleur, taille ect du bouton
  			bouton.setOpacity(1);
  			bouton.setArcHeight(25);
  			bouton.setArcWidth(25);
  			bouton.setFill(Color.WHITE);
  		
  			
  			
  			getChildren().addAll(bouton,text);
  		//Esthetique des boutons quand la souris passe dessus
  			setOnMouseEntered(event->{
  				bouton.setFill(Color.BLACK);
  				text.setFill(Color.WHITE);
  			});
  			setOnMouseExited(event->{
  				bouton.setFill(Color.WHITE);
  				text.setFill(Color.BLACK);
  			});
  			
  		}
	}




}
