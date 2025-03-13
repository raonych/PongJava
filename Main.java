import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        int width = 800;
        int height = 400;

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Pong");
        window.setResizable(false);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);

        Pong gamePanel = new Pong();
        window.add(gamePanel);

        window.pack();
        window.requestFocus();

        window.setVisible(true);

        

    }
}
