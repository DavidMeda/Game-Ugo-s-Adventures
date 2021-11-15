package gui.minigame.annegato;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import gui.button.Bottoni;
import minigame.annegato.Annegato;
import oggetti_di_gioco.Pg;
import statistiche.Classifica;
import statistiche.Posizione;

public class AnnegatoGame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5738550172657060025L;
	private Annegato gioco;
	private JPanel img;
	private JEditorPane parola=new JTextPane ();
	private JTextField testo=new JTextField ();
	private JTextField tentativi=new JTextField ("Tentativi=0");
	private JTextArea inserisci = new JTextArea("Inserisci la lettera o la parola qui -->");
	public JButton esci=Bottoni.esci;
	private JButton ok=new JButton("OK");
	private final Component horizontalStrut = Box.createHorizontalStrut(40);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(50);
	private Pg pg;
	
	public AnnegatoGame(Pg pg) {
		this.pg = pg;
		gioco=new Annegato(pg);
		img = new JPanel();
		parola.setEnabled(false);
		parola.setEditable(false);
		
		img = new AnimazioneAnnegato(gioco);
		parola.setContentType("text/html");
		tentativi.setHorizontalAlignment(SwingConstants.CENTER);
		tentativi.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		tentativi.setEditable(false);
		
		Font bigFont1=esci.getFont().deriveFont(20f);
		esci.setFont(bigFont1);
		
		testo.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		testo.addActionListener(this);
		
		ok.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		ok.addActionListener(this);
		
		inserisci.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		inserisci.setEditable(false);
		inserisci.setOpaque(false);
		inserisci.setColumns(10);
		
		parola.setText("<center>"+gioco.getWord()+"</center>");
		
		JPanel gruppo=new JPanel();
		gruppo.setLayout(new FlowLayout(FlowLayout.CENTER));
		testo.setColumns(10);
		gruppo.add(horizontalStrut);
		gruppo.add(inserisci);
		gruppo.add(testo);
		gruppo.add(ok);
		gruppo.add(horizontalStrut_1);
		gruppo.add(tentativi);
		

		////Iniz///
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, parola);
		add(BorderLayout.SOUTH, gruppo);
		add(BorderLayout.EAST,  esci);
		add(BorderLayout.CENTER, img);
	
	}
	
	 public String toString(){
	  StringBuilder sb = new StringBuilder();
	  
		sb.append("ANNEGATO GAME\n |-----------|\n Lo scopo del gioco è trovare la parola nascosta prima che Rinco Babbo, l'amico di Ugo, affondi."
			+ "\n Si inserisce una parola o lettera alla volta, "
			+ "se si sbaglia Rinco Babbo affonda.");	
    	return sb.toString();
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object O=e.getSource();
		if (O==ok) {
			if(testo.getText().length()==1)  
				gioco.cercaLettera(testo.getText().charAt(0));
			else
				gioco.cercaParola(testo.getText());
		
			parola.setText("<center>"+gioco.getWord()+"</center>");
			tentativi.setText("Tentativi="+gioco.getCont());
			
			if(!gioco.winOrLoose(gioco.getCont())){
				ok.removeActionListener(this);
				testo.setText("Hai perso!");
				testo.setEditable(false);
				return;
			}
			else if (gioco.trovaParola()) {
				testo.setText("Hai vinto!");
				testo.setEditable(false);
				ok.removeActionListener(this);
				gioco.alt=45;
				Classifica annegato = pg.statistiche().getClassifica("annegato");
				if(annegato.newRecord(gioco.getCont())){
					String s = (String)JOptionPane.showInputDialog(null,"Nuovo RECORD!\nCome ti chiami");
					annegato.addRecord(new Posizione(s,gioco.getCont()));
				}
				return;


			}
			testo.setText("");

		}
		if (O==testo){
			
			if(testo.getText().length()<=1)  
				gioco.cercaLettera(testo.getText().charAt(0));
			else 
				gioco.cercaParola(testo.getText());
			
			parola.setText("<center>"+gioco.getWord()+"</center>");
			tentativi.setText("Tentativi="+gioco.getCont());
			if(!gioco.winOrLoose(gioco.getCont())){
				ok.removeActionListener(this);
				testo.setText("Hai perso!");
				testo.setEditable(false);
				return;
			}
			else if (gioco.trovaParola()){
				ok.removeActionListener(this);
				testo.setText("Hai vinto!");
				testo.setEditable(false);
				gioco.alt=45;
				Classifica annegato = pg.statistiche().getClassifica("annegato");
				if(annegato.newRecord(gioco.getCont())){
					String s = (String)JOptionPane.showInputDialog(null,"Nuovo RECORD!\nCome ti chiami");
					annegato.addRecord(new Posizione(s,gioco.getCont()));
				}
				return;
			}
			testo.setText("");
		}
	}
	
	
}
