package design;

import javax.swing.JFrame;

public class DesignFrame extends JFrame {

    DesignPanel designPanel;

    public DesignFrame() {

        designPanel = new DesignPanel();

        this.add(designPanel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocation(500, 0);
    }
}
