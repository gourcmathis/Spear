public abstract class EntiteDynamique extends Entite {
    private int dx;
    private int dy;

    public EntiteDynamique(int x, int y,String nomFichier,int pixelImage) {
        super(x, y, nomFichier,pixelImage);
        dx = 0;
        dy = 0;
    }

    public void move() {
        this.posX += dx;
        this.posY += dy;
    }


    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
