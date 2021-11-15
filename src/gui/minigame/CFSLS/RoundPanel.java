package gui.minigame.CFSLS;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.Serializable;

import javax.swing.JPanel;

import minigame.CFSLS.CartaForbiceSassoLizardSpock;

public class RoundPanel extends JPanel implements Serializable{
	private static final long serialVersionUID = 2407786016878437028L;
	private CartaForbiceSassoLizardSpock g;
	
	public RoundPanel(CartaForbiceSassoLizardSpock g){
		this.g=g;
		setPreferredSize(new Dimension(100,300));
	}
	
	public void paintComponent(Graphics q){
		super.paintComponent(q);
		int x = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		q.drawImage(g.pc(),(x/4)+300,100, null);
		q.drawImage(g.player(),x/4,100, null);
	}

}
