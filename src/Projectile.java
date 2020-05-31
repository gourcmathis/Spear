import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Projectile extends EntiteDynamique {
    protected int targetX; // abscisse de la cible
    protected int targetY; // ordonnée de la cible
    private double theta=0; // rotation de l'image du projectile
    private ImageView iv ; // imageview permettant de faire des transformations sur l'image
    public Projectile(int x, int y, String nomFichier,int pixelImage) {
        super(x, y, nomFichier,pixelImage);
        iv = new ImageView(image);
        rotateImage();
        ismoving=true;
    }

   public void acquireTarget(int x, int y){
        targetX=x;
        targetY=y;
   }

   public void setDepart(int x,int y){
        posX = x;
        posY = y;
    }

    public void moveToTarget(){
       int distanceX = (targetX-posX);
       int distanceY = (targetY-posY);
        int speedX = distanceX / 5;
        int speedY = distanceY / 5;

       setDx(speedX);
       setDy(speedY);
       calculAngle(distanceX,distanceY);
       rotateImage();
   }


    /*Fonction permettant de calculer l'angle selon la direction de la cible*/
    private void calculAngle(int x, int y){
        if (x!=0) {
            resetrotation();
            theta = 180+ Math.toDegrees(-Math.atan2(x,y));
        }
    }


    /*Réinitialisation de la rotation de l'image*/
    public void resetrotation(){
        theta=-theta;
        rotateImage();
    }

    /*Application de la rotation de l'image grace à une imageview*/
    private void rotateImage(){
        iv.setRotate(theta);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        setImage(iv.snapshot(params, null));
    }
}
