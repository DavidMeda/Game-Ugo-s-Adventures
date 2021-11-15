package statistiche;

import java.io.Serializable;

/**
 * Gli oggetti statistica contengono le statistiche legate ad un personaggio,
 * <br>in particolare contengono:
 * <br>numero di passi;
 * <br>numero di uccisioni;
 * <br>numero di morti;
 * <br>classifiche dei minigiochi. 
 *
 */
public class Statistica implements Serializable {
	private static final long serialVersionUID = -636469843738313067L;

	private Classifica 
	nfu = new Classifica(),
	cfsls = new Classifica(),
	annegato = new Classifica(),
	memory = new Classifica(true);
	
	private int
	passi, morti, uccisioni;
	
	public Statistica(){}
	
	// GET ///////////////////////////////
	/**
	 * Restituisce la classifica del minigioco specificato dalla stringa s
	 * <br> nfu = classifica del minigioco need for ugo
	 * <br> cfsls = classifica del minigioco carta,forbici,sasso,lizard,spock
	 * <br> annegato = classifica del minigioco l'annegato
	 * <br> memory = classifica del minigioco memory
	 * @param s classifica da restituire
	 * @return classifica del minigioco
	 */
	public Classifica getClassifica(String s){
		switch(s){
		case "nfu"     : return nfu;
		case "cfsls"   : return cfsls;
		case "annegato": return annegato;
		case "memory"  : return memory;
		}
		throw new IllegalArgumentException
		("Classifica.getClassifica. classifica "+s+" inesistente");
	
	}
	/**  */
	public int getPassi(){return passi;}
	/**  */
	public int getMorti(){return morti;}
	/**  */
	public int getUccisioni(){return uccisioni;}
	
	// SET ////////////////////////////////////
	/** Incrementa il numero delle uccisioni */
	public void uccisione(){uccisioni++;}
	/** Incrementa il numero delle morti */
	public void morto(){morti++;}
	/** Incrementa il numero di passi */
	public void passo(){
		passi++;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("\n-- PASSI:  Hai percorso "+(getPassi()/5)+" passi "
				+ "\n-- MORTI:  Sei morto "+morti+" volte "
				+ "\n-- UCCISIONI:  Hai ucciso "+uccisioni+" nemici"
				+ "\n\nCLASSIFICA NEED FOR UGO \n"+nfu.toString()+""
				+ "\n CLASSIFICA CARTA FORBICE SASSO LIZARD SPOCK \n"+cfsls.toString()+""
				+ "\n CLASSIFICA ANNEGATO \n"+annegato+""
			    + "\n CLASSIFICA MEMORY \n"+memory);
		return s.toString();
	}
}
