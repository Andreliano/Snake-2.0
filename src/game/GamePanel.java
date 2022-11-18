package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import design.DesignColor;
import inputs.*;

public class GamePanel extends JPanel implements ActionListener {

    // !!! variabilele statice nu se reseteaza dupa resetarea jocului
    static final int SCREEN_WIDTH = 700;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    public static int DELAY = 120;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    public char direction = 'R';
    long score = 0;
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    public boolean running = false;
    public static boolean escapePressed = false;

    Timer timer;
    Random random;
    Rectangle obstacleDOWN;
    Rectangle obstacleRIGHT;
    Rectangle obstacleLEFT;
    Rectangle head;
    File file;
    AudioInputStream audioStream;
    Clip clip;
    public JLabel gameOver;
    boolean ok = false;
    GameFrame frame;

    ImageIcon snakeLife;
    int snakeLifeContor = 3;

    public GamePanel(GameFrame frame) {

        this.frame = frame;

        snakeLife = new ImageIcon("little_snake.jpg");

        random = new Random();

        this.addKeyListener(new inputs.MyKeyAdapter(this, frame));
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(DesignColor.colorBackground);
        this.setFocusable(true);

        startGame();

    }

    public void startGame() {

        newApple();

        try {
            checkApple();
        } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        running = true;
        timer = new Timer(DELAY, this);
        timer.start();


        for (int i = 0; i < bodyParts; i++)
            y[i] = 100;

    }

    public static void chewSound() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("snack.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

        long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(500L, TimeUnit.MILLISECONDS); // while-ul va dura 1 sec
        while (System.nanoTime() < endTime) ;

        clip.stop();
        clip.setMicrosecondPosition(0);
    }

    public void wallSound() throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException {
        File file = new File("wall_sound.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
        Thread.sleep(400);

        clip.stop();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

        if (!running && !ok && !escapePressed) {

            GameOver();
            ok = true;
        }

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

//		int red = DesignColor.colorWall.getRed();
//		int green = DesignColor.colorWall.getGreen();
//		int blue = DesignColor.colorWall.getBlue();

        g.setColor(DesignColor.colorWall);

        g.fillRect(0, 50, UNIT_SIZE, SCREEN_HEIGHT - 10);

        obstacleRIGHT = new Rectangle(SCREEN_WIDTH - UNIT_SIZE, 50, UNIT_SIZE, SCREEN_HEIGHT);

        g.fillRect(SCREEN_WIDTH - UNIT_SIZE, 50, UNIT_SIZE, SCREEN_HEIGHT);

        obstacleDOWN = new Rectangle(0, SCREEN_HEIGHT - UNIT_SIZE, SCREEN_WIDTH, UNIT_SIZE);

        g.fillRect(0, SCREEN_HEIGHT - UNIT_SIZE, SCREEN_WIDTH, UNIT_SIZE);


        if (snakeLifeContor == 3) {
            g.drawImage(snakeLife.getImage(), 0, SCREEN_HEIGHT / 2, null);

            g.drawImage(snakeLife.getImage(), SCREEN_WIDTH / 2, SCREEN_HEIGHT - UNIT_SIZE, null);

            g.drawImage(snakeLife.getImage(), SCREEN_WIDTH - UNIT_SIZE, SCREEN_HEIGHT / 2, null);
        } else if (snakeLifeContor == 2) {
            g.drawImage(snakeLife.getImage(), SCREEN_WIDTH / 2, SCREEN_HEIGHT - UNIT_SIZE, null);

            g.drawImage(snakeLife.getImage(), SCREEN_WIDTH - UNIT_SIZE, SCREEN_HEIGHT / 2, null);
        } else if (snakeLifeContor == 1) {
            g.drawImage(snakeLife.getImage(), SCREEN_WIDTH - UNIT_SIZE, SCREEN_HEIGHT / 2, null);
        }

    }

    public void newApple() {
        // marul se va genera in unul dintre patratele

        do {
            appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        }
        while (appleX <= 25 || appleX >= SCREEN_HEIGHT - UNIT_SIZE);

        do {

            appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;

        } while (appleY < 75 || appleY >= SCREEN_HEIGHT - UNIT_SIZE);

    }

    public void currentDirection() {
        int i;
        for (i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
    }

    public void changeToOppositeDirection() {
        int i, j;

        for (j = 0; j < bodyParts - 1; j++) {
            for (i = bodyParts; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
        }
    }

    public void move() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        int j;

        currentDirection();

        switch (direction) {
            case 'U' -> {
                if (MyKeyAdapter.oppositeDirection) {
                    y[0] = y[0] - (bodyParts - 1) * UNIT_SIZE;
                    changeToOppositeDirection();
                } else
                    y[0] = y[0] - UNIT_SIZE;
                MyKeyAdapter.oppositeDirection = false;
                checkApple();
            }
            case 'D' -> {
                if (MyKeyAdapter.oppositeDirection) {
                    y[0] = y[0] + (bodyParts - 1) * UNIT_SIZE;
                    changeToOppositeDirection();
                } else
                    y[0] = y[0] + UNIT_SIZE;
                MyKeyAdapter.oppositeDirection = false;
                checkApple();
            }
            case 'L' -> {
                if (MyKeyAdapter.oppositeDirection) {
                    x[0] = x[0] - (bodyParts - 1) * UNIT_SIZE;
                    changeToOppositeDirection();
                } else
                    x[0] = x[0] - UNIT_SIZE;
                MyKeyAdapter.oppositeDirection = false;
                checkApple();
            }
            case 'R' -> {
                if (MyKeyAdapter.oppositeDirection) {
                    x[0] = x[0] + (bodyParts - 1) * UNIT_SIZE;
                    changeToOppositeDirection();
                } else
                    x[0] = x[0] + UNIT_SIZE;
                MyKeyAdapter.oppositeDirection = false;
                checkApple();
            }
        }

    }

    public void checkApple() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

        if (x[0] == appleX && y[0] == appleY) {
            chewSound();
            DELAY -= 5;
            timer.setDelay(DELAY);
            score++;
            bodyParts += 2;
            newApple();
        }

    }

    public void leftCollision() {
        int i;

        for (i = bodyParts; i >= 0; i--)
            x[i] += 300;

    }

    public void rightCollision() {
        int i;

        for (i = bodyParts; i >= 0; i--)
            x[i] -= 300;

    }

    public void downCollision() {
        int i;

        for (i = bodyParts; i >= 0; i--)
            y[i] -= 300;
    }

    public void upCollision() {
        int i;

        for (i = bodyParts; i >= 0; i--)
            y[i] += 300;
    }

    public void checkCollisions() throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException {
        int i;

        if (direction != 'R' && head.intersects(obstacleLEFT)) {
            wallSound();
            if (snakeLifeContor == 1) {
                snakeLifeContor--;
                running = false;
            } else {
                snakeLifeContor--;
                leftCollision();
            }
        }

        if (head.intersects(obstacleRIGHT)) {
            wallSound();
            if (snakeLifeContor == 1) {
                snakeLifeContor--;
                running = false;
            } else {
                snakeLifeContor--;
                rightCollision();
            }

        }

        if (head.intersects(obstacleDOWN)) {
            wallSound();
            if (snakeLifeContor == 1) {
                snakeLifeContor--;
                running = false;
            } else {
                snakeLifeContor--;
                downCollision();
            }
        }

        // checks if head collides with body
        for (i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                if (snakeLifeContor == 1) {
                    snakeLifeContor--;
                    running = false;
                } else {
                    snakeLifeContor--;
                    break;
                }
            }
        }

        // checks if head collides transparent rectangle
        if (y[0] <= 50) {
            wallSound();
            if (snakeLifeContor == 1) {
                snakeLifeContor--;
                running = false;
            } else {
                snakeLifeContor--;
                upCollision();
            }
        }

    }

