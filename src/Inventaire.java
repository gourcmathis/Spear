import java.util.ArrayList;

public class Inventaire <T extends Item> {
    ArrayList listeObjets = new ArrayList<T>();

    public void remove(Object o){
        listeObjets.remove(o);
    }
    public void add (T item){
        listeObjets.add(item);
    }

    public void use(Object o){
       // utiliser la methode use d'item

        remove(o);
    }
}
