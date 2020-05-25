import java.util.ArrayList;

public class Personnage extends EntiteVivante {
    private ArrayList<Fleche> fleches;
    private int nbFleches;
    public Personnage(int x, int y,int pixelImage)
    {
        super(x, y, 3, "file:assets/Joueur.png",pixelImage, new String[]{"file:assets/Prun1.png", "file:assets/Prun2.png","file:assets/Prun3.png","file:assets/Prun4.png"});
        fleches = new ArrayList<>();
        nbFleches=3;
        for (int i = 0; i <nbFleches ; i++) {
            Fleche fleche = new Fleche(posX,posY,64);
            fleches.add(fleche);
        }
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
