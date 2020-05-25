import java.util.ArrayList;

public class Personnage extends EntiteVivante {
    private ArrayList<Fleche> fleches;
    private int nbFleches;
    private ArrayList<Argent> argents;
    
    private ArrayList<Cle> cles;
    public Personnage(int x, int y,int pixelImage)
    {
        super(x, y, 2, "file:assets/Joueur.png",pixelImage, new String[]{"file:assets/Prun1.png", "file:assets/Prun2.png","file:assets/Prun3.png","file:assets/Prun4.png"});
        fleches = new ArrayList<>();
        argents = new ArrayList<>();
        cles = new ArrayList<>();
        nbFleches=3;
        for (int i = 0; i <nbFleches ; i++) {
            Fleche fleche = new Fleche(posX,posY,64);
            fleches.add(fleche);
        }
       

    }
    
    public void addArgent(Argent argent){
        argents.add(argent);
    }
    public int getNbArgent(){
        return(argents.size());
    }
    
    public void addCle(Cle cle){
        cles.add(cle);
    }
    public int getNbCle(){
        return(cles.size());
    }
    public void removeCle(Cle cle) {
    	cles.remove(cle);
    }

    public void addFleche(Fleche fleche){
        fleches.add(fleche);
    }

    public void removeFleche(Fleche fleche){
        fleches.remove(fleche);
    }

    public int getNbFleches(){
        return(fleches.size());
    }

    public void shoot(int x, int y,Salle salle){
        if (!(fleches.isEmpty())) {
            Fleche fleche = fleches.get(0);
            fleche.setDepart(posX,posY);
            fleche.acquireTarget(x, y);
            fleches.remove(fleche);
            salle.addProjectile(fleche);
        }
    }

}
