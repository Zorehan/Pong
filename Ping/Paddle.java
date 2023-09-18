import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    private int paddleWidth;
    private int paddleHeight;
    private int paddleSpeed;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height)
    {
        this.paddleWidth = width;
        this.paddleHeight = height;
        paddleSpeed = 3;
        createImage();
    }

        /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage("paddle.png");
        setImage(image);
    }
    
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
         if(Greenfoot.isKeyDown("right")){
             setLocation(getX() + paddleSpeed,getY());
         }
         if (Greenfoot.isKeyDown("left")){
             setLocation(getX() - paddleSpeed,getY());
         }
    }    
}
