package minigame.memory;

import java.io.Serializable;
import java.util.ArrayList;

public class Memory implements Serializable{
	private static final long serialVersionUID = -2880955499961881939L;
	private ArrayList <Carte> mazzo=new ArrayList<>(30);
	public Carte [][]tavolo= new Carte[5][6];
	public ArrayList <Carte> carteSelezionate=new ArrayList<>(2);
	private int turno=0;
	
		
	public Memory(){
		for (int i=1; i<=15; i++) {
			mazzo.add(new Carte(i));
			mazzo.add(new Carte(i));
		}
		for (int k=0;k<5;k++){
			for (int j=0;j<6;j++){
				int r=(int)(Math.random()*mazzo.size());
				Carte c=mazzo.get(r);
				tavolo[k][j]=c;
				mazzo.remove(r);
			}
		}		
	}
	public Carte getCarta(int i, int j){
		return tavolo[i][j];
	}

/**
 * 
 * @return controlla se tutte le carte sono state trovate.
 */
	public boolean winOrLose(){
		for (int k=0;k<5;k++){
			for (int j=0;j<6;j++){
				if(!tavolo[k][j].bloccata()) return false;
			}
		}
		return true;		
	}
	public void giraCarta(int i, int j){
		if(!tavolo[i][j].bloccata() && !tavolo[i][j].visibilita()){
			tavolo[i][j].gira();
			carteSelezionate.add(tavolo[i][j]);
			tavolo[i][j].getIcon();
			turno++;
			}
		else throw new RuntimeException("non esiste la carta i:"+i+"j:"+j);
	}

/**
 * 
 * @return controlla, se le carte non sono già bloccate, se sono uguali.
 */
	public boolean controlla(){
			if(carteSelezionate.get(0).bloccata() && carteSelezionate.get(1).bloccata()) throw new RuntimeException("carte bloccate");
			else	
		    if (carteSelezionate.get(0).getNumero()==carteSelezionate.get(1).getNumero())return true;
		return false;
	}
	public int getTurno(){
		return turno/2;
	}
	public void clearArray(){
		carteSelezionate.clear();
	}
	public Carte getCarteArray(int i){
		return carteSelezionate.get(i);
	}
	public boolean add(Carte c){
		if (c.visibilita() && !c.bloccata()){
			this.carteSelezionate.add(c);
			return true;
		}
		return false;
	}
	public boolean isEmpty(){
		return carteSelezionate.size()==2;
	}

}
