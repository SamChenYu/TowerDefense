
package Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SuperBarrack {
    
    
    public BufferedImage image;
    int x,y;
    int damage;
    public int range = 600;
    long attackInterval;
    
    public SuperBarrack() {
        
        try {
            File file = new File("res/towers/barrack/barrack_level_1 (1).png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
        } catch(IOException e) {
            System.out.println(e);
            System.out.println("barrack image loading failed");
        }
    
        
        
        
        
        
    }
    
    public void draw(Graphics2D g2) {
        g2.drawImage(image,x,y,150,126,null);
    }
    
    
}
