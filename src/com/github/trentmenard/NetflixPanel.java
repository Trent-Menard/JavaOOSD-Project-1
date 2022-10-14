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

    public NetflixPanel() {
        super();
        super.getMainFrame().setContentPane(netflixPanel);
        super.getMainFrame().setBackground(new Color(224, 225, 221));

        ShowCollection<?> test = new ShowCollection<>();
        test.readFromFile("all-weeks-global.tsv");

        test.showCollection.forEach(s -> System.out.println(s.getShowTitle() + " : " + s.getClass().getSimpleName()));

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
