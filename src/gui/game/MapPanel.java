package gui.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import mappe.Mappa;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sfondo;
import oggetti_di_gioco.Sprite;
import supporto.MapBuilder;

public class MapPanel extends JComponent{	
	private static final long serialVersionUID = 1L;
	
	private Mappa map;
	private Pg pg;
	private boolean pausa = false;
	private BufferedImage cuore = Sprite.loadImg("cuore"); 
	private Sprite pausaImg = new Pausa();
	
	private static class Pausa extends Sfondo{
		private static final long serialVersionUID = -9006957265099458257L;
		public Pausa(){setImg(Sprite.loadImg("pausa"));}
	}
	
	public MapPanel(){
		pg = new Pg();
		map = MapBuilder.defaultMap(pg);
		map.setPg(pg);
		
		setFocusable(true);
		
	}
	
	// COSTR ///////////////////////////
	public MapPanel(Pg p, Mappa m){
		pg = p;
		map = m;
		map.setPg(pg);
		
	}
	
	public Pg getPg(){return pg;}
	
	public void togglePausa(){
		pausa = !pausa;		
	}
	
	public boolean getPausa(){return pausa;}
	
	public void mostraEnergia(Graphics g){
		int i = 0;
		while(i*100<pg.getHp()){
			g.drawImage(cuore, (i++*37)+60, 10, null);
		}
	}
	public void aggiorna(){
		try{
			map.aggiornaNemici();
		}catch(IndexOutOfBoundsException e){}
		if(pg.morto()) {
			pausa = true;
			JOptionPane.showMessageDialog(null, "sei morto!");
			map.setLivello(map.getIdLivello());
			pg.resetHp();
			pausa = false;
			}
			
			
		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//disegna livello
		Iterator<Sprite> it = map.iterator();
		Sprite s;
		while(it.hasNext()){
			s=it.next();
			g.drawImage(s.getImg(),s.x(),s.y(), null);
		}
		mostraEnergia(g);
		
		if(pausa) g.drawImage(pausaImg.getImg(), 0, 0, null);
		else aggiorna();
		
	}
	
	// METHODS //////////////////////////
	public boolean gameOver(){return map.gameOver();}
	
	/////////////////////////////////////////////
	/////////////////////////////////////////////


}
