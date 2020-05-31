import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public abstract class Salle {
    protected int largeur; // largeur en UNITE
    protected int hauteur; // hauteur en UNITE
    protected int unite;
    Mur mur;
    Sol sol;
    private int directionSortie=0;
    private Porte porte; // porte d'entree
    private Porte porte2; // porte de sortie
    private Porte[] portes;
    protected int[][] quadrillage;
    protected ArrayList<Projectile> projectiles;
    protected ArrayList<EntiteVivante> ennemis;
    protected List<AStarCell> totalPath = new ArrayList<>(200); // Algo A* non utilisé
    protected AStarGrid<Integer> pathfindinggrid; // Algo A* non utilisé
    protected ArrayList<Item> money;
    protected ArrayList<Item> potions;
    protected ArrayList<Item> cles;
    protected ArrayList<Item> coffres;
    private int entreex; // abscisse de l'entree de la salle
    private int entreey; // ordonnée de l'entree de la salle
    private int sortiex;// abscisse de la sortie de la salle
    private int sortiey; // ordonnée de la sortie de la salle


    public Salle(int casesHauteur,int caseLargeur ,int entreex,int entreey, int sortiex, int sortiey) {
        hauteur = casesHauteur;
        largeur = caseLargeur;
        unite = Variables.Hauteur() / casesHauteur;
        quadrillage = new int[casesHauteur][caseLargeur];
       
        mur = new Mur(0,0,unite);
        sol = new Sol(0,0,unite);
        porte=new Porte(0,0,unite);
        porte2=new Porte(0,0,unite);

        portes =new Porte[2];
        portes[0]=porte;
        portes[1]=porte2;

        projectiles = new ArrayList<>();
        ennemis = new ArrayList<>();
        money = new ArrayList<>();
        potions = new ArrayList<>();
        cles = new ArrayList<>();
        coffres = new ArrayList<>();
        this.entreex=entreex;
        this.entreey=entreey;
        this.sortiex=sortiex;
        this.sortiey=sortiey;
        }

    public int getEntreey() {
        return entreey;
    }

    public int getEntreex() {
        return entreex;
    }

    public int getSortiex() {
        return sortiex;
    }

    public int getSortiey() {
        return sortiey;
    }


    //ajoute un projectile
    public void addProjectile(Projectile p){
        projectiles.add(p);
    }

    
    //ramasse une fleche
    public void pickupArrow(Personnage personnage){
        Iterator<Projectile> i= projectiles.iterator();
        //si il reste des projectiles dans la salle
        if (!(projectiles.isEmpty())){
        	//tant qu'il en reste un
            while(i.hasNext()) {
            	//on passe au prochain
                Projectile p = i.next();
                //si p est une fleche
                if (p instanceof Fleche) {
                    Fleche fleche = (Fleche) p;
                    //si la fleche est immobile
                    if (!(fleche.isMoving())) {
                    	//si la fleche est en contact avec les personnage
                        if (fleche.intersects(personnage)) {
                        	//ajoute une fleche au personnage
                            personnage.addFleche(fleche);
                            //retire la fleche de la salle
                            i.remove();
                            //fait un bruit
                           /* String filepath = "assets/pickupFleche.wav";
               			    Audio musicObject = new Audio();
               			    musicObject.playArrow(filepath);*/
                        }
                    }
                }
            }
            }
        }
    
    //ajouter une piece d'argent
    public void addArgent(Item i) {
    	money.add(i);
    }
    
    //ramasser une piece
    public void pickupMoney(Personnage personnage) {
    	Iterator<Item> i= money.iterator();
    	//s'il reste des pieces dans la salle
    	if (!(money.isEmpty())) {
    	//tant qu'il y a une autre piece
    	 while(i.hasNext()) {
    		 //on passe au prochain
             Item p = i.next();
             //si p est une piece
             if (p instanceof Argent) {
                 Argent argent = (Argent) p;
                 //si le personnage est en contact avec la piece
                 if (argent.intersects(personnage)) {
                	 //on ajoute une piece au personnage
                      personnage.addArgent(argent);
                      //on fait un bruit
                     /* String filepath = "assets/argent.wav";
          			  Audio musicObject = new Audio();
          			  musicObject.playSong(filepath);*/
          			  //on retire la piece de la salle
                      i.remove();
                     
                 }
             }
         }
    	}
         
     }
    
    //ajoute un coffre
    public void addCoffre(Item i) {
    	coffres.add(i);
    }
    
    //ouvrir un coffre
    public void pickupCoffre(Personnage personnage) {
    	Iterator<Item> i= coffres.iterator();
    	//tant qu'il en reste un
    	 while(i.hasNext()) {
    		 //on passe au prochain
             Item p = i.next();
             //si p est un coffre
             if (p instanceof Coffre) {
                 Coffre coffre = (Coffre) p;  
                 //si le personnage est sur un coffre et qu'il a au moins une cle
                 if (coffre.intersects(personnage) & personnage.getNbCle()>=1) {  
                	//on retire la cle au personnage
					personnage.removeCle();
					//on fait un bruit
					/*String filepath = "assets/coffre.wav";
					Audio musicObject = new Audio();
					musicObject.playSong(filepath);*/
					//on retire le coffre de la salle
                      i.remove();
                      //on prend un nombre au hasard entre 5 et 10
                      Random r = new Random();
					  int n = r.nextInt(5);
					  n+=5;
					  //on fait apparaitre autant de pieces que le nombre tire
					  for (int y=0; y<n; y++) {
						  Argent argent = new Argent(p.posX,p.posY,"file:assets/Argent.png",40);
  						  addArgent(argent);
					  }
                 }
             }
         }
     }
    
    //ajoute une cle
    public void addCle(Item i) {
    	cles.add(i);
    }
    
    //ramasse un cle
    public void pickupCle(Personnage personnage) {
    	Iterator<Item> i= cles.iterator();
    	//s'il il y a encore des cles dans la salle
    	if (!(cles.isEmpty())) {
    	//tant qu'il y a encore une cle
    	 while(i.hasNext()) {
    		 //on passe a la suivante
             Item p = i.next();
             //si p est une cle
             if (p instanceof Cle) {
                 Cle cle = (Cle) p;  
                 //si le personnage passe dessus
                 if (cle.intersects(personnage)) {
                	 //on fait un bruit
                	 /* String filepath = "assets/cle.wav";
         			  Audio musicObject = new Audio();
         			  musicObject.playSong(filepath);*/
         			  //on ajoute une cle au personnage
                      personnage.addCle(cle);
                      //on la retire de la salle
                      i.remove();
                     
                 }
             }
         }
    	}
         
     }
    //ajoute une potion
    public void addPotion(Item i) {
    	potions.add(i);
    }
    
    //ramasse une potion
    public void pickupPotion(Personnage personnage) {
    	Iterator<Item> i= potions.iterator();
    	//tant qu'il y a encore une potion
    	 while(i.hasNext()) {
    		 //on passe a la prochaine
             Item p = i.next();
             //si p est une potion
             if (p instanceof Potion) {
                 Potion potion = (Potion) p;  
                 //si le personnage est sur une potion et qu'il a moins de 5 pv
                 if (potion.intersects(personnage) & personnage.getpV()<5) {
                	 //on fait un bruit
                	/* String filepath = "assets/potion.wav";
         			 Audio musicObject = new Audio();
         			 musicObject.playSong(filepath);*/
         			 //on ajoute un pv au pedrsonnage
                      personnage.gainpV();
                      //on retire la potion de la salle
                      i.remove();
                     
                 }
             }
         }
     }
    
    //ajoute un ennemi
    public void addEnnemi(EntiteVivante e) {
    	ennemis.add(e);
    }
    
    //un ennemi prend des degats
    public void ennemiesTakingDammage() {
    	//si il reste des projectiles
    	if (!(projectiles.isEmpty())) {
    	Iterator<EntiteVivante> i;
    	//pour chaque projectile
    	for (Projectile p : projectiles){
    		//s'il est en mouvement
    		if (p.isMoving()){
    	    i=ennemis.iterator();
    	    //s'il reste des ennemis
    		if (!(ennemis.isEmpty())) {
    		//tant qu'il y en a un autre
    		while(i.hasNext()) {
    			//on passe au prochain
    			EntiteVivante e = i.next();
    			//si p est une fleche
    			if (p instanceof Fleche) {
                    Fleche fleche = (Fleche) p;
                    //si la fleche touche un ennemi
                    if (fleche.intersects(e)) {
                    	//l'ennemi perd un pv (pour le moment les ennemis meurent en une fois)
                        e.losepV();
                        //si ses pv tombent à 0
                        if (e.getpV() == 0) {
                        	//on le retire de la salle
                            i.remove();
                            //on fait un bruit 
                           /* String filepath = "assets/ennemiMort.wav";
                			Audio musicObject = new Audio();
                			musicObject.playSong(filepath);*/
                			//on fait apparaitre une piece
                            Argent argent = new Argent(e.lastposX, e.lastposY, "file:assets/Argent.png", 40);
                            addArgent(argent);
                            //on tire un chiffre entre 0 et 3
                            Random r = new Random();
                            int n = r.nextInt(3);
                            //si ce nombre tombe sur 0, on fait apparaitre une potion
                            if (n == 0) {
                                Potion potion = new Potion(e.lastposX, e.lastposY, "file:assets/Potion.png", 30);
                                addPotion(potion);
                            }
                            //on retire un nombre, entre 0 et 5
                            Random a = new Random();
                            int m = a.nextInt(5);
                            //si on tombe sur 0, une cle apparait
                            if (m == 0) {
                                Cle cle = new Cle(e.lastposX, e.lastposY, "file:assets/Cle.png", 56);
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
    }


    //joueur prend des degats
    public void JoueurTakingDammage(Personnage personnage) {

    	Iterator<EntiteVivante> i;
    	i=ennemis.iterator();
    	//si il reste des ennemis
		if (!(ennemis.isEmpty())) {
			//tant qu'il en reste un
			while(i.hasNext()) {
				//on passe au prochain
				EntiteVivante e = i.next();
				//si le personnage touche un ennemi
				if (personnage.intersects(e)) {
					//le personnage perd un pv
					personnage.losepV();
					//on fait un bruit
				/*	String filepath = "assets/degatJoueur.wav";
					Audio musicObject = new Audio();
					musicObject.playSong(filepath);*/
					//rebond de l'ennemi sur le joueur
					e.setDx(-5);
					e.setDy(-5);
					e.move();
					appCols(e);
					personnage.setDx(8);
					personnage.setDy(8);
					personnage.move();
					appCols(personnage);
			


				}
			}
		}




}


    // si il reste des projectiles, les met à jour 
    public void updateProjectiles(){
        if (!(projectiles.isEmpty())){
        	for (Projectile p:projectiles) {
        		updateProjectile(p);
        	}
        }
    }
    
    // s'il en reste, affiche les projectiles
    public void renderProjectiles(GraphicsContext gc){
        if (!(projectiles.isEmpty())) {
            for (Projectile p : projectiles) {
                p.render(gc);
            }
        }
    }
    
 // s'il en reste, affiche les ennemis
    public void renderEnnemis(GraphicsContext gc){
        if (!(ennemis.isEmpty())) {
            for (EntiteVivante e : ennemis) {
                e.render(gc);
            }
        }
    }
    
 // s'il en reste, affiche les pieces
    public void renderArgent(GraphicsContext gc){
        if (!(money.isEmpty())) {
            for (Item i : money) {
                i.render(gc);
            }
        }
    }
    
 // s'il en reste, affiche les potions
    public void renderPotion(GraphicsContext gc){
        if (!(potions.isEmpty())) {
            for (Item i : potions) {
                i.render(gc);
            }
        }
    }
    
 // s'il en reste, affiche les coffres
    public void renderCoffre(GraphicsContext gc){
        if (!(coffres.isEmpty())) {
            for (Item i : coffres) {
                i.render(gc);
            }
        }
    }
    
 // s'il en reste, affiche les cles
    public void renderCle(GraphicsContext gc){
        if (!(cles.isEmpty())) {
            for (Item i : cles) {
                i.render(gc);
            }
        }
    }

    //s'il en reste, met à jour les ennemis
    public void updateEnnemis(Personnage p){
        if (!(ennemis.isEmpty())) {
            int px=getPosXSalle(p.getPosX());
            int py=getPosYSalle(p.getPosY());
            for (EntiteVivante e : ennemis) {
                updateEnnemi(e,px,py);
            }
        }

    }

    public void updateEnnemi(EntiteVivante ed,int targetX,int targetY){

        ed.moveTo(getPosReelX(targetX),getPosReelY(targetY));
        ed.move();
        appCols(ed);

    }


    public void updateProjectile(Projectile p){
        p.moveToTarget();
        p.move();
        appCols(p);
    }

    public int getPosXSalle(int x){
        return (x/unite);
    }

    public int getPosYSalle(int y){
        return(y/unite);
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
            voisinsEntite[0][1]=0;
            voisinsEntite[1][1]=1;

            voisinsEntite[0][2]=1;
            voisinsEntite[1][2]=0;
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
            if (ed instanceof Personnage){
            if (quadrillage[voisins[0][i]][voisins[1][i]] == 2){
                checkcollisionPorte(ed);

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
    protected void placePorte(boolean inverse){
        int k;

        if (inverse){
            k=0;
        for (int i = 0; i <hauteur ; i++) {
            for (int j = 0; j <largeur ; j++) {
                if (quadrillage[i][j] == 2) {
                    portes[k].setpos(getPosReelY(i),getPosReelX(j));

                    k++;
                }
            }
        }
        }
        else {
            k=1;
            for (int i = 0; i <hauteur ; i++) {
                for (int j = 0; j <largeur ; j++) {
                    if (quadrillage[i][j] == 2) {
                        portes[k].setpos(getPosReelY(i),getPosReelX(j));

                        k--;
                    }
                }
            }

        }
    }


    public void checkcollisionPorte(EntiteDynamique ed){
        if (portes[0].intersects(ed)){
            directionSortie=-1;
        }
        else if (portes[1].intersects(ed)){
            directionSortie=1;
        }

    }
    public int getDirectionSortie(){

        if (directionSortie==1){
            directionSortie=0;
            return(1);
        }
        else if (directionSortie==-1){
            directionSortie=0;
            return(-1);
        }
        else {
            return(0);
        }
    }


    public void getPath(int xdeb, int ydeb, int xfin, int yfin) {
        if (xdeb>=0 && xdeb<largeur && xfin>=0 && xfin<largeur && ydeb>=0 && ydeb<hauteur && yfin>=0 && yfin<hauteur){
            AStarAlgorithm A = new AStarAlgorithm();
            totalPath=A.getPath(pathfindinggrid,pathfindinggrid.getCell(xfin,yfin),pathfindinggrid.getCell(xdeb,ydeb),false).stream().skip(1).collect(Collectors.toList());
        }

    }

    public void Listprint( ){
        for (int i = 0; i <totalPath.size() ; i++) {
            System.out.println("ligne:"+totalPath.get(i).getRow()+"col :"+totalPath.get(i).getCol());
        }
        System.out.println("__");
        System.out.println("__");
    }
    public void gridprint() {
        for (int i = 0; i <hauteur ; i++) {
            for (int j = 0; j <largeur ; j++) {
                System.out.print(pathfindinggrid.getCell(j,i).getObject());
            }
            System.out.println();

        }
    }
    public void creergrid() {
        pathfindinggrid=new AStarGrid<>(largeur,hauteur);
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (quadrillage[j][i] == 0 || quadrillage[j][i]==2) {
                    pathfindinggrid.setCell(Integer.valueOf(quadrillage[j][i]), j, i, true);
                }
                if (quadrillage[j][i] == 1) {
                    pathfindinggrid.setCell(Integer.valueOf(1), j, i, false);
                }
            }
            }
        }

        public void dessinerMap(GraphicsContext gc){
        int k = 0;
        for (int i = 0; i < hauteur; i++) {
                for (int j = 0; j < largeur; j++) {
                    if (quadrillage[j][i] == 1) {
                        gc.drawImage(mur.getImage(), j * unite, i * unite);
                    } else if (quadrillage[j][i] == 2) {
                        gc.drawImage(portes[k].getImage(), j * unite, i * unite);
                        k++;
                    } else if (quadrillage[j][i] == 0) {
                        gc.drawImage(sol.getImage(), j * unite, i * unite);

                    }
                }
            }


    }
}