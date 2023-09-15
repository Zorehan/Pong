import greenfoot.*;

public class Ball extends Actor
{
    private static final int BALL_SIZE = 20; // boldens størrelse
    private static final int DELAY_TIME = 50; // delay for når bolden skal respawn

    private int speedX = 2; // boldens speed på x-aksen
    private int speedY = 2; // boldens speed på y-aksen
    private int hitCount; // laver en int for hitCount, som skal bruges til at tælle hitCounts.
    private int delay; // et delay for boldens spawn - så den ikke spawner med det samme.

    private boolean hasSpeedIncreased; // en boolean der returner om boldens speed er blevet inkremeret. 

    // Constructor for bolden
    public Ball()
    {
        // Her kalder vi metoden createImage(), som vi laver længere nede.
        createImage();
    }

    // Metode for at lave boldens billede
    private void createImage()
    {
        // Her laver vi et nyt billede, som er på størrelse med boldens størrelse.
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE); 
        ballImage.setColor(Color.BLACK); // Her sætter vi billedets farve til sort
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE); // Her udfylder vi bolden i 0, 0, som er oppe i venstre hjørne
        setImage(ballImage); // Her sætter vi det nye billedet til boldens billede
    }
    
    // Metode for act
    public void act() 
    {
        // Først kører act igennem delay, og tæller den ned indtil 0.
        if (delay > 0)
        {
            delay--; // dette dekrementere delay med 1 hvert act.
        }
        else
        {   
            moveBall(); // Dette sørger for at bolden bevæger sig
            checkWorldCollision(); // Dette tjekker om bolden kollidere med væggene
            checkPaddleCollision(); // Dette tjekker om bolden kollidere med paddle
            checkHitCount(); // Dette tjekker hvor mange gange bolden er blevet ramt
        }
    }  
    
    // Metode for boldens bevægelse
    private void moveBall(){
        setLocation(getX() + speedX, getY() + speedY); // Sætter boldens lokation til nuværende + speedX og speedY
    }
    
    // Metode for at tjekke om bolden kollidere med væggene
    private void checkWorldCollision(){
        // Tjek hvis bolden rammer toppen eller bunden af verden
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            respawnBall(); // Dette fjerner bolden, da den ikke må ramme toppen eller bunden
        }
        
        // Tjek hvis bolden rammer venstre/højre side af verden
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) {
            speedX = -speedX; // Dette gør, at den bevæger sig modsat retning
        }
    }
    
    // Metode for at tjekke om bolden kollidere med paddle
    private void checkPaddleCollision(){
        // Først tjekker vi om bolden kollidere med paddle
        Actor Paddle = getOneIntersectingObject(Paddle.class);
        Actor BotPaddle = getOneIntersectingObject(BotPaddle.class);
        
        // Hvis paddle ikke er null, betyder det at bolden kollidere med paddle.
        if (Paddle != null) {
            speedY = -speedY; // Her ændrer vi bolden til at gå modsat
            hitCount++; // Her inkrementere vi med 1 på hitcount
        }
        // Hvis botpaddle ikke er null, betyder det at bolden kollidere med paddle.
        else if (BotPaddle != null) {
            speedY = -speedY; // Her ændrer vi bolden til at gå modsat
            hitCount++; // Her inkrementere vi med 1 på hitcount
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
        } else hasSpeedIncreased = false; // Hvis hitCount >= 10, så er hasSpeedIncreased = false
    }
    
    // En getter for hasSpeedIncreased. Brugt inde i GameLevelDisplay.
    public boolean hasSpeedIncreased(){ 
        return hasSpeedIncreased;
    }
}
