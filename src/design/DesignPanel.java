package design;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import inputs.MouseInputDesign;

public class DesignPanel extends JPanel implements ActionListener {

    // !!! variabilele statice nu se reseteaza dupa resetarea jocului
    final int SCREEN_WIDTH = 600;
    final int SCREEN_HEIGHT = 400;
    int UNIT_SIZE = 25;
    final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    long score = 0;
    int bodyParts = 6;
    int applesEaten;
    static int appleX;
    static int appleY;
    Random random;
    Rectangle obstacleDOWN;
    Rectangle obstacleRIGHT;
    Rectangle obstacleLEFT;
    Rectangle head;
    Timer timer;

    DesignPanel() {

        timer = new Timer(0, this);
        timer.start();

        random = new Random();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);

        for (int i = 0; i < bodyParts; i++)
            y[i] = 100;

        newApple();
        move();

    }

    public void move() {
        int copy;

        copy = UNIT_SIZE;

        int j = 0;

        for (int i = bodyParts; i >= 0; i--) {
            x[j] += copy * i;
            j++;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        int i;

        for (i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }

        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        for (i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.white);
                head = new Rectangle(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.setColor(DesignColor.colorSnake);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }

        g.setColor(new Color(255, 165, 0, 127));
        g.fillRect(0, 0, SCREEN_WIDTH, 50);

        g.setColor(Color.cyan);
        g.setFont(new Font("MV Boli", Font.PLAIN, 40));
        g.drawString("Your score is " + score, SCREEN_WIDTH / 4, 40);

        obstacleLEFT = new Rectangle(0, 50, UNIT_SIZE, SCREEN_HEIGHT - UNIT_SIZE);

        g.setColor(DesignColor.colorWall);
        g.fillRect(0, 50, UNIT_SIZE, SCREEN_HEIGHT - 10);

        obstacleRIGHT = new Rectangle(SCREEN_WIDTH - UNIT_SIZE, 50, UNIT_SIZE, SCREEN_HEIGHT);

        g.fillRect(SCREEN_WIDTH - UNIT_SIZE, 50, UNIT_SIZE, SCREEN_HEIGHT);

        obstacleDOWN = new Rectangle(0, SCREEN_HEIGHT - UNIT_SIZE, SCREEN_WIDTH, UNIT_SIZE);

        g.fillRect(0, SCREEN_HEIGHT - UNIT_SIZE, SCREEN_WIDTH, UNIT_SIZE);

        this.setBackground(DesignColor.colorBackground);


    }

    public void newApple() {
        // marul se va genera in unul dintre patratele

        do {

            appleX = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;

        } while (appleX <= 25 || appleX >= SCREEN_HEIGHT - UNIT_SIZE);


        do {

            appleY = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        } while (appleY < 75 || appleY >= SCREEN_HEIGHT - UNIT_SIZE);


        while (appleX >= 0 && appleX <= 150 && appleY == 100) {
            appleX = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            appleY = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (MouseInputDesign.snakePressed || MouseInputDesign.wallPressed || MouseInputDesign.backgroundPressed) {
            repaint();
        }

        if (MouseInputDesign.newGameDesignPressed)
            timer.stop();

    }


}
