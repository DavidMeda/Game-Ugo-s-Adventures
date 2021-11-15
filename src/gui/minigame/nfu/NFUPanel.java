package gui.minigame.nfu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.button.Bottoni;
import minigame.NFU.NeedForUgo;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
 
public class NFUPanel extends JComponent{
    private static final long serialVersionUID = 5326770038997675651L;
      
    private KeyListener k = new KeySpy();
    public static JButton esci = Bottoni.esci;
    private NeedForUgo nfu;
    private BufferedImage[] sfondi = new BufferedImage[4];
    private BufferedImage 
        sfondo0, sfondo1, sfondo2;
    int ySfondo;
      
    public class KeySpy extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            String s = ""+e.getKeyChar();
            nfu.muoviPg(s.toLowerCase().charAt(0));
        }
           
    }
    public NFUPanel(){}
      
    public NFUPanel(Pg pg){
        nfu = new NeedForUgo(pg, pg.getDifficolta());
          
        try {
        sfondi[0]=ImageIO.read(getClass().getResource("/sfondoNFU0.png"));
        sfondi[1]=ImageIO.read(getClass().getResource("/sfondoNFU1.png"));
        sfondi[2]=ImageIO.read(getClass().getResource("/sfondoNFU2.png"));
        sfondi[3]=ImageIO.read(getClass().getResource("/sfondoNFU3.png"));
        } catch (IOException e) {e.printStackTrace();}
          
        sfondo0 = sfondi[(int)(Math.random()*4)];
        sfondo1 = sfondi[(int)(Math.random()*4)];
        sfondo2 = sfondi[(int)(Math.random()*4)];
          
        requestFocusInWindow();
        setFocusable(true);
          
        setLayout(new BorderLayout());
 
        JPanel j = new JPanel();
        Font bigFont1=esci.getFont().deriveFont(20f);
		esci.setFont(bigFont1);
        j.add(esci); 
        add(j, BorderLayout.NORTH);
         
        addKeyListener(k);
        requestFocusInWindow();
      
    }
    //////get///
    public KeyListener getKeyLis(){return k;}
    public void aggiorna(){
        if(nfu.gameOver()) return;
        ySfondo+=2;
        if(ySfondo>600){
            sfondo2 = sfondo1;
            sfondo1 = sfondo0;
            sfondo0 =sfondi[(int)(Math.random()*4)];
            ySfondo=0;
        }
        nfu.generaOstacoli();
        nfu.muoviOstacoli();
      
    }
    public void ripristinaPg(){
        nfu.ripristinaPg();
          
    }
    public void paintComponent(Graphics g){
        g.drawImage(sfondo0, 0, ySfondo-600, null);
        g.drawImage(sfondo1, 0, ySfondo, null);
        g.drawImage(sfondo2, 0, ySfondo+600, null);
        
 
        g.setColor(Color.white);
        g.fillRect (10, 60, 100, 20);
        g.setColor(Color.black);
        g.drawString("SCORE: "+nfu.getScore(), 16, 75);
         
        aggiorna();
          
        g.drawImage(nfu.getPg().getImg(), nfu.getPg().x()+480, nfu.getPg().y(), null);
        for(Sprite s : nfu.getMacchine())g.drawImage(s.getImg(), s.x(), s.y(), null);
          
          
    }
  
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.replace(0, sb.length(), "NEED FOR UGO GAME\n |-----------|\n"
    			+ "Lo scopo del gioco è percorrere più strada possibile evitando le macchine");
    	
    	return sb.toString();
    }
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
      
}