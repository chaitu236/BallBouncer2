/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import ballbouncer2.droppings.Dropping;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.swing.JFrame;

/**
 *
 * @author Chaitanya Vadrevu
 * This program is licensed under GNU GPLv3 or above.
 */
public class BallBouncer2 extends JFrame implements Runnable, KeyListener, MouseMotionListener, MouseListener {

    public Ball ball;
    private ArrayList<TouchableRectangle> rectangles;
    private Image image;
    private Graphics2D imgGraphics;
    private Slider slider;
    public ArrayList<Brick> bricks;
    private ArrayList<Dropping> droppings;
    private Weapon weapon;
    private LevelCreator levelCreator;
    public boolean stopThread;
    public int stoneCount;
    public int lifeCount;
    public int levelCount=1;
    
    private int scoreCount;
    private boolean showScore;
    private boolean justShowImg; // used to signal paint method that imgGraphics is drawn by calling method already.
    private Scores scores;
    private Image initImage;
    //private Image oImage;
        
    public static final String IMAGES_ROOT = "images/";
    public static final String AUDIO_ROOT = "audio/";
    public static final int INIT_WIDTH = 600;
    public static final int INIT_HEIGHT = 500;
    public static final long DELAY = 2;
    public static final int BRICK_SCORE=10;
    public static final int LIFE_SCORE=50;
    
    public static final int ARENA_X=10;
    public static final int ARENA_Y=60;
    public static final int BOUNDARY_WIDTH=2;
    
    public static final String INIT_SCREEN_IMG_LOC=IMAGES_ROOT+"init screen.png";
    //public static final String O_IMG_LOC=IMAGES_ROOT+"o.gif";
    
