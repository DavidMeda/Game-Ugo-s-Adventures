package gui.minigame.memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.button.Bottoni;
import minigame.memory.Carte;
import minigame.memory.Memory;
import oggetti_di_gioco.Pg;
import statistiche.Classifica;
import statistiche.Posizione;

public class MemoryPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3592105192281468266L;
	public Memory gioco;
	public JTextArea mes=new JTextArea();
	private JButton esci=Bottoni.esci;
	private int cont=0;
	private Pg pg;
    
	
	public MemoryPanel(Pg pg) {
		this.pg = pg;
		gioco = new Memory();
		setLayout(new BorderLayout());
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(6, 7, 10, 10));
		for(int i=0; i<5; i++)	{
			for(int j=0; j<6; j++){
				p.add(gioco.getCarta(i,j));
				gioco.getCarta(i,j).addActionListener(this);
			}
		}
		Font bigFont1=esci.getFont().deriveFont(20f);
		esci.setFont(bigFont1);
		p.setBackground(new Color(80,	200,	120));
		setBackground(new Color(80,	200,	120));
		mes.setEditable(false);
		mes.setEnabled(false);
		mes.setText("Trova le coppie uguali");
		mes.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		mes.setPreferredSize(new Dimension(700,150));
		mes.setBackground(new Color(247,	247,	247));
		add(mes, BorderLayout.SOUTH);
		add(p, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(esci,BorderLayout.CENTER);
		
		Component horizontalStrut = Box.createHorizontalStrut(150);
		panel.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(150);
		panel.add(horizontalStrut_1, BorderLayout.EAST);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.replace(0, sb.length(),"MEMORY GAME\n -----------\n"
				+ "Lo scopo del gioco è trovare tutte le coppie di carte uguali.\n"
				+ "Ugo scopre due carte, se sono uguali vengono bloccate,"
				+ "altrimenti vengono nuovamente coperte e rimesse nella loro posizione originale sul tavolo.\n"
				+ "Si vince quando tutte le carte saranno scoperte");
		return sb.toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		Carte carta=((Carte)o);
		if(!carta.visibilita())
			cont++;
		carta.gira().removeActionListener(this);
		if(cont==3){
			gioco.getCarteArray(0).rigira().addActionListener(this);
			gioco.getCarteArray(1).rigira().addActionListener(this);
			gioco.clearArray();
			cont=1;
			mes.setText("");
		}
		if(!carta.bloccata())
		mes.append("\nHai girato la carta: "+carta.getNumero());
		
		gioco.add(carta);
		
		if(gioco.carteSelezionate.size()==2){
			if(gioco.controlla()){
				gioco.getCarteArray(0).blocca().removeActionListener(this);
				gioco.getCarteArray(1).blocca().removeActionListener(this);
				mes.append("\nLe carte che hai girato SONO uguali!");
				
			}
			else mes.append("\nLe carte che hai girato NON SONO uguali!");
			
		}if(gioco.winOrLose()){
			mes.setText("Hai vinto!!");
			Classifica memory = pg.statistiche().getClassifica("memory");
			if(memory.newRecord(gioco.getTurno())){
				String s = (String)JOptionPane.showInputDialog(null,"Nuovo RECORD!\nCome ti chiami");
				memory.addRecord(new Posizione(s,gioco.getTurno()));
			}
		}
	}
		
	
}

