/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public class TouchableRectangle extends Rectangle2D.Double{

    public static enum direction{LEFT, RIGHT, TOP, BOTTOM, NONE};
        
    TouchableRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    //old one, something's wrong when the ball grazes the bricks
    /*public direction touches(Sphere s){
        
        if(s.y>=this.y && s.y+2*s.radius<=this.y+this.height && s.x+2*s.radius>=this.x && s.x<this.x){
            s.x=this.x-2*s.radius;
            return direction.LEFT;
        }
        if(s.y>=this.y && s.y+2*s.radius<=this.y+this.height && s.x<=this.x+this.width && s.x+2*s.radius>this.x+this.width){
            s.x=this.x+this.width;
            return direction.RIGHT;
        }
        if(s.x>=this.x && s.x+2*s.radius<=this.x+this.width && s.y+2*s.radius>=this.y && s.y<this.y){
            s.y=this.y-2*s.radius;
            return direction.TOP;
        }
        if(s.x>=this.x && s.x+2*s.radius<=this.x+this.width && s.y<=this.y+this.height && s.y+2*s.radius>=this.y+this.height){
            s.y=this.y+this.height;
            return direction.BOTTOM;
        }
        
        return direction.NONE;
    }*/
    
    public direction touches(Sphere s){
        
        if(s.y+s.radius>=this.y && s.y+s.radius<=this.y+this.height && s.x+2*s.radius>=this.x && s.x<this.x){
            s.x=this.x-2*s.radius;
            return direction.LEFT;
        }
        if(s.y+s.radius>=this.y && s.y+s.radius<=this.y+this.height && s.x<=this.x+this.width && s.x+2*s.radius>this.x+this.width){
            s.x=this.x+this.width;
            return direction.RIGHT;
        }
        if(s.x+s.radius>=this.x && s.x+s.radius<=this.x+this.width && s.y+2*s.radius>=this.y && s.y<this.y){
            s.y=this.y-2*s.radius;
            return direction.TOP;
        }
        if(s.x+s.radius>=this.x && s.x+s.radius<=this.x+this.width && s.y<=this.y+this.height && s.y+2*s.radius>=this.y+this.height){
            s.y=this.y+this.height;
            return direction.BOTTOM;
        }
        
        return direction.NONE;
    }
    
    public void bounce(direction dir, Ball b){
       
        switch(dir){
            case LEFT: b.velx=-b.velx;
                                       break;
            case RIGHT: b.velx=-b.velx;
                                        break;
            case TOP: b.vely=-b.vely;
                                         break;
            case BOTTOM: b.vely=-b.vely;
        }
    }
}
