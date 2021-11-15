package oggetti_di_gioco;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JComponent;

import gui.supporto.Animazione;
import gui.supporto.Messaggio;
import mappe.Mappa;
import oggetti_di_gioco.armi.Arma;
import oggetti_di_gioco.azionabili.Azionabile;
import oggetti_di_gioco.azionabili.Png;
import oggetti_di_gioco.azionabili.Porta;
import oggetti_di_gioco.azionabili.Scrigno;
import oggetti_di_gioco.danneggiabili.Danneggiabile;
import oggetti_di_gioco.item.Chiave;
import oggetti_di_gioco.item.Item;
import oggetti_di_gioco.item.Pozione;
import statistiche.Statistica;
import supporto.NonCalpestabile;

/**
 * Pg è la classe dedicata al personaggio principale UGO
 *
 */
public class Pg extends Sprite_AB implements Danneggiabile{
    private static final long serialVersionUID = -6795733447927282704L;
     
    // COSTR /////////////////////////////
    /**
     * Crea un oggetto Pg 
     * <br> vengono caricate le immagini del personaggio
     * e assegnata la priorità della coda
     * 
     */
    public Pg(){
    	mandaMessaggio(
    		"Premi i tasti W A S D per muoverti",
    		"Premi ESC per mettere in pausa il gioco",
    		"Tieni premuto qualsiasi altro tasto per ballare");
        
        setCollezione(Sprite.loadImg("pg"));
        creaAnimazioni();
        setImg(camminataDx.get(0));
        priorita=2; 					//posizione coda
        bordi.setFrame(x+15, y ,30,60); //bordi collisione
        
    }
     
    ////////////////////////////////////////////////////////////
    // LOGICA //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////
     
    private Mappa mappa;
    /**
     * hp: indica il livello di energia del pg
     * <br><strong>armi:</strong> indica una collezione di armi che il pg può avere
     * <br><strong>chiavi:</strong> indica una collezione di chiavi che il pg può avere nell'inventario
     * <br><strong>inventario:</strong> indica la collezione di Item che il pg può raccogliere
     * <br><strong>pannelloMinigame:</strong> contiene il pannello dei vari minigiochi da sostituire al pannello del gioco principale quando si entra nel minigioco
     *  
     */
    private int hp, difficolta, velocita = 6;
    private Arma arma;
    private Statistica stat = new Statistica();
    private char direzione = 'd';
    private LinkedList<Arma> armi = new LinkedList<>();
    private LinkedList<Chiave> chiavi = new LinkedList<>();
    private LinkedList<Item> inventario = new LinkedList<>();
    private Messaggio messaggio;
    private boolean pausa, attacca, hasMessage;
     
    private JComponent pannelloMinigame;
     
     
    // GET //////////////////////////////
    public Messaggio getMessaggio(){
    	hasMessage = false;
    	return messaggio;
    }
    public LinkedList<Chiave> getChiavi(){return chiavi;}
    public int getHp(){return hp;}
    public int getDifficolta(){return difficolta;}
    public char getDirezione(){return direzione;}
    public boolean minigame(){return pausa;}
    public LinkedList<Item> getInventario(){return inventario;}
    public LinkedList<Arma> getArmi(){return armi;}
    public Statistica statistiche(){return stat;}
    public Mappa getMappa(){return mappa;}
    public Arma getArma(){return arma;}
    public String nextMsg(){return messaggio.next();}
    public JComponent getPanel(){return pannelloMinigame;}
    public void attacca(KeyEvent e){
        if(arma!=null){
            attacca = true;
            arma.attacca(e, this);
        }
         
    }
    public boolean inAttacco(){return attacca;}
     
