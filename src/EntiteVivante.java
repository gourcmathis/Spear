import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class EntiteVivante extends EntiteDynamique {
    private int pV; // nombre de points de vie de l'entite vivante
    private Image[] runanimation; // ensemble d'images décrivant l'animation de course
    private int animationIndice; // indice courant de l'image de l'animation
    private Image idle; // image statique
    private int framespeed; // indice permettant
    private int xdirection; // direction horizontale de l'entite vivante


    public EntiteVivante(int x, int y,int pV, String nomFichier,int pixelImage, String[] animation) {
        super(x, y, nomFichier,pixelImage);
        this.pV = pV; // initialisation des pV
        idle=getImage(); //l'image par défaut est celle statique
        runanimation = new Image[4]; // L'animation de course se décompose en 4 images. On effectue une permutation circulaire de ces images pour donner l'impression de mouvement
        initialAnim(animation); // initialisation de l'animation
        animationIndice=0; // indice de début de l'animation
        framespeed=0; // vitesse de "changement" des images
        xdirection=1; // direction horizontale du joueur 1 = droite et -1 = gauche

    }


    /*Initialise le tableau d'images*/
    private void initialAnim(String[] anim){
        for (int i = 0; i <anim.length; i++) {
            image = new Image(anim[i],hauteur,hauteur,true,false);
            runanimation[i]=image; // A faire :  throw Exception si animation .length !=4
        }
    }


    /*Change l'image du joueur en fonction du temps écoulé*/
    public void imageRun(){
        framespeed++; // incrémentation du délai
        if(framespeed >=8) {
            if (ismoving) {
                setImage(runanimation[animationIndice]); // on passe à l'image suivante
                animationIndice = (animationIndice + 1) % 4; // permutation circulaire des images
                framespeed=0; // réinitialisation du délai
            } else {
                setImage(idle); //on repasse à l'image statique si le joueur ne bouge plus
                animationIndice = 0;
            }
        }
    }


    @Override
    public void move() {
        super.move();
        imageRun(); // en plus du déplacement on change dynamiquement l'image du joueur
    }


    public void gainpV() {
        pV++;
    }


    public void losepV() {
    	pV--;
    }


	public int getpV() {
		return pV;
	}


    @Override
    public void setDx(int dx) {
        super.setDx(dx);
        if (dx>0){
            xdirection=1; //direction droite
        }
        if (dx<0) {
            xdirection=-1; // direction gauche
        }

    }


    @Override
    public void render(GraphicsContext gc) {

            if (xdirection==1) { // si le joueur va à droite
                gc.drawImage(image, posX, posY);
            } else { // si le joueur va à gauche
                gc.drawImage(image, posX+largeur, posY, -largeur, hauteur);
            }
        }
    }