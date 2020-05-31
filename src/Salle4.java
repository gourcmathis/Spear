


public class Salle4 extends Salle{
    Squelette squelette = new Squelette(100,100,4,"file:assets/Squelette.png",56);
    Squelette squelette1 = new Squelette(500,200,4,"file:assets/Squelette.png",56);
    Squelette squelette2 = new Squelette(100,800,4,"file:assets/Squelette.png",56);
    Araignee araignee1= new Araignee(200,400,4,"file:assets/Araignee.png",56);
    Araignee araignee2= new Araignee(800,800,4,"file:assets/Araignee.png",56);
    Coffre coffre1= new Coffre(200,50,"file:assets/Coffre.png",56);
    Argent argent = new Argent(600,200,"file:assets/Argent.png",40);
    Potion potion = new Potion(700,800,"file:assets/Potion.png",30);
    Cle cle = new Cle(200,800,"file:assets/Cle.png",56);





   
    public Salle4(int casesHauteur, int caseLargeur) {
		super(casesHauteur, caseLargeur,200,800,540,540);
		creationMatrice();
		placePorte(true);
	    addEnnemi(squelette);
	    addEnnemi(squelette1);
	    addEnnemi(squelette2);
	    addEnnemi(araignee1);
	    addEnnemi(araignee2);
	    addCoffre(coffre1);
		 
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
      
        for (int i = 6; i <9 ; i++) {
        	quadrillage[i][6]=1;
        	
        }
        for (int i = 1; i <8 ; i++) {
        	quadrillage[i][11]=1;
        	
        }
        for (int i = 10; i <14 ; i++) {
        	quadrillage[i][10]=1;
        	
        }
        for (int j = 1; j<4 ; j++) {
        	quadrillage[6][j]=1;
        	
        }
        for (int j = 3; j <8 ; j++) {
        	quadrillage[12][j]=1;
        	
        }
        for (int i = 1; i <7 ; i++) {
        	quadrillage[i][3]=1;
        	quadrillage[3][3]=0;
        	
        }
    
        quadrillage[7][12]=1;


     
        
       
        //Porte1
        quadrillage[3][14]=1;
        quadrillage[4][14]=2;
        quadrillage[5][14]=1;
        //Porte2
        quadrillage[6][7]=1;
        quadrillage[7][7]=2;
        quadrillage[8][7]=1;
        
   
       
   
        
    }
    




}