package statistiche;

import java.io.Serializable;

/**
 * La classifica registra i record dei minigiochi
 *
 */
public class Classifica implements Serializable{
	private static final long serialVersionUID = -2176014205327160468L;
	
	/**
	 * @param classifica è un array di Posizione con cinque elementi
	 * @param crescente una booleana che permette di stabilire se la classifica <br> 
	 * sarà in ordine crescente o decrescente
	 */
	private Posizione[] classifica = new Posizione[5];
	private boolean crescente;
	
	/**
	 * Genera un oggetto classifica che segue l'ordine decrescente
	 */
	public Classifica(){}
	/**
	 * Genera un oggetto classifica che segue l'ordine crescente o decrescente in base alla 
	 * <br> variabile boolean passata come parametro 
	 * @param crescente true, ordine crescente <br> false, ordine decrescente
	 */
	public Classifica(boolean crescente){
		this.crescente = crescente;
	}
	
	/**
	 * Ci conferma se il punteggio raggiunto può essere aggiunto alla classifica
	 * @param score punteggio da aggiungere alla classifica
	 * @return true se lo score determina un nuovo record, false atrimenti
	 */
	public boolean newRecord(int score){
		if(classifica.length<5)return true;
		
		Posizione p;
		if(!crescente){
			for(int i=0; i<5; i++){
				p = classifica[i];
				if(p==null || p.compare(score)>=0)return true;
			}
		}else{
			for(int i=0; i<5; i++){
				p = classifica[i];
				if(p==null || p.compare(score)<=0)return true;
			}
		}
		return false;
	
	}
	/**
	 * Aggiunge la nuova posizione
	 * @param pos Posizione da aggiungere
	 */
	public void addRecord(Posizione pos){		
		Posizione temp;
		
		if(!crescente){
			for(int i=0; i<5; i++){
				if(classifica[i]==null){classifica[i]=pos; return;}
				
				if(pos.compareTo(classifica[i])>=0){
					temp=classifica[i];
					classifica[i]=pos;
					pos=temp;
				}
			}
		}else{
			for(int i=0; i<5; i++){
				if(classifica[i]==null){classifica[i]=pos; return;}
				
				if(pos.compareTo(classifica[i])<=0){
					temp=classifica[i];
					classifica[i]=pos;
					pos=temp;
				}
			}
		}
		
	}
	/**
	 * Restituisce la classifica
	 * @return l'array dedicato alla classifica
	 */
	public Posizione[] getClassifica(){return classifica;}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		int i=0;
		for(Posizione p : classifica){
			sb.append((++i)+" | "+p+"\n");
		}
		return sb.toString();

	}
	
}
