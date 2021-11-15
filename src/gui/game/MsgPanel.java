package gui.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;

import gui.supporto.Messaggio;
import oggetti_di_gioco.Pg;

public class MsgPanel extends JComponent{
	private static final long serialVersionUID = -6794738712812551146L;
	
	String invio = "INVIO >> continua";
	MsgBox msg = new MsgBox();
	Pg pg;
	JTextField avanza = new JTextField();
	{// set textfield
	avanza.setEditable(false);
	avanza.setHorizontalAlignment(JTextField.CENTER);
	avanza.setPreferredSize(new Dimension(50,50));
	}
		
	// COSTR ////////////////////////////
	public MsgPanel(Pg pg){
		this.pg = pg;
		setMessaggio(this.pg.getMessaggio());
		setLayout(new BorderLayout());
		add(msg, BorderLayout.CENTER);
		add(avanza, BorderLayout.SOUTH);
		
	}
	
	// SET //////////////////////////////
	public void setMessaggio(Messaggio mess){
		if(mess==null)return;
		msg.setMessaggio(mess);
		avanza.setText(invio);
		
	}
	
	// METHODS /////////////////////////
	public void nextText(){
		msg.nextText();
		if(msg.getText().equals("")){
			avanza.setText("");
		}
		else {
			avanza.setText(invio);
		}
	}
}
