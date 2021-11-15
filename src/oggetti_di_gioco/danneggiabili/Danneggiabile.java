package oggetti_di_gioco.danneggiabili;
 
import oggetti_di_gioco.Sprite;
 
/**
 * Le classi che implementano l'interfaccia Danneggiabile sono muniti di hp (healt point o punti vita)
 * se colpiti diminuiscono il loro numero di hp e "muoiono" nel momento in cui hp<=0
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public interface Danneggiabile extends Sprite {
    /**
     * Sottrae hp al Danneggiabile
     * @param n numero di ho da sottrarre
     */
    void riceviDanno(int n);
    /**
     * True se hp<=0
     * @return true se il danneggiabile è morto
     * <br>false altrimenti
     */
    boolean morto();
}