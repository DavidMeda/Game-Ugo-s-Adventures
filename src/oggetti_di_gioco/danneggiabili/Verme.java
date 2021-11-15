package oggetti_di_gioco.danneggiabili;

import mappe.Mappa;
import oggetti_di_gioco.Muro;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import oggetti_di_gioco.azionabili.Porta;

public class Verme extends Nemico implements Animato, Danneggiabile{
	private static final long serialVersionUID = -4905112503060810403L;

	public Verme(Nemico v, Pg pg){
		super();
		bordi.setFrame(x,y+15,60,30);
		setPathImage("verme");
		setIJ(v.i(), v.j());
		this.pg = pg;
		
	}
	public Verme(){
		super();
		bordi.setFrame(x,y+15,60,30);
		setPathImage("verme");
	
	}
	// OVERRIDE ///////////////////////////
	public boolean attacca(){
		if(collide(pg)){
			pg.riceviDanno(1);
			return true;
			}
		return false;
		
	}
	public void AI(){
		int dx = pg.x()-x;
		int dy = pg.y()-y;
		
		attacca();
		
		if(dx==0 || dy==0){return;} //divisione per 0 non consentita
		if(Math.abs(dx)>Math.abs(dy)){ //spostamento orizzontale
			int nx = 0;
			int ni = 0;
			if(dx!=0){
				nx = x+(dx/Math.abs(dx)); // nuova x +- 1
				ni = nx/60; //nuova i
			}
			if(ni < 0 || ni > 20)return; //controlli range
			Mappa m = pg.getMappa();
			Sprite s = m.get((y+30)/60, (nx+30)/60); //sprite in posizione i,j
			
			if(s instanceof Porta){//porta aperta
				if(!((Porta)s).isClosed()){ 
					if(nx!=0) setX(nx); //sposta verme, return
					return;
				}else return;
			}else if(!(s instanceof Muro)){ // s non è un muro
				if(nx!=0) setX(nx); //sposta verme, return
				return;
			}else if(s instanceof Muro){ // s è un muro
				nx = x; //controllo spostamento verticale
				int ny = 0;
				int nj = 0;
				if(dy!=0){
					ny = y+(dy/Math.abs(dy)); // nuova y +-1
					nj = ny/60; //nuova j
				}
				if(nj < 0 || nj > 9)return; //controlli range
				s = m.get((ny+30)/60, (x+30)/60);//sprite in posizione i,j
				if(s instanceof Porta){//porta aperta
					if(!((Porta)s).isClosed()){
						if(ny!=0) setY(ny);//sposta verme, return
						return;
					}else return;
				}else if(!(s instanceof Muro)){//non è un muro
					if(ny!=0) setY(ny);//sposta verme, return
					return;
				}else if(s instanceof Muro){//è un muro
					return;
				}
				
			}
			
		}else if (Math.abs(dx)<Math.abs(dy)){ //spostamento verticale
			int ny = 0;
			int nj = 0;
			if(dy!=0){
				ny = y+(dy/Math.abs(dy)); // nuova y +-1
				nj = ny/60; //nuova j
			}
			if(nj < 0 || nj > 9)return;//controlli range
			Mappa m = pg.getMappa();
			Sprite s = m.get((ny+30)/60, (x+30)/60);//sprite in posizione i,j
			if(s instanceof Porta){//porta aperta
				if(!((Porta)s).isClosed()){ 
					setY(ny);//sposta verme, return
					return;
				}else return;//porta chiusa, return
			
			}else if(!(s instanceof Muro)){//non è un muro
				setY(ny);//sposta verme, return
				return;
			
			}else if(s instanceof Muro){//è un muro
				int nx = 0;
				int ni = 0;
				if(dx!=0){
					nx = x+(dx/Math.abs(dx)); // nuova x +-1
					ni = nx/60; //nuova i
				}
				if(ni < 0 || ni > 20)return; //controlli range
				s = m.get((y+30)/60, (nx+30)/60); //sprite posizione i,j
				if(s instanceof Porta){//porta aperta
					if(!((Porta)s).isClosed()){ 
						if(nx!=0)setX(nx);//sposta verme, return
						return;
					}else return;//porta chiusa, return
				}else if(!(s instanceof Muro)){//non è un muro
					setX(nx);//sposta verme, return
					return;
				}else if(s instanceof Muro){
					return; //muro, return
				}
			}
		}else{ //spostamento diagonale
			int nx=0;
			int ny=0;
			if(dx!=0 && dy!=0){
				nx = x+(dx/Math.abs(dx)); // nuova x
				ny = y+(dy/Math.abs(dy)); // nuova y
			}
			Mappa m = pg.getMappa();
			Sprite s = m.get((ny+30)/60, (nx+30)/60);
			if(s instanceof Muro || s instanceof Porta)return;
			if(nx!=0 && ny!=0) setXY(nx,ny);
			
		}
	}

		

	// OVERRIDE OBJ ///////////////////////
	public String toString(){return "~ ";}
}