package com.mycompany.escapefullstackhell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Guard {

    public GamePanel panel;
    public int x;
    public int y;
    public int width;
    public int height;
    public Rectangle hitbox;
    public BufferedImage guardImage;

    public int boundaryL;
    public int boundaryR;
    public int xSpeed;
    public int speedLimit;
    public boolean goingForward = true;

    Guard(int x, int y, int boundaryL, int boundaryR, int speedLimit, GamePanel panel) {

        this.x = x;
        this.y = y;
        this.boundaryL = boundaryL;
        this.boundaryR = boundaryR;
        this.speedLimit = speedLimit;
        this.panel = panel;
        width = 26;
        height = 50;
        hitbox = new Rectangle(x, y, width, height);

        try {
            guardImage = ImageIO.read(getClass().getResourceAsStream("/guard.png"));
        } catch (IOException ex) {
            Logger.getLogger(Guard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void set() {
        if (xSpeed > speedLimit) {
            xSpeed = speedLimit;
        }
        if (xSpeed < -speedLimit) {
            xSpeed = -speedLimit;
        }

        x += xSpeed;
        hitbox.x += xSpeed;
    }

    public void draw(Graphics2D gtd) {
        gtd.drawImage(guardImage, x, y, width, height, null);
    }
}
