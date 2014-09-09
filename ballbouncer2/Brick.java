/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import ballbouncer2.droppings.BigSliderDropping;
import ballbouncer2.droppings.BombDropping;
import ballbouncer2.droppings.Dropping;
import ballbouncer2.droppings.LifeDropping;
import ballbouncer2.droppings.SmallSliderDropping;
import ballbouncer2.droppings.SuperBallDropping;
import ballbouncer2.droppings.WeaponDropping;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public class Brick extends TouchableRectangle{
    
    public Dropping dropping;
    public Image brickImage;
    public boolean stone;
    
    public static final double WIDTH=49;
    public static final double HEIGHT=27;
    
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"brick.png";
    public static final String STONE_IMG_LOC=BallBouncer2.IMAGES_ROOT+"stone.png";
    
    public Brick(double x, double y, boolean stone){
        super(x, y, WIDTH, HEIGHT);
        this.stone=stone;
        
        if(stone)
            brickImage=Toolkit.getDefaultToolkit().getImage(getClass().getResource(STONE_IMG_LOC));
        else
            brickImage=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
    }
    
    public void addDropping(Dropping.TYPE type){
        if(type==null || stone)
            return;
        
        switch(type){
            case BIG_SLIDER: dropping=new BigSliderDropping(x+(width-BigSliderDropping.WIDTH)/2, y+height);
                             break;
            case SMALL_SLIDER: dropping=new SmallSliderDropping(x+(width-BigSliderDropping.WIDTH)/2, y+height);
                               break;
            case BOMB: dropping=new BombDropping(x+(width-BigSliderDropping.WIDTH)/2, y+height);
                       break;
            case SUPER_BALL: dropping=new SuperBallDropping(x+(width-BigSliderDropping.WIDTH)/2, y+height);
                             break;
            case WEAPON: dropping=new WeaponDropping(x+(width-BigSliderDropping.WIDTH)/2, y+height);
                         break;
            case LIFE: dropping=new LifeDropping(x+(width-BigSliderDropping.WIDTH)/2, y+height);
                         break;
        }
    }
}
