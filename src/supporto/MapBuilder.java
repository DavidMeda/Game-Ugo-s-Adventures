package supporto;
 
import mappe.Livello;
import mappe.Mappa;
import oggetti_di_gioco.Cartello;
import oggetti_di_gioco.Muro;
import oggetti_di_gioco.Pg;
import oggetti_di_gioco.Sfondo;
import oggetti_di_gioco.Sprite;
import oggetti_di_gioco.Warp;
import oggetti_di_gioco.armi.Arma;
import oggetti_di_gioco.armi.Spada;
import oggetti_di_gioco.azionabili.Png;
import oggetti_di_gioco.azionabili.Porta;
import oggetti_di_gioco.azionabili.Scrigno;
import oggetti_di_gioco.danneggiabili.Nemico;
import oggetti_di_gioco.danneggiabili.Verme;
import oggetti_di_gioco.item.Chiave;
import oggetti_di_gioco.item.Pozione;
 
public final class MapBuilder {
    private MapBuilder(){}
//          Sprite[][] muriLvX = {/*                                            1                                                          2
//              /*      0     1     2     3     4     5     6     7     8     9     0     1     2     3     4     5     6     7     8     9    0
//              /*0*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*1*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*2*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*3*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*4*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*5*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*6*/{null ,null ,null ,null ,null ,null ,null ,null ,null, null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*7*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*8*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              /*9*/{null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
//              };
     
