 
package Player;

import UFO.SuperUFO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class SuperWizard {
    
    public BufferedImage image;
    public int x,y;
    int damage = 30;
    private long threadAttackInterval; // temp var to calculate when to attack
    private long attackInterval = 2000; // attack every 2 seconds
    public int range = 200;
    
    ArrayList<SuperUFO> ufos;
    ArrayList<EnergyBall> balls = new ArrayList<>();
    
    
    public SuperWizard(ArrayList<SuperUFO> ufos) {
        threadAttackInterval = System.currentTimeMillis();
        this.ufos = ufos;
        
        try {
            File file = new File("res/towers/wizard/wizard_level_1.png");
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
        } catch(IOException e) {
            System.out.println(e);
            System.out.println("wizard image loading failed");
        }
    }
    
    public void draw(Graphics2D g2) {
        g2.drawImage(image,x,y,150,126,null);
        for(int i=0; i<balls.size();i++) {
            balls.get(i).draw(g2);
        }
    }
    
    
    public void placed(int arrayX, int arrayY) {
        //the location of tile will be parsed in
        //then we will calculate the coordinates of the actual x and y plane for the tower to be placed
        if(arrayY%2 == 0) {
            x = (arrayX * 235) +60; //tileWidth
            y = arrayY * 70;
        } else {
            x = (arrayX * 235) - 65;
            y = arrayY * 70;
        }
        
    }
    
    public void update() {
        
        // check if there are any nearby ufos
        //firstly, how do we access the ufos? -> damn, looks like we have to do multilayer dependency injections
        // if cooldown isn't active, then you can shoot
        
        //step one check for nearest ufo and whether it is in range or not

        
        int closetsUfo = 100;
        boolean hasTarget;
        double distance = range + 1; //  distance is set to range++ so that it will not initially engage anything by default
        if(ufos.isEmpty()) {
            hasTarget = false;
            distance = range+1;
        } else if(ufos.size() == 1){
            hasTarget = true;
            closetsUfo = 0;
            distance = Math.sqrt( ((double) x - ufos.get(0).x)*((double) x - ufos.get(0).x) + ((double) y - ufos.get(0).y)*((double) y - ufos.get(0).y));
        } else {
            closetsUfo = 0;
            hasTarget = true;
            for(int i =0; i < ufos.size(); i++) {
                distance = Math.sqrt( ((double) x - ufos.get(i).x)*((double) x - ufos.get(i).x) + ((double) y - ufos.get(i).y)*((double) y - ufos.get(i).y));
                double tempDistance = Math.sqrt( ((double) x - ufos.get(closetsUfo).x)*((double) x - ufos.get(closetsUfo).x) + ((double) y - ufos.get(closetsUfo).y)*((double) y - ufos.get(closetsUfo).y));
                if (distance < tempDistance) {
                    closetsUfo = i;
                }
            }
        }        
        if(distance <= range  && System.currentTimeMillis()-threadAttackInterval > attackInterval && hasTarget) {
//            System.out.println(this + " attack called");
//            System.out.println(ufos.size());
//            System.out.println(distance);
//            System.out.println(System.currentTimeMillis()-attackInterval);
//            System.out.println(hasTarget);
            attack(closetsUfo);
            threadAttackInterval = System.currentTimeMillis();
        }
        
        for(int i=0; i<balls.size();i++){
            balls.get(i).update();
        }
    }
    
    public void attack(int closest) {
        EnergyBall ball = new EnergyBall(ufos.get(closest));
        balls.add(ball);
//        System.out.println("ball added");
    }
    
    
    
    
    public class EnergyBall {
        
        
        
        public BufferedImage image;
        int x,y;
        SuperUFO target;
        
        public EnergyBall(SuperUFO ufo) {
            x = SuperWizard.this.x;
            y = SuperWizard.this.y;
            target = ufo;
            try {
                File file = new File("res/towers/wizard/wizard_bullet.png");
                FileInputStream fis = new FileInputStream(file);
                image = ImageIO.read(fis);
            } catch(IOException e) {
                System.out.println(e);
                System.out.println("wizard ernergy ball loading failed");
            }
        }
        
        
        public void update() {
            
                if (ufos.isEmpty()) {
                    balls.clear();
                } 
                else {
                    try {
                        if( x == target.x) {
                            // do nothing
                        } else if (x < target.x) {
                            x+=3;
                        } else if (x > target.x) {
                            x-=3;
                        }

                        if( y == target.y) {
                            // do nothing
                        } else if (y < target.y) {
                            y+=3;
                        } else if (y > target.y) {
                            y-=3;
                        }

                        // calculating distance between ball and the UFO
                        double distance = Math.round( (target.x-x)*(target.x-x) + (target.y-y)*(target.y-y));

                        if(distance < 3) {

                            target.health -= damage; // damage the ball does to the target
        //                    System.out.println("ball removed");
                            balls.remove(this);               
                        }
                    } catch (Exception e) {
                        System.out.println(e);
        //                System.out.println("ball with no target");
                        balls.remove(this);
                    }

                }
        }
        
        public void draw(Graphics2D g2) {
            g2.drawImage(image,x,y,14,15,null);
            
        }
    }
    
    
    
}