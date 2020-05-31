public class Mur extends Entite {
    public Mur(int x, int y,int pixelImage) {
        super(x, y,"file:assets/Mur.png",pixelImage);
    }
    public void setpos(int x,int y){
        this.posX = x;
        this.posY = y;
    }
}
