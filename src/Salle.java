import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Iterator;

public class Salle {
    private int largeur; // largeur en UNITE
    private int hauteur; // hauteur en UNITE
    private int unite;
    Mur mur;
    Sol sol;
    private int nbEnnemis;
    private int nbCoffres;
    private int[][] quadrillage;
    private ArrayList<Projectile> projectiles;

    public Salle(int casesHauteur,int caseLargeur ) {
        hauteur = casesHauteur;
        largeur = caseLargeur;
        unite = Variables.Hauteur() / casesHauteur;
        quadrillage = new int[casesHauteur][caseLargeur];
        creationMatrice();
        mur = new Mur(0,0,unite);
        sol = new Sol(0,0,unite);
        projectiles = new ArrayList<>();
    }

    public void addProjectile(Projectile p){
        projectiles.add(p);
    }

    public void removeProjectile(Projectile p){
        projectiles.remove(p);
    }

    public void pickupArrow(Personnage personnage){
        Iterator<Projectile> i= projectiles.iterator();
        if (!(projectiles.isEmpty())){
            while(i.hasNext()) {
                Projectile p = i.next();
                if (p instanceof Fleche) {
                    Fleche fleche = (Fleche) p;
                    if (fleche.isMoving()) {
                        if (fleche.intersects(personnage)) {
                            personnage.addFleche(fleche);
                            i.remove();
                        }
                    }
                }
            }
            }
        }


    public void updateProjectiles(){
        if (!(projectiles.isEmpty())){
        for (Projectile p:projectiles) {
            updateProjectile(p);
        }
        }
    }

    public void renderProjectiles(GraphicsContext gc){
        if (!(projectiles.isEmpty())) {
            for (Projectile p : projectiles) {
                p.render(gc);
            }
        }
    }

    public void updateProjectile(Projectile p){
        p.moveToTarget();
        p.move();
        appCols(p);
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

        

        if (x==0 && y==0){
            voisinsEntite = new int[2][3];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;
            voisinsEntite[0][2]=0;
            voisinsEntite[1][2]=1;

            voisinsEntite[0][3]=1;
            voisinsEntite[1][3]=0;
        }
        else if (x == 0 && y == hauteur - 1) {
            voisinsEntite = new int[2][3];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;

            voisinsEntite[0][1]=1;
            voisinsEntite[1][1]=y;

            voisinsEntite[0][2]=0;
            voisinsEntite[1][2]=y-1;
        }
        else if (x == largeur-1 && y == 0) {
            voisinsEntite = new int[2][3];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;

            voisinsEntite[0][1]=x-1;
            voisinsEntite[1][1]=0;

            voisinsEntite[0][2]=x;
            voisinsEntite[1][2]=1;
        }
        else if (x == largeur-1 && y == hauteur-1) {
            voisinsEntite = new int[2][3];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;
            voisinsEntite[0][1]=x-1;
            voisinsEntite[1][1]=y;

            voisinsEntite[0][2]=x+1;
            voisinsEntite[1][2]=y;


        }
        else if (x==0 && y>0){
            voisinsEntite = new int[2][4];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;
            voisinsEntite[0][1]=0;
            voisinsEntite[1][1]=0;

            voisinsEntite[0][2]=0;
            voisinsEntite[1][2]=0;

            voisinsEntite[0][3]=0;
            voisinsEntite[1][3]=0;

        }
        else if (x==largeur-1 && y>0){
            voisinsEntite = new int[2][4];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;
            voisinsEntite[0][1]=0;
            voisinsEntite[1][1]=0;

            voisinsEntite[0][2]=0;
            voisinsEntite[1][2]=0;

            voisinsEntite[0][3]=0;
            voisinsEntite[1][3]=0;


        }
        else if (y==hauteur-1 && x>0){
            voisinsEntite = new int[2][4];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;
            voisinsEntite[0][1]=0;
            voisinsEntite[1][1]=0;

            voisinsEntite[0][2]=0;
            voisinsEntite[1][2]=0;

            voisinsEntite[0][3]=0;
            voisinsEntite[1][3]=0;


        }
        else if (y==0 && x>0){
            voisinsEntite = new int[2][4];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;
            voisinsEntite[0][1]=0;
            voisinsEntite[1][1]=0;

            voisinsEntite[0][2]=0;
            voisinsEntite[1][2]=0;

            voisinsEntite[0][3]=0;
            voisinsEntite[1][3]=0;


        }

        else if (x<0 || y<0){
            voisinsEntite = new int[2][1];
            voisinsEntite[0][0]=0;
            voisinsEntite[1][0]=0;


        }
        else {
            voisinsEntite = new int[2][9];
            voisinsEntite[0][0]=x;
            voisinsEntite[1][0]=y;
            voisinsEntite[0][1] = x - 1;
            voisinsEntite[1][1] = y;

            voisinsEntite[0][2] = x + 1;
            voisinsEntite[1][2] = y;

            voisinsEntite[0][3] = x;
            voisinsEntite[1][3] = y - 1;

            voisinsEntite[0][4] = x;
            voisinsEntite[1][4] = y + 1;

            voisinsEntite[0][5] = x + 1;
            voisinsEntite[1][5] = y + 1;

            voisinsEntite[0][6] = x - 1;
            voisinsEntite[1][6] = y - 1;

            voisinsEntite[0][7] = x + 1;
            voisinsEntite[1][7] = y - 1;

            voisinsEntite[0][8] = x - 1;
            voisinsEntite[1][8] = y + 1;
        }


        
        return(voisinsEntite);
    }
    
    public void appCols(EntiteDynamique ed){
        int[][] voisins = voisinDe(ed);

        if (voisins[0].length ==9){
        for (int i = 0; i <voisins[0].length ; i++) {
            if (quadrillage[voisins[0][i]][voisins[1][i]] == 1) {
                if (checkcollisionCase(ed, voisins[0][i], voisins[1][i])) {
                    ed.resetpos();
                }
            }
        }
        }
        else{
            ed.resetpos();
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
