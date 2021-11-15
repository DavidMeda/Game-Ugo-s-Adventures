package oggetti_di_gioco;
 
import java.io.Serializable;

import supporto.NonCalpestabile;
 
/**
 * I muri sono Sprite che non potranno essere attraversati dal personaggio
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public class Muro extends NonCalpestabile implements Serializable{
    private static final long serialVersionUID = -7971322914454869712L;
 
    /**
     * Genera un oggetto muro
     * @param n numero identificativo dell'immagine da utilizzare
     */
    public Muro(int n){
        priorita=3;
         
        altezza = 60;
        larghezza = 60;
        setPathImage("muro"+n);
        setCalpestabile(false);
 
    }
     
     
    public String toString(){return "XX";}
 
}