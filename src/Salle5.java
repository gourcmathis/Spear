



public class Salle5 extends Salle{
	private int nbEnnemis;
    private int nbCoffres;
    Squelette squelette1 = new Squelette(700,100,4,"file:assets/Squelette.png",56);
    Squelette squelette2 = new Squelette(450,800,4,"file:assets/Squelette.png",56);
    Squelette squelette3= new Squelette(400,280,4,"file:assets/Squelette.png",56);
    Squelette squelette4 = new Squelette(200,600,4,"file:assets/Squelette.png",56);
    Araignee araignee1= new Araignee(140,200,4,"file:assets/Araignee.png",56);
    Araignee araignee2= new Araignee(800,400,4,"file:assets/Araignee.png",56);
    Araignee araignee3= new Araignee(140,450,4,"file:assets/Araignee.png",56);
    Araignee araignee4= new Araignee(800,800,4,"file:assets/Araignee.png",56);
    Argent argent = new Argent(600,200,"file:assets/Argent.png",40);
    Potion potion = new Potion(700,800,"file:assets/Potion.png",30);
    Cle cle = new Cle(200,800,"file:assets/Cle.png",56);




    
    public Salle5(int casesHauteur, int caseLargeur) {
		super(casesHauteur, caseLargeur,540,540,160,800);
		creationMatrice();
		placePorte();
		 addEnnemi(squelette1);
		 addEnnemi(squelette2);
		 addEnnemi(squelette3);
		 addEnnemi(squelette4);
		 addEnnemi(araignee1);
		 addEnnemi(araignee2);
		 addEnnemi(araignee3);
		 addEnnemi(araignee4);
		 
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
        
        for (int i = 9; i <14 ; i++) {
        	quadrillage[i][10]=1;
        	
        }
        for (int i = 3; i <6 ; i++) {
        	quadrillage[i][11]=1;
        	
        }
        for (int i = 3; i <8 ; i++) {
        	quadrillage[i][3]=1;
        	
        }
      

     
        
       
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