package com.github.trentmenard;
// Trent Menard
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        ShowCollection showCollection = new ShowCollection();
        showCollection.readFromFile("all-weeks-global.tsv");

        JFrame netflixFrame = new JFrame("Netflix");
        netflixFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        NetflixPanel netflixPanel = new NetflixPanel(showCollection);
        netflixFrame.setContentPane(netflixPanel.getNetflixPanel());

        netflixFrame.setVisible(true);
        netflixFrame.pack();

        // TODO: 10/10/2022 MAYBE: Replace ArrayList w/ Hash/TreeSet to prevent duplicates.
    }
}