    // SET //////////////////////////////
    public void setMessaggio(Messaggio mex){
    	if(mex!=null && !(messaggio.getProprietario() == mex.getProprietario())){
    		hasMessage=true;
    		messaggio = new Messaggio(mex);
    	}

    }
    public void setHp(int n){hp=n;}
    public void setArma(Arma a){arma=a;}
    public void setArmi(LinkedList<Arma> armi){this.armi=armi;}
    public void setChiavi(LinkedList<Chiave> chiavi){this.chiavi=chiavi;}
    public void setInventario(LinkedList<Item> inv){this.inventario=inv;}
    public void setStatistiche(Statistica stat){this.stat=stat;}
    public void resetHp(){hp = ((2-difficolta)*100)+50;}
    public void setPausa(boolean pause){pausa = pause;}
    public void setDirezione(char d){direzione=d;}
    public void setMappa(Mappa m){mappa=m;}
    public void exitMinigame(){pausa=false;}
    public void setDifficolta(int n){
        difficolta = n;
        hp = ((2-difficolta)*100)+50;
    }
     
    // METHODS //////////////////////////
    /**
     * Questo metodo nasconde l'immagine della spada se è stata usata
     * 
     */
    public void rimuoviAttacco(){
    	if(attacca){
    		//rimuovi attacco
    		mappa.removeImage(arma);
    		attacca = false;
    		arma.setInAttacco(false);
    	}else if(arma!=null){arma.setIJ(-1, -1);}
    
    }
    /**
     * Questo metodo crea un oggetto messaggio assegnandogli message come stringa
     * <br>impostando il pg come proprietario 
     * @param message stringa di messaggi
     * @return messaggi
     */
    public Messaggio mandaMessaggio(String...message){
        messaggio = new Messaggio(message);
        messaggio.setProprietario(this);
        hasMessage = true;
        return messaggio;
    }
    /**
     * Questo metodo usa una pozione, se è presente nell'inventario, per aumentare l'energia del personaggio
     */
    public void curati(){
    	if(inventario.remove(new Pozione())){
    		hp+=100;
    		mandaMessaggio("Hai usato una pozione");
    	}
    	
    }
    /**
     * 
     */
    public void preparaSalvataggio(){
        super.preparaSalvataggio();
        messaggio = null;
         
    }
    
    /**
     * 
     */
    public void caricaSalvataggio(){
        super.caricaSalvataggio();
        setCollezione(Sprite.loadImg("pg"));
     
    }
    public boolean hasMessage(){return hasMessage;}
    
