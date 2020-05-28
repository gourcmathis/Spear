public class Porte extends Entite{
    private boolean ouverte=false;
    public Porte(int x, int y,int pixelImage) {
        super(x, y, "file:assets/Porte.png",pixelImage);
    }
    public void setouvrir(int taillepixel){
        if (!ouverte) {
            setImage("file:assets/Porteouverte.png", taillepixel);
            ouverte=true;
        }
    }
    public void setpos(int x,int y){
        this.posX = x;
        this.posY = y;
    }
}
