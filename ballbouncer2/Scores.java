/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballbouncer2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chaitanya
 */
public class Scores {
    public static final String SCORES_FILE="scores";
    int highScore=-1;
    public Scores(){
        DataInputStream dis=null;
        
        File file=new File(SCORES_FILE);
        
        try {            
            if(!file.exists())
                file.createNewFile();
            else{
                dis=new DataInputStream(new FileInputStream(file));
                highScore=dis.readInt();
                dis.close();
            }
        }catch (Exception ex) {
            System.out.println("some score file exception");
        }
    }
    
    public boolean isHighScore(int sc){
        return (sc>=highScore)?true:false;
    }
    
    public void setHighScore(int sc){
        highScore=sc;
        writeScore();
    }
    
    private void writeScore(){
        DataOutputStream dos;
        try {
            dos=new DataOutputStream(new FileOutputStream(new File(SCORES_FILE)));
            dos.writeInt(highScore);
            
            dos.close();
        } catch (Exception ex) {
            Logger.getLogger(Scores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getHighScore(){
        return highScore;
    }
    
    public static void main(String[] args){
        Scores s=new Scores();
        System.out.println("high is "+s.getHighScore());
        System.out.println("is high "+s.isHighScore(10));
        s.setHighScore(10);
        System.out.println("high is "+s.getHighScore());
    }
}
