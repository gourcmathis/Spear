import java.util.ArrayList;

public class Personnage extends EntiteVivante {
    private ArrayList<Fleche> fleches; // fleches que le joueur possede dans son inventaire
    private int nbFleches;
    private ArrayList<Argent> argents; // argent que le joueur possede dans son inventaire
    private ArrayList<Cle> cles; // cles que le joueur possède dans son inventaire

    public Personnage(int x, int y,int pixelImage)
    {
        super(x, y, 5, "file:assets/Joueur.png",pixelImage, new String[]{"file:assets/Prun1.png", "file:assets/Prun2.png","file:assets/Prun3.png","file:assets/Prun4.png"});
        fleches = new ArrayList<>();
        argents = new ArrayList<>();
        cles = new ArrayList<>();
        nbFleches=3;
        /*Initialisation des fleches dans l'inventaire*/
        for (int i = 0; i <nbFleches ; i++) {
            Fleche fleche = new Fleche(posX,posY,64);
            fleches.add(fleche);
        }
    }


    /*Permet de déplacer le joueur pour la prochaine salle*/
    public void teleport(int x, int y){
        posX=x;
        posY=y;
    }

    /*Stocke les pieces*/
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


    public void removeCle() {
    	cles.remove(0);
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


    /*permet de tirer les projectiles du joueur*/
    public void shoot(int x, int y,Salle salle){
        if (!(fleches.isEmpty())) {
            Fleche fleche = fleches.get(0); // on sélectionne une fleche de fleches
            fleche.setDepart(posX,posY); // position de départ de la fleche
            fleche.acquireTarget(x, y); // cible de la fleche
            removeFleche(fleche); // on retire la fleche de l'array
            salle.addProjectile(fleche); // on ajoute la fleche à la salle pour le rendu et le calcul des positions
           /* String filepath = "assets/fleche.wav";
			Audio musicObject = new Audio();
			musicObject.playSong(filepath);*/
        }
    }

}
