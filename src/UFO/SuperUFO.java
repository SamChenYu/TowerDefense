
package UFO;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public final class SuperUFO {
    
    
    public BufferedImage image;
    public int speed,health, level;
    public int x = 0;
    public double y = 98;
    private int stage = 1; // this is used to handle the piecewise function for movement
    ArrayList<SuperUFO> ufos;
    
    final int[] healthLevel =   {120,200,220,50,600,620,1040};
    final int[] speedLevel =    {1,  1,  2,  3,  3,  1,  1};    
    
    public SuperUFO(int level, ArrayList<SuperUFO> ufos) {
        this.ufos = ufos;
        this.level = level;
        switch(level) {
            case(1):
                loadImage("res/UFO/UFO(1).png");
                break;
            case(2):
                loadImage("res/UFO/UFO(2).png");
                break;
            case(3):
                loadImage("res/UFO/UFO(3).png");
                break;
            case(4):
                loadImage("res/UFO/UFO(4).png");
                break;
            case(5):
                loadImage("res/UFO/UFO(5).png");
                break;
            case(6):
                loadImage("res/UFO/UFO(6).png");
                break;
            case(7):
                loadImage("res/UFO/UFO(7).png");
                break;
        }
        
        health = healthLevel[level-1];
        speed = speedLevel[level-1];
    }
    
    public void loadImage(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
        } catch(IOException e) {
            System.out.println(e);
            System.out.println("UFO image loading failed");
        }
    }
    
    
    public void draw(Graphics2D g2) {
        g2.drawImage(image,x,(int) y,64,80,null);
        
        //Drawing healthbar
        double percentage = (double) (healthLevel[level-1] - health) / healthLevel[level-1] * 64;
        g2.setColor(Color.GREEN);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2.fillRect(x,(int)y-10,64,10);
        g2.setColor(Color.RED);
        g2.fillRect(x,(int)y-10,(int)percentage,10);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
    public void update() {
        
        
        if(health < 1 && ufos.contains(this)) {
            ufos.remove(this);
        }    
        
        //below is a piecewise function that guides the UFO
        /*
        
        y\ =\ 0.59322034x+98\ \left\{0<x\le438\right\}
        y\ =-0.595395x+618.614\ \left\{90\le x\le435\right\}
        y\ =\ 0.59322034x+511.442\left\{90\le x\le330\right\}
        y\ =-0.595395x+903.685\left\{330\le x\le1266\right\}
        y\ =0.595395x-603.855\left\{1266\le x\le1620\right\}
        y\ =-0.595395x+1325.22\left\{1030\le x\le1620\right\}
        y\ =0.595395x+98.7062\left\{1030\le x\le1380\right\}
        y\ =-0.595395x+1759.86\left\{1380\le x\le2000\right\}
        
        */
        
            switch(stage) {
                case(1):
                    //going down
                    x+=speed;
                    y = (0.59322034) * (double)(x) + 98;
                    
                    if(x >= 448) { stage++; }
                    
                    break;
                case(2):
                    //going left
                    x-=speed;
                    y = (-0.59322034) * (double)(x) + 624.568;
                    
                    if(x <=90 ) { stage++; }
                    
                    break;
                case(3):
                    //going down
                    x+=speed;
                    y = (0.59322034) * (double)(x) + 511.442;
                    
                    if(x >= 334) { stage++; }
                    
                    break;
                case(4):
                    //going right
                    x+=speed;
                    y = (-0.59322034) * (double)(x) + 903.685;
                    
                    if(x >=1266 ) { stage++; }
                    
                    break;
                case(5):
                    //going down
                    x+=speed;
                    y = (0.59322034) * (double)(x) - 603.855 ;
                    
                    if(x >= 1620) { stage++; }
                    
                    break;
                case(6):
                    x-=speed;
                    y = (-0.59322034) * (double)(x) + 1325.22;
                    
                    if(x <=1030 ) { stage++; }
                    
                    break;
                case(7):
                    //going down
                    x+=speed;
                    y = (0.59322034) * (double)(x) + 98.7062;
                    
                    if(x >= 1390) { stage++; }
                    
                    break;
                case(8):
                    //going right
                    x+=speed;
                    y = (-0.59322034) * (double)(x) + 1742;
                    
                    if(x >=2200 ) { stage++; }
                    
                    break;
                case(9):
                    System.out.println("UFO breached, health--");
                    System.out.println(".// not yet implemeneted tho");
                    ufos.remove(this);
                    break;
            }
            
            
            
            
            
        
    }    
}
