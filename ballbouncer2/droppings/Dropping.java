/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2.droppings;

import ballbouncer2.BallBouncer2;
import ballbouncer2.Slider;
import java.awt.Image;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public abstract class Dropping {
    public double x;
    public double y;
    private double velx;
    private double vely;
    
    protected double width;
    protected double height;
    
    public Image image;
    
    public static final double INIT_VELY=0.4;
    public static enum TYPE{BIG_SLIDER, SMALL_SLIDER, BOMB, SUPER_BALL, WEAPON, LIFE};
    
    public Dropping(double x, double y){
        this.x=x;
        this.y=y;
        velx=0;
        vely=INIT_VELY;
    }
    
    public void move(){
        y+=vely;
    }
    
    public boolean touches(Slider s, BallBouncer2 bb){
        
        if(x+width/2>=s.x && x+width/2<=s.x+s.width && y+height>=s.y){
            touchesSlider(s, bb);
            return true;
        }
        return false;
    }
    
    protected abstract void touchesSlider(Slider s, BallBouncer2 bb);
}
