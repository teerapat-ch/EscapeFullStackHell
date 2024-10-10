package com.mycompany.escapefullstackhell;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener {

    GamePanel panel;

    public JPanel menu;
    public JLabel title;
    public JPanel buttonPanel;
    public JButton start;
    public JPanel lowerPanel;
    public JPanel controlsPanel;
    public JLabel control1;
    public JLabel control2;
    public JLabel control3;

    BackgroundMusic bgMusic;

    GameFrame() {
        panel = new GamePanel();
        panel.setLocation(0, 0);
        panel.setSize(this.getSize());
        panel.setVisible(true);

        bgMusic = new BackgroundMusic(("/gameBG.wav"), 0.9f);
        new Thread(() -> {
            try {
                bgMusic.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Menu
        menu = new JPanel();
        menu.setLayout(new GridLayout(2, 1, 0, 20));
        menu.setBackground(new Color(191, 143, 143));
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(191, 143, 143));

        lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(2, 1));

        title = new JLabel("Escape Full Stack Hell");
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.BOTTOM);

        start = new JButton("Start");
        start.setFocusable(false);
        start.setFont(new Font("Serif", Font.PLAIN, 30));
        start.setBackground(new Color(232, 192, 192));
        start.setHorizontalAlignment(JLabel.CENTER);
        start.addActionListener(this);

        controlsPanel = new JPanel();
        controlsPanel.setBackground(new Color(191, 143, 143));
        controlsPanel.setLayout(new GridLayout(3, 1));

        control1 = new JLabel("A/D  -  Move");
        control1.setHorizontalAlignment(JLabel.CENTER);
        control1.setFont(new Font("Serif", Font.BOLD, 20));
        controlsPanel.add(control1);

        control2 = new JLabel("W  -  Jump");
        control2.setHorizontalAlignment(JLabel.CENTER);
        control2.setFont(new Font("Serif", Font.BOLD, 20));
        controlsPanel.add(control2);

        control3 = new JLabel("Spacebar  -  Interact");
        control3.setHorizontalAlignment(JLabel.CENTER);
        control3.setFont(new Font("Serif", Font.BOLD, 20));
        controlsPanel.add(control3);

        menu.add(title);
        buttonPanel.add(start);

        lowerPanel.add(buttonPanel);
        lowerPanel.add(controlsPanel);

        menu.add(lowerPanel);
        this.add(menu);

        addKeyListener(new KeyControl(panel));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            menu.setVisible(false);
            this.add(panel);
        }
    }
}