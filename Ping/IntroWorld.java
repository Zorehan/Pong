import greenfoot.*;

public class IntroWorld extends World {
    public IntroWorld() { 
        // Sætter introWorlds dimensioner (højde, længde, og cell size)
        super(500, 700, 1); 
        
        // Tilføjer StartButton til introWorld. 
        addObject(new StartButton(), getWidth() / 2, getHeight() / 2);
        addObject(new PizzaButton(), getWidth() / 2, 500);
        showText("Press the pizza if you want to play with power-up", getWidth() / 2, 450);
        setBackground(new GreenfootImage("baggrundIntro.jpeg"));
    }
}