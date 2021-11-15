package gui.minigame.annegato;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import minigame.annegato.Annegato;

public class AnimazioneAnnegato extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1157412920938619575L;
	private Annegato gioco;
	private int ind;
	private static BufferedImage[] frame= new BufferedImage[5] ;
	public static BufferedImage sea, nuotatoreMorto;
	
	public AnimazioneAnnegato(Annegato game){
		gioco=game;
			try {
				frame[0]=ImageIO.read(AnnegatoGame.class.getResource("/nuotatore1.png"));
				frame[1]=ImageIO.read(AnnegatoGame.class.getResource("/nuotatore2.png"));
				frame[2]=ImageIO.read(AnnegatoGame.class.getResource("/nuotatore3.png"));
				frame[3]=ImageIO.read(AnnegatoGame.class.getResource("/nuotatore4.png"));
				frame[4]=ImageIO.read(AnnegatoGame.class.getResource("/nuotatore5.png"));
				nuotatoreMorto=ImageIO.read(AnnegatoGame.class.getResource("/nuotatoreMorto.png"));
				sea=ImageIO.read(AnnegatoGame.class.getResource("/sea.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public BufferedImage getNextSprite() {
        return frame[++ind% frame.length];
        
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!gioco.winOrLoose(gioco.getCont())){
			g.drawImage(sea, 10,20,null);
			g.drawImage(nuotatoreMorto, 580, 500, null);
		}else{
		g.drawImage(sea, 10,20,null);
		g.drawImage(getNextSprite(),580,gioco.getAlt(),null);
		}
		try {
			Thread.sleep(150);
			}
			catch (Exception e) {}
		repaint();
	}

}
