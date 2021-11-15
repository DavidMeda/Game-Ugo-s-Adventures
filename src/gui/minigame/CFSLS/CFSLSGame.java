package gui.minigame.CFSLS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

import gui.button.Bottoni;
import minigame.CFSLS.CartaForbiceSassoLizardSpock;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import statistiche.Classifica;
import statistiche.Posizione;

public class CFSLSGame extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1403308682472139499L;
	private JPanel 
	panel_1 = new JPanel(),
	panel_2 = new JPanel(),
	roundPanel;
	private JTextArea risultati = new JTextArea();
	private JEditorPane esito = new JTextPane();
	private CartaForbiceSassoLizardSpock gioco;
	private Pg pg;
	
	private JToggleButton 
	carta = new JToggleButton((new ImageIcon(Sprite.class.getResource("/carta.png")))),
	forbici = new JToggleButton((new ImageIcon(Sprite.class.getResource("/forbice.png")))),
	sasso = new JToggleButton((new ImageIcon(Sprite.class.getResource("/sasso.png")))),
	lizard = new JToggleButton((new ImageIcon(Sprite.class.getResource("/lizard.png")))),
	spock = new JToggleButton((new ImageIcon(Sprite.class.getResource("/spock.png"))));
	
	public CFSLSGame(Pg pg) {
		this.pg = pg;
		gioco = new CartaForbiceSassoLizardSpock();
		gioco.scegliLivello(pg.getDifficolta());
		
		carta.addActionListener(this);
		forbici.addActionListener(this);
		sasso.addActionListener(this);
		lizard.addActionListener(this);
		spock.addActionListener(this);
		
		roundPanel = new RoundPanel(gioco);
		
		Font bigFont1=Bottoni.esci.getFont().deriveFont(20f);
		Bottoni.esci.setFont(bigFont1);
		
		esito.setSize(1000, 200);
		Font bigFont2=esito.getFont().deriveFont(30f);
		esito.setFont(bigFont2);
		
		carta.setSelectedIcon(new ImageIcon(getClass().getResource("/carta schiacciato.png")));
		forbici.setSelectedIcon(new ImageIcon(getClass().getResource("/forbice schiacciato.png")));
		sasso.setSelectedIcon(new ImageIcon(getClass().getResource("/sasso schiacciato.png")));
		lizard.setSelectedIcon(new ImageIcon(getClass().getResource("/lizard schiacciato.png")));
		spock.setSelectedIcon(new ImageIcon(getClass().getResource("/spock schiacciato.png")));
		
		
		carta.setContentAreaFilled(false);
		carta.setBorderPainted(false);
		carta.setFocusPainted(false);
		
		forbici.setContentAreaFilled(false);
		forbici.setBorderPainted(false);
		forbici.setFocusPainted(false);
		
		sasso.setContentAreaFilled(false);
		sasso.setBorderPainted(false);
		sasso.setFocusPainted(false);
		
		lizard.setContentAreaFilled(false);
		lizard.setBorderPainted(false);
		lizard.setFocusPainted(false);
		
		spock.setContentAreaFilled(false);
		spock.setBorderPainted(false);
		spock.setFocusPainted(false);
		
		setLayout(new BorderLayout(0, 0));
		Component verticalStrut = Box.createVerticalStrut(120);
		Component verticalStrut_1 = Box.createVerticalStrut(80);
		
		JPanel panel = new JPanel();
		add(roundPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1,7,1,1));
		
		esito.setContentType("text/html");
		
		int larghezza = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		panel.setPreferredSize(new Dimension(larghezza,200));
		
		panel.add(verticalStrut);
		panel.add(carta);		
		panel.add(forbici);
		panel.add(sasso);
		panel.add(lizard);
		panel.add(spock);
		
		panel.add(verticalStrut_1);
		
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(esito, BorderLayout.SOUTH);
		Component horizontalStrut = Box.createHorizontalStrut(40);
		panel_2.add(panel_1, BorderLayout.NORTH);
		panel_1.add(risultati);
		panel_1.add(horizontalStrut);
		panel_1.add(Bottoni.esci);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.replace(0, sb.length(), "CARTA FORBICE SASSO LIZARD SPOCK\n --------------------------------\n "
				+ "Lo scopo del gioco è scegliere un segno capace di annientare quello scelto da Rinco Babbo.\n"
				+ "Questo gioco è un'estensione del classico carta forbice sasso creato per avere più segni da lanciare a disposizione.\n"
				+ "\nEcco le regole:\n"
				+ "1. Le forbici tagliano la carta\n"
				+ "2. La carta avvolge il sasso\n"
				+ "3. Il sasso uccide Lizard\n"
				+ "4. Lizard avvelena Spock\n"
				+ "5. Spock rompe le forbici\n"
				+ "6. Le forbici decapitano Lizard\n"
				+ "7. Lizard mangia la carta\n"
				+ "8. La carta invalida Spock\n"
				+ "9. Spock vaporizza il sasso\n"
				+ "10. E, come è sempre stato, il sasso rompe le forbici\n"
				+ "Si vince quando si ottengono "+gioco.getDifficolta()+" vittorie, su 10 mani giocate.");
		return sb.toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e .getSource();
		if(o == carta){
			gioco.scegli(0);
			gioco.AI();
			repaint();
		
		}else if(o == forbici){
			gioco.scegli(1);
			gioco.AI();
			repaint();
		
		}else if(o == sasso){
			gioco.scegli(2);
			gioco.AI();
			repaint();
		
		}else if(o == lizard){
			gioco.scegli(3);
			gioco.AI();
			repaint();
		
		}else if(o == spock){
			gioco.scegli(4);
			gioco.AI();
			repaint();
		}
		String s1 = "";
		String s2 = gioco.risultati();
		if(!s2.equals("Pareggio")){
			if(gioco.aggiornaPunteggio()){s1 = "Hai vinto!!";}
			else s1 = "Hai perso!!";
		}else gioco.aggiornaPunteggio();
		
		esito.setText("<center>"+s1+s2+"</center>");
		risultati.setText("Partite vinte: "+gioco.getPunteggio()+" su "+gioco.getPartite());
		
		if(gioco.getPartite() == 10){
			
			carta.removeActionListener(this);
			forbici.removeActionListener(this);
			sasso.removeActionListener(this);
			lizard.removeActionListener(this);
			spock.removeActionListener(this);

			if(gioco.winOrLoose()){
				esito.setText("<center>Hai vinto il minigioco</center>");
				Classifica CFSLS = pg.statistiche().getClassifica("cfsls");
				if(CFSLS.newRecord(gioco.getPunteggio())){
					String s = (String)JOptionPane.showInputDialog(null,"Nuovo RECORD!\nCome ti chiami");
					CFSLS.addRecord(new Posizione(s,gioco.getPunteggio()));
				}
			}else 
				esito.setText("<center>Hai perso il minigioco</center>");
		}
		
	}
	
}
