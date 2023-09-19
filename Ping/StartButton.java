import greenfoot.*;

public class StartButton extends Actor {
    public StartButton() {
        GreenfootImage image = new GreenfootImage("start_button.png"); // Sæt et billede for knappen
        image.scale(500,200); // Skalere billedet lidt ned, så det passer bedre på skærmen.
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new PingWorld(true)); // Skift til PingWorld hvis man trykker på knappen. True er nødvendigt, da dette gør at spillet starter.
        }
        else if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new PingWorld(true)); // Skift til PingWorld hvis man trykker på enter. True er nødvendigt, da dette gør at spillet starter.
        }
    }
}