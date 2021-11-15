package mappe;
 
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import oggetti_di_gioco.armi.Arma;
import oggetti_di_gioco.azionabili.Porta;
import oggetti_di_gioco.azionabili.Scrigno;
import oggetti_di_gioco.danneggiabili.Nemico;
import oggetti_di_gioco.danneggiabili.Verme;
import oggetti_di_gioco.item.Item;
import supporto.CodaDiPriorita;
import supporto.NonCalpestabile;
/**
 * La classe Livello è costituita da una coda di priorità di Sprite che ne gestisce l’ordine di stampa a video
 * <br>inoltre è istanziata una matrice generata tramite l'iterazione della coda di priorità
 * 
 *
 */
public class Livello implements Iterable<Sprite>, Serializable{
    private final boolean DEBUG = false;
    private static final long serialVersionUID = -5510137961655219036L;
     
    private CodaDiPriorita<Sprite> livello = new CodaDiPriorita<>(4);
    private Sprite[][] matrice;
    private LinkedList<Nemico> nemici = new LinkedList<>();
    private LinkedList<Item> items = new LinkedList<>();
    private LinkedList<Porta> porte = new LinkedList<>();
     
     
    // COSTR ////////////////////////////
    
    public Livello(){}
     
    // SET //////////////////////////////
    public void setListaNemici(LinkedList<Nemico> enemies){
        nemici = enemies;
        for(Nemico n : nemici)n.caricaSalvataggio();
     
    }
    public void setListaItem(LinkedList<Item> item){
        items = item;
        for(Item i : items)i.caricaSalvataggio();
 
    }
    public void setListaPorte(LinkedList<Porta> doors){
        porte = doors;
        for(Porta p : porte)p.caricaSalvataggio();
 
    }
    public void setMatrice(Sprite[][]matr){
        if(DEBUG){System.out.println("Livello.setMatrice.");}
        matrice = matr;
    }
    // GET ////////////////////////////////
    /**     */
    public LinkedList<Sprite> sfondi(){return livello.priorita(0);}
    public LinkedList<Sprite>   muri(){return livello.priorita(3);}
    public LinkedList<Sprite>   item(){return livello.priorita(1);}
    public LinkedList<Sprite> nemici(){return livello.priorita(2);}
    public LinkedList<Nemico> getListaNemici(){return new LinkedList<Nemico>(nemici);}
    public LinkedList<Item> getListaItem(){return new LinkedList<Item>(items);}
    public LinkedList<Porta> getListaPorte(){return new LinkedList<Porta>(porte);}
    public CodaDiPriorita<Sprite> getLivello(){
        if(DEBUG){System.out.println("Livello.getLivello.");}
        return livello;
         
    }
    public Sprite get(int i,int j){
        if(DEBUG){System.out.println("Livello.get. matrice["+i+"]["+j+"]='"+matrice[i][j]+"'");}
        return matrice[i][j];
         
    }
    public Sprite[][] getMatrice(){
        if(DEBUG){System.out.println("Livello.getMatrice.");}
        return matrice;
         
    }
    
     
    // METHODS ////////////////////////////
    /**
     * Questo metodo crea una matrice di sprite 
     * <br>inserendo gli sprite tramite iterazione della coda di priorità
     */
    public void generaMatrice(){
    	if(matrice == null){
    		matrice = new Sprite[10][21];
    		Iterator<Sprite> it = livello.iterator();
    		while(it.hasNext()){
    			Sprite s = it.next();
    			if(s instanceof Nemico)
    				matrice[s.i()][s.j()]=s;
    		}
    		if(DEBUG){
    			System.out.println("Livello.generaMatrice.");
    			for(int i=0; i<10; i++){
    				System.out.print("       . ");
    				for(int j=0; j<21; j++){
    					System.out.print(matrice[i][j]);
    				}System.out.println();}
    		}
    	}
    	
    }
    /**
     * Qusto metodo rimuove gli sprite dalla coda di priorità
     * @param s Sprite da rimuovere
     */
    public void removeImage(Sprite s){
        livello.remove(s.getPriorita(), s);
     
    }
    
