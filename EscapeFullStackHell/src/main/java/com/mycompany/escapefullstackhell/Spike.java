package com.mycompany.escapefullstackhell;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Spike {

    public int x;
    public int y;
    public int width;
    public int height;
    public Rectangle hitbox;
    public BufferedImage spikeImage;
    
    Spike(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitbox = new Rectangle(x + 10, y + 10, width - 20, height - 10);
        
        try {
            spikeImage = ImageIO.read(getClass().getResourceAsStream("/spike.png"));
        } catch (IOException ex) {
            Logger.getLogger(Spike.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics2D gtd) {
        gtd.drawImage(spikeImage, x, y, width, height, null);            
    }
}
