package mappe;
 
import java.util.Iterator;
import java.util.LinkedList;

import oggetti_di_gioco.Muro;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import oggetti_di_gioco.azionabili.Scrigno;
import oggetti_di_gioco.danneggiabili.Nemico;
import supporto.NonCalpestabile;

/**
 * 
 *
 */
 
public class Mappa extends Mappa_AB implements Iterable<Sprite>{
    private static final long serialVersionUID = -9215433210600093286L;
    /**
     * <strong>idLivello:</strong> indica l'identificatore del livello
     * <br><strong>i:</strong> indica la riga del pg
     * <br><strong>j:</strong> indica la colonna del pg
     */
    private int
    idLivello, 
    //posizione iniziale pg
    i , j;
    private Livello level;
    private String msg;
     
    // COSTR ///////////////////////
    
    public Mappa(){super();}
     
    // GET /////////////////////////
    public int getI(){return i;}
    public int getJ(){return j;}
    public Livello getLivello(){return level;}
    public Sprite get(int i, int j){ return level.get(i, j);}
    public int getIdLivello(){return idLivello;}
    public String getMsg(){return msg;}
    public LinkedList<Sprite> sfondi(){return livelli.get(idLivello).sfondi();}
    public LinkedList<Sprite> muri()  {return livelli.get(idLivello).muri();}
    public LinkedList<Sprite> item()  {return livelli.get(idLivello).item();}
    public LinkedList<Sprite> nemici(){return livelli.get(idLivello).nemici();}
 
    // SET /////////////////////////
   
    public void setMsg(String message){msg=message;}
    
    public void setPg(Pg p){
        pg=p;
        pg.setMappa(this);
     
    }
    /**
     * Questo metodo posiziona il pg sulla matrice
     * @param i riga del pg
     * @param j colonna del pg
     */
    public void setSpawnPg(int i, int j){
        this.i=i; this.j=j;
         
    }
    public void setLivello(int id){
        if(level!=null){
            level.removeNemici();
        }
        idLivello=id;
        level=getLivello(id);
        pg.setIJ(i,j);
        level.spawn(pg);
         
    }
     
    // METHODS /////////////////////
    public void removeImage(Sprite s){
        level.removeImage(s);
         
    }
    public void addImage(Sprite s){
        level.addImage(s);
     
    }
    /**
     * Questo metodo controlla se il pg è morto
     * @return true se il pg è morto 
     * <br> false altrimenti
     */
    public boolean gameOver(){return pg.morto();} 
    /**
     * Questo metodo controlla il danno inferto ai nemici
     */
    public void aggiornaNemici(){
        Nemico n = null;
        Sprite s = null;
        Iterator<Sprite> it = nemici().iterator();
        while(it.hasNext()){
            s = it.next();
            
            if(s instanceof Pg)continue;
            n = (Nemico)s;
        	if(pg.getArma()!=null && n.collide(pg.getArma())){ // nemico sotto attacco
    			n.riceviDanno(1); 
    			if(n.morto())it.remove(); //nemico morto
        	}
            n.AI(); //muovi nemico
        }
             
    }
    public void remove(Sprite...sprites){
        for(Sprite s : sprites){
            level.remove(s);
        }
    
    }
    public Iterator<Sprite> iterator(){return level.iterator();}
     
    public boolean isMuro(int i, int j){
        return (get(i, j) instanceof Muro);
    }
     
    public boolean isNonCalpestabile(int i, int j){
            return(get(i,j) instanceof NonCalpestabile || get(i,j) instanceof Scrigno);
    }
         
    public void addSprite(Sprite s){
        level.add(s);
     
    }
     
     
    // OVERRIDE OBJ ///////////////////
    public String toString(){
        Sprite m[][] = level.getMatrice();
        StringBuilder sb = new StringBuilder(420);
        for(int i=0; i<10; i++){
            for(int j=0; j<21; j++){
                if(i==pg.i() && j==pg.j())sb.append("PG");
                else sb.append(m[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
     
}