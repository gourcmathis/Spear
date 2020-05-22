import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Projectile extends EntiteDynamique {
    private int speed=1;
    private int speedX;
    private int speedY;
    protected int targetX;
    protected int targetY;
    private double theta=0;
    ImageView iv ;
    public Projectile(int x, int y, String nomFichier,int pixelImage) {
        super(x, y, nomFichier,pixelImage);
        iv = new ImageView(image);
        rotateImage();
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
       speedX=distanceX/5;
       speedY=distanceY/5;

       setDx(speedX);
       setDy(speedY);
       calculAngle(distanceX,distanceY);
       rotateImage();
   }

    public void moveTo(int x, int y){

            int distanceX = (x-posX);
            int distanceY = (y-posY);
            speedX=distanceX/5;
            speedY=distanceY/5;

            setDx(speedX);
            setDy(speedY);
            calculAngle(distanceX,distanceY);
            rotateImage();
        }

        private void calculAngle(int x, int y){
            if (x!=0) {
                resetrotation();
                theta = 180+ Math.toDegrees(-Math.atan2(x,y));
            }
        }
        public void resetrotation(){
            theta=-theta;
            rotateImage();
        }

        public void rotateImage(){
            iv.setRotate(theta);
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            setImage(iv.snapshot(params, null));
        }


}
