


public class Salle3 extends Salle{
	private int nbEnnemis;
    private int nbCoffres;
    Squelette squelette1 = new Squelette(650,150,4,"file:assets/Squelette.png",56);
    Squelette squelette2 = new Squelette(450,850,4,"file:assets/Squelette.png",56);
    Squelette squelette3 = new Squelette(450,470,4,"file:assets/Squelette.png",56);
    Araignee araignee1= new Araignee(140,200,4,"file:assets/Araignee.png",56);
    Araignee araignee2= new Araignee(710,630,4,"file:assets/Araignee.png",56);

    Coffre coffre1= new Coffre(150,50,"file:assets/Coffre.png",56);
    Coffre coffre2= new Coffre(710,560,"file:assets/Coffre.png",56);
    Argent argent = new Argent(600,200,"file:assets/Argent.png",40);
    Potion potion = new Potion(700,800,"file:assets/Potion.png",30);
    Cle cle = new Cle(200,800,"file:assets/Cle.png",56);

    public Salle3(int casesHauteur, int caseLargeur) {
		super(casesHauteur, caseLargeur);
		creationMatrice();
	    addEnnemi(squelette1);
	    addEnnemi(squelette2);
	    addEnnemi(squelette3);
	    addEnnemi(araignee1);
	    addEnnemi(araignee2);
	
	    addCoffre(coffre1);
	    addCoffre(coffre2);
		 
	     addArgent(argent);
	     addPotion(potion);
	     addCle(cle);
	    
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
        for (int i = 0; i<8 ; i++) {
        	quadrillage[i][6]=1;
        	
        }
        for (int i = 3; i <6 ; i++) {
        	quadrillage[i][5]=1;
        	
        }
        for (int i = 10; i <13 ; i++) {
        	quadrillage[i][8]=1;
        	
        }
        for (int i = 10; i <13 ; i++) {
        	quadrillage[i][5]=1;
        	
        }
        for (int j= 9; j <13 ; j++) {
        	quadrillage[7][j]=1;
        	
        }
        for (int j= 1; j <3 ; j++) {
        	quadrillage[5][j]=1;
        	
        }
     
     
        
       
        //Porte1
        quadrillage[3][6]=1;
        quadrillage[4][6]=2;
        quadrillage[5][6]=1;
        //Porte2
        quadrillage[9][1]=1;
        quadrillage[10][1]=2;
        quadrillage[11][1]=1;
        
   
       
   
        
    }
    




}
