package gui.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gui.button.Bottoni;

public class MenuDifficolta extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage sfondo;

	public MenuDifficolta() {
		try {
			sfondo = ImageIO.read(getClass().getResource("/sfondoDifficolta.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		Bottoni.facile.setBounds(480,200,440, 126);
		Bottoni.facile.setSelectedIcon(new ImageIcon(getClass().getResource("/facile schiacciato.png")));
		Bottoni.facile.setContentAreaFilled(false);
		Bottoni.facile.setBorderPainted(false);
		Bottoni.facile.setFocusPainted(false);
		add(Bottoni.facile);
		
		Bottoni.medio.setBounds(480,360,440, 126);
		Bottoni.medio.setSelectedIcon(new ImageIcon(getClass().getResource("/medio schiacciato.png")));
		Bottoni.medio.setContentAreaFilled(false);
		Bottoni.medio.setBorderPainted(false);
		Bottoni.medio.setFocusPainted(false);
		add(Bottoni.medio);
		
		Bottoni.difficile.setBounds(480,520,440, 126);
		Bottoni.difficile.setSelectedIcon(new ImageIcon(getClass().getResource("/difficile schiacciato.png")));
		Bottoni.difficile.setContentAreaFilled(false);
		Bottoni.difficile.setBorderPainted(false);
		Bottoni.difficile.setFocusPainted(false);
		add(Bottoni.difficile);

	}
	
	public void paintComponent(Graphics g){
		g.drawImage(sfondo,0,0,null);
	}
	

}
