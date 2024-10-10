package com.mycompany.escapefullstackhell;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Quiz extends JFrame implements ActionListener{
    
    public Player player;
    public JLabel quiz;
    public JTextField input = new JTextField(10);
    public JButton enter = new JButton("Enter");
    public String password;
    
    Quiz(int level, Player player) {
        this.player = player;
        setTitle("Door " + level + " password");
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setResizable(false);
        setLocation(525, 325);
        setSize(400, 100);
        setVisible(true);
        enter.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        if (level == 1) {
            password = "19";
            quiz = new JLabel("(10011)₂ = (???)₁₀ ");
            add(quiz);
            add(input);
            add(enter);
        }
        else if (level == 2) {
            password = "377";
            quiz = new JLabel("(571)₈ = (???)₁₀ ");
            add(quiz);
            add(input);
            add(enter);
        }
        else if (level == 3) {
            password = "501";
            quiz = new JLabel("(1F5)₁₆ = (???)₁₀ ");
            add(quiz);
            add(input);
            add(enter);
        }
        else if (level == 4) {
            password = "29";
            quiz = new JLabel("(0010 1001)BCD = (???)₁₀ ");
            add(quiz);
            add(input);
            add(enter);
        }
        else if (level == 5) {
            password = "40";
            quiz = new JLabel("(0111 0011)X3 = (???)₁₀ ");
            add(quiz);
            add(input);
            add(enter);
        }
        else if (level == 6) {
            password = "11000100";
            quiz = new JLabel("(10100110)Gray = (???)₂ ");
            add(quiz);
            add(input);
            add(enter);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            if (input.getText().strip().equals(password)) {
                player.LoadLevel(1);
                this.dispose();
            }
            else {
                input.setText("");
            }
        }
    }
}

