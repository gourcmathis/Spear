import javafx.scene.canvas.GraphicsContext;

public abstract class EntiteVivante extends EntiteDynamique {
    private int pV;
    private Inventaire inv;
    public EntiteVivante(int x, int y,int pV, String nomFichier,int pixelImage) {
        super(x, y, nomFichier,pixelImage);
        this.pV = pV;
        inv = new Inventaire();
    }

    public void setpV(int pV) {
        this.pV = pV;
    }
}
