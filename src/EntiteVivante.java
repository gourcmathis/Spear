import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class EntiteVivante extends EntiteDynamique {
    public int pV;
    private Inventaire inv;
    private Image[] runanimation;
    private int animationIndice;
    private Image idle;
    private int framespeed=0;
    private int xdirection=1;


    public EntiteVivante(int x, int y,int pV, String nomFichier,int pixelImage, String[] animation) {
        super(x, y, nomFichier,pixelImage);
        this.pV = pV;
        inv = new Inventaire();
        idle=getImage();
        runanimation = new Image[4]; // A faire :  throw Exception si animation .length !=4
        initialAnim(animation);
        animationIndice=0;
    }

    private void initialAnim(String[] anim){
        for (int i = 0; i <anim.length; i++) {
            image = new Image(anim[i],hauteur,hauteur,true,false);
            runanimation[i]=image; // A faire :  throw Exception si animation .length !=4
        }
    }

    public void imageRun(){
        framespeed++;
        if(framespeed >=8) {
            if (ismoving) {
                setImage(runanimation[animationIndice]);
                animationIndice = (animationIndice + 1) % 4;
                framespeed=0;
            } else {
                setImage(idle);
                animationIndice = 0;
            }
        }
    }

    @Override
    public void move() {
        super.move();
        imageRun();
    }

    public void setpV(int pV) {
        this.pV = pV;
    }
    
    public void losepV() {
    	this.pV=pV-1;
    }

	public int getpV() {
		return pV;
	}

    @Override
    public void setDx(int dx) {
        super.setDx(dx);
        if (dx>0){
            xdirection=1;
        }
        if (dx<0) {
            xdirection=-1;
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        if (visible) {
            if (xdirection==1) {
                gc.drawImage(image, posX, posY);
            } else {
                gc.drawImage(image, posX+largeur, posY, -largeur, hauteur);
            }
        }
    }
}
