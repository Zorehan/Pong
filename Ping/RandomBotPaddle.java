import greenfoot.*;
/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class RandomBotPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public RandomBotPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        outOfMap();
        tryChangeDirection();
        setLocation(getX() + dx, getY());
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0)
        {
            //Change our 'x' direction to the inverted direction:
            dx = dx * -1;
        }
    }
    
    // Den her metode kigger på om Pagajen befinder sig i en af kanterne.
    private void outOfMap()
    {
    if (getX() == 450 || getX() == 50)
    {
        respawnAtNewHeight();       
    }
    }
    
     
    private void respawnAtNewHeight() {
    int worldWidth = getWorld().getWidth(); // IntroWorlds Bredde
    int worldHeight = getWorld().getHeight(); // IntroWorlds højde
    int paddleWidth = getImage().getWidth(); // Her får vi Pagajens bredde
    int minY = 200; 
    int maxY = worldHeight - 200; 
    int newX;
    
    /* if statementen checker om getX (altså Padlens midtpunkt) er mindre
     * eller ligemed halvdelen af dens fulde bredde.
     * det bliver brugt til at finde ud af om Pagajen befinder sig på venstre
     * side af banen.
     * Det er egentlig bare for at vi ved om vi skal spawne den næste paddle 
     * på venstre eller højre side
     */
    if (getX() <= paddleWidth / 2) 
    {
        newX = worldWidth - paddleWidth / 2;
    } else 
        {
        newX = paddleWidth / 2;
        }
    //Her opretter vi en ny variabel der benytter sig af vores max og minus
    //y-værdier så den ikke kan spawne pagajen lige foran spilleren eller 
    //ai'en
    int newY = Greenfoot.getRandomNumber(maxY - minY) + minY;
    setLocation(newX, newY);
    } 
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */

     private void createImage()
    {
        GreenfootImage image = new GreenfootImage("paddle.png");
        setImage(image);
    }
}