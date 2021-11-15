package oggetti_di_gioco;
 
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
 
public abstract class Sprite_AB implements Sprite, Serializable{
    private static final long serialVersionUID = 1633733431933207907L;
 
     
    protected int altezza = 60, larghezza = 60;
    protected int i,j,x,y,priorita = -1;
 
    protected Rectangle2D bordi = new Rectangle();
     
    private BufferedImage img;
    protected BufferedImage collezione;
     
    protected String pathImg;
     
    public Sprite_AB(){}
     
    public Sprite_AB(int i, int j){
        setIJ(i,j);
    }
     
    // GET ///////////////////////
    public BufferedImage getImg(){return img;}
    public Rectangle2D getBounds(){return bordi;}
    public int i(){return i;}
    public int j(){return j;}
    public int x(){return x;}
    public int y(){return y;}
    public int getAltezza(){return altezza;}
    public int getLarghezza(){return larghezza;}
    public BufferedImage collezione(){return collezione;}
    public int getPriorita(){return priorita;}
         
    // SET ////////////////////
    public void setLarghezza(int l){larghezza=l;}
    public void setAltezza(int h){altezza=h;}
    public void setCollezione(BufferedImage img){collezione=img;}
     
    public void setIJ(int ii, int jj){
        i=ii;
        j=jj;
         
        x=j*60;
        y=i*60;
        bordi.setFrame(x, y, larghezza, altezza);
 
    }
    public void setI(int ii){
        i=ii;
        y=i*60;
        bordi.setFrame(x, y, larghezza, altezza);
 
    }
    public void setJ(int jj){
        j=jj;
        x=j*60;
        bordi.setFrame(x, y, larghezza, altezza);
 
    }
    public void setXY(int xx, int yy){
        x=xx;
        y=yy;
        bordi.setFrame(x, y, larghezza, altezza);
 
        i=(y+(altezza/2))/60;
        j=(x+(larghezza/2))/60;
        
    }
    public void setX(int xx){
        x=xx;
        j=(x+(larghezza/2))/60;
        bordi.setFrame(x, y, larghezza, altezza);
         
    }
    public void setY(int yy){
        y=yy;
        i=(y+(altezza/2))/60;
        bordi.setFrame(x, y, larghezza, altezza);
         
    }
    public void setPathImage(String imgg){
        try {
            pathImg = imgg;
            img = ImageIO.read(getClass().getResource("/"+pathImg+".png"));
        } catch
        (IOException e)
        {e.printStackTrace();}
     
    }
    public void setImg(BufferedImage imgg){
        img = imgg;
         
    }
    // METHODS ////////////////////////
    public boolean collide(Sprite s){
        return bordi.intersects(s.getBounds());
     
    }
    public void preparaSalvataggio(){
        img = null;
         
    }
    public void caricaSalvataggio(){
        try {
            img = ImageIO.read(getClass().getResource("/"+pathImg+".png"));
        } catch (IOException e) {e.printStackTrace();}
     
    }
}