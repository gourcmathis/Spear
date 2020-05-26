import javafx.animation.FadeTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class MapdeJeu {
    private Salle salleCourante;
    private ArrayList<Salle> listeSalles;
    private int indiceCourant;
    private Personnage joueur;

    public MapdeJeu(int hauteur,int largeur,Personnage personnage){
        listeSalles = new ArrayList<>();
        Salle1 salle1 = new Salle1(hauteur,largeur);
        Salle2 salle2 = new Salle2(hauteur,largeur);
        Salle3 salle3 = new Salle3(hauteur,largeur);
        Salle4 salle4 = new Salle4(hauteur,largeur);
        Salle5 salle5 = new Salle5(hauteur,largeur);
        listeSalles.add(salle1);
        listeSalles.add(salle2);
        listeSalles.add(salle3);
        listeSalles.add(salle4);
        listeSalles.add(salle5);
        indiceCourant=0;
        salleCourante=listeSalles.get(indiceCourant);
        joueur = personnage;
    }
    public void updateSalle(){
        salleCourante.updateProjectiles();
        salleCourante.appCols(joueur);
        salleCourante.pickupArrow(joueur);
        salleCourante.pickupMoney(joueur);
        salleCourante.pickupPotion(joueur);
        salleCourante.pickupCle(joueur);
        salleCourante.pickupCoffre(joueur);
        salleCourante.ennemiesTakingDammage();
        salleCourante.updateEnnemis(joueur);

        Random r = new Random();
        int n = r.nextInt(5);
        if(n==0) {
            salleCourante.JoueurTakingDammage(joueur);
        }
        salleCourante.JoueurTakingDammage(joueur);
    }
    public void renderSalle(GraphicsContext gc){
        salleCourante.dessinerMap(gc);
        salleCourante.renderProjectiles(gc);
        salleCourante.renderEnnemis(gc);
        salleCourante.renderArgent(gc);
        salleCourante.renderPotion(gc);
        salleCourante.renderCle(gc);
        salleCourante.renderCoffre(gc);
    }
    public void changementSalle(boolean changement, Canvas canvas){
        if (changement){
            indiceCourant=(indiceCourant+1)%5;
            salleCourante=listeSalles.get(indiceCourant);
            teleport(salleCourante.getPosReelX(1),salleCourante.getPosReelY(1));
        }
    }



    public void teleport(int x, int y){
        joueur.teleport(x,y);
    }

    public Salle getSalleCourante() {
        return salleCourante;
    }
}
