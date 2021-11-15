package gui.game;

import java.awt.Graphics;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

import oggetti_di_gioco.Pg;
import oggetti_di_gioco.armi.Arma;
import oggetti_di_gioco.item.Chiave;
import oggetti_di_gioco.item.Item;

public class InventarioPanel extends JPanel{
	private static final long serialVersionUID = 8727791726167233723L;
	private Pg pg;
	private JEditorPane inv = new JEditorPane(); 

	public InventarioPanel(Pg pg){
		this.pg = pg;
		inv.setEnabled(false);
		inv.setEditable(false);
		inv.setBounds(0, 0, 50, 600);
		inv.setContentType("text/html");
		inv.setText("INVENTARIO");
		add(inv);
	}
	
	public void paintComponent(Graphics g){
		StringBuilder sb = new StringBuilder();
		sb.append("<font size=\"4\" color=black>"
				+ "<center>"
				+ "<strong>  "
				+ "INVENTARIO<br>");
		if(pg.getChiavi().size()>0)sb.append("------------------------------<br>CHIAVI<br>");
		for(Chiave c : pg.getChiavi()){sb.append(c+"<br>");}
		
		if(pg.getArmi().size()>0)sb.append("------------------------------<br>ARMI<br>");
		for(Arma c : pg.getArmi()){sb.append(c+"<br>");}

		if(pg.getInventario().size()>0)sb.append("------------------------------<br>OGGETTI<br>");
		for(Item c : pg.getInventario()){sb.append(c+"<br>");}
		
		sb.append("------------------------------ "
				+ "</strong>"
				+ "</center>");
		
		inv.setText(sb.toString());


			
	}

}
