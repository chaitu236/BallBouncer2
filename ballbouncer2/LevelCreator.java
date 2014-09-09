/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import ballbouncer2.droppings.Dropping;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public class LevelCreator {
    
    private BallBouncer2 bb;
    
    public static final double STARTX=14;
    public static final double STARTY=90;
    
    public static final double BRICK_SPACING=1;
    public static final double LINE_SPACING=2;    
    public static final double STONE_PROBABILITY=0.03;
    public static final double DROPPING_PROBABILITY=0.15;
    
    public LevelCreator(BallBouncer2 bb){
        this.bb=bb;
    }
    
//    public void createLevel(int level){
//        double x=STARTX;
//        double y=STARTY;
//        Brick b;
//        double temp;
//        boolean tempBool;
//        bb.stoneCount=0;
//        bb.lifeCount=(level==1)?5:level/2+2;
//        
//        for(;level>=0;level--){
//            x=STARTX+(Brick.WIDTH/2)*(level%2);
//            
//            while(x+Brick.WIDTH+BRICK_SPACING<BallBouncer2.INIT_WIDTH){
//                
//                tempBool=Math.random()<STONE_PROBABILITY?true:false;
//                if(tempBool)
//                    bb.stoneCount+=1;
//                
//                b=new Brick(x, y, tempBool);
//                x+=Brick.WIDTH+BRICK_SPACING;
//                
//                temp=Math.random();
//                
//                if(temp<DROPPING_PROBABILITY){
//                    temp=Math.random()*6;
//                    
//                    switch((int)temp){
//                        case 0: b.addDropping(Dropping.TYPE.BIG_SLIDER); break;
//                        case 1: b.addDropping(Dropping.TYPE.BOMB); break;
//                        case 2: b.addDropping(Dropping.TYPE.SMALL_SLIDER); break;
//                        case 3: b.addDropping(Dropping.TYPE.SUPER_BALL); break;
//                        case 4: b.addDropping(Dropping.TYPE.WEAPON); break;
//                        default: b.addDropping(Dropping.TYPE.LIFE);
//                    }
//                }
//                bb.bricks.add(b);
//            }
//            y+=Brick.HEIGHT+LINE_SPACING;
//        }
//    }
    
    public void createLevel(int level){
        
        if(level>11){
            bb.levelCount=1;
        }
        
        double x=STARTX;
        double y=STARTY;
        Brick b;
        double temp;
        boolean tempBool;
        bb.stoneCount=0;
        bb.lifeCount=(level==1)?5:level/2+2;
        
        int bCount=0;
        int inc=1;
        double sp;
        
        
        for(int k=0;level>=-1;level--){
        
            
            sp=x=STARTX+Brick.WIDTH*k;
            bCount=0;
            
            while(x+Brick.WIDTH+BRICK_SPACING<BallBouncer2.INIT_WIDTH-sp){
                
                tempBool=Math.random()<STONE_PROBABILITY?true:false;
                if(tempBool)
                    bb.stoneCount+=1;
                
                b=new Brick(x, y, tempBool);
                x+=Brick.WIDTH+BRICK_SPACING;
                
                temp=Math.random();
                
                if(temp<DROPPING_PROBABILITY){
                    temp=Math.random()*6;
                    
                    switch((int)temp){
                        case 0: b.addDropping(Dropping.TYPE.BIG_SLIDER); break;
                        case 1: b.addDropping(Dropping.TYPE.BOMB); break;
                        case 2: b.addDropping(Dropping.TYPE.SMALL_SLIDER); break;
                        case 3: b.addDropping(Dropping.TYPE.SUPER_BALL); break;
                        case 4: b.addDropping(Dropping.TYPE.WEAPON); break;
                        default: b.addDropping(Dropping.TYPE.LIFE);
                    }
                }
                bb.bricks.add(b);
                
                    bCount++;
            }
            
            k+=inc;
            
            if(bCount<=3)
                inc=-1;
            else if(bCount>11)
                inc=0;
            else
                inc=1;
            
            y+=Brick.HEIGHT+LINE_SPACING;
        }
    }
}
