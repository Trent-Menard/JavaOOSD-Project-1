package com.github.trentmenard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NetflixPanel extends NetflixFrame{
    private JPanel netflixPanel;
    private JButton addShowButton;
    private JTextField searchTextField;
    private JComboBox<String> comboBox1;
    private JRadioButton filterNoneRadioButton;
    private JRadioButton filterMoviesRadioButton;
    private JRadioButton filterTVShowsRadioButton;
    private ButtonGroup filterButtonGroup = new ButtonGroup();

    public NetflixPanel(ShowCollection showCollection) {
        super();
        super.getMainFrame().setContentPane(netflixPanel);
        super.getMainFrame().setBackground(new Color(224, 225, 221));

        filterButtonGroup.add(filterNoneRadioButton);
        filterButtonGroup.add(filterMoviesRadioButton);
        filterButtonGroup.add(filterTVShowsRadioButton);

        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println(e.getKeyChar());
            }
        });

    }

    public JPanel getNetflixPanel() {
        return netflixPanel;
    }
}
