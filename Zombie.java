import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;
import java.util.List;
import java.util.Set;



/**
 * Write a description of class Zombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zombie extends Actor
{
    private int marinesEaten;
    public static int lives = 3;
    public static int level = 1;
    
    boolean rFoot = false;
    double wTime = 0;  // Walking animation timer
    
    public Zombie()  //Initializes Zombie
    {
        marinesEaten = 0;
    }
    
    public void act() 
    {
        checkKeyPress();
        lookforMarines();
        lookforBoss();
        checkWorld();
        wTime = wTime + 1;
    }
    
    public void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("a"))
        {
            turn(-4);
        }
        if (Greenfoot.isKeyDown("d"))
        {
            turn(4);
        } 
        
        if (Greenfoot.isKeyDown("w"))
        {
           if( rFoot == true && wTime % 15 == 0)
           {
            this.setImage("zombie_walk1.png");
            rFoot = false;
           }
           else if(wTime % 15 == 0){
            this.setImage("zombie_walk2.png");
            rFoot = true;
            }
            move(2);
        } 
        if (!Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("s") )
        {     
           if( rFoot = true)
           {
            this.setImage("zombie_walk3.png");
           }
           
        } 
        if (Greenfoot.isKeyDown("s"))
        {
            if( rFoot == true && wTime % 15 == 0)
           {
            this.setImage("zombie_walk2.png");
            rFoot = false;
           }
           else if(wTime % 15 == 0){
            this.setImage("zombie_walk1.png");
            rFoot = true;
            }
           move(-2);
        } 
    }
    
    public void lookforMarines()
    {
        Marine marine = (Marine) getOneIntersectingObject(Marine.class);
        ZMarine zmarine = new ZMarine();
        Dead dead = new Dead();
        if (marine != null) {       
        removeTouching(Marine.class);
        getWorld().addObject(zmarine,getX(),getY());
        getWorld().addObject(dead,getX(),getY());
        marinesEaten = marinesEaten + 1;
        Greenfoot.playSound("slurp.wav");
        
        Life life = new Life();
        int x= (int) Math.ceil(Math.random()*100);
        if(x <= 25){
         getWorld().addObject(life,MyWorld.randomX(),MyWorld.randomY());
         }
         
         
        }
        
        getWorld().showText("Marines Eaten:" + marinesEaten,100,40);
        getWorld().showText("Lives:" + lives,60,15);
        getWorld().showText("Level:" + level,140,15);
       
        Actor player;
        player = getOneObjectAtOffset(0, 0, Player.class);
        if(player != null){
            int x = player.getX();
            int y = player.getY();
          
            //getWorld().removeObject(zombie);
            //ZombieGuts zg1 = new ZombieGuts();
            //getWorld().addObject( zg1 ,x, y);
            
            getWorld().removeObject(player);

        getWorld().showText("Level:" + level,140,15);
       }
      }
      
      public void lookforBoss()
      {
        Fzombie fzombie = (Fzombie) getOneIntersectingObject(Fzombie.class);
         if (fzombie != null) {       
          removeTouching(Fzombie.class);
           }
       
        Boss1 boss = (Boss1) getOneIntersectingObject(Boss1.class);
         if (boss != null) {       
          removeTouching(Boss1.class);
           } 
           
       }

    
        
    
    public void checkWorld()
    {
        if(isAtEdge()){
           if(this.getX() >= getWorld().getWidth()-1){
               this.setLocation( 0 , this.getY() );
           }
           else if(this.getX() <= 0 ){
               this.setLocation( getWorld().getWidth() , this.getY());
            }
            if(this.getY() <= 0){
               this.setLocation( this.getX() , getWorld().getHeight());
           }
           else if (this.getY() >= getWorld().getHeight()-1){
               this.setLocation( this.getX() , 0 );
            }
        }
    }
}
