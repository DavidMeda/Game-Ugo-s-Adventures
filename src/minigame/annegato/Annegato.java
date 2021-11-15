
package minigame.annegato;

import java.io.Serializable;

import oggetti_di_gioco.Pg;

public class Annegato implements Serializable{
	
	private static final long serialVersionUID = -1866418225447278112L;
	private String parola;
	public String []v={"inconsistente", "stereotipo", "contemporaneamente", "triangolarizzazione", "inconcruenze", "simmetria", "relativismo", "esistenzialismo", "teoria", "meteo", "idilliaco", "transistor", "codificatore", "selettore", "sequenziale", "algoritmo", "computazionale", "progetto", "gradiente", "derivata", "limite"};
	public int 
	cont=0,
	alt=45;
	
	private int livello;
	private StringBuilder word;
	
	public Annegato(Pg pg){
		scegliLivello(pg.getDifficolta());
		int k=(int)(Math.random()*v.length);
		parola=v[k];
		word= new StringBuilder(parola.length()*2);
		word();
	}

/**
 * 
 * @return parola da indovinare nascosta.
 */
	public StringBuilder word(){
		for (int i=0; i<parola.length(); i++) word.append("_ ");
		return word;
		
	}
/**
 * 
 * @param c lettera scelta dal player.
 * 
 */
	public void cercaLettera(char c){
		boolean flag = false;
		for (int i=0; i<parola.length(); i++){
			if(c == parola.charAt(i))	{
				word.setCharAt(i*2, c);
				flag = true;
			}
		} 
		if(!flag){
			cont++;
			switch(livello){
			case 0: alt+=23; break;
			case 1: alt+=36; break;
			case 2: alt+=52; break;
			}
		
		}
		
	}
	public void cercaParola(String s){
		if(s.equals(parola)) word.replace(0, word.length(), s);
		else{
			cont++;
			switch(livello){
			case 0: alt+=23; break;
			case 1: alt+=36; break;
			case 2: alt+=52; break;
			}
		}
	}
	
	public void scegliLivello(int l){
		if(l<1 && l>3) throw new RuntimeException("Scegli un numero tra 1 e 3");
		this.livello=l;		
    }
	
    public boolean winOrLoose(int cont){
		switch(livello){
		case 0: if (cont<=15) return true;
		case 1: if (cont<=10) return true; 
		case 2: if (cont<=7) return true;
		}
		return false;
    }

/**
 * 
 * @return controlla se ci sono lettere non trovate nella parola return false, 
 * 		   altrimenti true.
 */
    public boolean trovaParola(){
    	boolean trovata = true;
    	for (int i=0; i<word.length();i++){
    		if(word.charAt(i)=='_') trovata = false;
    	}
    	if(word.equals(parola)) trovata = true;
    	return trovata;
    }
    
   
    
    /////get///////
    public int getCont(){return cont;}
    public int getAlt(){return alt;}
    public String getWord(){return word.toString();}
    public String getParola(){return parola;}
    
}
