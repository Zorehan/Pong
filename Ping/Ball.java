import greenfoot.*;

public class Ball extends Actor
{
    private static final int BALL_SIZE = 20; // Boldens størrelse
    private static final int DELAY_TIME = 100; // Delay for boldens bevægelse når den respawner

    private int speedX = 2; // Boldens fart på x-aksen
    private int speedY = 2; // Boldens fart på y-aksen
    private int hitCount; // Laver en integer for hitCount, som skal bruges til at tælle når bolden rammer en paddle.
    private int delay; // Et delay for boldens spawn - så den ikke bevæger sig med det samme.

    private boolean hasSpeedIncreased; // En boolean der returner om boldens speed er blevet inkremeret. Bruges i GameLevelDisplay.
    private boolean isCollidingWithPaddle = false; // En boolean der checker om bolden kollidere med en paddle. Brugt til at løse kollisions-bug
    private boolean gotBoostPizza = false;

    // Konstruktør for bolden
    public Ball()
    {
        // Her kalder vi metoden createImage(), som vi laver længere nede. Derudover sætter vi et delay på bolden.
        delay = 50;
        createImage();
    }

    // Metode for at lave boldens billede
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage("bold.png"); // Her laver vi et nyt billede, som bruger bold.png som reference.
        setImage(ballImage); // Her sætter vi det nye billede til boldens billede
    }
    
    // Metode for act
    public void act() 
    {
        // Først kører act igennem delay, og tæller den ned indtil 0.
        if (delay > 0)
        {
            delay--; // Dette dekrementere delay med 1 hvert act. 
        }
        else // Når delay > 0, så gør dette:
        {   
            moveBall(); // Dette sørger for at bolden bevæger sig
            checkWorldCollision(); // Dette tjekker om bolden kollidere med væggene
            checkPaddleCollision(); // Dette tjekker om bolden kollidere med paddle
            checkHitCount(); // Dette tjekker hvor mange gange bolden skal rammes, før bolden bliver hurtigere.
            getBoostPizza();
        }
    }  
    
    // Metode for boldens bevægelse
    private void moveBall(){
        setLocation(getX() + speedX, getY() + speedY); // Sætter boldens lokation til nuværende lokation + speedX og speedY
    }
    
    // Metode for at tjekke om bolden kollidere med væggene
    private void checkWorldCollision(){
        // Tjek hvis bolden rammer toppen eller bunden af verden
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            respawnBall(); // Dette respawner bolden, da den ikke må ramme toppen eller bunden
        }
        
        // Tjek hvis bolden rammer venstre/højre side af verden
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) {
            Greenfoot.playSound("ball.mp3"); // Spil ball.mp3 hvis den rammer en væg
            speedX = -speedX; // Dette gør, at den bevæger sig modsat retning
        }
    }
    public void getBoostPizza()
    {
        Actor pizza = getOneObjectAtOffset(0, 0, BoostPizza.class);
            if (pizza != null & speedY > 0)
            {
            getWorld().removeObject(pizza);
            gotBoostPizza = true;
            speedX += 3;
            speedY += 3;
            }
            else if (pizza != null & speedY < 0)
            {
            getWorld().removeObject(pizza);
            gotBoostPizza = true;
            speedX -= 3;
            speedY -= 3;
            }
    }
    // Metode for at tjekke om bolden kollidere med paddle
    private void checkPaddleCollision() {
        if (!isCollidingWithPaddle) { // Tjek for kollision. Hvis den ikke kollidere, gør dette:
            // Først tjekker vi om bolden kollidere med paddle
            BotPaddle bot = (BotPaddle) getWorld().getObjects(BotPaddle.class).get(0);
            Actor Paddle = getOneIntersectingObject(Paddle.class);
            Actor BotPaddle = getOneIntersectingObject(BotPaddle.class);
            Actor RandomBotPaddle = getOneIntersectingObject(RandomBotPaddle.class);

            // Hvis paddle ikke er null, betyder det at bolden kollidere med paddle.
            if (Paddle != null) {
                speedY = -speedY; // Her ændrer vi bolden til at gå modsat
                hitCount++; // Her inkrementere vi med 1 på hitcount
                Greenfoot.playSound("ball.mp3"); // Spil ball.mp3 hvis den rammer en paddle
                isCollidingWithPaddle = true; // Sæt kollision med paddle til true
                bot.paddleReturned(); // Sætter hasReturned til false. Dette gør, at paddle følger efter bolden.
            }
            // Hvis botpaddle ikke er null, betyder det at bolden kollidere med BotPaddle.
            else if (BotPaddle != null) {
                speedY = -speedY; // Her ændrer vi bolden til at gå modsat
                hitCount++; // Her inkrementere vi med 1 på hitcount
                Greenfoot.playSound("ball.mp3"); // Spil ball.mp3 hvis den rammer en væg
                bot.botPaddleReturned(); // Sætter hasReturned til true. Dette gør, at paddle går mod midten.
                isCollidingWithPaddle = true; // Sæt kollision med paddle til true
            }
            // Hvis RandomBotPaddle ikke er null, betyder det at bolden kollidere med RandomBotPaddle.
             else if (RandomBotPaddle != null && speedY < 0) {
                speedY = -speedY; // Her ændrer vi bolden til at gå modsat
                Greenfoot.playSound("ball.mp3"); // Spil ball.mp3 hvis den rammer en væg
                bot.paddleReturned(); // Sætter hasReturned til false. Dette gør, at paddle følger efter bolden
                isCollidingWithPaddle = true; // Sæt kollision med paddle til true.
            }
        } 
        else {
            Actor Paddle = getOneIntersectingObject(Paddle.class);
            Actor BotPaddle = getOneIntersectingObject(BotPaddle.class);
            Actor RandomBotPaddle = getOneIntersectingObject(RandomBotPaddle.class);
            
            // Tjek for hvis bolden ikke kollidere med nogle af paddle
            if (Paddle == null && BotPaddle == null && RandomBotPaddle == null) {
                isCollidingWithPaddle = false; // Sæt kollision med paddle til false.
            }
        }
    }
    
    private void respawnBall(){
        // Her kalder vi resetGameLevel inde fra GameLevelDisplay. Dette gør vi så level resetter sammen med bold.
        GameLevelDisplay level = (GameLevelDisplay) getWorld().getObjects(GameLevelDisplay.class).get(0);
        level.resetGameLevel();
        
        setLocation(getWorld().getWidth() / 2, (getWorld().getHeight() / 2)); // sætter bolden til midten
        speedX = 2; // resetter boldens speed på x-aksen
        speedY = 2; // resetter boldens speed på y-aksen 
        delay = DELAY_TIME; // adder et delay for når bolden skal bevæge sig igen
    }
    
    // Her laver jeg en metode for, hvis bolden er blevet ramt 10 gange, så inkremere vi speed.
    private void checkHitCount(){
        if (hitCount >= 10){ // Hvis hitcount >= 10, så gør vi følgende
            speedX++; // Inkrementere speedX  med 1. Dette gør bolden hurtigere på x-aksen.
            speedY++; // Inkrementere speedX  med 1. Dette gør bolden hurtigere på y-aksen.
            hasSpeedIncreased = true; // Sætter hasSpeedIncreased til true. Dette bliver brugt inde i GameLevelDisplay
            hitCount = 0; // Resetter hitcount til 0
        } else hasSpeedIncreased = false; // Hvis hitCount <= 10, så er hasSpeedIncreased = false
    }
    
    // En getter for hasSpeedIncreased. Brugt inde i GameLevelDisplay.
    public boolean hasSpeedIncreased(){ 
        return hasSpeedIncreased;
    }
}
