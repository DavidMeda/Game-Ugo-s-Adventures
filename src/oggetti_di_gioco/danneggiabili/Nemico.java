package oggetti_di_gioco.danneggiabili;

import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite_AB;

public abstract class Nemico extends Sprite_AB implements Danneggiabile, Animato {
	private static final long serialVersionUID = 1425904617390744133L;
	
	protected Pg pg;
	protected int attacco, hp, mp;
	protected char direzione;
	
	// COSTR //////////////////////
	public Nemico(){
		priorita=2;
	}
	
	// ABSTRACT ///////////////////
	public abstract void AI();
	public abstract boolean attacca();
	
	// SET ///////////////////////
	public void setPg(Pg p){pg = p;}
	
	// OVERRIDE //////////////////
	public void riceviDanno(int n) {
		hp-=n;

	}
	public boolean morto(){
		boolean morto = hp<=0;
		if(morto)pg.statistiche().uccisione();
		return morto;
	}
	
}
