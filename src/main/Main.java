
package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


/*
Okay there are some pretty weird stuff going on here, and this has
been my project over about 2 years on and off so there are some serious
development issues with this program

1) I developed this at the start that could only work at one resolution,
I don't know what that monitor resolution was, but I know that when the JFrame
is maximised, the resolution is: [I wrote this down in a comment]
//Resolution of fullscreen is roughly 2194, 1234


1739
978



BASICALLY MOUSEX and MOUSEY need to AMPLIFIED SO THAT IT APPEARS TO BE ON THE BIG SCREEN
ANY INPUT NEEDS TO BE AMPLIFIED

ANY GRAPHICS NEED TO BE REDUCED
*/









public class Main extends JFrame {
    

    UI ui;
    final int virtualScreenWidth = 2194;
    final int virtualScreenHeight = 1234;
    int screenWidth, screenHeight;
    double scaleX;
    double scaleY;
    
    public Main() {
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Calculating the scale
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        scaleX = (double) virtualScreenWidth / screenWidth;
        scaleY = (double) virtualScreenHeight / screenHeight;
        System.out.println("scale"+scaleX);
        
        
        ui = new UI(scaleX, scaleY);
        add(ui);
        setVisible(true);
        
        setResizable(false);
        setTitle("Tower Defense");
            
        
        ui.startThread();
        
        System.out.println(screenWidth);
        System.out.println(screenHeight);
        
        
    }
    
    public static void main(String[] args) {

        Main main = new Main();
        
    }
    
}
