package gui.supporto;

import java.io.Serializable;
import java.util.LinkedList;

import oggetti_di_gioco.Sprite;

public class Messaggio implements Serializable{
	private static final long serialVersionUID = 8534786168567917958L;
	private LinkedList<String> msg = new LinkedList<>();
	private Sprite proprietario;
	
	// COSTR /////////////////////
	public Messaggio(){}
	public Messaggio(String...messaggi){
		for(String messaggio : messaggi)msg.addLast(messaggio);
	
	}
	public Messaggio(Messaggio mes){
		msg = new LinkedList<String>(mes.msg);
		proprietario = mes.proprietario;
		
	}
	
	// SET ///////////////////////
	public void setProprietario(Sprite s){
		proprietario = s;
		
	}
	public void add(String...messaggi){
		for(String messaggio : messaggi)msg.addLast(messaggio);

	}
	
	// GET ///////////////////
	public Sprite getProprietario(){return proprietario;}
	
	// METHODS /////////////////
	public String next(){
		if(!msg.isEmpty())return msg.removeFirst();
		else return "";
	}
	public int size(){return msg.size();}
	public boolean hasNext(){return msg.size()!=0;}
	
	// OVERRID OBJ ////////////////
	public String toString(){
		return msg.toString();
	}
}
