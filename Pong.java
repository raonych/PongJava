import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class Pong extends JPanel implements ActionListener, KeyListener {
    private HashSet<Integer> keysPressed = new HashSet<>();
    int boardWidth = 800;
    int boardHeight = 400;

    static class Player {
        int x, y;
        static final int width = 15;
        static final int height = 70;

        Player(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int ballDiameter = 20;

     //Ball's initial position
    int ballX = (790 / 2) - (ballDiameter/2);
    int ballY = (390 / 2) - (ballDiameter/2);

    class Ball {
        int x = ballX;
        int y = ballY;
        Boolean Xdirection = null;
    }
    int playerInitialPosition = boardHeight/2-(Player.height/2);

    Player playerOne;

    Player playerTwo;

    Ball ball;

    Timer gameloop;

    Pong() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        playerOne = new Player(25, playerInitialPosition);
        playerTwo = new Player(765, playerInitialPosition);
        ball = new Ball();

        gameloop = new Timer(1000/60, this);
        gameloop.start();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        g.setColor(Color.WHITE);

        g.drawRect(5,5,790,390);
        g.drawLine(790/2,5,790/2,395);
        int circleDiameter = 60;
        int circleX = (790 / 2) - (circleDiameter/2);
        int circleY = (390 / 2) - (circleDiameter/2);
        g.drawOval(circleX, circleY, circleDiameter, circleDiameter);



        g.fillRect(playerOne.x, playerOne.y, Player.width, Player.height);

        g.fillRect(playerTwo.x, playerTwo.y, Player.width, Player.height);

        g.setColor(Color.RED);

        g.fillOval(ball.x, ball.y,ballDiameter, ballDiameter);

    }

    public void move() {
        boolean initialDirection = Math.random() < 0.5;
        ball.Xdirection = (ball.Xdirection == null) ? initialDirection : ball.Xdirection;
        if (ball.Xdirection) {
            ball.x += 10;
        } else {
            ball.x -= 10;
        }
        if (keysPressed.contains(KeyEvent.VK_W)) {
            playerOne.y -= 20;
        }
        if (keysPressed.contains(KeyEvent.VK_S)) {
            playerOne.y += 20;
        }

        if (keysPressed.contains(KeyEvent.VK_UP)) {
            playerTwo.y -= 20;
        }
        if (keysPressed.contains(KeyEvent.VK_DOWN)) {
            playerTwo.y += 20;
        }



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}


}
