package com.mycompany.escapefullstackhell;
import java.awt.*;

public class Wall {

    public int x;
    public int y;
    public int width;
    public int height;
    public Rectangle hitbox;
    
    Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        hitbox = new Rectangle(x, y, width, height);
    }
    
    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.GRAY);
        gtd.drawRect(x, y, width, height);
        gtd.fillRect(x, y, width, height);
    }
}