    public void addImage(Sprite s){
        livello.add(s.getPriorita(), s);
        if(!(s instanceof Arma) && s instanceof Item ){
            matrice[s.i()][s.j()] = s;
        }
    }
    public Iterator<Sprite> iterator(){return livello.iterator();}
    /**
     * Questo metodo rimuove gli Sprite dalla coda e dalla matrice
     * @param sprites da rimuovere
     */
    public void despawn(Sprite...sprites){
        for(Sprite s : sprites){
            if(s instanceof Nemico)nemici.remove(s);
            else if(s instanceof Item){items.remove(s);}
            else if(s instanceof Porta)porte.remove(s);
        }
     
    }
    /**
     * Questo medoto rimuove tutti i nemici dalla matrice
     */
    public void removeNemici(){
        Iterator<Sprite> it = nemici().iterator();
        while(it.hasNext()){
            if(it.next() instanceof Nemico)
            it.remove();
        }
     
    }
    /**
     * Questo metodo aggiunge nemici alla lista dei nemici
     * @param enemies Nemici
     */
    public void addNemico(Nemico...enemies){
        for(Nemico n : enemies){nemici.add(n);}
     
    }
    /**
     * Questo metodo aggiunge porte alla lista delle porte
     * @param doors Porte
     */
    public void addPorta(Porta...doors){
        for(Porta p : doors){porte.add(p);}
     
    }
    /**
     * Quetso metodo aggiunge Item alla lista degli item
     * @param item Item
     */
    public void addItem(Item...item){
        for(Item i : item){items.add(i);}
     
    }
    /**
     * Questo metodo aggiunge nemici, Item e porte alla matrice iterando le liste 
     * @param pg personaggio 
     */
    public void spawn(Pg pg){
        for(Nemico n : nemici){
            livello.add(n.getPriorita(), new Verme(n,pg));
        }
        for(Item i : items){
            livello.add(i.getPriorita(), i);
            matrice[i.i()][i.j()]=i;
        }
        for(Porta p : porte){
            livello.add(p.getPriorita(), p);
            matrice[p.i()][p.j()]=p;
        }
        add(pg);
 
    }
    /**
     * Questo metodo rimuove lo Sprite <strong>s</strong> dal livello e dalla mappa
     * @param s Sprite da rimuovere
     * @return true se lo Sprite viene rimosso
     * <br>false altrimenti
     */
    public boolean remove(Sprite s){
        boolean rimosso = false;
        rimosso = livello.remove(s.getPriorita(),s);
        if(rimosso && matrice[s.i()][s.j()]!=null){
            matrice[s.i()][s.j()]=null;
//            if(s instanceof Scrigno) items.remove(s);
        }
        return rimosso;
     
    }
    /**
     * Questo metodo controlla se in posizione [i][j] c'è uno sprite non calpescabile
     * @param i riga dello sprite
     * @param j colonna dello sprite
     * @return true se l'oggetto è non calpestabile
     * <br>false altrimenti
     */
    public boolean isNonCalpestabile(int i, int j){
        return(get(i,j) instanceof NonCalpestabile || get(i,j) instanceof Scrigno);
     
    }
    /**
     * Questo metodo rimuove l'elemento in [i][j] e inserisce uno sfondo in posizione [i][j] nella matrice
     * @param i riga
     * @param j colonna
     */
    public void reset(int i, int j){
        remove(matrice[i][j]);
 
        for(Sprite s : livello.priorita(0)){
            if(s.i()==i && s.j()==j){
                matrice[i][j]=s;
            }
        }
 
    }
    /**     */
    public void add(Sprite...sprites){
        for(Sprite s : sprites){
            if(DEBUG){
            System.out.println("Livello.add. aggiunto '"+s+"' al livello");
            }
            if(s instanceof Nemico){ addNemico((Nemico)s);}
            else if(s instanceof Porta){ addPorta((Porta)s);}
            else if(s instanceof Item){ addItem((Item)s);}
            else{
                livello.add(s.getPriorita(), s);
                if(!(s instanceof Pg))matrice[s.i()][s.j()]=s;
            }
        }
        if(DEBUG){
        for(Sprite s : sprites){
        System.out.println("           . '"+s+"' ");
        }System.out.println();
        }
     
    }   
     
    // OVERRIDE OBJ ////////////////////
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<10; i++){
            for(int j=0; j<21; j++){
                sb.append(matrice[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
     
    }
     
}