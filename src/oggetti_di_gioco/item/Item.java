package oggetti_di_gioco.item;

import oggetti_di_gioco.Sprite_AB;

public abstract class Item extends Sprite_AB{
	private static final long serialVersionUID = -5755067408696550032L;
	private boolean raccolto = false;
	
	// COSTR ///////////////////////////////
	public Item(){
		priorita=1;
	}
	public Item(int i, int j){
		super(i,j);
		priorita=1;
		}
	
	// METHODS /////////////////////////////
	public void raccogli(){raccolto = true;}
	public boolean raccolto(){return raccolto;}
	
	// STATICS /////////////////////////////
	public static Item getItem(int id){
		switch(id){
			case 0 : return new Chiave(0);
			
			default : throw new QualcuadraNonQosa("Item");
		}
	}
	
	private static class QualcuadraNonQosa extends RuntimeException {private static final long serialVersionUID = 1L;public QualcuadraNonQosa(String s){super(s);}}
}