    public BallBouncer2() {
        super("Ball Bouncer 2");
        this.setSize(INIT_WIDTH, INIT_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        levelCreator = new LevelCreator(this);
        scores=new Scores();

        //init();
        stopThread=true;
        initSound();    //to be removed if found unsuitable
    }

    private void init() {
        ball = new Ball();

        rectangles = new ArrayList<TouchableRectangle>(4);
        slider = new Slider();
        //new Thread(slider).start(); // to be looked into if really required
        createBoundaries();

        bricks = new ArrayList<Brick>();
        droppings = new ArrayList<Dropping>();
        weapon = null;
        justShowImg=false;

        //createBricks();
        //levelCreator.createLevel(levelCount++);
    }

    public static void main(String[] args) {
        BallBouncer2 bb = new BallBouncer2();
        bb.addKeyListener(bb);
        bb.addMouseMotionListener(bb);
        bb.addMouseListener(bb);
        /*bb.levelCreator.createLevel(1);

        Thread th = new Thread(bb);
        th.start();*/
        //bb.newGame();
    }

    private void createBoundaries() {
        rectangles.add(new TouchableRectangle(ARENA_X,ARENA_Y, this.getSize().width-2*ARENA_X, BOUNDARY_WIDTH));
        rectangles.add(new TouchableRectangle(ARENA_X, ARENA_Y, BOUNDARY_WIDTH, getSize().height - ARENA_Y));
        rectangles.add(new TouchableRectangle(getSize().width - ARENA_X, ARENA_Y, BOUNDARY_WIDTH, getSize().height - ARENA_Y));
        //repaint();
    }

    private void initImage() {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);

            assert image.getGraphics() instanceof Graphics2D;

            imgGraphics = (Graphics2D) image.getGraphics();
        }
    }

    /*
     * Fill background i.e., clear screen
     */
    private void clearBackground() {
        imgGraphics.setColor(getBackground());
        imgGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
    /*
     * Draw ball
     */

    private void drawInitScreen(){
        if(initImage==null){
            URL url=getClass().getResource(INIT_SCREEN_IMG_LOC);
            if(url==null)
                System.out.println("images not found");
            initImage=Toolkit.getDefaultToolkit().getImage(url);
        }
        imgGraphics.drawImage(initImage, 0, 0, this);
        //imgGraphics.drawImage(oImage, 418, 185, this);
    }
    
    private void drawBall() {
        imgGraphics.drawImage(ball.image, (int) ball.x, (int) ball.y, this);
    }

    /*
     * Draw slider
     */
    private void drawSlider() {
        imgGraphics.drawImage(slider.sliderImage, (int) slider.x, (int) slider.y, this);
    }
    /*
     * Draw boundaries
     */

    private void drawBoundaries() {
        imgGraphics.setColor(Color.BLACK);
        for (TouchableRectangle r : rectangles) {
            imgGraphics.fill(r);
        }
    }
    /*
     * Draw bricks
     */

    private void drawBricks() {
        imgGraphics.setColor(getBackground());
        for (int i = 0; i < bricks.size(); i++) {
            Brick br = bricks.get(i);

            imgGraphics.fill(br);
            imgGraphics.drawImage(br.brickImage, (int) br.x, (int) br.y, this);
        }
    }
    /*
     * Draw droppings
     */

    private void drawDroppings() {
        for (Dropping d : droppings) {
            imgGraphics.drawImage(d.image, (int) d.x, (int) d.y, this);
        }
    }
    /*
     * Draw weapon
     */

    private void drawWeapon() {
        if (weapon != null) {
            imgGraphics.drawImage(weapon.image, (int) weapon.x, (int) weapon.y, this);
        }
    }
    /*
     * Calls other functions sequentially and paints in 'image'
     */
    private void drawGame(){
        clearBackground();
        drawBall();
        drawSlider();
        drawBoundaries();
        drawBricks();
        drawDroppings();
        drawWeapon();
        drawScore();
    }
    
    private void drawScore(){
        imgGraphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        imgGraphics.setColor(Color.BLACK);
        imgGraphics.drawString("Lives: "+lifeCount+" Level: "+levelCount+" Score: "+scoreCount, this.getSize().width-240, 50);
        imgGraphics.drawString("by Chaitanya Vadrevu", 15, 50);
    }
    
    @Override
    public void paint(Graphics g) {
                
        initImage();
        
        if(justShowImg)
            ;
        else if(stopThread){
                drawInitScreen();
        }
        else if(showScore)
            drawScoreScreen();
        else
            drawGame();

        ((Graphics2D) g).drawImage(image, 0, 0, this);
    }

    /*
     * Check if game completed
     */
    private void checkGameComplete() {
        /*if (bricks.isEmpty()) {
            bricksOver();
        }*/
        if(bricks.size()==stoneCount)
            bricksOver();
    }
    
    /*
     * Check if ball out of court
     */
    private void checkBallOut() {
        if (!this.contains((int) ball.x, (int) ball.y)) {
            ballOut();
        }

    }
    
    /*
     * Check boundary collision
     */
    private void checkBoundaryCollision() {
        TouchableRectangle.direction dir;
        for (TouchableRectangle r : rectangles) {
            if ((dir = r.touches(ball)) != TouchableRectangle.direction.NONE) {
                r.bounce(dir, ball);
                //break;
            }
        }
    }
    
    /*
     * Check brick collision with ball
     */
    private void checkBrickCollisionWithBall() {
        TouchableRectangle.direction dir;
        for (int i = 0; i < bricks.size(); i++) {
            Brick br = bricks.get(i);
            if ((dir = br.touches(ball)) != Brick.direction.NONE) {
                if (!ball.isSuper || (ball.isSuper && br.stone)) {
                    br.bounce(dir, ball);
                }

                Dropping temp = br.dropping;
                if (temp != null) {
                    droppings.add(br.dropping);
                }
                if(!br.stone){
                    bricks.remove(br);
                    playSound();
                    scoreCount+=BRICK_SCORE;
                    i--;
                }
            }
        }
    }

    /*
     * Check slider collision with ball
     */
    private void checkSliderCollisionWithBall() {
        if (slider.touches(ball) == TouchableRectangle.direction.TOP) {
            slider.bounce(TouchableRectangle.direction.TOP, ball);
        }
    }

    /*
     * Check and Update Dropping collision & position
     */
    private void checkAndUpdateDropping() {
        for (int i = 0; i < droppings.size(); i++) {
            Dropping d = droppings.get(i);
            if (d.y > this.getSize().getHeight() || d.touches(slider, this)) {
                droppings.remove(d);
                i--;
            } else {
                d.move();
            }
        }
    }

    /*
     * Check and Update Weapon collision & position
     */
    private void checkAndUpdateWeapon() {
        TouchableRectangle.direction dir;
        for (int i = 0; i < bricks.size() && weapon != null; i++) {
            Brick br = bricks.get(i);
            if ((dir = br.touches(weapon)) != Brick.direction.NONE) {

                Dropping temp = br.dropping;
                if (temp != null) {
                    droppings.add(br.dropping);
                }

                if(!br.stone){
                    bricks.remove(br);
                    scoreCount+=BRICK_SCORE;
                    i--;
                }
                weapon = null;
                break;
            }
        }
        if (weapon != null) {
            if (weapon.y < 0) {
                weapon = null;
            } else {
                weapon.move();
            }
        }
    }

    /*
     * Update Slider position
     */
    private void updateSlider() {
        if (slider.buttonPressed == Slider.button.LEFT) {
            slider.moveTo(slider.getX() - Slider.MOVE_LENGTH);
        } else if (slider.buttonPressed == Slider.button.RIGHT) {
            slider.moveTo(slider.getX() + Slider.MOVE_LENGTH);
        }

    }
    /*
     * Draws a socre screen which is later painted in paint(..) method.
     */
    private void drawScoreScreen(){
        clearBackground();
        imgGraphics.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        imgGraphics.setColor(Color.BLACK);
        imgGraphics.drawString("Your SCORE "+scoreCount, 180, 200);
        //if(lifeCount<0)
        //    imgGraphics.drawString("GAME OVER", 200, 270);
       // else
            imgGraphics.drawString("Get Ready for Level "+(levelCount+1), 160, 270);
    }
    
    private void drawGameOverScreen(){
        clearBackground();
        imgGraphics.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        imgGraphics.setColor(Color.BLACK);
        imgGraphics.drawString("GAME OVER", 200, 200);
        
        imgGraphics.setColor(Color.RED);
        
        imgGraphics.drawString("YOU SCORED "+scoreCount, 165, 250);
        
        imgGraphics.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        imgGraphics.setColor(Color.BLACK);  
        
        if(scores.isHighScore(scoreCount)){
            scores.setHighScore(scoreCount);
            imgGraphics.drawString("CONGRATULATIONS!! YOU HAVE A HIGH SCORE", 20, 310);
        }
        else{
            imgGraphics.drawString("HIGH SCORE IS "+scores.getHighScore(), 195, 310);
        }
    }
    /*
     * Update ball position
     */
    private void updateBall() {
        ball.move();
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(BallBouncer2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("started thread");
        stopThread = false;
        
        while (!stopThread) {
            checkGameComplete();
            checkBallOut();
            checkBoundaryCollision();
            checkBrickCollisionWithBall();
            checkSliderCollisionWithBall();
            checkAndUpdateDropping();
            checkAndUpdateWeapon();
            updateSlider();
            updateBall();

            repaint();

            sleep(DELAY);
        }
        System.out.println("stopped thread");
        gameStopped();
    }
    
    private void gameStopped(){
        justShowImg=true;
        drawGameOverScreen();
        repaint();
        sleep(3000);
        justShowImg=false;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(showScore)
            return;
        if(stopThread){
            newGame();
        }
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                slider.buttonPressed = Slider.button.LEFT;
                break;
            case (KeyEvent.VK_RIGHT):
                slider.buttonPressed = Slider.button.RIGHT;
                break;
            case (KeyEvent.VK_SPACE):
                if (slider.weaponized && weapon == null) {
                    launchWeapon();
                }
                break;
        }
    }

    private void launchWeapon() {
        weapon = new Weapon(slider.x + (slider.width/2)-(Weapon.RADIUS/2), slider.y - slider.height / 2);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        slider.buttonPressed = Slider.button.NONE;
    }
    
    public void stopThread() {
        stopThread = true;
    }

    public void startThread() {
        if (stopThread) {
            Thread th = new Thread(this);
            th.start();
        }
    }

    public void bricksOver() {
        System.out.println("bricks over");
        scoreCount+=lifeCount*LIFE_SCORE;   //'enscore' lifes
        
        showScore=true;
        
        repaint();
        System.out.println("thread count "+Thread.activeCount());
        this.sleep(2000);
        
        nextLevel();
    }

    public void newGame() {
        init();
        levelCount = 1;
        scoreCount=0;
        showScore=false;
        stopThread=true;
        
        levelCreator.createLevel(levelCount);
        startThread();
    }

    public void gameOver() {
        stopThread();
    }
    
    //testing area
    private Clip[] clip;
    private AudioInputStream[] ais;
    private int playCount=0;
    public static final int CLIP_COUNT=8;
    
    private void initSound(){
        try {
            ais=new AudioInputStream[CLIP_COUNT];
            clip=new Clip[CLIP_COUNT];
            
            for(int i=0;i<CLIP_COUNT;i++){
                ais[i]=AudioSystem.getAudioInputStream(this.getClass().getResource(AUDIO_ROOT+"audio.wav"));
                clip[i]=(Clip) AudioSystem.getLine(new Line.Info(Clip.class));
                clip[i].open(ais[i]);
            }
            
        }catch (Exception ex) {
            Logger.getLogger(BallBouncer2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void playSound(){
        clip[playCount].start();
        clip[playCount].loop(1);
        clip[playCount].setFramePosition(0);
        playCount=(playCount>=CLIP_COUNT-1)?1:playCount+1;
    }
    //end testing area
    
    public void ballOut() {
        if (--lifeCount >= 0) {
            slider=new Slider();// new slider appears
            droppings = new ArrayList<Dropping>();//droppings vanish if ball out...
            weapon=null;        //weapons vanish
            newBall();          //super ball if any also vanishes
        } else {
            gameOver();
        }
    }

    public void nextLevel() {
        System.out.println("level " + ++levelCount);
        init();
        levelCreator.createLevel(levelCount);
        showScore=false;
    }

    private void newBall() {
        System.out.println("new ball");
        ball = new Ball();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if(!stopThread && !showScore)
            slider.moveTo(me.getX()-slider.width/2);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(stopThread)
            newGame();
        else if(slider.weaponized && weapon==null)
                    launchWeapon();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
