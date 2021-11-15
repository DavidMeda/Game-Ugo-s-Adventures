package oggetti_di_gioco.azionabili;
 
import javax.swing.JComponent;

import gui.minigame.CFSLS.CFSLSGame;
import gui.minigame.annegato.AnnegatoGame;
import gui.minigame.memory.MemoryPanel;
import gui.minigame.nfu.NFUPanel;
import gui.supporto.Messaggio;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import supporto.NonCalpestabile;
 
/**
 * I Png (acronimo di personaggio non giocanti) sono Sprite che azionabili che
 * <br>permetteranno il caricamento del pannello del minigioco a loro associato 
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public class Png extends NonCalpestabile implements Azionabile{
    private static final long serialVersionUID = -2392435801756034913L;
    /**
     * Istanza del personaggio indispensabile per la creazione dei pannelli dei minigiochi
     */
    private Pg pg;
    /**
     * Identificativo del minigioco associato
     */
    private int id;
    /**
     * Messaggio tutorial (spiega come lanciare il minigioco)
     */
    private Messaggio messaggio;
     
    /**
     * Crea un oggetto Png
     * @param pg personaggio principale
     * @param id identificativo del minigioco
     */
    public Png(Pg pg, int id){
        messaggio = new Messaggio("F >> Aziona");
        messaggio.setProprietario(this);
 
        setImg(Sprite.loadImg("png"+id));
        
        this.pg=pg;
        this.id=id;
        setCalpestabile(false);
        priorita = 1;
         
    }
    /**
     * Restituisce il JComponent(JPanel) del minigioco
     * @return pannello del minigioco associato
     */
    public JComponent getPanel(){
        switch(id){
            case 0 : return new NFUPanel(pg);
            case 1 : return new CFSLSGame(pg);
            case 2 : return new AnnegatoGame(pg);
            case 3 : return new MemoryPanel(pg);
            default: throw new IllegalArgumentException();
        }
 
    }
    /**
     * Restituisce il messaggio tutorial
     * @return messaggio tutorial
     */
    public Messaggio getMessaggio(){return messaggio;}
    /**
     * @deprecated 
     * <br>Inutile per gli oggetti Png
     */
    public boolean aziona(Sprite... sprites) {
        return true;
    }/**
     * @deprecated 
     * <br>Inutile per gli oggetti Png
     */
    public boolean azionato() {
        return false;
    }
     
    public String toString(){return "0"+id;}
}