    public void GameOver() {

        gameOver = new JLabel();

        Font font = new Font("MV Boli", Font.PLAIN, 50);

        gameOver.setFont(font);
        gameOver.setText("GAME OVER");
        gameOver.setForeground(Color.orange);
        gameOver.setBackground(Color.black);
        gameOver.setOpaque(true);

        FontMetrics fm = gameOver.getFontMetrics(font);
        int Ilength = fm.stringWidth("GAME OVER");
        int Iheight = fm.getHeight();

        gameOver.setBounds(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4, Ilength + 20, Iheight - 20);

        this.add(gameOver);

        DELAY = 120;

        // IN MOMENTUL IN CARE DAM timer.stop() nu se mai intra in actionPerformed
        //timer.stop();


		/*
		Graphics2D g2D = (Graphics2D) g;

		g2D.setColor(Color.orange);

		Font font = new Font("MV Boli", Font.PLAIN, 50);

		g2D.setFont(font);

		g2D.drawString("GAME OVER", SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4);

		*/


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(inputs.MyKeyAdapter.oppositeDirection);

        if (!escapePressed) {
            if (MouseInput.continuePressed) {
                if (Objects.equals(frame.getTitle(), "alta")) {
                    frame.setVisible(false);
                    frame.dispose();
                    timer.stop();
                    return;
                }

                frame.setVisible(true);
                MouseInput.continuePressed = false;
            } else if (MouseInput.exitPressed) {
                System.exit(0);
            }


            if (running) {
                try {
                    move();
                } catch (InterruptedException | UnsupportedAudioFileException | IOException |
                         LineUnavailableException e1) {
                    e1.printStackTrace();
                }

                try {
                    checkCollisions();
                } catch (InterruptedException | LineUnavailableException | UnsupportedAudioFileException |
                         IOException e1) {
                    e1.printStackTrace();
                }

                repaint();

            }

        }

    }


}
