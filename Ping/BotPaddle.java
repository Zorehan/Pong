import greenfoot.*;

/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class BotPaddle extends Actor
{
    private int paddleWidth;
    private int paddleHeight;
    private int paddleSpeed;
    
    private boolean hasReturnedBall = true;
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public BotPaddle(int width, int height)
    {
        this.paddleWidth = width;
        this.paddleHeight = height;
        paddleSpeed = 2;
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
        if (!hasReturnedBall) { // Hvis hasReturnedBall er false, bevæg mod bolden
            // Få en reference til ball object inde i BotPaddle
            Ball ball = (Ball) getWorld().getObjects(Ball.class).get(0);
        
            // Få Ball og botPaddle x-værdier
            int ballX = ball.getX();
            int botPaddleX = getX();
        
            // Få forskellen mellen Ball og botPaddle x-værdier
            int dx = ballX - botPaddleX;
        
            // Hvis bolden er til højre for botPaddle, så setLocation til at bevæge mod højre
            if (dx > 0) {
                setLocation(botPaddleX + paddleSpeed, getY());
            }
            // Hvis bolden er til venstre for botPaddle, så setLocation til at bevæge mod højre
            else if (dx < 0) {
                setLocation(botPaddleX - paddleSpeed, getY());
            }
        }
        else if (hasReturnedBall) { // Hvis hasReturned ball er true, så bevæg mod midten            
            int botPaddleX = getX();
            int middleX = getWorld().getWidth() / 2;       
            
            if (middleX > botPaddleX) {
                setLocation(botPaddleX + paddleSpeed, getY());
            }
            // Hvis bolden er til venstre for botPaddle, så setLocation til at bevæge mod højre
            else if (middleX < botPaddleX) {
                setLocation(botPaddleX - paddleSpeed, getY());
            }
        } 
    }
    
    //Metode for at sætte hasReturnedBall til true
    public void botPaddleReturned(){
        hasReturnedBall = true;
    }
    
    //Metode for at sætte hasReturnedBall til false
    public void paddleReturned(){
        hasReturnedBall = false;
    }
}