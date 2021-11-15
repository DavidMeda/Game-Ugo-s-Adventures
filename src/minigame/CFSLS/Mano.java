package minigame.CFSLS;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Mano implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7721807668853977952L;
	private BufferedImage 
	mano,
	carta,
	forbice,
	sasso,
	lizard,
	spock;

	public Mano(){
		try {			
			mano=ImageIO.read(getClass().getResource("/mano.png"));
			carta=ImageIO.read(getClass().getResource("/carta.png"));
			forbice=ImageIO.read(getClass().getResource("/forbice.png"));
			sasso=ImageIO.read(getClass().getResource("/sasso.png"));
			lizard=ImageIO.read(getClass().getResource("/lizard.png"));
			spock=ImageIO.read(getClass().getResource("/spock.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

/**
 * 
 * @param n numero associato ad ogni segno scelto.
 * @return l'immagine corrispondente al segno.
 */
	public BufferedImage getSprite(int n){
		switch(n){
		case 0: return carta;
		case 1: return forbice;
		case 2: return sasso;
		case 3: return lizard;
		case 4: return spock; 
		case -1: return mano;
		}
		return null;
	}
}
