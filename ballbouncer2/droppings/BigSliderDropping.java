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
public class BigSliderDropping extends Dropping{

    public static final double BIG_SLIDER_WIDTH=76;    
    public static final double WIDTH=16;
    public static final double HEIGHT=5;
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"bigsliderdropping.png";
    public static final String BIG_SLIDER_IMG_LOC=BallBouncer2.IMAGES_ROOT+"bigslider.png";
    
    public BigSliderDropping(double x, double y){
        super(x,y);
        
        this.image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
        this.width=WIDTH;
        this.height=HEIGHT;
    }
    
    @Override
    protected void touchesSlider(Slider s, BallBouncer2 bb) {
        s.width=BIG_SLIDER_WIDTH;
        s.weaponized=false;
        s.sliderImage=Toolkit.getDefaultToolkit().getImage(getClass().getResource(BIG_SLIDER_IMG_LOC));
    }
    
}
