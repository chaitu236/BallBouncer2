/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import java.awt.Image;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public abstract class Sphere extends Ellipse2D.Double{
    public double velx;
    public double vely;
    public double radius;
    
    public Image image;
    
    protected Sphere(double x, double y, double width, double height){
        super(x, y, width, height);
    }
    
    public void move(){
        x+=velx;
        y+=vely;
    }
}
