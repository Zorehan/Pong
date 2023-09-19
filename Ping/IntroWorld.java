import greenfoot.*;

public class IntroWorld extends World {
    public IntroWorld() { 
        // Sætter introWorlds dimensioner (højde, længde, og cell size)
        super(800, 600, 1); 
        
        // Tilføjer StartButton til introWorld. 
        addObject(new StartButton(), getWidth() / 2, getHeight() / 2);
        addObject(new PizzaButton(), getWidth() / 2, 400);
    }
}