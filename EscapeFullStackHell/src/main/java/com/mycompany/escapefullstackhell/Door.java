package com.mycompany.escapefullstackhell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Door {
    
    public GamePanel panel;
    public int x;
    public int y;
    public int width;
    public int height;
    public Rectangle hitbox;
    public BufferedImage doorImage;
    
    Door(int x, int y, GamePanel panel) {
        
        this.x = x;
        this.y = y;
        this.panel = panel;
        width = 60;
        height = 70;
        hitbox = new Rectangle(x, y, width, height);
        
        try {
            doorImage = ImageIO.read(getClass().getResourceAsStream("/door.png"));
        } catch (IOException ex) {
            Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics2D gtd) {
        gtd.drawImage(doorImage, x, y, width, height, null);            
    }
}