    //MUOVI    
    /**
     * Questo metodo gestisce il movimento del pg
     * <br>In base alla direzione, specificata dal parametro "char d", viene assegnata l'animazione del movimento al pg;
     * <br>se il pg ha la stessa posizione di un item lo raccoglie o se è in prossimità di un warp cambia mappa
     * <br>
     * @param d direzione del pg
     */
    public void muovi(char d){
    	if(pausa)return;
    	
    	rimuoviAttacco();
    	
    	switch(d){
        		case 'w' : animazioneCorrente = camminaSp; direzione=d; break;
        		case 'a' : animazioneCorrente = camminaSx; direzione=d; break;
        		case 's' : animazioneCorrente = camminaSt; direzione=d; break;
        		case 'd' : animazioneCorrente = camminaDx; direzione=d; break;
        		case 'f' : return;
        		//balletto
        		default : animazioneCorrente = camminaSt; setImg(); return;
        }
        //prossimo frame animazione -
        setImg();
        //??
        prendi();
        //controllo cambio mappa
        warp();
    	
        try{
        	/////////////
        	int direzioneX = 0;
        	int direzioneY = 0;
        	switch(direzione){
        	case 'w' : direzioneY--; break;
        	case 'a' : direzioneX--; break;
        	case 's' : direzioneY++; break;
        	case 'd' : direzioneX++; break;
        	default: return;
        	}
        	
        	int nuovaI = i+direzioneY;
        	int nuovaJ = j+direzioneX;
        	
        	Sprite s = mappa.get(nuovaI, nuovaJ);
        	if(s==null){s = new Sfondo(0);}
        	//messaggio
        	if(s instanceof Messaggero){
        		setMessaggio(((Messaggero)s).getMessaggio());
        	}
        	
        	//muro
        	if(s instanceof NonCalpestabile || s instanceof Scrigno){
        		if(collide(s))return;
        	}
        	//porta
        	else if(s instanceof Porta){
    			Porta p = (Porta)s;
    			if (p.isClosed() && collide(p)){
    				return;
    			}
    		}
        	x=x+(velocita*direzioneX);
        	y=y+(velocita*direzioneY);
        	setXY(x,y);
        	stat.passo();
        	
        	System.gc();
        	
        }
        catch(IndexOutOfBoundsException e){System.out.println("Ops**");}
        
    }
    /**
     * Questo metodo gestisce il cambio mappa e lo spawn del pg
     */
    public void warp(){
    	Sprite s = mappa.get(i, j);
    	
    	try{
    	if(s instanceof Warp){
			((Warp)s).setSpawn(mappa);
    	}}catch(IllegalArgumentException e){
    		switch(direzione){
    		case 'w': setI(i+1); break;
    		case 'a': setJ(j+1); break;
    		case 's': setI(i-1); break;
    		case 'd': setJ(j-1); break;
    		}
    	}
 
    }
    /**
     * Questo metodo gestisce le operazioni degli Azionabili
     * @return true se viene azionato l'oggetto
     * <br>false altrimenti
     */
    public boolean aziona(){
        Sprite s = null;
        switch(direzione){
        case 'w' : if(i>0) s = mappa.get(i-1, j); break;
        case 'a' : if(j>0) s = mappa.get(i, j-1); break;
        case 's' : if(i<9) s = mappa.get(i+1, j); break;
        case 'd' : if(j<20)s = mappa.get(i, j+1); break;
        }
        if(s instanceof Png){
            pausa=true;
            pannelloMinigame = ((Png)s).getPanel();
        }
        if(aziona(s))return true;
        return false;
     
    }private boolean aziona(Sprite s){
        if(s instanceof Azionabile){
            Azionabile a = (Azionabile)s;
            if(a.aziona(this)){
            	return true;
            }
 
        }
        return false;
     
    }
    /**
     * Questo metodo gestisce l'apertura di porte e scrigni se si possiede la chieve corrispondente 
     * @param s Sprite da aprire
     * @return true se viene aperto lo Sprite
     * <br>false altrimenti
     */
    public boolean apre(Sprite s){
        if(s instanceof Porta){
            Porta p = (Porta)s;
            for(Chiave c : chiavi){
                if(c.id()==p.id()){
                    return chiavi.remove(c);
                }
            }
        }else if(s instanceof Scrigno){
            Scrigno p = (Scrigno)s;
            for(Chiave c : chiavi){
                if(c.id()==p.id()){
                    return chiavi.remove(c);
                }
            }
        }
        return false;
     
    }
    /**
     * Questo metodo serve per raccogliere gli oggetti che il pg incontra sulla mappa 
     * <br> inserendoli nella lista corrispondente
     */
    public void prendi(){
        Sprite s = mappa.get(i, j);
        if(s instanceof Item){
        	String mex = "Hai preso: "+s;
            
            if(s instanceof Chiave){
            	prendiChiave((Chiave)s);
                mandaMessaggio(mex);
                return;
            }
            else if(s instanceof Arma){
            	prendiArma((Arma)s);

            }
            else if(s instanceof Pozione){
                mex+=" ( premi \"P\" per usarla )";
            }
            
            mandaMessaggio(mex);
            prendiItem((Item)s);
        }
    } private void prendiChiave(Chiave c){
        chiavi.add(c);
        mappa.remove(c);
        mappa.getLivello().despawn(c);
        //set X e Y per stampa a video inventario
        c.setX(1380);
        int numeroElem = chiavi.size()+armi.size()+inventario.size();
        c.setY((numeroElem*65)+20);
    } private void prendiArma(Arma a){
        if(arma==null){
            arma=a;
            mappa.remove(a);
            mappa.getLivello().despawn(a);
            //set X e Y per stampa a video inventario
            //a.setX(1380);
            //
            //int numeroElem = chiavi.size()+armi.size()+inventario.size();
            //a.setY((numeroElem*65)+20);
        
        }else{
            armi.add(a);
            mappa.remove(a);
            mappa.getLivello().despawn(a);
            //set X e Y per stampa a video inventario
            //a.setX(1380);
            //int numeroElem = chiavi.size()+armi.size()+inventario.size();
            //a.setY((numeroElem*65)+20);
        }
    } private void prendiItem(Item i){
        inventario.add(i);
        mappa.remove(i);
        mappa.getLivello().despawn(i);
        //set X e Y per stampa a video inventario
        //i.setX(1380);
        //int numeroElem = chiavi.size()+armi.size()+inventario.size();
        //i.setY((numeroElem*65)+20);
    }
     
