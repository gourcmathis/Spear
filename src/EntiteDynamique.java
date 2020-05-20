public abstract class EntiteDynamique extends Entite {
    private int dx;
    private int dy;
    private int lastposX;
    private int lastposY;

    public EntiteDynamique(int x, int y,String nomFichier,int pixelImage) {
        super(x, y, nomFichier,pixelImage);
        dx = 0;
        dy = 0;
        lastposX = posX;
        lastposY = posY;
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
    }

    public int getLastposX() {
        return lastposX;
    }

    public int getLastposY() {
        return lastposY;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
