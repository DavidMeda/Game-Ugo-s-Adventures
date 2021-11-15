package statistiche;

import java.io.Serializable;

/**
 
 * La classe posizione verrà utilizzata dalla classifica 
 */
public class Posizione implements Comparable<Posizione>, Serializable{
	private static final long serialVersionUID = 3194622497272881152L;
	
	/**
	 * @param nome il nome dell'utente che entrerà in classifica
	 * @param score punteggio raggiunto dall'utente
	 */
	private String nome;
	private int score;
	
	/**
	 * Genera un oggetto Posizione
	 * @param nome nome utente
	 * @param score punteggio raggiunto
	 */
	public Posizione(String nome, int score){
		if(nome == null) this.nome="<unknown>";
		else this.nome = nome;
		this.score = score;
	
	}
	/**
	 * Utile per determinare in quale posizione sarà collocato il nuovo record
	 */
	public int compareTo(Posizione pos){
		return score-pos.score;
	
	}
	/**
	 * Utilizzato per verificare se il punteggio raggiunto dall'utente può entrare in classifica
	 * @param score
	 * @return
	 */
	public int compare(int score){
		return this.score-score;
	
	}
		
	public String toString(){return nome+" - "+score;}
	
}
