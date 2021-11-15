package oggetti_di_gioco.azionabili;
 
import gui.supporto.Messaggio;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import oggetti_di_gioco.Sprite_AB;
 
/**
 * Gli oggetti Porta impedisce il passaggio al personaggio se è chiusa
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public class Porta extends Sprite_AB implements Azionabile{
    private static final long serialVersionUID = -3028556001613101831L;
    private final boolean DEBUG = false;
     
    /**
     * Identifica la chiave necessaria per l'apertura della porta
     */
    private int id;
    /**
     * True se la porta è stata aperta in precedenza
     */
    private boolean aperta;
    /**
     * Percorso corrispondente all'immagine della porta quando aperta
     */
    private String pathPortaAperta;
    /**
     * Messaggio tutorial
     */
    private Messaggio messaggio;
     
    // COSTR ////////////////////////////////////
    /**
     * Crea un oggetto porta
     * @param id identificativo della chiave che ne permetterà l'apertura
     * @param orizzontale true se la porta è posizionata in modo orizzontale sulla mappa
     * <br> false altrimenti(utile per la selezione dell'immagine da utilizzare)
     */
    public Porta(int id, boolean orizzontale){
        this.id=id;
        priorita = 3;
        messaggio = new Messaggio("F >> Apri");
        messaggio.setProprietario(this);
 
         
        if(orizzontale){
            setPathImage("portaOC");
            pathPortaAperta = "portaOA";
        }else{
            setPathImage("portaVC");
            pathPortaAperta ="portaVA";
        }
    }
     
    // SET //////////////////////////////////
    /**
     * Apre la porta e ne cambia l'immagine
     */
    public void apri(){
            aperta=true;
            setPathImage(pathPortaAperta);
            messaggio = null;
    }
     
    // GET //////////////////////////////////
    /**
     * Restituisce l'id della chiave necessaria per l'apertura
     * @return
     */
    public int id(){return id;}
    /**
     * Restituisce true se la porta è chiusa
     * @return true se la porta è chiusa
     * <br>false altrimenti
     */
    public boolean isClosed(){return !aperta;}
 
    // OVERRIDE //////////////////////////////
    /**
     * Restituisce il messaggio tutorial
     */
    public Messaggio getMessaggio(){
        return messaggio;
         
    }
    /**
     * Restituisce true se la porta è aperta
     * @return true se la porta è aperta
     * <br> false altrimenti
     */
    public boolean azionato(){return aperta;}
    /**
     * Apre la porta se il personaggio passato come parametro possiede la chiave necessaria
     * @throws IllegalArgumentException se lo Sprite in posizione 0 dell'array non è un Pg
     * @return true se il personaggio ha la chiave necessaria(aprendo la porta)
     * <br>false altrimenti
     */
    public boolean aziona(Sprite... s) {
        if(!(s[0] instanceof Pg))throw new IllegalArgumentException("Per azionare la porta serve un pg");
        if(id==-1){apri(); return true;}
        if(!aperta){
            Pg pg = (Pg)s[0];
            if(pg.apre(this)){
                apri();
                if(DEBUG){System.out.println("Porta.aziona. Hai aperto la porta");}
                return true;
            }
            if(DEBUG){System.out.println("Porta.aziona. Non hai aperto la porta");}
            return false;
        }
        if(DEBUG){System.out.println("Porta.aziona. Hai aperto la porta");}
        return true;
     
    }
     
    // OVERRIDE OBJ /////////////////////////
    public String toString(){
       return "porta";
    }
 
}