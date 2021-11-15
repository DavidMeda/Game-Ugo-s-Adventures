package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import gestioneDati.Partita;
import gui.button.Bottoni;
import gui.game.GamePanel;
import gui.main.MainPanel;
import gui.main.MenuDifficolta;
import oggetti_di_gioco.Pg;
import supporto.MapBuilder;

public class MainFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Pg pg;
	private static JPanel mainPanel = new MainPanel();
	
	// LISTENER
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == Bottoni.newGame){
			pg = new Pg();
			getContentPane().removeAll();
			JPanel p = new MenuDifficolta();
			getContentPane().add(p);
			p.requestFocus();
			revalidate();
			repaint();
			
		}else if(o == Bottoni.loadGame){
			JFileChooser finestra = new JFileChooser();
			finestra.setApproveButtonText("Carica");
        	finestra.setFileSelectionMode(JFileChooser.FILES_ONLY);
        	finestra.setMultiSelectionEnabled(false);
			finestra.setFileFilter(new FileFilter(){
				public boolean accept(File file) {
				    if (file.isDirectory()) return true;
				    String fname = file.getName().toLowerCase();
				    return fname.endsWith("par");
				  }

				  public String getDescription() {
				    return "File di salvataggio";
				  }
			});
			int n = finestra.showOpenDialog(this);
			if(n == JFileChooser.APPROVE_OPTION){
				File f = finestra.getSelectedFile();
				pg = Partita.carica(f.getAbsolutePath());
				getContentPane().removeAll();
				JPanel p = new GamePanel(pg);
				getContentPane().add(p);
				p.requestFocus();
				revalidate();
				repaint();
			
			}
			
		
		}else if (o == Bottoni.facile) {
			pg.setDifficolta(0);
			MapBuilder.defaultMap(pg);
			getContentPane().removeAll();
			JPanel p = new GamePanel(pg);
			getContentPane().add(p);
			p.requestFocus();
			revalidate();
			repaint();
		}
			
		else if (o == Bottoni.medio){
			pg.setDifficolta(1);
			MapBuilder.defaultMap(pg);
			getContentPane().removeAll();
			JPanel p = new GamePanel(pg);
			getContentPane().add(p);
			p.requestFocus();
			revalidate();
			repaint();
		}
			
		else if (o == Bottoni.difficile) {
			pg.setDifficolta(2);
			MapBuilder.defaultMap(pg);
			getContentPane().removeAll();
			JPanel p = new GamePanel(pg);
			getContentPane().add(p);
			p.requestFocus();
			revalidate();
			repaint();
		
		}else if(o == Bottoni.quit){
	    	getContentPane().removeAll();
	        add(mainPanel, BorderLayout.CENTER);
	        revalidate();
	        repaint();
	        return;
    }
}

// COSTR.
public MainFrame(){
	setSize(1388, 785);
		int x = Toolkit.getDefaultToolkit().getScreenSize().width/2;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height/2;
		setBounds(x-(1388/2), y-(786/2), 1388, 786);
		setTitle("Ugo's Adventure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Bottoni.newGame.addActionListener(this);
		Bottoni.loadGame.addActionListener(this);
		Bottoni.statistiche.addActionListener(this);
		Bottoni.facile.addActionListener(this);
		Bottoni.medio.addActionListener(this);
		Bottoni.difficile.addActionListener(this);
		Bottoni.quit.addActionListener(this);
		
		add(mainPanel);
		setResizable(false);
		setVisible(true);
		
		
	}

	
	public static void main(String[] args) {
		
		(new Thread(new Runnable() {
            public void run() {
                JFrame f = new MainFrame();
        		for(;;)f.repaint();
            }
        })).start();
		
//		JFrame f = new MainFrame();
//		for(;;)f.repaint();
	}


}