public abstract class EntiteVivante extends EntiteDynamique {
    private int pV;
    private Inventaire inv;
    public EntiteVivante(int x, int y,int pV, String nomFichier) {
        super(x, y, nomFichier);
        this.pV = pV;
        inv = new Inventaire();
    }
}
