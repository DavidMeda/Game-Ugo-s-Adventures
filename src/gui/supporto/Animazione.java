package gui.supporto;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;

import oggetti_di_gioco.Sprite;

public class Animazione implements Serializable{
	private final boolean DEBUG = false;
	private static final long serialVersionUID = 2824664638370320047L;

	private int img = 0;
	
	private long
		currentMilliSec, milliSec;
	
	private LinkedList<BufferedImage> frames;
	
	// COSTR /////////////////////
	public Animazione(LinkedList<BufferedImage> animazione, long s){
		milliSec = s;
		currentMilliSec = System.currentTimeMillis();
		frames = animazione;
		
		if(DEBUG){
		System.out.println("Animazione. creata");
		System.out.println("          . milli sec="+milliSec);
		}
	}
	
	// METHODS ///////////////////
	public BufferedImage nextFrame(){
		long mSec = System.currentTimeMillis();
		
		if(DEBUG){System.out.println("Animazione.nextSprite. milliSec trascorsi=("+mSec+" - "+currentMilliSec+" = "+(mSec-currentMilliSec)) ;}
		
		if(mSec - currentMilliSec>=milliSec){
			if(DEBUG){
			System.out.println("                     . frame corrente="+img);
			System.out.println("                     . prossimo frame="+img+1);
			}
			
			img = (img+1)%frames.size();
			currentMilliSec = mSec; 
		}
		
		return frames.get(img);
	}
	
	// STATIC ///////////////////////////////////////
	public static BufferedImage getFrame(Sprite s, int x, int y){
		BufferedImage collezione = s.collezione();
		
		return collezione.getSubimage(x*s.getLarghezza(), y*s.getAltezza(), s.getLarghezza(), s.getAltezza());
	}
}
