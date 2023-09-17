import greenfoot.*;

/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        if (gameStarted)
        {
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            // Create a new world with WORLD_WIDTH x WORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new Paddle(100,20), WORLD_WIDTH/2, WORLD_HEIGHT - 50);
            addObject(new BotPaddle(100,20),WORLD_WIDTH/2,WORLD_HEIGHT - 650);
            addObject(new GameLevelDisplay(),WORLD_WIDTH - 75,WORLD_HEIGHT - 675);
            addObject(new Scoreboard(),WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
            addObject(new RandomBotPaddle(100,20),WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
            setPaintOrder(RandomBotPaddle.class, Ball.class, Scoreboard.class, GameLevelDisplay.class);
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
}
