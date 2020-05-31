import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entite {
    protected int posX; // position sur l'axe x du canvas
    protected int posY; // position sur l'axe y du canvas
    protected Image image; // image de l'entite
    protected double hauteur; // hauteur de l'image
    protected double largeur; // largeur de l'image


    public Entite(int x, int y,String nomFichier,int pixelImage){
        posX=x; // initialisation de la positionx
        posY=y; // initialisation de la positiony
        setImage(nomFichier,pixelImage); // pixelImage est la taille que l'on souhaite attribuer à l'entité
    }


    public void setImage(String nomFichier,int pixelImage ){
        image = new Image(nomFichier,pixelImage,pixelImage,true,false); // nomFichier est l'url de l'image source, conserve le ratio de l'image
        hauteur = image.getHeight(); //hauteur de l'image
        largeur = image.getWidth(); // largeur de l'image
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


    public void render(GraphicsContext gc){
            //on dessine l'entite dans le contexte graphique
            gc.drawImage(image, posX, posY);

    }
}
