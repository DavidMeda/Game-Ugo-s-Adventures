package oggetti_di_gioco.azionabili;
 
import gui.supporto.Messaggio;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import oggetti_di_gioco.item.Item;;
 
/**
 * Gli scrigni conterranno ricompense per il personaggio una volta aperti
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public class Scrigno extends Item implements Azionabile{
    private static final long serialVersionUID = 2232536206033777468L;
     
    /**
     * True se lo scrigno è stato azionato in precedenza
     */
    public boolean azionato = false;
    /**
     * Identificativo della chiave necessaria per l'apertura (corrisponde a -1 se non necessita di chiavi)
     */
    private int chiave = -1;
    /**
     * Item rilasciato dopo l'azionamento dello scrigno
     */
    private Item item;
    /**
     * Messaggio tutorial
     */
    private Messaggio messaggio;
     
    // COSTR ////////////////////////
    public Scrigno(){}
    /**
     * Crea un oggetto Scrigno che non necessita di chiavi per l'apertura
     * @param item contenuto dello scrigno
     */
    public Scrigno(Item item){
        super();
        setPathImage("scrigno");
        setItem(item);
        messaggio = new Messaggio("F >> Apri");
        messaggio.setProprietario(this);
     
    }
    /**
     * Crea un oggetto Scrigno che necessita di chiave per l'apertura
     * @param item contenuto dello scrigno
     * @param chiave id della chiave necessaria per l'apertura dello scrigno
     */
    public Scrigno(Item item, int chiave){
        super();
        setPathImage("scrigno");
        setItem(item);
        this.chiave = chiave;
        messaggio = new Messaggio("F >> Apri");
        messaggio.setProprietario(this);
     
    }
     
    // GET //////////////////////
    /**
     * Restituisce l'id della chiave necessaria per l'apertura
     * @return id chiave
     */
    public int id(){return chiave;}
    /**
     * Restituisce l'item contenuto all'interno dello scrigno
     * @return item contenuto nello scrigno
     */
    public Item getItem(){return item;}
    /**
     * 
     */
    public boolean azionato(){return azionato;}
     
    // SET //////////////////////
    /**
     * Imposta l'item contenuto all'interno dello scrigno
     * @param item item da contenere
     */
    public void setItem(Item item){
        if(item == null){throw new IllegalArgumentException("item nullo");}
        this.item = item;; 
        this.item.setIJ(i, j);
         
    }
    /**
     * Oltre ad impostare gli indici di riga i e colonna j dello scrigno
     * <br>li imposta anche all'item contenuto
     */
    public void setIJ(int i, int j){
        super.setIJ(i,j);
        item.setIJ(i,j);
    }
 
    // OVERRIDE ///////////////////
    public void preparaSalvataggio(){
        if(item!=null)item.preparaSalvataggio();
        super.preparaSalvataggio();
     
    }
    public void caricaSalvataggio(){
        if(item!=null)item.caricaSalvataggio();
        super.caricaSalvataggio();
     
    }
    /**
     * Restituisce il messaggio tutorial
     */
    public Messaggio getMessaggio(){return messaggio;}
   
    public boolean aziona(Sprite...s){
        if(azionato)return true;
        if(siPuoAprire(s) && item!=null){         
        	Pg pg = (Pg)s[0];
            pg.getMappa().getLivello().despawn(this);
            pg.getMappa().remove(this);
            pg.getMappa().addSprite(item);
            pg.getMappa().addImage(item);
            item=null;
            azionato=true;
        }
        return azionato;
         
    }private boolean siPuoAprire(Sprite...s){
        if(chiave!= -1){
            Pg pg = (Pg)s[0];
            return pg.apre(this);
        }
        return true;
     
    }
     
    // OVERRIDE OBJ ///////////////////////
    public String toString(){return "scrigno";}
         
}