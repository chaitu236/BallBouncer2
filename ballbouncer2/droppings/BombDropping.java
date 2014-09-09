/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2.droppings;

import ballbouncer2.BallBouncer2;
import ballbouncer2.Slider;
import java.awt.Toolkit;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public class BombDropping extends Dropping{
    
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"bomb.png";
    public static final double WIDTH=16;
    public static final double HEIGHT=20;
    
    public BombDropping(double x, double y){
        super(x, y);
        
        this.image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
        this.width=WIDTH;
        this.height=HEIGHT;
    }

    /*
     * Game over or Restart level
     */
    @Override
    protected void touchesSlider(Slider s, BallBouncer2 bb) {
        if(--bb.lifeCount<0){
            bb.gameOver();
            return;
        }
        bb.levelCount--;
        bb.nextLevel();
    }    
}
