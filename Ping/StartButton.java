import greenfoot.*;

public class StartButton extends Actor {
    public StartButton() {
        GreenfootImage image = new GreenfootImage("start_button.png"); // Load an image for the button
        image.scale(500,200);
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new PingWorld(true)); // Switch to the game world when the button is clicked
        }
        else if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
}