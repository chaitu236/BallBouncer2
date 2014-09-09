/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public class Slider extends TouchableRectangle implements Runnable{
    
    public static final double SLIDER_WIDTH=50;
    public static final double SLIDER_HEIGHT=8;
    public static final double MOVE_LENGTH=1.4;
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"slider50px.png";
    
    public static enum button{LEFT, RIGHT, NONE};
    
    public button buttonPressed;
    public boolean weaponized=false;
    
    public Image sliderImage;
    
    public Slider(){
        super((BallBouncer2.INIT_WIDTH-SLIDER_WIDTH)/2, BallBouncer2.INIT_HEIGHT-SLIDER_HEIGHT, SLIDER_WIDTH, SLIDER_HEIGHT);
        buttonPressed=button.NONE;
        
        sliderImage=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
    }
    
    @Override
    public void bounce(direction dir, Ball b){
        assert dir==TouchableRectangle.direction.TOP;
        
        double tempx=b.x+Ball.RADIUS;
        double fraction=(this.x+this.width-tempx)/this.width;
        //System.out.println("fraction ="+fraction);
        fraction=0.1+fraction*0.8; // limiting angle 
        double angle=fraction*Math.PI;
        b.setAngle(angle);
    }
    
    public void moveTo(double i){
        if(i<BallBouncer2.ARENA_X||i+width>BallBouncer2.INIT_WIDTH-BallBouncer2.ARENA_X)
            return;
        setFrame(i, getY(), getWidth(), getHeight());
    }
    
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Slider.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(buttonPressed==Slider.button.LEFT)
                moveTo(getX()-Slider.MOVE_LENGTH);
            else if(buttonPressed==Slider.button.RIGHT)
                moveTo(getX()+Slider.MOVE_LENGTH);
                         
        }
    }
}
