package menuDesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import inputs.MouseInputDesign;

public class MenuDesignPanel extends JPanel {

    static final int MENU_DESIGN_WIDTH = 450;
    static final int MENU_DESIGN_HEIGHT = 600;
    ImageIcon image;

    public static int backgroundX;
    public static int backgroundY;
    public static int snakeX;
    public static int snakeY;
    public static int wallX;
    public static int wallY;
    public static int newGameDesignX;
    public static int newGameDesignY;

    public static int length;
    public static int height;


    public MenuDesignPanel(MenuDesignFrame frame) {

        image = new ImageIcon("rainbow.jpg");

        this.setPreferredSize(new Dimension(MENU_DESIGN_WIDTH, MENU_DESIGN_HEIGHT));
        this.addMouseListener(new MouseInputDesign(frame));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(image.getImage(), 0, 0, null);

        Font font = new Font("MV Boli", Font.PLAIN, 50);

        FontMetrics fm = getFontMetrics(font);

        length = fm.stringWidth("Background color");
        height = fm.getHeight();

        g2D.setFont(font);

        g2D.drawString("Background color", MENU_DESIGN_WIDTH / 2 - length / 2, MENU_DESIGN_HEIGHT / 4);
        g2D.drawString("Snake color", MENU_DESIGN_WIDTH / 2 - length / 3 - 10, MENU_DESIGN_HEIGHT / 3 + 80);
        g2D.drawString("Wall color", MENU_DESIGN_WIDTH / 2 - length / 3 + 5, MENU_DESIGN_HEIGHT / 3 + 220);
        g2D.drawString("New game", MENU_DESIGN_WIDTH / 2 - length / 3 + 5, MENU_DESIGN_HEIGHT / 2 + 240);

        g2D.setColor(new Color(120, 0, 0, 150));

        backgroundX = MENU_DESIGN_WIDTH / 2 - length / 2;
        backgroundY = MENU_DESIGN_HEIGHT / 6;

        snakeX = MENU_DESIGN_WIDTH / 2 - length / 2;
        snakeY = MENU_DESIGN_HEIGHT / 3 + 20;

        wallX = MENU_DESIGN_WIDTH / 2 - length / 2;
        wallY = MENU_DESIGN_HEIGHT / 3 + 160;

        newGameDesignX = MENU_DESIGN_WIDTH / 2 - length / 2;
        newGameDesignY = MENU_DESIGN_HEIGHT / 3 + 300;

        g2D.fillRect(backgroundX, backgroundY, length + 10, height);
        g2D.fillRect(snakeX, snakeY, length + 10, height);
        g2D.fillRect(wallX, wallY, length + 10, height);
        g2D.fillRect(newGameDesignX, newGameDesignY, length + 10, height);

    }


}
