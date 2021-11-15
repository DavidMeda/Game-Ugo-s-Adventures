package supporto;

import oggetti_di_gioco.Sprite_AB;

public class NonCalpestabile extends Sprite_AB{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7317395189120530962L;
	private boolean calpestabile = false;
	
	// COSTR ///////////////////////////////
	public NonCalpestabile(){}
	
	// GET /////////////////////////////////
	public boolean calpestabile(){return calpestabile;}
	
	// SET /////////////////////////////////
	public void setCalpestabile(boolean b){calpestabile=b;}
	public void inverti(){calpestabile =! calpestabile;}
}
