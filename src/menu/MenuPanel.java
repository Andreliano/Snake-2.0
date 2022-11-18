package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import inputs.MouseInput;

public class MenuPanel extends JPanel {

    ImageIcon image;
    static final int MENU_WIDTH = 700;
    static final int MENU_HEIGHT = 700;
    public int continueX;
    public int continueY;
    public int newGameX;
    public int newGameY;
    public int exitX;
    public int exitY;
    public int continueLength;
    public int continueHeight;
    public int newGameLength;
    public int newGameHeight;
    public int exitLength;
    public int exitHeight;

    MenuPanel(MenuFrame menuFrame) {
        image = new ImageIcon("background.png");

        this.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        this.addMouseListener(new MouseInput(menuFrame, this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(image.getImage(), 0, 0, null);

        Font font = new Font("MV Boli", Font.PLAIN, 80);

        continueX = MENU_WIDTH / 2 - 200;
        continueY = MENU_HEIGHT / 3;
        newGameX = MENU_WIDTH / 2 - 230;
        newGameY = MENU_HEIGHT / 3 + 120;
        exitX = MENU_WIDTH / 2 - 120;
        exitY = MENU_HEIGHT / 3 + 240;

        g2D.setFont(font);

        FontMetrics fm = getFontMetrics(font);

        continueLength = fm.stringWidth("Continue");
        continueHeight = fm.getHeight();

        newGameLength = fm.stringWidth("New Game");
        newGameHeight = fm.getHeight();

        exitLength = fm.stringWidth("Exit");
        exitHeight = fm.getHeight();

        g2D.setColor(Color.red);
        g2D.drawString("Continue", continueX, continueY);
        g2D.drawString("New Game", newGameX, newGameY);
        g2D.drawString("Exit", exitX, exitY);


        g2D.setColor(new Color(0, 0, 0, 127));

        g2D.fillRect(continueX, continueY - 60, continueLength + 2, continueHeight - 50);
        g2D.fillRect(newGameX, newGameY - 70, newGameLength + 2, newGameHeight - 50);
        g2D.fillRect(exitX, exitY - 70, exitLength + 2, exitHeight - 50);


    }


}
