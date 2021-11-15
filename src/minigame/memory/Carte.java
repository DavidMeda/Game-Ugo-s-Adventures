package minigame.memory;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Carte extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7512519956071521676L;
	private int numero;
	private boolean visibilita=false;
	private boolean bloccata=false;
	private ImageIcon img;

	public Carte() {}

	public Carte(int i) {
		setPreferredSize(new Dimension(63,103));
		visibilita=false;
		numero=i;
		setIcon(new ImageIcon(Carte.class.getResource("/cartaCoperta.png")));
		setBackground(new Color(192,	192,	192));
	}
	public int getNumero(){
		return numero;
	}
	public Carte gira(){
		visibilita=true;
		setIcon(setImg(getNumero()));
		return this;
	}
	private ImageIcon setImg(int n) {
		if (n==0) return new ImageIcon(Carte.class.getResource("/cartaCoperta.png"));
		return new ImageIcon(Carte.class.getResource("/carta"+n+".png"));
	}

	public ImageIcon getImg(){
		return img;
	}

	public boolean visibilita(){ 
		return visibilita;
	}
	public Carte rigira(){
		if(!bloccata){
			visibilita=false;
			setIcon(setImg(0));
		}
		return this;
	}
	
	public boolean bloccata(){
		return bloccata;
	}
	public Carte blocca(){
		bloccata=true;
		return this;
	}
	public String toString(){
		return ""+numero;
	}
}
