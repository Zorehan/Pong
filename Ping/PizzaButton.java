import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PizzaButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PizzaButton extends Actor
{
    /**
     * Act - do whatever the PizzaButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
        PingWorld.isPizzaTurnedOn = true;
        Greenfoot.setWorld(new PingWorld(true));
        }
    }   
}
