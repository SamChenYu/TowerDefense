
package Player;

import UFO.SuperUFO;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Player {
    
    public int crystal, health;
    ArrayList<SuperWizard> wizard = new ArrayList<>();
    ArrayList<SuperArcher> archer = new ArrayList<>();
    ArrayList<SuperBarrack> barrack = new ArrayList<>();
    
    ArrayList<SuperUFO> ufos;
    
    public Player(ArrayList<SuperUFO> ufos) {
        
        crystal = 300;
        health = 10;
        
        this.ufos = ufos;
        
        
        
    }
    
    public void draw(Graphics2D g2) {
        for(int i=0;i<wizard.size();i++) {
            wizard.get(i).draw(g2);
        }
        for(int i=0;i<archer.size();i++) {
            archer.get(i).draw(g2);
        }
//        for(int i=0;i<barrack.size();i++) {
//            barrack.get(i).draw(g2);
//        }
    }
    
    public void update() {
        for(int i=0;i<wizard.size();i++) {
            wizard.get(i).update();
        }
        for(int i=0;i<archer.size();i++) {
            archer.get(i).update();
        }
//        for(int i=0;i<barrack.size();i++) {
//            barrack.get(i).update();
//        }
    }
    
    public void newWizard(int x, int y) {
        SuperWizard temp = new SuperWizard(ufos);
        temp.placed(x, y);
        wizard.add(temp);
    }

    public void newArcher(int x, int y) {
        SuperArcher temp = new SuperArcher(ufos);
        temp.placed(x,y);
        archer.add(temp);
    }
}
