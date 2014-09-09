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
public class Ball extends Sphere{
    //private Ellipse2D.Double ball;
    
    //public double velx;
    //public double vely;
    //public double radius;
    
    public static final double RADIUS=5;
    public static final double STARTX=BallBouncer2.INIT_WIDTH/2;
    public static final double STARTY=BallBouncer2.INIT_HEIGHT-20;
    public static final double START_VELX=0.8;
    public static final double START_VELY=-0.6;
    
    //public Image ballImage;
    public static final String IMG_LOC=BallBouncer2.IMAGES_ROOT+"ball.png";
    
    public boolean isSuper=false;
        
    public Ball(){
        super(STARTX, STARTY, 2*RADIUS, 2*RADIUS);
        
        velx=START_VELX;
        vely=START_VELY;
        radius=RADIUS;
        
        image=Toolkit.getDefaultToolkit().getImage(getClass().getResource(IMG_LOC));        
    }
        
    public void setAngle(double angle){
        //System.out.println("angle ="+angle*180/Math.PI);
        double vel=Math.sqrt(velx*velx+vely*vely);
        
        velx=vel*Math.cos(angle);
        vely=-vel*Math.sin(angle);
    }
}
