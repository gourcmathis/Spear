import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;


public class MapdeJeu {
    private Salle salleCourante; // salle courante du joueur
    private ArrayList<Salle> listeSalles; // liste des salles
    private int indiceCourant; // indice de la salle courante
    private Personnage joueur; // joueur
    private double tmp; //temps


    public MapdeJeu(int hauteur,int largeur,Personnage personnage){
        listeSalles = new ArrayList<>(); //initialisation des salles
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
        indiceCourant=0; // on commence à la salle 1
        salleCourante=listeSalles.get(indiceCourant);
        joueur = personnage;
    }
    public void updateSalle(){
        /*Mise à jour des ennemis, projectiles des murs portes et collisions des entités*/
        salleCourante.updateProjectiles(); // déplacement des projectiles dans la salle
        salleCourante.appCols(joueur); // collision du joueur
        salleCourante.pickupArrow(joueur); // collision entre le joueur et les fleches
        salleCourante.pickupMoney(joueur); // collision entre le joueur et l'argent
        salleCourante.pickupPotion(joueur); // collision entre le joueur et les potions
        salleCourante.pickupCle(joueur); // collision entre le joueur et les clés
        salleCourante.pickupCoffre(joueur); // collision entre le joueur et les coffres
        salleCourante.ennemiesTakingDammage(); // collision entre les ennemis et les fleches
        salleCourante.updateEnnemis(joueur); // mise à jour de la position des ennemis

        //Lapse de temps entre deux perte de Pv
        if(tmp%50==0) {
            salleCourante.JoueurTakingDammage(joueur); // le joueur prend des dégats des ennemis s'il y a intersection
        }
    }


    public double getTmp() {
    	return this.tmp;
    }


    public void setTmp(double autreTmp) {
    	this.tmp=autreTmp;
    }


    public void renderSalle(GraphicsContext gc){
        salleCourante.dessinerMap(gc); // dessiner la salle
        salleCourante.renderProjectiles(gc); // dessiner les projectiles
        salleCourante.renderEnnemis(gc); // dessiner les ennemis
        salleCourante.renderArgent(gc); // dessiner l'argent
        salleCourante.renderPotion(gc); // dessiner les potions
        salleCourante.renderCle(gc); // dessiner les clés
        salleCourante.renderCoffre(gc); // dessiner les coffres
    }


    /*Cette fonction permet de changer de salle lorsque le joueur est au contact d'une porte*/
    public void changementSalle(int changement){
        // Permutation circulaire des salles

        if (changement ==1){ // si le joueur passe la porte de sortie
            indiceCourant=(indiceCourant+1)%5;// on passe à la salle suivante
            salleCourante=listeSalles.get(indiceCourant);
            teleport(salleCourante.getEntreex(),salleCourante.getEntreey()); // on déplace le joueur à la porte d'entrée de la salle suivante
        }

        else if (changement==-1){ // sinon s'il passe la porte d'entrée il revient à la salle précédente
            if (indiceCourant==0){
                indiceCourant=4; // cas particulier pour revenir à la salle précédente
            }
            else {
                indiceCourant--;
            }
            salleCourante=listeSalles.get(indiceCourant);
            teleport(salleCourante.getSortiex(),salleCourante.getSortiey()); // on déplace le joueur à la porte de sortie de la salle précédente
        }
    }


    public void teleport(int x, int y){
        joueur.teleport(x,y); // déplace le joueur à une position
    }


    public Salle getSalleCourante() {
        return salleCourante;
    }
}
