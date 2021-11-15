package minigame.NFU;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import oggetti_di_gioco.Sprite;
import oggetti_di_gioco.Sprite_AB;

public class Macchina extends Sprite_AB{
	private static final long serialVersionUID = -6062114166281926109L;
	
	public Macchina(){
		BufferedImage img = null;
		try {
			img = ImageIO.read(Sprite.class.getResource
				("/macchina.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		setImg(img);
	
	}
	public int x(){return x+480;}
	public String toString(){return "  m ";}
}
