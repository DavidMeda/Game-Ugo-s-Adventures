
package oggetti_di_gioco.item;

public class Chiave extends Item{
	private static final long serialVersionUID = 5316919993873969841L;
	private int id;
	
	public Chiave(int idChiave){
		super();
		id = idChiave;
		setPathImage("chiave0");
	}
	
	// GET /////////////////////
	public int id(){return id;}
	
	// OVERRIDE OBJ ///////////////////////
	public String toString(){return "Chiave "+id;}
		
}
