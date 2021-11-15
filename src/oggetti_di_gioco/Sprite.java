package oggetti_di_gioco;
 
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
 
public interface Sprite {
 
    // GET ///////////////////////
    /**
     * Restituisce la collezione di immagini nel caso di oggetti muniti di animazione
     * @return collezione immagini per l'animazione
     */
    BufferedImage collezione();
    /**
     * Restituisce l'immagine dello Sprite
     * @return immagine dello sprite
     */
    BufferedImage getImg();
    /**
     * Restituisce i bordi dell'area di collisione
     * @return bordi dell'area di collisione
     */
    Rectangle2D getBounds();
    boolean collide(Sprite s);
    int x();
    int y();
    int i();
    int j();
    int getAltezza();
    int getLarghezza();
    int getPriorita();
     
    // SET ////////////////////
    void setIJ(int i, int j);
    void setXY(int xx, int yy);
    void setX(int x);
    void setY(int y);
    void setImg(BufferedImage img);
    void setPathImage(String path);
     
    // METHODS ////////////////
    void preparaSalvataggio();
    void caricaSalvataggio();
     
    // STATICS ////////////////
    public static BufferedImage loadImg(String file){       
        BufferedImage sprite = null;
         
        try{
        sprite = ImageIO.read(Sprite.class.getResource("/"+file+".png"));
        }catch(IOException e){e.printStackTrace();}
         
        return sprite;
    }
}