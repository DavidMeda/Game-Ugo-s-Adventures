package gui.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gui.button.Bottoni;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage sfondo;

	public MainPanel() {
		try {
			sfondo = ImageIO.read(getClass().getResource("/sfondoMain.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(1388,786);
		setLayout(null);

        
		Bottoni.newGame.setBounds(480,420,440, 126);
		Bottoni.newGame.setSelectedIcon(new ImageIcon(getClass().getResource("/newG schiacciato.png")));
		Bottoni.newGame.setContentAreaFilled(false);
		Bottoni.newGame.setBorderPainted(false);
		Bottoni.newGame.setFocusPainted(false);
		add(Bottoni.newGame);
		
		Bottoni.loadGame.setBounds(480,580, 440, 126);
		Bottoni.loadGame.setSelectedIcon(new ImageIcon(getClass().getResource("/loadG schiacciato.png")));
		Bottoni.loadGame.setContentAreaFilled(false);
		Bottoni.loadGame.setBorderPainted(false);
		Bottoni.loadGame.setFocusPainted(false);
		add(Bottoni.loadGame);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(sfondo, 0, 0, null);
	}

}
