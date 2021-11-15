package gestioneDati;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import mappe.Livello;
import mappe.Mappa;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.armi.Arma;
import oggetti_di_gioco.azionabili.Porta;
import oggetti_di_gioco.danneggiabili.Nemico;
import oggetti_di_gioco.item.Chiave;
import oggetti_di_gioco.item.Item;
import statistiche.Statistica;
import supporto.MapBuilder;

public class Partita implements Serializable{
	private static final long serialVersionUID = -2916444702402010484L;
	
	// info mappa
	private String nomeMappa;
	private int idLivello;
	private LinkedList<LinkedList<Nemico>> listaNemici = new LinkedList<>();
	private LinkedList<LinkedList<Porta>> listaPorte = new LinkedList<>();
	private LinkedList<LinkedList<Item>> listaItem = new LinkedList<>();
	// info pg
	private int difficolta, hp, xPg, yPg, iMap, jMap;
	private Arma arma;
	private LinkedList<Arma> armi = new LinkedList<>();
	private LinkedList<Chiave> chiavi = new LinkedList<>();
	private LinkedList<Item> inventario = new LinkedList<>();
	// statistica
	private Statistica stat;
	
	private Partita(Pg pg){
		//mappa
		nomeMappa = pg.getMappa().getNome();
		idLivello = pg.getMappa().getIdLivello();
		listaPorte = pg.getMappa().getListePorte();
		listaNemici = pg.getMappa().getListeNemici();
		listaItem = pg.getMappa().getListeItem();
		//pg
		difficolta = pg.getDifficolta();
		hp = pg.getHp();
		stat = pg.statistiche();
		xPg = pg.x();
		yPg = pg.y();
		iMap = pg.getMappa().getI();
		jMap = pg.getMappa().getJ();
		arma = pg.getArma();
		armi = pg.getArmi();
		chiavi = pg.getChiavi();
		System.out.println(chiavi);
		inventario = pg.getInventario();
	
	}
	// METHODS ///////////////////////
	public static void salva(Pg pg, String directory){
		Partita p = new Partita(pg);
		
		if(p.arma!=null)p.arma.preparaSalvataggio();
		for(Arma a : p.armi)a.preparaSalvataggio();
		for(Chiave c : p.chiavi)c.preparaSalvataggio();
		for(Item i : p.inventario)i.preparaSalvataggio();
		
		for(LinkedList<Nemico> nemici : p.listaNemici){
			for(Nemico n : nemici) n.preparaSalvataggio();
		}
		for(LinkedList<Item> items : p.listaItem){
			for(Item i : items) i.preparaSalvataggio();
		}
		for(LinkedList<Porta> porte : p.listaPorte){
			for(Porta d : porte) d.preparaSalvataggio();
		}
		
		ObjectOutputStream oos;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(directory+".par"));
			oos.writeObject(p);
			oos.flush();
		} 
		catch 
		(IOException e) 
		{e.printStackTrace();}
		
		if(p.arma!=null)p.arma.caricaSalvataggio();
		for(Arma a : p.armi)a.caricaSalvataggio();
		for(Chiave c : p.chiavi)c.caricaSalvataggio();
		for(Item i : p.inventario)i.caricaSalvataggio();
		
		for(LinkedList<Nemico> nemici : p.listaNemici){
			for(Nemico n : nemici) n.caricaSalvataggio();
		}
		for(LinkedList<Item> items : p.listaItem){
			for(Item i : items)i.caricaSalvataggio();
		}
		for(LinkedList<Porta> porte : p.listaPorte){
			for(Porta d : porte) d.caricaSalvataggio();
		}

	}
	public static Pg carica(String directory){
		ObjectInputStream ois;
		Partita p = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(directory));
			p = (Partita)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Pg pg = new Pg();
		Mappa m = MapBuilder.defaultMap(pg);
		
		Iterator<Livello> it = m.getLivelli().iterator();
		Livello l = null;
		for(int i=0; i<m.getLivelli().size(); i++){
			l = it.next();
			l.setListaPorte(p.listaPorte.get(i));
			l.setListaNemici(p.listaNemici.get(i));
			l.setListaItem(p.listaItem.get(i));
		}

		m.setLivello(p.idLivello);
		m.setNome(p.nomeMappa);
		m.setSpawnPg(p.iMap, p.jMap);
		m.setPg(pg);
		
		for(Arma a : p.armi)a.caricaSalvataggio();
		for(Chiave c : p.chiavi)c.caricaSalvataggio();
		for(Item i : p.inventario)i.caricaSalvataggio();
		pg.setDifficolta(p.difficolta);
		pg.setHp(p.hp);
		pg.setXY(p.xPg,p.yPg);
		pg.setArma(p.arma);
		pg.setArmi(p.armi);
		pg.setChiavi(p.chiavi);
		pg.setInventario(p.inventario);
		pg.setStatistiche(p.stat);
				
		return pg;
        
	}

}
