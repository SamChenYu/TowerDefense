
package main;

import Player.Player;
import Player.SuperArcher;
import Player.SuperBarrack;
import Player.SuperWizard;
import UFO.SuperUFO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tile.Map;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UI extends JPanel implements Runnable {
    
    //JPanel Variables
    Thread gameThread;
    Color bg = Color.decode("#23395d");
    Map map;
    double scaleX, scaleY;
    
    //Game Variables
    
    ArrayList<SuperUFO> ufos = new ArrayList<>();
    SuperUFO ufo = new SuperUFO(1, ufos);
    Player player = new Player(ufos);
    
    //Runnable Variables
    long threshold = 16; // slightly more than 60fps
    boolean alive = true;
    
    //GUI Variables
    SuperWizard wiz = new SuperWizard(ufos);
    SuperArcher arch = new SuperArcher(ufos);
    SuperBarrack barr = new SuperBarrack();
    BufferedImage wizard = wiz.image, archer = arch.image, barrack = barr.image, crystal, shield;
    boolean wizardSelected = false, archerSelected = false, barrackSelected = false, crystalSelected = false;
    int mouseX=0, mouseY=0;
    float transparency = 0.6f;
    Font font = new Font("Arial", Font.BOLD,50);
    
    //Mouse Listeners
    MouseAdapter mouseA= new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                mouseX = (int) Math.round(mouseX * scaleX);
                mouseY = (int) Math.round(mouseY * scaleY);
                System.out.println(mouseX + " " + mouseY);
                //FIRST CHECKS IF THE Y level is satisified. if not, can save computing space and carry on
                // then if checks for the individual bounds
                // however the reason for so many nested is that if it is already selected,
                //pressing it again will unselect it
                if(mouseY<200 && player.crystal > 150) {
                    if(mouseX > 1500 && mouseX < 1600) {
                        if(wizardSelected) {
                            wizardSelected = false;
                        } else {
                            wizardSelected = true;
                            archerSelected = false;
                            barrackSelected = false;
                            crystalSelected = false;
                        }
                    } else if(mouseX > 1600 && mouseX < 1800) {
                        if(archerSelected) {
                            archerSelected = false;
                        } else {
                            wizardSelected = false;
                            archerSelected = true;
                            barrackSelected = false;
                            crystalSelected = false;
                        }
                    } else if(mouseX > 1800 && mouseX < 1990) {
                        if(barrackSelected) {
                            barrackSelected = false;
                        } else {
                            wizardSelected = false;
                            archerSelected = false;
                            barrackSelected = true;
                            crystalSelected = false;
                        }
                    } else if(mouseX > 1990 && mouseX < 2115) {
                        if(crystalSelected) {
                            crystalSelected = false;
                        } else {
                        wizardSelected = false;
                        archerSelected = false;
                        barrackSelected = false;
                        crystalSelected = true;
                        }
                    }
                }

                
                // ADD THE TOWER IF IT IS CLICKED
                
                if(!(mouseY < 200 && mouseX >1500)) {
                    if(wizardSelected) {
                        //Then the tower will be placed
                        player.newWizard(map.getRow(), map.getCol());
                        wizardSelected = false;
                    }
                }
                
                if(!(mouseY < 200 && mouseX >1500)) {
                    if(archerSelected) {
                        //Then the tower will be placed
                        player.newArcher(map.getRow(), map.getCol());
                        archerSelected = false;
                    }
                }
                
                
                
                
            }
    };
    MouseMotionListener mouseL = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e){
            mouseX = e.getX();
            mouseY = e.getY();
            mouseX = (int) Math.round(mouseX * scaleX);
            mouseY = (int) Math.round(mouseY * scaleY);
            if(mouseY<200 && player.crystal > 150) {
                if(mouseX > 1500 && mouseX < 1600) {
                        wizardSelected = true;
                        archerSelected = false;
                        barrackSelected = false;
                        crystalSelected = false;

                } else if(mouseX > 1600 && mouseX < 1800) {
                        wizardSelected = false;
                        archerSelected = true;
                        barrackSelected = false;
                        crystalSelected = false;
                } else if(mouseX > 1800 && mouseX < 1990) {
                        wizardSelected = false;
                        archerSelected = false;
                        barrackSelected = true;
                        crystalSelected = false;
                } else if(mouseX > 1990 && mouseX < 2115) {
                    wizardSelected = false;
                    archerSelected = false;
                    barrackSelected = false;
                    crystalSelected = true;
                }
            }
            if(wizardSelected || archerSelected || barrackSelected || crystalSelected) {
                //Calculating the tile to change on the map
                map.updateTile(mouseX,mouseY);
            }

            repaint();
        } 
        @Override 
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            mouseX = (int) Math.round(mouseX * scaleX);
            mouseY = (int) Math.round(mouseY * scaleY);
            
            if(wizardSelected || archerSelected || barrackSelected || crystalSelected) {
                //Calculating the tile to change on the map
                map.updateTile(mouseX,mouseY);
            }
            repaint();
        };
    };
    KeyListener keyL = new KeyListener() {
        
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
            switch(keyCode) {
                case(KeyEvent.VK_1):
                    SuperUFO na = new SuperUFO(1, ufos);
                    ufos.add(na);
                    break;
                case(KeyEvent.VK_2):
                    na = new SuperUFO(2, ufos);
                    ufos.add(na);
                    break;
                case(KeyEvent.VK_3):
                    na = new SuperUFO(3, ufos);
                    ufos.add(na);
                    break;
                case(KeyEvent.VK_4):
                    na = new SuperUFO(4, ufos);
                    ufos.add(na);
                    break;
                case(KeyEvent.VK_5):
                    na = new SuperUFO(5, ufos);
                    ufos.add(na);
                    break;
                case(KeyEvent.VK_6):
                    na = new SuperUFO(6, ufos);
                    ufos.add(na);
                    break;
                case(KeyEvent.VK_7):
                    na = new SuperUFO(7, ufos);
                    ufos.add(na);
                    break;
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
            
        }
    };
    
    
    
    public UI(double scaleX, double scaleY) {
        
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        
        setBackground(bg);
        startThread();
        map = new Map(this);
        
        addMouseListener(mouseA);
        addMouseMotionListener(mouseL);
        addKeyListener(keyL);
        setFocusable(true); // methods to allow focus for keylistener
        requestFocusInWindow();
        //Getting additional images for GUI
        try {
            File file = new File("res/landscape/crystal(1).png");
            FileInputStream fis = new FileInputStream(file);
            crystal = ImageIO.read(fis);
            
            file = new File("res/towers/barrack/shield.png");
            fis = new FileInputStream(file);
            shield = ImageIO.read(fis);
            
        }   catch(IOException e) {
                System.out.println(e);
                System.out.println("gui tower/archer/barrack io issue");
        }
    }
    
    
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    public void drawUI(Graphics g) {
        

        // Cast to Graphics2D for more advanced drawing operations
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the color with desired transparency
        Color transparentColor = new Color(0, 0, 0, transparency);

        // Set the composite to enable transparency
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));

        // Draw the semi-transparent rectangle
        int x = 1500;
        int y = 0;
        int width = 800;
        int height = 200;
        g2d.setColor(transparentColor);
        g2d.fillRect(x, y, width, height);

        //Reset the transparency to full
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(wizard,1500,28,150,126,null);
        g2d.drawString("150",1525,200);
        g2d.drawImage(archer,1675,0,130,166,null);
        g2d.drawString("200",1700,200);
        g2d.drawImage(barrack,1830,0,142,154,null);
        g2d.drawString("250",1850,200);
        g2d.drawImage(crystal,2022,0,82,131,null);
        g2d.drawString(""+player.crystal,2022,200);
        g2d.drawImage(shield,2129,50,69,74,null);
        g2d.drawString(""+player.health,2129,200);
        // Dispose the Graphics2D object
        g2d.dispose();
              
    }
    

    @Override
    public void run() {
        long timeNow = System.currentTimeMillis();
        while(alive) {
            
            if(System.currentTimeMillis() - timeNow >= threshold) {
                    
                    for(int i=0; i<ufos.size(); i++) {
                        ufos.get(i).update();
                    }
                    player.update();
                    repaint();
                    timeNow = System.currentTimeMillis();
                }
            }    
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.scale(1/scaleX, 1/scaleY); // this is the line that saved my life from redoing the entire dev process again cuz I made it at one resolution
        map.draw(g2);
        
        for(int i=0; i<ufos.size(); i++) {
            ufos.get(i).draw(g2);
        }
        player.draw(g2);
        drawUI(g2);
        if(wizardSelected) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new java.awt.BasicStroke(4));
            g2.drawRect(1500,0,150,150);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            g2.drawOval(mouseX-(wiz.range),mouseY-(wiz.range),wiz.range*2,wiz.range*2);
            g2.drawImage(wiz.image,mouseX-75,mouseY-63,150,126,null);
        } else if(archerSelected) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new java.awt.BasicStroke(4));
            g2.drawRect(1675,0,130,166);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            g2.drawOval(mouseX-(arch.range),mouseY-(arch.range),arch.range*2,arch.range*2);
            g2.drawImage(archer,mouseX-60,mouseY-83,130,166,null);
        } else if(barrackSelected) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new java.awt.BasicStroke(4));
            g2.drawRect(1830,0,142,166);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            g2.drawOval(mouseX-(barr.range),mouseY-(barr.range),barr.range*2,barr.range*2);
            g2.drawImage(barrack,mouseX-71,mouseY-83,142,166,null);
        } else if(crystalSelected) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new java.awt.BasicStroke(4));
            g2.drawRect(2022,0,82,131);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            g2.drawImage(crystal,mouseX-41,mouseY-65,82,131,null);
        }
        g2.dispose();
    }    
    



}
