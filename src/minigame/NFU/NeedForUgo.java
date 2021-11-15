package minigame.NFU;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sprite;
import statistiche.Classifica;
import statistiche.Posizione;

public class NeedForUgo implements Serializable{
	private static final long serialVersionUID = -6769870145921501907L;
	
	private static final int
	FACILE = 0,
	MEDIO = 1,
	DIFFICILE = 2;
	
	private long currentMill;
	private BufferedImage imgPg;
	private LinkedList<Sprite> macchine = new LinkedList<>();
	private Pg pg;
	private int xPg,yPg,score;
	private boolean gameOver = false;
	
	// COSTR //////////////////
	public NeedForUgo(Pg pg, int n){
		this.pg = pg;
		xPg=pg.x();
		yPg=pg.y();
		
		switch(n){
			case FACILE    : pg.setI(8); break;
			case MEDIO     : pg.setI(6); break;
			case DIFFICILE : pg.setI(4); break;
			default : throw new IllegalArgumentException
					("NeedForUgo.init. difficoltà "+n+" inesistente");
		}
		this.pg.setJ(2);
		
		try {
		imgPg = ImageIO.read(Sprite.class.getResource("/pgNFU.png"));
		} catch (IOException e){e.printStackTrace();}
			
		this.pg.setImg(imgPg);
		
	}
	// GET ///////////////////////////
	public LinkedList<Sprite> getMacchine(){return macchine;}
	public Pg getPg(){return pg;}
	public int getScore(){return score;}
	// METHODS ////////////////////////
	public boolean gameOver(){return gameOver;}	
	public void muoviPg(char d){
		if(gameOver)return;
		if(d=='a' && pg.x()>0)pg.setX(pg.x()-60);
		if(d=='d' && pg.x()<230)pg.setX(pg.x()+60);
		
	}
	public void ripristinaPg(){
		Classifica nfu = pg.statistiche().getClassifica("nfu");
		if(gameOver && nfu.newRecord(score)){
			String s = (String)JOptionPane.showInputDialog(null,"Nuovo RECORD!\nCome ti chiami");
			nfu.addRecord(new Posizione(s,score));
		}
		
			pg.setXY(xPg,yPg);
		pg.setImg();
		
	}
	public void generaOstacoli(){
		long mill=System.currentTimeMillis();
		if(mill - currentMill <= 1000) return;
		currentMill = mill;
		
			int n = (int)(Math.random()*3);
			for(; n>0; n--){
				Macchina m = new Macchina();
				
				m.setJ((int)(Math.random()*5));
				m.setI(0); //spawn fuori dallo schermo
				macchine.add(m);
			}
		
	}
	public void muoviOstacoli(){
		pg.setImg(imgPg);
		Sprite s;
		
		for(Iterator<Sprite> it = macchine.iterator(); it.hasNext();){
			s=it.next(); 
			s.setY(s.y()+3); // aggiornamento posizione macchine
			
			if(pg.collide(s) && !gameOver){ // game over
				if(!gameOver){ 
					gameOver=true;
					BufferedImage esplosione = null; //carica immagine esplosione
					try {
					esplosione = ImageIO.read(getClass().getResource("/esplosione.png"));
					} catch (IOException e) {e.printStackTrace();}
					
					s.setImg(esplosione); // setta immagine
				}
				return;

			}
			if(s.y()==pg.y())score++;
			if(s.y()>1200)it.remove();
		}
	
	}

}
