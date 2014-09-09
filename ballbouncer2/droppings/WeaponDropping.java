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
public class WeaponDropping extends Dropping{    
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"weapon.png";
    public static final String WEAPONIZED_SLIDER_LOC=BallBouncer2.IMAGES_ROOT+"weaponizedslider50px.png";
    public static final double WEAPONIZED_SLIDER_HEIGHT=12; //not updated in actual slider to be looked into
    public static final double WEAPONIZED_SLIDER_WIDTH=50;
    
    public static final double WIDTH=11;
    public static final double HEIGHT=19;
    
    public WeaponDropping(double x, double y){
        super(x, y);
        
        this.image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
        this.width=WIDTH;
        this.height=HEIGHT;        
    }
    
    @Override
    protected void touchesSlider(Slider s, BallBouncer2 bb) {
        if(s.weaponized)
            return;
        s.weaponized=true;
        s.y-=WEAPONIZED_SLIDER_HEIGHT-Slider.SLIDER_HEIGHT;
        s.width=WEAPONIZED_SLIDER_WIDTH;
        
        s.sliderImage=Toolkit.getDefaultToolkit().getImage(getClass().getResource(WEAPONIZED_SLIDER_LOC));
    }
}
