package com.mycompany.escapefullstackhell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Button {
    
    public GamePanel panel;
    public int x;
    public int y;
    public int width;
    public int height;
    public Rectangle hitbox;
    public BufferedImage buttonImage;
    public BufferedImage buttonPressedImage;
    
    public boolean pressed = false;
    
    Button(int x, int y, GamePanel panel) {
        
        this.x = x;
        this.y = y;
        this.panel = panel;
        width = 40;
        height = 50;
        hitbox = new Rectangle(x, y, width, height);
        
        try {
            buttonImage = ImageIO.read(getClass().getResourceAsStream("/button_red.png"));
            buttonPressedImage = ImageIO.read(getClass().getResourceAsStream("/button_green.png"));
        } catch (IOException ex) {
            Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics2D gtd) {
        if (!pressed) {
            gtd.drawImage(buttonImage, x, y, width, height, null);            
        }
        else {
            gtd.drawImage(buttonPressedImage, x, y, width, height, null);
        }
    }
}
