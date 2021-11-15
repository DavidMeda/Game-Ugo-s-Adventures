package supporto;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
 
public class CodaDiPriorita<T> implements Iterable<T>, Serializable {
    private final boolean DEBUG = false;
    private static final long serialVersionUID = -4694912217803272480L;
     
    private ArrayList<LinkedList<T>> coda;
    ////////////////////////////////////
     
    // COSTR ///////////////////////////
    public CodaDiPriorita(int n){
        coda = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            coda.add(new LinkedList<T>());
        }
        if(DEBUG){
        System.out.println("CodaDiPriorita. nuova coda");
        for(int i=0; i<coda.size(); i++){
        System.out.println("              . "+i+":"+coda.get(i));
        }
         
        }
    }
     
    // GET ////////////////////////////
    public ArrayList<LinkedList<T>> coda() {return coda;}
    public LinkedList<T> priorita(int n){return coda.get(n);}
     
    // METHODS /////////////////////////
    public boolean remove(int p, T...oggetti){
        boolean flag = false;
        for(T o : oggetti){
            if(DEBUG){
            System.out.println("\nCodaDiPriorita. add");
            System.out.println("              . aggiungo '"+o+"' alla coda["+p+"]");
            }
            flag = coda.get(p).remove(o);
        }
        return flag;
         
    }
    public void add(int p, T...oggetti){
        for(T o : oggetti){
            if(DEBUG){
            System.out.println("\nCodaDiPriorita. add");
            System.out.println("              . aggiungo '"+o+"' alla coda["+p+"]");
            }
            coda.get(p).add(o);
        }
    }
     
    public Iterator<T> iterator(){
        return new Iteratore();
    }
     
    // OVERRIDE OBJ ///////////////////////////
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<coda.size(); i++){
            sb.append(i+") "+coda.get(i)+"\n");
        }
        return sb.toString();
    }
     
    // CLASSE ITERATORE ////////////////////////
    private class Iteratore implements Iterator<T>{
        Iterator<T> it;
        int ind=-1;
         
        public Iteratore(){
            while(++ind < coda.size()){
                if(coda.get(ind).size()>0){
                    it=coda.get(ind).iterator();
                    break;
                }
            }
        }
        public boolean hasNext() {
            if(it==null)return false;
            if(it.hasNext())return true;
            if(ind > coda.size())return false;
            while(++ind < coda.size()){
                if(coda.get(ind).size()>0)return true;
            }
            return false;
        }
 
        public T next() {
            if(it.hasNext())return it.next();
            while(ind < coda.size()){
                if(coda.get(ind).size()>0){
                    it=coda.get(ind).iterator();
                    return it.next();
                }
                ind++;
            }
            throw new NoSuchElementException("Non ci sono più elementi nella coda di priorità");
        }
         
    }   
     
}