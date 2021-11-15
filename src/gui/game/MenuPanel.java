package gui.game;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.button.Bottoni;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 2376337969155928554L;
	
	private JButton 
	esci = Bottoni.quit,
	salva = Bottoni.salva,
	stat = Bottoni.statistiche,
	pausa = Bottoni.pausa;
	
	

	public MenuPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		salva.setPreferredSize(new Dimension(50,50));
		esci.setPreferredSize(new Dimension(10,50));
		stat.setPreferredSize(new Dimension(10,50));
		add(esci);
		add(salva);
		add(stat);
		add(pausa);
		
	}



	
	
}
