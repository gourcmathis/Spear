import javafx.scene.canvas.GraphicsContext;

public class Salle {
    private int largeur; // largeur en UNITE
    private int hauteur; // hauteur en UNITE
    private int unite;
    private int nbEnnemis;
    private int nbCoffres;
    private int[][] quadrillage;
    public Salle(int casesHauteur,int caseLargeur ) {
        hauteur = casesHauteur;
        largeur = caseLargeur;
        unite = Variables.Hauteur() / casesHauteur;
        quadrillage = new int[casesHauteur][caseLargeur];
        creationMatrice();

    }

    private void creationMatrice(){
        for (int i = 0; i <hauteur ; i++) {
            for (int j = 0; j <largeur ; j++) {
                if (i == 0 || j ==0|| i==hauteur-1||j==largeur-1) {
                    quadrillage[i][j]=1;
                }
                else{
                    quadrillage[i][j] = 0;
                }
            }
        }
    }


    public void dessinerMap(GraphicsContext gc){
        Mur mur = new Mur(0,0,unite);
        Sol sol = new Sol(0,0,unite);

        for (int i = 0; i <hauteur ; i++) {
            for (int j = 0; j <largeur ; j++) {

                if (quadrillage[i][j] == 1) {
                    gc.drawImage(mur.getImage(), j*unite, i * unite);
                }

                else {
                    gc.drawImage(sol.getImage(),j*unite,i*unite);
                }
            }
        }


    }
}
