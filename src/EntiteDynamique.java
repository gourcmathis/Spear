public abstract class EntiteDynamique extends Entite {
    private int vitesseX;
    private int vitesseY;

    public EntiteDynamique(int x, int y,String nomFichier) {
        super(x, y, nomFichier);
        vitesseX = 0;
        vitesseY = 0;
    }


}
