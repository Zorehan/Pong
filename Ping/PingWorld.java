import greenfoot.*;

/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    // PingWorlds dimensioner. Verden er 700 høj, 500 bred
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    public static boolean isPizzaTurnedOn = false;

    // Konstruktør til PingWorld. Her tilføjer vi alle objekterne, og bestemmer rækkefølge.
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        if (gameStarted) // Hvis gameStarted = true, lav disse objekter:
        {
            // Her opretter vi alle objekterne, og sætter deres position.
            addObject(new Ball(), WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
            addObject(new Paddle(100,20), WORLD_WIDTH/2, WORLD_HEIGHT - 50);
            addObject(new BotPaddle(100,20),WORLD_WIDTH/2,WORLD_HEIGHT - 650);
            addObject(new GameLevelDisplay(),WORLD_WIDTH - 80,WORLD_HEIGHT - 675);
            addObject(new Scoreboard(),WORLD_WIDTH - 370, WORLD_HEIGHT - 675);
            addObject(new RandomBotPaddle(100,20),WORLD_WIDTH / 3, WORLD_HEIGHT / 3);
            setBackground(new GreenfootImage("baggrund.jpeg"));
            
            // Dette sætter objekterne i rækkefølge af hvad der skal være forrest på skærmen
            setPaintOrder(RandomBotPaddle.class, Ball.class, Scoreboard.class, GameLevelDisplay.class);
        }
        else // Hvis gameStarted = false, sæt verden til IntroWorld.
        {
            Greenfoot.setWorld(new IntroWorld());
        }
        
    }
    
    private void randomBoostPizza(int numberToSpawn)
    {
    int minY = 200;
    int maxY = 500 -200;
    
    for (int i=0; i<numberToSpawn; i++)
    {
    int newX = Greenfoot.getRandomNumber(480)+25;
    int newY = Greenfoot.getRandomNumber(maxY-minY)+minY;
    
    addObject(new BoostPizza(), newX, newY);
    }
    }
    
    public void act()
    {
    if(Greenfoot.getRandomNumber(2500) < 1 && isPizzaTurnedOn == true)
    {
    randomBoostPizza(1);
    }
}
}