    // OVERRIDE ///////////////////////////
    /**
     * 
     */
    public void riceviDanno(int n){hp-=n;}
    public boolean morto(){
        if(hp<=0){
            stat.morto();
            return true;
        }
        return false;
    }
     
    // OVERRIDE OBJ ///////////////////////
    public String toString(){return "P ";}
     
     
    ///////////////////////////////////////////////////
    // GRAFICA ////////////////////////////////////////
    ///////////////////////////////////////////////////
    /**
     * Questo metodo crea le liste contenenti le immagini del pg 
     */
    private LinkedList<BufferedImage>  
    camminataDx = new LinkedList<>(), 
    camminataSx = new LinkedList<>(), 
    camminataSp = new LinkedList<>(), 
    camminataSt = new LinkedList<>(),
//  attaccataDx = new LinkedList<>(),
//  attaccataSx = new LinkedList<>(),
    fermataDx = new LinkedList<>(),
    fermataSx = new LinkedList<>();
    /**
     * Questo metodo crea le animazioni del movimento del pg  
     */
    private Animazione
//  fermaDx = new Animazione(fermataDx, 200),
//  fermaSx = new Animazione(fermataSx, 200),
    camminaDx = new Animazione(camminataDx, velocita*22), 
    camminaSp = new Animazione(camminataSp, velocita*34),
    camminaSx = new Animazione(camminataSx, velocita*22), 
    camminaSt = new Animazione(camminataSt, velocita*34);
     
    private Animazione animazioneCorrente = camminaDx;
     
    /**
     * Questo metodo aggiunge le immagini alle liste dei movimenti
     */
    private void creaAnimazioni(){ 
        camminataDx.add(Animazione.getFrame(this,0,0));
        camminataDx.add(Animazione.getFrame(this,1,0));
        camminataDx.add(Animazione.getFrame(this,2,0));
        camminataDx.add(Animazione.getFrame(this,3,0));
         
        camminataSx.add(Animazione.getFrame(this,0,1));
        camminataSx.add(Animazione.getFrame(this,1,1));
        camminataSx.add(Animazione.getFrame(this,2,1));
        camminataSx.add(Animazione.getFrame(this,3,1));
         
        camminataSp.add(Animazione.getFrame(this,0,3));
        camminataSp.add(Animazione.getFrame(this,1,3));
         
        camminataSt.add(Animazione.getFrame(this,2,3));
        camminataSt.add(Animazione.getFrame(this,3,3));
         
        fermataDx.add(Animazione.getFrame(this, 0,2));
     
        fermataSx.add(Animazione.getFrame(this, 1,2));
         
//      attaccaDx.add(Animazione.getFrame(this, 2,2));
         
//      attaccaSx.d(Animazione.getFrame(this, 3,2));
         
    }
    /**
     * Questo metodo scorre le immagini nella lista delle animazioni  
     */
    public void setImg(){
        setImg(animazioneCorrente.nextFrame());
    }
     
 
     
 /*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*//*/*/
{;}{;}{;}{;}{;}{;}{;}{;}{;}{;}{;}{;}{;}{;}
}