public class Porte extends Entite{
    public Porte(int x, int y,int pixelImage) {
        super(x, y, "file:assets/Porte.png",pixelImage);
    }
    public void setpos(int x,int y){
        this.posX = x;
        this.posY = y;
    }
}
