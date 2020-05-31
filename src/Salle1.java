

public class Salle1 extends Salle{
    Squelette squelette1 = new Squelette(700,100,4,"file:assets/Squelette.png",56);
    Squelette squelette2 = new Squelette(450,800,4,"file:assets/Squelette.png",56);
    Araignee araignee1= new Araignee(140,200,4,"file:assets/Araignee.png",56);
    Araignee araignee2= new Araignee(800,400,4,"file:assets/Araignee.png",56);

    Coffre coffre1= new Coffre(150,50,"file:assets/Coffre.png",56);
    Coffre coffre2= new Coffre(900,50,"file:assets/Coffre.png",56);
    Argent argent = new Argent(600,200,"file:assets/Argent.png",40);
    Potion potion = new Potion(700,800,"file:assets/Potion.png",30);
    Cle cle = new Cle(200,800,"file:assets/Cle.png",56);



    public Salle1(int casesHauteur, int caseLargeur) {
		super(casesHauteur, caseLargeur,30+Variables.Largeur()/2,800,30+Variables.Largeur()/2,800);
		creationMatrice();
		placePorte(true);
	    addEnnemi(squelette1);
	    addEnnemi(squelette2);
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
        for (int i= 2; i <6 ; i++) {
        	quadrillage[i][9]=1;
        	
        }
        for (int i = 10; i <14 ; i++) {
        	quadrillage[i][9]=1;
        	
        }
        for (int i = 8; i <15; i++) {
        	quadrillage[i][4]=1;
        	
        }
        for (int j = 0; j<5 ; j++) {
        	quadrillage[4][j]=1;
        	
        }
     
     
        
       
        quadrillage[4][4]=1;
        //porte
        quadrillage[6][14]=1;
        quadrillage[7][14]=2;
        quadrillage[8][14]=1;
       
   
        
    }
    




}
