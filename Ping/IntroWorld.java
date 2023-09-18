import greenfoot.*;

public class IntroWorld extends World {
    public IntroWorld() {
        super(800, 600, 1); // Set the world dimensions
        addObject(new StartButton(), getWidth() / 2, getHeight() / 2); // Add a "Start" button
    }
}