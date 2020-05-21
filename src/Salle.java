import javafx.scene.canvas.GraphicsContext;

public class Salle {
    private int largeur; // largeur en UNITE
    private int hauteur; // hauteur en UNITE
    private int unite;
    Mur mur;
    Sol sol;
    private int nbEnnemis;
    private int nbCoffres;
    private int[][] quadrillage;
    public Salle(int casesHauteur,int caseLargeur ) {

        hauteur = casesHauteur;
        largeur = caseLargeur;
        unite = Variables.Hauteur() / casesHauteur;
        quadrillage = new int[casesHauteur][caseLargeur];
        creationMatrice();
        mur = new Mur(0,0,unite);
        sol = new Sol(0,0,unite);

    }
    public int getPosXSalle(int x){
        return (int) Math.floor(x/unite);
    }

    public int getPosYSalle(int y){

        return((int) Math.floor(y/unite));
    }

    public int getPosReelX(int x){
        return(x*unite);
    }


    public int getPosReelY(int y){
        return(y*unite);
    }

    public int[][] voisinDe(EntiteDynamique ed){
        int[][] voisinsEntite;
        int x = getPosXSalle(ed.getPosX());
        int y = getPosYSalle(ed.getPosY());

        
        voisinsEntite = new int[2][9];
        
        voisinsEntite[0][0]=x;
        voisinsEntite[1][0]=y;
        
        voisinsEntite[0][1]=x-1;
        voisinsEntite[1][1]=y;
        
        voisinsEntite[0][2]=x+1;
        voisinsEntite[1][2]=y;
        
        voisinsEntite[0][3]=x;
        voisinsEntite[1][3]=y-1;
        
        voisinsEntite[0][4]=x;
        voisinsEntite[1][4]=y+1;
       
        voisinsEntite[0][5]=x+1;
        voisinsEntite[1][5]=y+1;
       
        voisinsEntite[0][6]=x-1;
        voisinsEntite[1][6]=y-1;
       
        voisinsEntite[0][7]=x+1;
        voisinsEntite[1][7]=y-1;
       
        voisinsEntite[0][8]=x-1;
        voisinsEntite[1][8]=y+1;
        
        return(voisinsEntite);
    }
    
    public void appCols(EntiteDynamique ed){
        int[][] voisins = voisinDe(ed);
        for (int i = 0; i <voisins[0].length ; i++) {
                if (quadrillage[voisins[0][i]][voisins[1][i]]==1){
                    if (checkcollisionCase(ed,voisins[0][i],voisins[1][i])){
                        ed.resetpos();
                    }
            }
        }
    }
    
    // 0<i<hauteur et 0<j<largeur
    public boolean checkcollisionCase(EntiteDynamique ed,int i, int j){
        mur.setpos(getPosReelY(i),getPosReelX(j));
        return(mur.intersects(ed));
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
        quadrillage[4][4]=1;
    }


    public void dessinerMap(GraphicsContext gc){
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
