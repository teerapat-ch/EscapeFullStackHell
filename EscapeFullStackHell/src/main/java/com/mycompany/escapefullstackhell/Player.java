package com.mycompany.escapefullstackhell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Player {
    
    public GamePanel panel;
    public int x;
    public int y;
    public int width;
    public int height;
    
    public double xSpeed;
    public double ySpeed;
    
    public Rectangle hitbox;
    public BufferedImage playerImage;
    public boolean quizShowing = false;
    
    public boolean keyLeft;
    public boolean keyRight;
    public boolean keyUp;
    public boolean interact;
    
    public int level;
    
    public SoundFX jumpSound;
    public SoundFX deathSound;
    public SoundFX buttonSound;
    public SoundFX doorOpenSound;
    
    Player(int x, int y, GamePanel panel) {
        
        this.panel = panel;
        this.level = panel.level;
        this.x = x;
        this.y = y;
        
        width = 26;
        height = 50;
        hitbox = new Rectangle(x, y, width, height);
        
        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/player.png"));

        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

        jumpSound = new SoundFX(("/jump.wav"), 0.75f);
        deathSound = new SoundFX(("/death.wav"), 0.9f);
        buttonSound = new SoundFX(("/buttonSound.wav"), 0.9f);
        doorOpenSound = new SoundFX(("/doorOpen.wav"), 0.9f);
    }
    
    public void set() {
        
        if (keyLeft && keyRight || !keyLeft && !keyRight) {
            xSpeed*= 0.1;
        }
        else if (keyLeft && !keyRight) {
            xSpeed--;
        }
        else if (!keyLeft && keyRight) {
            xSpeed++;
        }
        
        if (xSpeed > 0 && xSpeed < 0.75) {
            xSpeed = 0;
        }
        if (xSpeed < 0 && xSpeed > -0.75) {
            xSpeed = 0;
        }
        if (xSpeed > 7) {
            xSpeed = 7;
        }
        if (xSpeed < -7) {
            xSpeed = -7;
        }
        
        if (keyUp) {
            hitbox.y++;
            for (Wall wall: panel.walls) {
                if (wall.hitbox.intersects(hitbox)) {
                    ySpeed = -9;
                    new Thread(() -> {
                        try {
                            jumpSound.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
            hitbox.y--;
        }
        
        // Gravity
        ySpeed += 0.5;
        
        hitbox.x += xSpeed;
        for (Wall wall: panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                hitbox.x -= xSpeed;
                while (!wall.hitbox.intersects(hitbox)) {
                    hitbox.x += Math.signum(xSpeed);
                }
                hitbox.x -= Math.signum(xSpeed);
                xSpeed = 0;
                x = hitbox.x;
            }
        }
        
        hitbox.y += ySpeed;
        for (Wall wall: panel.walls) {
            if (hitbox.intersects(wall.hitbox)) {
                hitbox.y -= ySpeed;
                while (!wall.hitbox.intersects(hitbox)) {
                    hitbox.y += Math.signum(ySpeed);
                }
                hitbox.y -= Math.signum(ySpeed);
                ySpeed = 0;
                y = hitbox.y;
            }
        }
        
        // Click button
        for (Button button: panel.button) {
            if (button.hitbox.intersects(hitbox)) {            
                if (interact) {
                    button.pressed = true;
                    new Thread(() -> {
                        try {
                            buttonSound.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } 
        }
        
        // Interact with door
        for (Door door: panel.door) {
            if (door.hitbox.intersects(hitbox)) {            
                if (interact) {
                    for (Button button: panel.button) {
                        if (button.pressed == true && !quizShowing) {
                            new Quiz(level, this);
                            quizShowing = true;
                        }
                    }
                }
            }
        }
        
        for (Spike spike: panel.spikes) {
            if (hitbox.intersects(spike.hitbox)) {
                LoadLevel(0);
                new Thread(() -> {
                    try {
                        deathSound.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        
        for (Guard guard: panel.guards) {
            if (hitbox.intersects(guard.hitbox)) {
                LoadLevel(0);
                new Thread(() -> {
                    try {
                        deathSound.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        
        x += xSpeed;
        y += ySpeed;
        
        hitbox.x = x;
        hitbox.y = y;
    }
    
    public void LoadLevel(int n) {
        
        if (n != 0) {
            new Thread(() -> {
                try {
                    doorOpenSound.play();
                } catch (Exception e) {
                    e.printStackTrace();
                        }
            }).start();
        }
        panel.level += n;
        panel.walls.clear();
        panel.spikes.clear();
        panel.door.clear();
        panel.button.clear();
        panel.guards.clear();
        panel.buildLevel();
    }
    
    public void draw(Graphics2D gtd) {
        gtd.drawImage(playerImage, x, y, width, height, null);
    }

}
