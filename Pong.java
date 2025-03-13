import javax.swing.*;
import java.awt.*;

public class Pong extends JPanel {

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

    int playerInitialPosition = boardHeight/2-(Player.height/2);

    Player playerOne;

    Player playerTwo;


    Pong() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.BLACK);
        setFocusable(true);

        playerOne = new Player(25, playerInitialPosition);

        playerTwo = new Player(765, playerInitialPosition);



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

    }


}
