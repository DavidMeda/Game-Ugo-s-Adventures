package oggetti_di_gioco.danneggiabili;
 
import oggetti_di_gioco.Sprite;
 
/**
 * Gli Sprite animati possono muoversi all'interno dell'area di gioco
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public interface Animato extends Sprite {
    /**
     * Regole da seguire per il movimento
     */
    void AI();
}