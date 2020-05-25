public abstract class EntiteDynamique extends Entite {
    private int dx;
    private int dy;
    protected int lastposX;
    protected int lastposY;
    protected boolean ismoving=false;


    public EntiteDynamique(int x, int y,String nomFichier,int pixelImage) {
        super(x, y, nomFichier,pixelImage);
        dx = 0;
        dy = 0;
        lastposX = posX;
        lastposY =posY;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    public void moveTo(int x, int y){
        int distanceX = (x-posX);
        int distanceY = (y-posY);
        int speedX=distanceX/20;
        int speedY=distanceY/20;

        setDx(speedX);
        setDy(speedY);

    }
    public boolean isMoving(){
        return(ismoving);
    }

    private void updateMotionstate(){
        ismoving= !(dx==0 && dy==0);
    }

    public void move() {
        lastposX = posX;
        lastposY = posY;
        this.posX += dx;
        this.posY += dy;
    }
    
   

    public void resetpos(){
        posX = lastposX;
        posY = lastposY;
        setDx(0);
        setDy(0);
    }


    public int getLastposX() {
        return lastposX;
    }

    public int getLastposY() {
        return lastposY;
    }

    public void setDx(int dx) {
        this.dx = dx;
        updateMotionstate();
    }

    public void setDy(int dy) {
        this.dy = dy;
        updateMotionstate();

    }
}
