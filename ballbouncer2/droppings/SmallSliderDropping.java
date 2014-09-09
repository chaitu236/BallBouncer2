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
public class SmallSliderDropping extends Dropping{

    public static final double SMALL_SLIDER_WIDTH=34;
    public static final double WIDTH=16;
    public static final double HEIGHT=5;
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"smallsliderdropping.png";
    public static final String SMALL_SLIDER_IMG_LOC=BallBouncer2.IMAGES_ROOT+"smallslider.png";
    
    public SmallSliderDropping(double x, double y){
        super(x,y);
        
        this.image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
        this.width=WIDTH;
        this.height=HEIGHT;
    }
    
    @Override
    protected void touchesSlider(Slider s, BallBouncer2 bb) {
        s.width=SMALL_SLIDER_WIDTH;
        s.weaponized=false;
        s.sliderImage=Toolkit.getDefaultToolkit().getImage(getClass().getResource(SMALL_SLIDER_IMG_LOC));
    }
    
}
