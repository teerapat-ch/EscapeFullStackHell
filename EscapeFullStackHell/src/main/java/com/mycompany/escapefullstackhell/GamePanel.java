package com.mycompany.escapefullstackhell;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.*;

public class GamePanel extends javax.swing.JPanel implements ActionListener {
    
    public Player player;
    public CopyOnWriteArrayList<Wall> walls = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Button> button = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Door> door = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Spike> spikes = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<Guard> guards = new CopyOnWriteArrayList<>();
    
    public Timer gameTimer;
    public int level = 1;
    
    public JPanel levelPanel = new JPanel();
    public JLabel levelText = new JLabel("Level: " + level);
    
    GamePanel() {
        
        setBackground(new Color(70,42,42));
        buildLevel();
        add(levelText);
        
        levelText.setFont(new Font("Serif", Font.PLAIN, 20));
        levelPanel.add(levelText);
        add(levelPanel);
        
        gameTimer = new Timer(10, new TimerListener());
        gameTimer.start();
    }
    
    private class TimerListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            player.set();
                if (!guards.isEmpty()) {
                    for (Guard guard: guards) {
                        if (guard.goingForward) {
                            guard.xSpeed++;  
                            if (guard.x >= guard.boundaryR) { 
                                guard.x = guard.boundaryR;  
                                guard.goingForward = false;  
                            }
                        }
            
                        else {
                            guard.xSpeed--;  
                            if (guard.x <= guard.boundaryL) { 
                                guard.x = guard.boundaryL;  
                                guard.goingForward = true;  
                            }
                        }
            
                        guard.set();
                    }
                }
                if (level < 7) {
                    levelText.setText("Level: " + level);
                }
                else {
                    levelText.setFont(new Font("Serif", Font.PLAIN, 75));
                    levelText.setText("You escaped!");
                }
                repaint();
        }
    }
    
    public void buildLevel() {
        
        if (level == 1) {
            
            button.add(new Button(900, 100, this));
            door.add(new Door(50, 80, this));
            player = new Player(80, 450, this);
            
            spikes.add(new Spike(200, 520, 50, 30));
            spikes.add(new Spike(400, 520, 50, 30));
            spikes.add(new Spike(600, 520, 50, 30));
            spikes.add(new Spike(550, 320, 50, 30));
            spikes.add(new Spike(375, 320, 50, 30));
            spikes.add(new Spike(330, 320, 50, 30));
            spikes.add(new Spike(400, 120, 50, 30));
            spikes.add(new Spike(550, 120, 50, 30));
            spikes.add(new Spike(700, 120, 50, 30));
            spikes.add(new Spike(745, 120, 50, 30));
            
            // Floor 1
            for (int i = -50; i < 1000; i += 50) {
                walls.add(new Wall(i, 550, 50, 50));
            }
            
            // Platform 1-2
            walls.add(new Wall(800, 500, 50, 10));
            walls.add(new Wall(850, 450, 50, 10));
            walls.add(new Wall(900, 400, 50, 10));
            
            // Floor 2
            for (int i = -50; i < 800; i += 50) {
                walls.add(new Wall(i, 350, 50, 50));
            }
            
            // Platform 2-3
            walls.add(new Wall(270, 300, 50, 10));
            walls.add(new Wall(220, 250, 50, 10));
            walls.add(new Wall(170, 200, 50, 10));
            
            // Floor 3
            for (int i = -50; i < 1000; i += 50) {
                if (i < 150 || i > 300) {
                    walls.add(new Wall(i, 150, 50, 50));
                }
            }
            
            // Wall
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(-40, i, 50, 50));
            }
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(970, i, 50, 50));
            }   
        }
        else if (level == 2) {
            
            button.add(new Button(150, 300, this));
            door.add(new Door(850, 80, this));
            player = new Player(90, 450, this);
            
            for (int i = 250; i < 950; i += 45) {
                spikes.add(new Spike(i, 520, 50, 30));
            }
            
            spikes.add(new Spike(650, 320, 50, 30));
            spikes.add(new Spike(500, 320, 50, 30));
            spikes.add(new Spike(455, 320, 50, 30));

            spikes.add(new Spike(500, 120, 50, 30));   
            spikes.add(new Spike(620, 120, 50, 30)); 
            spikes.add(new Spike(740, 120, 50, 30)); 
            
            // Floor 1
            for (int i = -50; i < 1000; i += 50) {
                walls.add(new Wall(i, 550, 50, 50));
            }
            
            // Platform 1
            walls.add(new Wall(150, 500, 50, 10));
            walls.add(new Wall(300, 500, 50, 10));
            walls.add(new Wall(420, 500, 50, 10));
            walls.add(new Wall(540, 500, 50, 10));
            walls.add(new Wall(660, 500, 50, 10));
            walls.add(new Wall(780, 500, 100, 10));
            walls.add(new Wall(900, 450, 50, 10));
            walls.add(new Wall(800, 390, 50, 10));
            
            // Floor 2
            for (int i = -50; i < 800; i += 50) {
                walls.add(new Wall(i, 350, 50, 50));
            }
            
            // Platform 2
            walls.add(new Wall(250, 300, 50, 10));
            walls.add(new Wall(350, 250, 50, 10));
            walls.add(new Wall(250, 200, 50, 10));
            
            walls.add(new Wall(10, 300, 50, 10));
            walls.add(new Wall(150, 250, 50, 10));
            walls.add(new Wall(10, 200, 50, 10));
            
            // Floor 3
            for (int i = 400; i < 1000; i += 50) {
                walls.add(new Wall(i, 150, 50, 50));
            }
            
            // Wall
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(-40, i, 50, 50));
            }
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(970, i, 50, 50));
            }
            
            for (int i = 460; i < 600; i += 50) {
                walls.add(new Wall(200, i, 50, 50));
            }
            
            for (int i = 150; i < 350; i += 50) {
                walls.add(new Wall(200, i, 50, 50));
            }
        }
        else if (level == 3) {
            
            button.add(new Button(630, 40, this));
            door.add(new Door(770, 430, this));
            player = new Player(50, 50, this);
            
            for (int i = 0; i < 1000; i += 45) {
                spikes.add(new Spike(i, 520, 50, 30));
            }
            for (int i = 820; i < 1000; i += 45) {
                spikes.add(new Spike(i, 270, 50, 30));
            }
            spikes.add(new Spike(150, 190, 50, 30));
            spikes.add(new Spike(80, 390, 50, 30));
            spikes.add(new Spike(505, 60, 50, 30));
            spikes.add(new Spike(550, 60, 50, 30));
            spikes.add(new Spike(700, 220, 50, 30));
            
            // Floor 1
            for (int i = -50; i < 1000; i += 50) {
                walls.add(new Wall(i, 550, 50, 50));
            }
            
            // Platforms
            walls.add(new Wall(10, 120, 100, 10));
            walls.add(new Wall(150, 220, 150, 10));
            walls.add(new Wall(10, 420, 120, 10));
            walls.add(new Wall(250, 500, 200, 10));
            walls.add(new Wall(550, 450, 50, 10));
            walls.add(new Wall(650, 400, 50, 10));
            walls.add(new Wall(450, 350, 50, 10));
            walls.add(new Wall(350, 300, 50, 10));
            walls.add(new Wall(500, 250, 150, 10));
            walls.add(new Wall(350, 170, 50, 10));
            walls.add(new Wall(450, 90, 250, 10));
            walls.add(new Wall(820, 300, 150, 10));
            walls.add(new Wall(750, 500, 100, 10));
            
            
            // Wall
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(-40, i, 50, 50));
            }
            for (int i = -50; i < 400; i += 50) {
                walls.add(new Wall(300, i, 50, 50));
            }
            for (int i = -50; i < 900; i += 50) {
                if (i < 100 || i > 200) {
                    walls.add(new Wall(700, i, 50, 50));
                }
                
            }
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(970, i, 50, 50));
            }
            
        }
        else if (level == 4) {
            
            button.add(new Button(805, 330, this));
            door.add(new Door(50, 80, this));
            player = new Player(870, 450, this);
            
            guards.add(new Guard(200, 500, 200, 750,  5, this));
            
            // Floor 1
            for (int i = -50; i < 1000; i += 50) {
                walls.add(new Wall(i, 550, 50, 50));
            }
            for (int i = 200; i < 1000; i += 50) {
                walls.add(new Wall(i, 380, 50, 50));
            }
            for (int i = 200; i < 800; i += 50) {
                if (i < 365 || i > 515) {
                    walls.add(new Wall(i, 430, 50, 70)); 
                }
            }
            
            // Floor 2
            for (int i = 200; i < 650; i += 45) {
                spikes.add(new Spike(i, 350, 50, 30));
            }
            
            
            walls.add(new Wall(650, 330, 50, 50));
            
            walls.add(new Wall(450, 490, 50, 10)); 
            walls.add(new Wall(10, 490, 50, 10)); 
            walls.add(new Wall(150, 430, 50, 10)); 
            walls.add(new Wall(10, 370, 50, 10)); 
            walls.add(new Wall(200, 320, 380, 10)); 
            
            guards.add(new Guard(500, 270, 220, 530, 5, this));
            guards.add(new Guard(850, 330, 720, 930, 3, this));
            
            for (int i = 650; i < 1000; i += 50) {
                if (i < 800 || i > 840) {
                    walls.add(new Wall(i, 320, 50, 10));
                }
            }
            
            walls.add(new Wall(920, 240, 50, 10));
            walls.add(new Wall(800, 190, 50, 10));

            // Floor 3
            for (int i = -50; i < 800; i += 50) {
                walls.add(new Wall(i, 150, 50, 50));
            }
            
            spikes.add(new Spike(600, 120, 50, 30));
            spikes.add(new Spike(450, 120, 50, 30));
            spikes.add(new Spike(405, 120, 50, 30));
            spikes.add(new Spike(300, 120, 50, 30));
            spikes.add(new Spike(255, 120, 50, 30));
            
            // Wall
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(-40, i, 50, 50));
            }
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(970, i, 50, 50));
            }
        }
        else if (level == 5) {
            
            button.add(new Button(370, 500, this));
            door.add(new Door(380, 330, this));
            player = new Player(870, 50, this);
            
            
            // Floor 3
            for (int i = 150; i < 1000; i += 50) {
                walls.add(new Wall(i, 100, 50, 50));
            }
            
            walls.add(new Wall(750, 50, 50, 50));
            walls.add(new Wall(150, 50, 50, 50));
            
            guards.add(new Guard(200, 50, 200, 450, 2, this));
            guards.add(new Guard(450, 50, 450, 700, 2, this));
            guards.add(new Guard(500, 500, 400, 700, 2, this));
            
            walls.add(new Wall(100, 200, 50, 50));
            spikes.add(new Spike(100, 170, 50, 30));
            walls.add(new Wall(10, 450, 50, 50));
            spikes.add(new Spike(10, 420, 50, 30));
            
            walls.add(new Wall(250, 480, 50, 10));
            walls.add(new Wall(200, 410, 50, 10));
            walls.add(new Wall(250, 340, 50, 10));
            walls.add(new Wall(200, 270, 50, 10));
            
            // Floor 2
            walls.add(new Wall(350, 400, 450, 10)); 
            for (int i = 300; i < 800; i += 50) {
                walls.add(new Wall(i, 250, 50, 50));
            }
            spikes.add(new Spike(400, 220, 50, 30));
            spikes.add(new Spike(550, 220, 50, 30));
            spikes.add(new Spike(700, 220, 50, 30));
            
            walls.add(new Wall(900, 450, 50, 10));
            walls.add(new Wall(850, 500, 50, 10));
            
            // Floor 1
            for (int i = -50; i < 1000; i += 50) {
                walls.add(new Wall(i, 550, 50, 50));
            }
            
            spikes.add(new Spike(550, 370, 50, 30));
            spikes.add(new Spike(700, 370, 50, 30));
            
            spikes.add(new Spike(750, 520, 50, 30));
            spikes.add(new Spike(600, 520, 50, 30));
            spikes.add(new Spike(450, 520, 50, 30));
            
            // Wall
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(-40, i, 50, 50));
            }
            for (int i = 100; i < 500; i += 50) {
                walls.add(new Wall(150, i, 50, 50));
            }
            for (int i = 300; i < 600; i += 50) {
                walls.add(new Wall(300, i, 50, 50));
            }
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(970, i, 50, 50));
            }
        }
        else if (level == 6) {
            
            button.add(new Button(80, 300, this));
            door.add(new Door(880, 80, this));
            player = new Player(90, 500, this);
            
            
            // Floor 1
            for (int i = -50; i < 1000; i += 50) {
                walls.add(new Wall(i, 550, 50, 50));
            }
            
            guards.add(new Guard(700, 500, 200, 700, 7, this));
            guards.add(new Guard(200, 500, 200, 700, 4, this));
            
            spikes.add(new Spike(200, 520, 50, 30));
            spikes.add(new Spike(245, 520, 50, 30));
            spikes.add(new Spike(350, 520, 50, 30));
            spikes.add(new Spike(395, 520, 50, 30));
            spikes.add(new Spike(500, 520, 50, 30));
            spikes.add(new Spike(545, 520, 50, 30));
            spikes.add(new Spike(650, 520, 50, 30));
            spikes.add(new Spike(695, 520, 50, 30));
                    
            walls.add(new Wall(800, 500, 50, 10));
            walls.add(new Wall(920, 440, 50, 10));
            walls.add(new Wall(800, 390, 50, 10));
            
            // Floor 2
            for (int i = -50; i < 800; i += 50) {
                walls.add(new Wall(i, 350, 50, 50));
            }
            
            guards.add(new Guard(550, 300, 430, 540, 18, this));
            
            spikes.add(new Spike(225, 320, 50, 30));
            spikes.add(new Spike(375, 320, 50, 30));
            spikes.add(new Spike(525, 320, 50, 30));
            spikes.add(new Spike(675, 320, 50, 30));
            
            walls.add(new Wall(150, 300, 50, 10));
            walls.add(new Wall(10, 240, 50, 10));
            walls.add(new Wall(150, 190, 50, 10));
            
            // Floor 3
            for (int i = 200; i < 1000; i += 50) {
                walls.add(new Wall(i, 150, 50, 50));
            }
            
            guards.add(new Guard(700, 100, 250, 680, 4, this));
            guards.add(new Guard(500, 100, 250, 680, 2, this));
            
            spikes.add(new Spike(250, 120, 50, 30));
            spikes.add(new Spike(475, 120, 50, 30));
            spikes.add(new Spike(700, 120, 50, 30));
            
            // Wall
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(-40, i, 50, 50));
            }
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(970, i, 50, 50));
            }
        }
        else if (level == 7) {
            
            setBackground(new Color(161,227,238));
            
            button.add(new Button(500, 700, this));
            player = new Player(200, 500, this);
            
            for (int i = -50; i < 1000; i += 50) {
                walls.add(new Wall(i, 550, 50, 50));
            }
            for (int i = 400; i < 1000; i += 50) {
                walls.add(new Wall(0, i, 100, 50));
            }
            
            door.add(new Door(20, 480, this));
            
            for (int i = -50; i < 900; i += 50) {
                walls.add(new Wall(990, i, 50, 50));
            }
        }
    }
    
    public void paint(Graphics g) {

        super.paint(g);
        
        Graphics2D gtd = (Graphics2D) g;
        
        for (Button button: button) {
            button.draw(gtd);
        }
        
        for (Guard guard: guards) {
            guard.draw(gtd);
        }
        
        for (Spike spike: spikes) {
            spike.draw(gtd);
        }
        
        for (Wall wall: walls) {
            wall.draw(gtd);
        }
        
        for (Door door: door) {
            door.draw(gtd);
        }
        
        player.draw(gtd);
        
    }
        
    void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a') player.keyLeft = true;
        if (e.getKeyChar() == 'd') player.keyRight = true;
        if (e.getKeyChar() == 'w') player.keyUp = true;
        if (Character.isSpaceChar(e.getKeyChar())) player.interact = true;
    }
    
    void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'a') player.keyLeft = false;
        if (e.getKeyChar() == 'd') player.keyRight = false;
        if (e.getKeyChar() == 'w') player.keyUp = false;
        if (Character.isSpaceChar(e.getKeyChar())) player.interact = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }
}