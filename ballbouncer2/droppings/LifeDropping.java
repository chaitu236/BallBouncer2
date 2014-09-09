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
 * @author chaitanya
 */
public class LifeDropping extends Dropping{
    
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"life.png";
    public static final double WIDTH=16;
    public static final double HEIGHT=16;
    
    public LifeDropping(double x, double y){
        super(x, y);
        
        this.image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
        this.width=WIDTH;
        this.height=HEIGHT;
    }

    @Override
    protected void touchesSlider(Slider s, BallBouncer2 bb) {
        bb.lifeCount++;
    }
}
