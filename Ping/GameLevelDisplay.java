import greenfoot.*;

public class GameLevelDisplay extends Actor {
    private int gameLevel = 1; // Game level i starten af spillet
    
    // Constructor for GameLevelDisplay
    public GameLevelDisplay() {
        updateText(); // Her opdatere vi vores tekst
    }

    // Metoden for at opdatere teksten. Vi laver et nyt billede, som faktisk er en text.
    private void updateText() { 
        setImage(new GreenfootImage("Game Level: " + gameLevel, 24, Color.BLACK, Color.WHITE));
    }
    
    // Metoden for act
    public void act() {
        // Tjek hvis boldens speed er blevet større
        Ball ball = (Ball) getWorld().getObjects(Ball.class).get(0);
        if (ball.hasSpeedIncreased()) {
            // Hvis bolden er blevet hurtigere, inkrementere vi med 1 på gameLevel. 
            gameLevel++;
            updateText(); // Her opdatere vi teksten, så det passer med nye gameLevel.
        }
    }
    
    // Method for at resette gameLevel. Dette er brugt inde i respawnBall.
    public void resetGameLevel(){
        gameLevel = 1;
        updateText();
    }
}