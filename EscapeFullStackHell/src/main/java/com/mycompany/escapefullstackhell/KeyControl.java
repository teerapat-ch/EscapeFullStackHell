package com.mycompany.escapefullstackhell;

import java.awt.event.*;

public class KeyControl extends KeyAdapter {
    
    public GamePanel panel;
    
    KeyControl(GamePanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        panel.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        panel.keyReleased(e);
    }
}
