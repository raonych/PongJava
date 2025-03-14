import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class Pong extends JPanel implements ActionListener, KeyListener {
    private final HashSet<Integer> keysPressed = new HashSet<>();
    int boardWidth = 800;
    int boardHeight = 400;
    int courtWidth = 790;
    int courtHeight = 390;

    static class Player {
        int x, y;
        static final int width = 10;
        static final int height = 60;

        Player(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int ballDiameter = 20;

     //Ball's initial position
    int ballX = (courtWidth / 2) - (ballDiameter/2);
    int ballY = (courtHeight / 2) - (ballDiameter/2);

    class Ball {
        int x = ballX;
        int y = ballY;
        Boolean Xdirection = null;
        Boolean Ydirection = null;
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

        g.drawRect(5,5,courtWidth,courtHeight);
        g.drawLine(courtWidth/2,5,courtWidth/2,395);
        int circleDiameter = 60;
        int circleX = (courtWidth / 2) - (circleDiameter/2);
        int circleY = (courtHeight / 2) - (circleDiameter/2);
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
        if(ball.Ydirection != null){
            if (ball.Ydirection) {
                ball.y -= 5;
            }
            if(!ball.Ydirection){
                ball.y += 5;
            }
        }




        //colisão dos players com o cenário
        if (keysPressed.contains(KeyEvent.VK_W)) {
            playerOne.y = Math.max(5, playerOne.y - 20);
        }
        if (keysPressed.contains(KeyEvent.VK_S)) {
            playerOne.y = Math.min(395 - Player.height, playerOne.y + 20);
        }

        if (keysPressed.contains(KeyEvent.VK_UP)) {
            playerTwo.y = Math.max(5, playerTwo.y - 20);
        }
        if (keysPressed.contains(KeyEvent.VK_DOWN)) {
            playerTwo.y = Math.min(395 - Player.height, playerTwo.y + 20);
        }

        //colisão vertical da bola
        if(ball.y <= 5 ){
            ball.Ydirection = false;
        }
        if(ball.y >= courtHeight - ballDiameter){
            ball.Ydirection = true;
        }
        //colisão da bola com os players

            //colisão horizontal player 1
        if (ball.x <= playerOne.x + Player.width && ball.y + ballDiameter >= playerOne.y && ball.y <= playerOne.y + Player.height) {
            ball.Xdirection = true;
        }
        //colisão vertical player 1
        if(ball.x <= playerOne.x + Player.width && ball.y + ballDiameter >= playerOne.y && ball.y <= playerOne.y + Player.height && keysPressed.contains(KeyEvent.VK_W)){
           ball.Ydirection = true;
        }
        if(ball.x <= playerOne.x + Player.width && ball.y + ballDiameter >= playerOne.y && ball.y <= playerOne.y + Player.height && keysPressed.contains(KeyEvent.VK_S)){
            ball.Ydirection = false;
        }
        //colisão horizontal player 2
        if (ball.x + ballDiameter >= playerTwo.x && ball.y + ballDiameter >= playerTwo.y && ball.y <= playerTwo.y + Player.height) {
            ball.Xdirection = false;

            //colisão vertical player 2
            if(ball.x <= playerTwo.x + Player.width && ball.y + ballDiameter >= playerTwo.y && ball.y <= playerTwo.y + Player.height && keysPressed.contains(KeyEvent.VK_UP)){
                ball.Ydirection = true;
            }
            if(ball.x <= playerTwo.x + Player.width && ball.y + ballDiameter >= playerTwo.y && ball.y <= playerTwo.y + Player.height && keysPressed.contains(KeyEvent.VK_DOWN)){
                ball.Ydirection = false;
            }
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
