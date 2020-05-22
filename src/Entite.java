import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entite {
    protected int posX;
    protected int posY;
    protected Image image;
    private double hauteur;
    private double largeur;
    private boolean visible;



    public Entite(int x, int y,String nomFichier,int pixelImage){
        posX=x;
        posY=y;
        setImage(nomFichier,pixelImage); // pixelImage est la taille que l'on souhaite attribuer à l'entité
        setVisible(true);
    }


    public void setImage(String nomFichier,int pixelImage ){
        image = new Image(nomFichier,pixelImage,pixelImage,true,false); // nomFichier est l'url de l'image source, conserve le ratio de l'image
        hauteur = image.getHeight();
        largeur = image.getWidth();
    }
    public void setImage(Image image){
        this.image=image;
        hauteur = image.getHeight();
        largeur = image.getWidth();
    }

    public Image getImage(){
        return(image);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public BoundingBox getBoundingBox(){
        // BoundingBox est une boite rectangulaire 2D qui permettra la détection de collision
        // BoundingBox(x,y,w,h) avec x et y : coordonnées du coin supérieur gauche du rectangle, w et h : largeur et hauteur du rectangle
        return(new BoundingBox(posX,posY,largeur,hauteur));
    }

    public boolean intersects (Entite e){
        // On vérifie si les deux boites se coupent
        return(e.getBoundingBox().intersects(this.getBoundingBox()));
    }

    public void setVisible(boolean visible){
        // booleen permettant de savoir s'il faut afficher à l'écran l'entite ou non
        this.visible= visible;
    }
    public void render(GraphicsContext gc){
        if (visible) {
            gc.drawImage(image, posX, posY);
        }
    }
}
