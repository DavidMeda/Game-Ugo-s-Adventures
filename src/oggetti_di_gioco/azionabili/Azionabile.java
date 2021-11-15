package oggetti_di_gioco.azionabili;
 
import oggetti_di_gioco.Messaggero;
import oggetti_di_gioco.Sprite;
 
/**
 * Gli sprite che implementano questa interfaccia compieranno azioni particolari
 * <br>alla pressione del tasto "F"
 * @author Capa00
 *
 */
public interface Azionabile extends Sprite, Messaggero{
    /**
     * Prova ad azionare l'oggetto
     * @param sprites sprites necessari per l'azionamento dell'oggetto
     * @return true se l'oggetto è stato azionato
     * <br>false altrimenti
     */
    boolean aziona(Sprite...sprites);
    /**
     * Restituisce true se l'oggetto è stato azionato in precedenza
     * @return true se l'oggetto è stato azionato in precedenza
     * <br>false altrimenti
     */
    boolean azionato();
}