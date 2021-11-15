package oggetti_di_gioco;

public class Sfondo extends Sprite_AB{
	private static final long serialVersionUID = -8769283862770937516L;
	
	public Sfondo(){priorita = 0;}
	public Sfondo(int n){
		setPathImage("sfondo"+n);
		
		priorita=0;
	}
	
	public String toString(){return " .";}
	
}
