package com.github.trentmenard;

import javax.swing.*;
import java.awt.*;

public class NetflixFrame {
    private final JFrame mainFrame;
    public NetflixFrame() {
        mainFrame = new JFrame("Netflix");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        mainFrame.setSize(getHalfScreenSize());
        // Center the JFrame
        mainFrame.setLocationRelativeTo(null);
        // Size & fit window relative to components
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private Dimension getHalfScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int halfWidth = Math.floorDiv(screenSize.getSize().width, 2);
        int halfHeight = Math.floorDiv(screenSize.getSize().height, 2);
        return new Dimension(halfWidth, halfHeight);
    }
    protected JFrame getMainFrame() {
        return mainFrame;
    }
}
