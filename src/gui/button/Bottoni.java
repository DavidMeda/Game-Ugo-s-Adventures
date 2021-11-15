package gui.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import oggetti_di_gioco.Sprite;

public final class Bottoni {
	private Bottoni(){}
	
	public static JToggleButton
	newGame = new JToggleButton(new ImageIcon(Sprite.class.getResource("/newG.png"))),
	loadGame = new JToggleButton(new ImageIcon(Sprite.class.getResource("/loadG.png"))),
	facile = new JToggleButton(new ImageIcon(Sprite.class.getResource("/facile.png"))),
	medio = new JToggleButton(new ImageIcon(Sprite.class.getResource("/medio.png"))),
	difficile = new JToggleButton(new ImageIcon(Sprite.class.getResource("/difficile.png")));
	
	static{
		facile.setSelectedIcon(new ImageIcon(Sprite.class.getResource("/newG schiacciato.png")));
		loadGame.setSelectedIcon(new ImageIcon(Sprite.class.getResource("/loadG schiacciato.png")));
		facile.setSelectedIcon(new ImageIcon(Sprite.class.getResource("/facile schiacciato.png")));
		medio.setSelectedIcon(new ImageIcon(Sprite.class.getResource("/medio schiacciato.png")));
		difficile.setSelectedIcon(new ImageIcon(Sprite.class.getResource("/difficile schiacciato.png")));
	}
	
	
	public static JButton 
	statistiche = new JButton("STATISTICHE"),
		
	salva = new JButton("SALVA"),
	pausa = new JButton("PAUSA"),
	
	esci = new JButton("ESCI"),
	quit = new JButton("ESCI");

	
	
}
