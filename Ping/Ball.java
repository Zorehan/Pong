import greenfoot.*;

/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private int speed;
    private int hitCount; // laver en int for hitCount, som skal bruges til at tælle hitCounts.
    private int delay;
    
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private boolean hasSpeedIncreased;


    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        createImage();
        initBall();
    }

        /**
     * Initialize the ball settings.
     */
    private void initBall()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
    
        public void restart(){
        GameLevelDisplay gameLevelDisplay = (GameLevelDisplay) getWorld().getObjects(GameLevelDisplay.class).get(0);
        gameLevelDisplay.resetGameLevel(); 
        initBall();
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
    
    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        ballImage.setColor(Color.BLACK);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffPaddle();
            checkBounceOffBotPaddle();
            checkHitCount(); // Her tjekker vi om hitcount er 10, og hvis den er inkremer speed
            checkRestart();
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    private boolean isTouchingPaddle()
    { 
        return isTouching(Paddle.class); 
    }
    
    private boolean isTouchingBotPaddle()
    {
        return isTouching(BotPaddle.class);
    }
    
    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }
    
    private void checkBounceOffBotPaddle()
    {
        if (isTouchingBotPaddle())
        {
            hitCount++; //Hver gang bolden rammer paddle, så inkremere vi hitCount med 1.
            Greenfoot.playSound("ball.mp3");
            if(!hasBouncedVertically)
            {   
            revertVertically();
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }

    private void checkBounceOffPaddle()
    {
        if (isTouchingPaddle())
        {
            hitCount++; //Hver gang bolden rammer paddle, så inkremere vi hitCount med 1.
            Greenfoot.playSound("ball.mp3");
            if (! hasBouncedVertically)
            {
                revertVertically();
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart() // Her har jeg implementeret, at når bolden rører jorden så resetter vi gameLevel
    {
        if (isTouchingFloor()) {
            restart();
        }
        if (isTouchingCeiling()) {
            restart();
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
    }
    
    // Her laver jeg en metode for, hvis bolden er blevet ramt 10 gange, så inkremere vi speed.
    private void checkHitCount(){
        if (hitCount >= 10){
            hasSpeedIncreased = true;
            speed++;
            hitCount = 0;
        } else hasSpeedIncreased = false;
    }
    
    public boolean hasSpeedIncreased(){
        return hasSpeedIncreased;
    }
}
