package oggetti_di_gioco.armi;
 
import java.awt.event.KeyEvent;

import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
 
/**
 * Arma di tipo Spada
 * @author Giuseppe Di Puglia Pugliese
 *
 */
public class Spada extends Arma {
    private static final long serialVersionUID = 4081432220334890262L;
     
    /**
     * Genera un oggetto spada
     * @param n identificativo spada (in questa versione del gioco esiste un 
     * <br>solo tipo di spada corrispondente all'id 0, imposta danno e raggio d'azione a 1)
     */
    public Spada(int n){
        setPathImage("spadaSX");
        switch(n){
            case 0 : raggioDazione = 1; danno = 1;
        }
    }
 
    // OVERRIDE ///////////////////////
    /**
     * Questo metodo permette la stampa a video della spada quando e ne abilita l'attacco
     */
    public void attacca(KeyEvent e, Pg pg) {
        int keyCode = e.getKeyCode();
         
        if(keyCode == KeyEvent.VK_LEFT){
            altezza = 35;
            larghezza = 60;
            setXY(pg.x()-30, pg.y());
            setImg(Sprite.loadImg("spadaSX"));
            if(!inAttacco){
                pg.getMappa().addImage(this);
                inAttacco = true;
            }       
        }else if(keyCode == KeyEvent.VK_UP){
            altezza = 60;
            larghezza = 35;
            setXY(pg.x(), pg.y()-30);
            setImg(Sprite.loadImg("spadaSP"));
            if(!inAttacco){
                pg.getMappa().addImage(this);
                inAttacco = true;
            }       
        }else if(keyCode == KeyEvent.VK_RIGHT){
            altezza = 35;
            larghezza = 60;
            setXY(pg.x()+30, pg.y());
            setImg(Sprite.loadImg("spadaDX"));
            if(!inAttacco){
                pg.getMappa().addImage(this);
                inAttacco = true;
            }       
        }else if(keyCode == KeyEvent.VK_DOWN){
            altezza = 60;
            larghezza = 35;
            setXY(pg.x(), pg.y()+30);
            setImg(Sprite.loadImg("spadaST"));
            if(!inAttacco){
                pg.getMappa().addImage(this);
                inAttacco = true;
            }
        }
    }
     
    // OVERRIDE OBJ ////////////////////
    public String toString(){return "Spada";}
 
}