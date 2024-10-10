package com.mycompany.escapefullstackhell;
import javax.swing.*;

public class EscapeFullStackHell {

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setTitle("Escape Full Stack Hell");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
