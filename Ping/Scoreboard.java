import greenfoot.*;

public class Scoreboard extends Actor {
    private int playerScore = 0;
    private int botScore = 0; // Game level i starten af spillet
    
    // Konstruktør for GameLevelDisplay
    public Scoreboard() {
        updateText(); // Her opdatere vi vores tekst
    }

    // Metoden for at opdatere teksten. Vi laver et nyt billede, som faktisk er en text.
    private void updateText() { 
        setImage(new GreenfootImage("Player: " + playerScore + " ||" + " Computer: " + botScore, 24, Color.BLACK, Color.WHITE));
    }
    
    // Metoden for act
    public void act() {
        // Tjek hvis boldens speed er blevet større
        Ball ball = (Ball) getWorld().getObjects(Ball.class).get(0);
        if (ball.getY() <= 2) { 
            playerScore++;
            updateText();
        }
        if (ball.getY() >= getWorld().getHeight() - 2){
            botScore++;
            updateText();
        }
    }
}
