package src.utl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Resource {
    public  static BufferedImage getResourceImage(String path){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(path));
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        return img;
    }

    public static InputStream getResourceStream(String string) {
        return null;
    }
}
