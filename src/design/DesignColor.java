package design;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;

import inputs.MouseInputDesign;
import menuDesign.MenuDesignFrame;

public class DesignColor implements ActionListener {

    public static Color colorBackground = Color.black;
    public static Color colorSnake = Color.green;
    public static Color colorWall = Color.blue;
    JDialog dialog;
    JFrame frame;
    boolean cancelPressed = false;
    static boolean DesignFramePrinted = false;
    static boolean DesignColorCanceled = false;
    public static boolean DesignOptionOpened = false;
    Timer timer;
    MenuDesignFrame menuDesignFrame;

    public DesignColor(MenuDesignFrame menuDesignFrame) {

        this.menuDesignFrame = menuDesignFrame;

        timer = new Timer(0, this);
        timer.start();

        if (!DesignFramePrinted && !MouseInputDesign.newGameDesignPressed) {
            frame = new DesignFrame();
            windowColorChooserListener();
            DesignFramePrinted = true;
        }

        if (DesignColorCanceled) {
            windowColorChooserListener();
            DesignColorCanceled = false;
        }

    }

    public void windowColorChooserListener() {
        final JColorChooser chooser = new JColorChooser();

        if (cancelPressed)
            return;

        ActionListener okListener = e -> {
            // TODO Auto-generated method stub

            if (MouseInputDesign.backgroundPressed)
                colorBackground = chooser.getColor();
            else if (MouseInputDesign.snakePressed)
                colorSnake = chooser.getColor();
            else if (MouseInputDesign.wallPressed)
                colorWall = chooser.getColor();


            windowColorChooserListener();

        };

        ActionListener cancelListener = e -> {

            cancelPressed = true;

            DesignColorCanceled = true;
            DesignOptionOpened = false;

            MouseInputDesign.backgroundPressed = false;
            MouseInputDesign.snakePressed = false;
            MouseInputDesign.wallPressed = false;


        };

        dialog = JColorChooser.createDialog(null, "Colors", false, chooser, okListener, cancelListener);

        dialog.setVisible(true);
        dialog.setLocation(500, 435);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (MouseInputDesign.newGameDesignPressed) {

            if (frame != null) {
                frame.setVisible(false);
                frame.dispose();
            }

            if (dialog != null) {
                dialog.setVisible(false);
                dialog.dispose();
            }

            menuDesignFrame.setVisible(false);
            menuDesignFrame.dispose();

            timer.stop();

        }

    }


}
