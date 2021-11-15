package oggetti_di_gioco;

import gui.supporto.Messaggio;
import supporto.NonCalpestabile;

public class Cartello extends NonCalpestabile implements Messaggero{
	private static final long serialVersionUID = -8361475485668338882L;
	private Messaggio msg;
	
	public Cartello(String...messaggi){
		setPathImage("muro23");
		setCalpestabile(true);
		priorita = 3;
		msg = new Messaggio(messaggi);
		msg.setProprietario(this);
		
	}
	
	public Messaggio getMessaggio(){return msg;}

}
