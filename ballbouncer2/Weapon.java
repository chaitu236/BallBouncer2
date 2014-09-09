/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import java.awt.Toolkit;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public class Weapon extends Sphere{
    
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"weapon.png";
    public static final double START_VELY=-1;
    public static final double RADIUS=6;
    
    public Weapon(double x, double y){
        super(x, y, 2*RADIUS, 2*RADIUS);
        
        velx=0;
        vely=START_VELY;
        radius=RADIUS;
        
        image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));
    }
}
