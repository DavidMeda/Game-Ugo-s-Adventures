package oggetti_di_gioco;

import exception.IllegalArgumentException;
import mappe.Mappa;

public class Warp extends Sprite_AB {
	private static final long serialVersionUID = 1427607917813046683L;
	private int ii,jj,nextM;
	
	public Warp(int i, int j, int nextMap){
		ii=i; jj=j; nextM=nextMap;
		priorita = 0;
	}
	
	public void setSpawn(Mappa map){
		if(nextM<0){
			map.remove(this);
			throw new IllegalArgumentException("qualCuadra non Qosa");
		}
		
		map.setSpawnPg(ii, jj);
		map.setLivello(nextM);
	}
	
	public String toString(){return "w"+nextM;}

}
