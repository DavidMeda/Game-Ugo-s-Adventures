package minigame.CFSLS;

/**
 * @author davide_meda
 */

import java.awt.image.BufferedImage;
import java.io.Serializable;


public class CartaForbiceSassoLizardSpock implements Serializable{
	private static final long serialVersionUID = -3003123790028435220L;
	public int manoCPU=-1, manoPlayer=-1, l; 
	private int punteggio=0, partite=0;
	private Mano player=new Mano();
	private int difficolta;
	
	
/**
 * 	
 * @param numero associato ad ogni segno scelto del player .
 * @return immagine del segno scelto.
 */
	public BufferedImage scegli(int n) {
		this.manoPlayer=n;
		return player.getSprite(n);
	}
	public void scegliLivello(int l){
		if(l<1 && l>3) throw new RuntimeException("Scegli un numero tra 1 e 3");
		this.l=l;
		switch(l){
		case 0: difficolta = 3; break;
		case 1: difficolta = 5; break;
		case 2: difficolta = 7; break;}
	}
	
/**
 * 
 * @return l'immagine del segno scelto.
 */
	public BufferedImage player(){return player.getSprite(manoPlayer);}
	public BufferedImage pc(){return player.getSprite(manoCPU);}

/**
 * 	
 * @return scelta del pc di un segno random.
 */
	public int AI(){
		this.manoCPU=(int)(Math.random()*5);

		return manoCPU;
	}
/**
 * 
 * @return controlla le combinazioni tra il segno
 * 			del player e il segno del pc, se risulta una combinazione vincente 
 * 			return true, altrimenti false.
 * 
 */
	public boolean aggiornaPunteggio(){
		partite++;
		if(manoPlayer==0){
			switch (manoCPU){
			case 2:punteggio++; return true; 
			case 4: punteggio++;return true;
			} 
		}
		else if (manoPlayer==1){
			switch (manoCPU){
			case 0:punteggio++; return true; 
			case 3:punteggio++; return true;
			}
		}
		else if (manoPlayer==2){
			switch(manoCPU){
			case 1:punteggio++;return true; 
			case 3:punteggio++;return true;
			}
		}
		else if (manoPlayer==3){
			switch(manoCPU){
			case 0:punteggio++;return true; 
			case 4:punteggio++;return true;
			}
		}
		else if (manoPlayer==4){
			switch(manoCPU){
			case 1:punteggio++;return true; 
			case 2:punteggio++;return true;
			}
		}
		return false;
	}
	
/**
 * 
 * @return le combinazioni vincenti.
 */
	public String risultati(){
		
		if (manoPlayer==manoCPU) return "Pareggio";
		else if( manoPlayer==0 && manoCPU==1 || manoCPU==0 &&manoPlayer==1) return ("Forbice taglia carta!");
		else if( manoPlayer==0 && manoCPU==2 || manoCPU==0 &&manoPlayer==2) return ("Carta avvolge sasso!");
		else if( manoPlayer==0 && manoCPU==3 || manoCPU==0 &&manoPlayer==3) return ("Lizard mangia carta!"); 
		else if( manoPlayer==0 && manoCPU==4 || manoCPU==0 &&manoPlayer==4) return ("carta smentisce Spock!"); 
		else if( manoPlayer==1 && manoCPU==2 || manoCPU==1 &&manoPlayer==2) return ("sasso rompe forbice!");
		else if( manoPlayer==1 && manoCPU==3 || manoCPU==1 &&manoPlayer==3) return ("Forbici decapitano Lizard");
		else if( manoPlayer==1 && manoCPU==4 || manoCPU==1 &&manoPlayer==4) return ("Spock rompe forbici!");
		else if( manoPlayer==2 && manoCPU==3 || manoCPU==2 &&manoPlayer==3) return ("sasso schiaccia Lizard!");
		else if( manoPlayer==2 && manoCPU==4 || manoCPU==2 &&manoPlayer==4) return ("Spock vaporizza sasso!");
		else if( manoPlayer==3 && manoCPU==4 || manoCPU==3 &&manoPlayer==4) return ("Lizard avvelena Spock!");
		throw new RuntimeException();
	}
	

	public int getDifficolta(){return difficolta;}
	
	public int getPunteggio(){
		return punteggio;
	}
	public int getPartite(){
		return partite;
	}
	
	public boolean winOrLoose(){
		if (punteggio<difficolta) return false;
		return true;
	}

	
}
