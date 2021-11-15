package gui.game;

import java.awt.Dimension;

import javax.swing.JEditorPane;

import gui.supporto.Messaggio;

public class MsgBox extends JEditorPane{
	private static final long serialVersionUID = -48535173754987059L;
	
	
	private Messaggio messaggi = new Messaggio();
	private String messaggio = "";
	private final Messaggio msgVuoto = new Messaggio("");
	
	// COSTR /////////////////////////
	public MsgBox(){
		setEditable(false);
		setFocusable(true);
		requestFocusInWindow();
		setContentType("text/html");
		setPreferredSize(new Dimension(50,85));
	
	}
	
	// GET ///////////////////////////
	public Messaggio getMsg(){
		return messaggi;
	}
	
	// SET ///////////////////////////
	public void setMessaggio(Messaggio mess){
		if(mess==null || mess.getProprietario() == messaggi.getProprietario()){return;}
		messaggi = mess;
		nextText();
		
	}
	
	// METHODS ///////////////////////
	public String getText(){return messaggio;}
	public void noMessage(){messaggi = msgVuoto;}
	public boolean nextText(){
		messaggio = messaggi.next();
		setText("<font size=\"6\"><center><strong>" + messaggio + "</strong></center>");
		return true;
	}
	
	
	public String toString(){return messaggi.toString();}
}