    public static Mappa defaultMap(Pg pg){
         
        Mappa m = new Mappa();
        m.setPg(pg);
         
        //variabile ausiliare
        Sprite s = null;
         
        Livello lv0 = new Livello();
         
        Sprite[][] muriLv0 = {/*                                            1                                                          2
        /*      0     1     2     3     4     5     6     7     8     9     0     1     2     3     4     5     6     7     8     9    0
        /*0*/{m(2) ,m(0) ,m(8),m(0) ,m(0) ,m(0) ,m(0) ,m(3) ,m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(14),null},
        /*1*/{m(1) ,null ,m(1) ,null ,null ,null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(12),null},
        /*2*/{m(1) ,null ,null ,null ,null ,null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(12),null},
        /*3*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
        /*4*/{m(1) ,null ,null ,null ,null ,null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
        /*5*/{m(1) ,null ,null ,null ,null ,null ,null ,m(1) ,m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(16),m(22)},
        /*6*/{m(7) ,null ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(9), null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22),m(22)},
        /*7*/{m(1) ,null ,null ,null ,null ,null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22),m(22),m(22)},
        /*8*/{m(1) ,null ,null ,null ,null ,null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,m(22),m(22),m(22),m(22),m(22)},
        /*9*/{m(4) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(5) ,null ,null ,null ,null ,m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22)},
        };
         
        lv0.generaMatrice();
         
        for(int i=0; i<10; i++){
            for(int j=0; j<21; j++){
                s = new Sfondo(0);
                s.setIJ(i, j);
                lv0.add(s);
                 
                if(muriLv0[i][j] == null) continue;
                 
                s = muriLv0[i][j];
                s.setIJ(i, j);
                lv0.add(s);
            }
        }
        //scale
        Sfondo sf0 = new Sfondo(1); sf0.setIJ(8, 6);
        lv0.add(sf0);
        // png
        Png carte = new Png(pg, 3); carte.setIJ(1, 3);
        lv0.add(carte);
        // nemici
        Nemico v00 = new Verme(); v00.setIJ(9, 8);
        lv0.add(v00);
        // scrigni
        Scrigno s00 = new Scrigno(new Pozione());
        s00.setIJ(1, 1);
        lv0.add(s00);
        //porte
        Porta p00 = new Porta(-1, false); p00.setIJ(3, 7); p00.apri();
        Porta p01 = new Porta(-1/*10*/, true) ; p01.setIJ(6, 1);
        lv0.add(p00,p01);
        //cartelli
        Cartello cart01 = new Cartello("Sicuro di avere la chiave??"); cart01.setIJ(2, 2);
        lv0.add(cart01);
        //warp
        Warp w00 = new Warp(4,1,1); w00.setIJ(3,20);
        Warp w01 = new Warp(5,1,1); w01.setIJ(4,20);
        Warp w02 = new Warp(1,2,2); w02.setIJ(8,6);

        lv0.add(w00,w01,w02);
         
        m.addLivello(lv0);
         
        ///////////////////////////
        Livello lv1 = new Livello();
         
        Sprite[][] muriLv1 = {/*                                                    1                                                          2
                /*      0     1     2     3     4     5     6     7     8     9     0     1     2     3     4     5     6     7     8     9    0
                /*0*/{null ,null ,m(22),m(22),m(22),m(22),m(22),m(2) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(3)},
                /*1*/{m(14),null ,null ,m(22),m(22),null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(1)},
                /*2*/{m(12),null ,null ,null ,m(22),null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(1)},
                /*3*/{m(12),null ,null ,null ,m(12),null ,null ,m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(1)},
                /*4*/{null ,null ,null ,null ,null ,null ,null ,m(4) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,null ,m(0) ,m(0) ,m(0) ,m(5)},
                /*5*/{null ,null ,null ,null ,m(12),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(12),null ,m(22)},
                /*6*/{m(16),m(22),null ,null ,m(12),null ,null ,null ,null, null ,null ,null ,null ,null ,m(22),m(22),null ,null ,m(12),null ,null},
                /*7*/{m(22),m(22),null ,null ,m(12),m(22),null ,null ,null ,null ,null ,null ,null ,null ,m(22),null ,null ,null ,null ,null ,null},
                /*8*/{m(22),m(22),m(22),null ,m(12),m(22),m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
                /*9*/{m(22),m(22),m(22),m(22),m(15),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(11),m(16),m(22),m(22)},
                };
         
        lv1.generaMatrice();
         
        for(int i=0; i<10; i++){
            for(int j=0; j<21; j++){
                s = s(0);
                s.setIJ(i, j);
                lv1.add(s);
                 
                if(muriLv1[i][j] == null)continue;
                 
                s = muriLv1[i][j];
                s.setIJ(i, j);
                lv1.add(s);
            }
        }
        //cartello
        Cartello cart10 = new Cartello("Hai preso la spada??","Se l'hai presa prova a premere le frecce");
        cart10.setIJ(6, 3);
        lv1.add(cart10);
        //item - spada
        Arma spada = new Spada(0); spada.setIJ(5, 3);
        lv1.add(spada);
        //warp
        Warp w10 = new Warp(3,19,0); w10.setIJ(4,0);																																								Warp magic = new Warp(0,0,-1);magic.setIJ(0,0);lv1.add(magic);
        Warp w11 = new Warp(4,19,0); w11.setIJ(5,0);
        Warp w12 = new Warp(7,1 ,3); w12.setIJ(7,20);
        Warp w13 = new Warp(8,1 ,3); w13.setIJ(8, 20);
	    Warp w14 = new Warp(6,1 ,3); w14.setIJ(6,20);
        lv1.add(w10,w11,w13,w14,w12);
        //chiave
        Chiave ch00 = new Chiave(2);
        //scrigno
        Scrigno s01 = new Scrigno(ch00); s01.setIJ(8, 3);
        Scrigno s02 = new Scrigno(new Chiave(3), ch00.id()); s02.setIJ(5, 19);
        lv1.add(s01,s02);
        //porte
        Porta p10 = new Porta(-1, false) ; p10.setIJ(4, 4);
        Porta p13 = new Porta(3, true) ; p13.setIJ(4, 16);
        lv1.add(p10,p13);
        //nemici
        Nemico v01 = new Verme(); v01.setIJ(1, 6);
        Nemico v02 = new Verme(); v02.setIJ(2, 19);
        lv1.add(v01,v02);
        //png
        Png pn1 = new Png(pg,2); pn1.setIJ(2, 16);
        lv1.add(pn1);
        
        
        m.addLivello(lv1);
        //////////////////////////////////
        Livello lv2 = new Livello();
	      Sprite[][] muriLv2 = {/*                                            1                                                          2
	      /*      0     1     2     3     4     5     6     7     8     9     0     1     2     3     4     5     6     7     8     9    0
	      /*0*/{m(2) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(3)},
	      /*1*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(1)},
	      /*2*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(1)},
	      /*3*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
	      /*4*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
	      /*5*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
	      /*6*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null},
	      /*7*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(1)},
	      /*8*/{m(1) ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(1)},
	      /*9*/{m(4) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(0) ,m(5)},
	      };
	      
	      lv2.generaMatrice();
	      
	      for(int i=0; i<10; i++){
	          for(int j=0; j<21; j++){
	              s = s(3);
	              s.setIJ(i, j);
	              lv2.add(s);
	               
	              if(muriLv2[i][j] == null)continue;
	               
	              s = muriLv2[i][j];
	              s.setIJ(i, j);
	              lv2.add(s);
	          }
	      }
	      //strada
	      Sfondo strada0 = s(4); strada0.setIJ(3, 20);
	      Sfondo strada1 = s(4); strada1.setIJ(3, 19);
	      Sfondo strada2 = s(4); strada2.setIJ(3, 18);
	      Sfondo strada3 = s(4); strada3.setIJ(3, 17);
	      Sfondo strada4 = s(4); strada4.setIJ(3, 16);
	      Sfondo strada5 = s(4); strada5.setIJ(3, 15);
	      Sfondo strada6 = s(4); strada6.setIJ(3, 14);
	      Sfondo strada7 = s(4); strada7.setIJ(3, 13);
	      Sfondo strada8 = s(4); strada8.setIJ(3, 12);
	      Sfondo strada9 = s(4); strada9.setIJ(3, 11);
	      Sfondo strada10 = s(4); strada10.setIJ(3, 10);
	      Sfondo strada11 = s(4); strada11.setIJ(3, 9);
	      Sfondo strada12 = s(4); strada12.setIJ(3, 8);
	      Sfondo strada13 = s(4); strada13.setIJ(3, 7);
	      Sfondo strada14 = s(4); strada14.setIJ(3, 6);
	      Sfondo strada15= s(4); strada15.setIJ(3, 5);
	      Sfondo strada16 = s(4); strada16.setIJ(3, 4);
	      Sfondo strada17 = s(4); strada17.setIJ(3, 3);
	      
	      lv2.add(strada0,strada1,strada2,strada3,strada4,strada5,strada6,
	    		  strada7,strada8,strada9,strada10,strada11,strada12,strada13,
	    		  strada14,strada15,strada16,strada17);
	      //png
	      Png moto = new Png(pg,0); moto.setIJ(5, 13);
	      lv2.add(moto);
	      //cartello
	      Cartello c20 = new Cartello("Che diamine ci fanno i vermi in casa?!","","","","","","","","","sembra un","BioParco!!!");
	      c20.setIJ(6,2);
	      lv2.add(c20);
	      //verme
	      Nemico verme20 = new Verme(); verme20.setIJ(8,15);
	      Nemico verme21 = new Verme(); verme21.setIJ(2,17);
	      Nemico verme22 = new Verme(); verme22.setIJ(4,19);
	      Nemico verme23 = new Verme(); verme23.setIJ(6,17);
	      Nemico verme24 = new Verme(); verme24.setIJ(1,14);
	      Nemico verme25 = new Verme(); verme25.setIJ(7,14);
	      Nemico verme26 = new Verme(); verme26.setIJ(3,18);

	      lv2.add(verme20,verme21,verme22,verme23,verme24,verme25,verme26);
	 
	      //scala
	      Sfondo s20 = s(2); s20.setIJ(1, 1);
	      lv2.add(s20);
	      //warp
	      Warp warp20 = new Warp(8,5,0); warp20.setIJ(1, 1);
	      lv2.add(warp20);
	      
	      m.addLivello(lv2);
	      ///////////////////////////////////////
	      Livello lv3 = new Livello();
	      
        Sprite[][] muriLv3 = {/*                                            1                                                          2
        /*      0     1     2     3     4     5     6     7     8     9     0     1     2     3     4     5     6     7     8     9    0
        /*0*/{m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22)},
        /*1*/{m(22),m(22),m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*2*/{m(22),m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*3*/{m(22),m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*4*/{m(22),m(22),m(22),m(22),m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*5*/{m(22),null ,null ,null ,null ,null ,null ,m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*6*/{null ,null ,null ,null ,null ,null ,null ,m(22),null, null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*7*/{null ,null ,null ,null ,null ,null ,null ,m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*8*/{null ,null ,m(22),m(22),m(22),null ,m(22),m(22),null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,null ,m(22)},
        /*9*/{m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22),m(22)},
        };
        lv3.generaMatrice();
        
        for(int i=0; i<10; i++){
	          for(int j=0; j<21; j++){
	              s = s(0);
	              s.setIJ(i, j);
	              lv3.add(s);
	               
	              if(muriLv3[i][j] == null)continue;
	               
	              s = muriLv3[i][j];
	              s.setIJ(i, j);
	              lv3.add(s);
	          }
	    }
        //nemici
        Nemico v30 = new Verme(); v30.setIJ(3, 10);
        Nemico v31 = new Verme(); v31.setIJ(4, 9);
        Nemico v32 = new Verme(); v32.setIJ(5, 8);
        Nemico v33 = new Verme(); v33.setIJ(6, 7);
        Nemico v34 = new Verme(); v34.setIJ(7, 6);
        Nemico v35 = new Verme(); v35.setIJ(8, 5);
      
        lv3.add(v30,v31,v32,v33,v34,v35,v34);
        // item - chiave
        Chiave c10 = new Chiave(10); c10.setIJ(4, 6);
        lv3.add(c10);
        //png
        Png rincoBabbo = new Png(pg,1); rincoBabbo.setIJ(3, 3);
        lv3.add(rincoBabbo);
        //warp
        Warp w31 = new Warp(8,19,1); w31.setIJ(8,0);
        Warp w41 = new Warp(6,19,1); w41.setIJ(6,0);
        Warp w51 = new Warp(7,19,1); w51.setIJ(7,0);
        lv3.add(w31,w41,w51);
        
        m.addLivello(lv3);
	      /////////////////////////////////////
	      m.setSpawnPg(3, 3);
	      m.setLivello(0);
	      return m;
    }
     
    // METODI DI SUPPORTO ////////////////
    private static Sfondo s(int n){return new Sfondo(n);}
    private static Muro m(int n){return new Muro(n);}
     
}