import greenfoot.*;
public class GameLevelDisplay extends Actor {
    private int gameLevel = 1; // Initial game level
    
    public GameLevelDisplay() {
        updateText(); // Set the initial text
    }
    
    public void act() {
        // Check if the ball's speed has been increased
        Ball ball = (Ball) getWorld().getObjects(Ball.class).get(0);
        if (ball.isSpeedIncreased()) {
            // Increase the game level
            gameLevel++;
            updateText(); // Update the text to display the new game level
        }
    }
    
    // Getter method for gameLevel
    public int getGameLevel() {
        return gameLevel;
    }
    
    public void resetGameLevel(){
        gameLevel = 1;
        updateText();
    }
    
    // Update the text to display the game level
    private void updateText() {
        // Set the text to display the game level
        setImage(new GreenfootImage("Game Level: " + gameLevel, 24, Color.BLACK, Color.WHITE));
    }
}