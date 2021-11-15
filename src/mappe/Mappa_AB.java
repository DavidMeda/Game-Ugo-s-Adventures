package mappe;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
 
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.azionabili.Porta;
import oggetti_di_gioco.danneggiabili.Nemico;
import oggetti_di_gioco.item.Item;
/**
 * 
 * 
 *
 */
public abstract class Mappa_AB implements Serializable {
    private static final long serialVersionUID = 3722915930746883586L;
     
    protected ArrayList<Livello> livelli = new ArrayList<>(10);
    protected Pg pg;
    private String nome;
 
 
    // COSTR //////////////////////////
   
    public Mappa_AB(){}
    public Mappa_AB(String name){nome = name;}
     
    // GET ////////////////////////
    public String getNome(){return nome;}
    public Pg getPg(){return pg;}
    public ArrayList<Livello> getLivelli(){return livelli;}
    public Livello getLivello(int n){return livelli.get(n);}
    /**
     * Questo metodo crea una lista e inserisce i nemici
     * @return lista di nemici
     */
    public LinkedList<LinkedList<Nemico>> getListeNemici(){
        LinkedList<LinkedList<Nemico>> lista = new LinkedList<>();
        for(Livello l : getLivelli()){
            lista.addLast(l.getListaNemici());
        }
        return lista;
     
    }
    /**
     * Questo metodo crea una lista e inserisce gli Item
     * @return lista di Item
     */
    public LinkedList<LinkedList<Item>> getListeItem(){
        LinkedList<LinkedList<Item>> lista = new LinkedList<>();
        for(Livello l : getLivelli()){
            lista.addLast(l.getListaItem());
        }
        return lista;
     
    }
    /**
     * Questo metodo crea una lista e inserisce di porte
     * @return lista di porte
     */
    public LinkedList<LinkedList<Porta>> getListePorte(){
        LinkedList<LinkedList<Porta>> lista = new LinkedList<>();
        for(Livello l : getLivelli()){
            lista.addLast(l.getListaPorte());
        }
        return lista;
     
    }
     
    // SET ////////////////////////
    public void setNome(String name){nome = name;}
    public void setLivelli(ArrayList<Livello> l){
        livelli=l;
         
    }
    // METHODS //////////////////////////// 
    /**
     * Questo metodo aggiunge livelli alla coda di priorità
     * @param l livello
     */
    public void addLivello(Livello l){
        livelli.add(l);
     
    }
    // OBJ ////////////////////////////////
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for(Livello l : livelli){
            sb.append("Livello "+n+"\n");
            sb.append(l+"\n");
        }
        return sb.toString();
     
    }
 
}