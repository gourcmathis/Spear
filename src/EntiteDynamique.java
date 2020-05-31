public abstract class EntiteDynamique extends Entite {
    private int dx; // le pas de déplacement horizontal
    private int dy; // le pas de déplacement vertical
    protected int lastposX; // dernière position x
    protected int lastposY; // dernière position y
    protected boolean ismoving=false; // entite en mouvement ?


    public EntiteDynamique(int x, int y,String nomFichier,int pixelImage) {
        super(x, y, nomFichier,pixelImage);
        dx = 0;/*Pas initial nul*/
        dy = 0;/*Pas initial nul*/
        lastposX = posX; // dernière position x = position actuelle x
        lastposY =posY; // dernière position y = position actuelle y
    }


    public int getDx() {
        return dx;
    }


    public int getDy() {
        return dy;
    }


    public void moveTo(int x, int y){
        /*x et y sont les points cibles */
        int distanceX = (x-posX); // distance x à la position cible
        int distanceY = (y-posY); // distance y à la position cible
        int speedX=distanceX/50; // facteur permettant  de réduire la vitesse X
        int speedY=distanceY/50; // facteur permettant de réduite la vitesse Y
        setDx(speedX); // on attribue la vitesse x
        setDy(speedY); // on attribue la vitesse y
    }


    public boolean isMoving(){
        return(ismoving);
    }


    private void updateMotionstate(){
        /*Verifie si l'entite dynamique est en mouvement*/
        ismoving= !(dx==0 && dy==0);
    }


    public void move() {
        /*Mise à jour de la position*/
        lastposX = posX; /*On stocke la position x actuel*/
        lastposY = posY; /*On stocke la position y actuel*/
        this.posX += dx; /*On met à jour la position x avec le pas dx*/
        this.posY += dy; /*On met à jour la position y avec le pas dy*/
    }
   

    public void resetpos(){
        /*Fonction permettant de réinitialiser la position à la position précèdente (utilse pour les collisions avec les murs )*/
        posX = lastposX;
        posY = lastposY;
        setDx(0); /*On réinitialise la vitesse x à 0*/
        setDy(0); /*On réinitialise la vitesse y à 0*/
    }


    public int getLastposX() {
        return lastposX;
    }


    public int getLastposY() {
        return lastposY;
    }


    public void setDx(int dx) {
        this.dx = dx; /*On change la valeur du pas dx*/
        updateMotionstate(); /*On met à jour le statut enmouvement*/
    }


    public void setDy(int dy) {
        this.dy = dy; /*On change la valeur du pas dy*/
        updateMotionstate(); /*On met à jour le statut enmouvement*/

    }
}
