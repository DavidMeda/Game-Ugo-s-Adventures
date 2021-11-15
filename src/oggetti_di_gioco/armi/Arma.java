package oggetti_di_gioco.armi;
 
import java.awt.event.KeyEvent;
 
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.item.Item;
 
/**
 * Le Armi sono Item capaci di neutralizzare i nemici se sono in possesso del personaggio principale
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public abstract class Arma extends Item {
    private static final long serialVersionUID = 4176861308939651221L;
     
    protected int
    /**
     * Numero di hp (punti vita) che l'arma riesce a sottrarre al nemico
     */
    danno, 
    /**
     * Raggio in cui ha effetto l'arma
     * <br>(Utile per quanto riguarda armi che provocano danni a zona, come per esempio bombe,
     * <br>in questa versione del gioco però non sono presenti bombe quindi non è mai utilizzata)
     */
    raggioDazione;
    protected boolean
    /**
     * True se può danneggiare un nemico
     */
    inAttacco;
     
    // COSTR ////////////////////////
    public Arma(){}
    /**
     * Crea un oggetto Arma
     * @param danno danno che può arrecare l'arma
     * @param raggioDazione raggio d'azione dell'arma
     */
    public Arma(int danno, int raggioDazione){
        this.danno = danno;
        this.raggioDazione = raggioDazione;
    }
     
    // SET //////////////////////////
    /**
     * Modifica la variabile di controllo
     * @param flag true se l'arma può danneggiare il nemico
     * <br> false altrimenti
     */
    public void setInAttacco(boolean flag){
        inAttacco = flag;
        if(!inAttacco)setIJ(-1,-1);
    }
    // GET //////////////////////////
    /**
     * Restituisce il raggio d'azione dell'arma
     * @return raggio d'azione
     */
    public int raggioDazione(){return raggioDazione;}
    /**
     * Restituisce il danno dell'arma
     * @return danno
     */
    public int danno(){return danno;}
     
    // ABSTRACT ////////////////////
    /**
     * Metodo da ridefinire in base all'arma che si vuole creare
     * @param e key event
     * @param pg personaggio
     */
    public abstract void attacca(KeyEvent e, Pg pg);
     
}