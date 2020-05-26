


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.shape.Rectangle;




public class Salle {
    protected int largeur; // largeur en UNITE
    protected int hauteur; // hauteur en UNITE
    protected int unite;
    Mur mur;
    Sol sol;
    Porte porte;
    
    protected int[][] quadrillage;
    private ArrayList<Projectile> projectiles;
    private ArrayList<EntiteVivante> ennemis;
    private ArrayList<Item> money;
    private ArrayList<Item> potions;
    private ArrayList<Item> cles;
    private ArrayList<Item> coffres;


    public Salle(int casesHauteur,int caseLargeur ) {
        hauteur = casesHauteur;
        largeur = caseLargeur;
        unite = Variables.Hauteur() / casesHauteur;
        quadrillage = new int[casesHauteur][caseLargeur];
       
        mur = new Mur(0,0,unite);
        sol = new Sol(0,0,unite);
        porte=new Porte(0,0,unite);
        projectiles = new ArrayList<>();
        ennemis = new ArrayList<>();
        money = new ArrayList<>();
        potions = new ArrayList<>();
        cles = new ArrayList<>();
        coffres = new ArrayList<>();
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
                    if (!(fleche.isMoving())) {
                        if (fleche.intersects(personnage)) {
                            personnage.addFleche(fleche);
                            i.remove();
                        }
                    }
                }
            }
            }
        }
    public void addArgent(Item i) {
    	money.add(i);
    }
    
    public void pickupMoney(Personnage personnage) {
    	Iterator<Item> i= money.iterator();
    	if (!(money.isEmpty())) {
    	 while(i.hasNext()) {
             Item p = i.next();
             if (p instanceof Argent) {
                 Argent argent = (Argent) p;   
                 if (argent.intersects(personnage)) {
                      personnage.addArgent(argent);
                      i.remove();
                     
                 }
             }
         }
    	}
         
     }
    
    public void addCoffre(Item i) {
    	coffres.add(i);
    }
    
    
    public void pickupCoffre(Personnage personnage, Cle cle) {
    	Iterator<Item> i= coffres.iterator();
    	 while(i.hasNext()) {
             Item p = i.next();
             if (p instanceof Coffre) {
                 Coffre coffre = (Coffre) p;   
                 if (coffre.intersects(personnage) & personnage.getNbCle()>=1) {  
					personnage.removeCle(cle);
                      i.remove();
                      Random r = new Random();
					  int n = r.nextInt(5);
					  n+=5;
					  for (int y=0; y<n; y++) {
						  Argent argent = new Argent(p.posX,p.posY,"file:assets/Argent.png",40);
  						  addArgent(argent);
					  }
                 }
             }
         }
     }
    
    public void addCle(Item i) {
    	cles.add(i);
    }
    
    public void pickupCle(Personnage personnage) {
    	Iterator<Item> i= cles.iterator();
    	if (!(cles.isEmpty())) {
    	 while(i.hasNext()) {
             Item p = i.next();
             if (p instanceof Cle) {
                 Cle cle = (Cle) p;   
                 if (cle.intersects(personnage)) {
                      personnage.addCle(cle);
                      i.remove();
                     
                 }
             }
         }
    	}
         
     }
    
    public void addPotion(Item i) {
    	potions.add(i);
    }
    
    public void pickupPotion(Personnage personnage) {
    	Iterator<Item> i= potions.iterator();
    	 while(i.hasNext()) {
             Item p = i.next();
             if (p instanceof Potion) {
                 Potion potion = (Potion) p;   
                 if (potion.intersects(personnage) & personnage.pV<3) {
                      personnage.pV++;
                      i.remove();
                     
                 }
             }
         }
     }
    
    public void addEnnemi(EntiteVivante e) {
    	ennemis.add(e);
    }
    
    
    public void ennemiesTakingDammage() {
    	if (!(projectiles.isEmpty())) {
    	Iterator<EntiteVivante> i;
    	for (Projectile p : projectiles){
    		i=ennemis.iterator();
    		if (!(ennemis.isEmpty())) {
    		while(i.hasNext()) {
    			EntiteVivante e = i.next();
    			if (p instanceof Fleche) {
    				Fleche fleche = (Fleche) p;
    				if (fleche.intersects(e)) {
    					e.losepV();
    					if (e.getpV()==0) {
    						i.remove();
    						Argent argent = new Argent(e.lastposX,e.lastposY,"file:assets/Argent.png",40);
    						addArgent(argent);
    						Random r = new Random();
    					    int n = r.nextInt(3);
    					    if (n==0) {
    					    	Potion potion = new Potion(e.lastposX,e.lastposY,"file:assets/Potion.png",30);
    					    	addPotion(potion);
    					    }
    					    Random a = new Random();
    					    int m = a.nextInt(5);
    					    if (m==0) {
    					    	Cle cle = new Cle(e.lastposX,e.lastposY,"file:assets/Cle.png",56);
    					    	addCle(cle);
    					    }
    					}
    				}
    
    			}
    		}
    		}
    		}
    	}
    }
    
    
    
    public void JoueurTakingDammage(Personnage personnage) {
			
    	Iterator<EntiteVivante> i;
    	i=ennemis.iterator();
		if (!(ennemis.isEmpty())) {
			while(i.hasNext()) {
				EntiteVivante e = i.next();
				if (personnage.intersects(e)) {
					personnage.losepV();
					//rebond de l'ennemi sur le joueur
					e.setDx(-15);
					e.setDy(-15);
					e.move();
					personnage.setDx(15);
					personnage.setDy(15);
					personnage.move();
					if (personnage.pV == 0) {
						GameOver gameOver=new GameOver();
						Stage window = new Stage();
						window = gameOver.getMainStage();
						window.show();
						
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
    
    public void renderEnnemis(GraphicsContext gc){
        if (!(ennemis.isEmpty())) {
            for (EntiteVivante e : ennemis) {
                e.render(gc);
            }
        }
    }
    public void renderArgent(GraphicsContext gc){
        if (!(money.isEmpty())) {
            for (Item i : money) {
                i.render(gc);
            }
        }
    }
    
    public void renderPotion(GraphicsContext gc){
        if (!(potions.isEmpty())) {
            for (Item i : potions) {
                i.render(gc);
            }
        }
    }
    
    public void renderCoffre(GraphicsContext gc){
        if (!(coffres.isEmpty())) {
            for (Item i : coffres) {
                i.render(gc);
            }
        }
    }
    
    public void renderCle(GraphicsContext gc){
        if (!(cles.isEmpty())) {
            for (Item i : cles) {
                i.render(gc);
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
        for (int i= 0; i <voisins[0].length ; i++) {
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
    public void dessinerMap(GraphicsContext gc){
        for (int i = 0; i <hauteur ; i++) {
            for (int j = 0; j <largeur ; j++) {

                if (quadrillage[j][i] == 1) {
                    gc.drawImage(mur.getImage(), j*unite, i * unite);
                }
                else if (quadrillage[j][i] == 2) {
                    gc.drawImage(porte.getImage(), j*unite, i * unite);
                }
         
                else if(quadrillage[j][i] == 0) {
                    gc.drawImage(sol.getImage(),j*unite,i*unite);
                }
            }
        }


    }
}