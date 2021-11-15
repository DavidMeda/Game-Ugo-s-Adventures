package gui.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import gestioneDati.Partita;
import gui.button.Bottoni;
import gui.minigame.nfu.NFUPanel;
import mappe.Mappa;
import oggetti_di_gioco.Pg;

public class GamePanel extends JPanel{
    private static final long serialVersionUID = 707541448637345077L;
      
    JComponent mainP = new JPanel();
    JComponent minigamePanel;
    MapPanel gameP;
    JPanel menu = new JPanel();
    MsgPanel msg;
    private KeyListener k = new KeySpy();
     
    private class ActionSpy implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
             
            if(o == Bottoni.esci){
                JComponent j = gameP.getPg().getPanel();
                if(j instanceof NFUPanel){
                    ((NFUPanel)j).ripristinaPg();
                    removeKeyListener(((NFUPanel)j).getKeyLis());
                }
                 
                removeAll();
                add(mainP, BorderLayout.CENTER);
                revalidate();
                gameP.getPg().exitMinigame();
                return;
             
            }else if(o == Bottoni.salva){
                requestFocus();
                JFileChooser finestra = new JFileChooser();
                finestra.setApproveButtonText("Salva");
                finestra.setFileSelectionMode(JFileChooser.FILES_ONLY);
                finestra.setMultiSelectionEnabled(false);
                finestra.setFileFilter(new FileFilter(){
                    public boolean accept(File file) {
                        String percorso = file.getAbsolutePath().toLowerCase();
                        return percorso.endsWith("par");
                      }public String getDescription() {return ".par";}
                });
                int n = finestra.showSaveDialog(null);
                if(n == JFileChooser.APPROVE_OPTION){
                    File f = finestra.getSelectedFile();
                    Partita.salva(gameP.getPg(), f.getAbsolutePath());
                }
             
            }else if( o == Bottoni.statistiche){
                StringBuilder sb = new StringBuilder();
                sb.append("STATISTICHE\n");
                sb.append(gameP.getPg().statistiche());
                JOptionPane.showMessageDialog(null, sb);
                requestFocus();
                 
            }else if( o == Bottoni.pausa){
            	gameP.togglePausa();;
                requestFocus();

            }
        }
      
    }
    private class KeySpy implements KeyListener{
        public void keyPressed(KeyEvent e) {
            if (gameP.getPg().minigame()) return;
            
            //avanza messaggi
            if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            	System.out.println("gamepanel. INVIO");
            	msg.nextText();
            	return;
            }
            //esc - pausa
            else if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
            	gameP.togglePausa();
            	return;
            }
             
            
            if (gameP.getPausa())return;
        	char direzione = (("" + e.getKeyChar()).toLowerCase()).charAt(0);
        	
        	if(gameP.getPg().hasMessage()){
            	msg.setMessaggio(gameP.getPg().getMessaggio());            	
            }else msg.setMessaggio(null);
            
            //aziona
            if(direzione=='f'){
                gameP.getPg().aziona();
                if(gameP.getPg().minigame()){
                    removeAll();
                    JComponent j = gameP.getPg().getPanel();
                    if(j instanceof NFUPanel){
                    	requestFocus();
                    	removeKeyListener(k);
                        addKeyListener(((NFUPanel)j).getKeyLis());
                    }
                    JOptionPane.showMessageDialog(null, j.toString());
                    requestFocus();
                    
                    add(j, BorderLayout.CENTER);
                    revalidate();
                }
            
            }
            //usa pozione
            else if(direzione=='p'){gameP.getPg().curati();}
            
            gameP.getPg().attacca(e);
 
        }
                  
        public void keyTyped(KeyEvent e){
        	//annulla listener se c'è un minigame
            if(gameP.getPg().minigame()) return;
            //annulla listener se in pausa
            if(gameP.getPausa()) return;
 
            char direzione = (("" + e.getKeyChar()).toLowerCase()).charAt(0);
            //muovi pg
            try{
            gameP.getPg().muovi(direzione);
            }catch(IndexOutOfBoundsException ex){}
            //aggiorna messaggio
            if(gameP.getPg().hasMessage()){
            	msg.setMessaggio(gameP.getPg().getMessaggio());            	
            }
              
        }

		@Override
		public void keyReleased(KeyEvent arg0) {
			gameP.getPg().rimuoviAttacco();
		}
    }
     
      
    // COSTR /////////////////////////////
    public GamePanel(Pg pg){
        msg = new MsgPanel(pg);
        Bottoni.esci.addActionListener(new ActionSpy());
        Bottoni.salva.addActionListener(new ActionSpy());
        Bottoni.statistiche.addActionListener(new ActionSpy());
        Bottoni.pausa.addActionListener(new ActionSpy());

        addKeyListener(new KeySpy());
        setFocusable(true);
          
        Mappa m = pg.getMappa();
        gameP = new MapPanel(pg, m);
          
        menu.setLayout(new BorderLayout());
        menu.add(new MenuPanel(), BorderLayout.NORTH);
        menu.add(new InventarioPanel(pg), BorderLayout.CENTER);
        menu.setBackground(Color.BLACK);
        menu.setPreferredSize(new Dimension(110,640));
          
        mainP.setLayout(new BorderLayout());
        mainP.add(msg, BorderLayout.SOUTH);
        mainP.add(menu, BorderLayout.EAST);
        mainP.add(gameP, BorderLayout.CENTER);
          
        setLayout(new BorderLayout());
        add(mainP, BorderLayout.CENTER);
  
          
    }
      
          
}