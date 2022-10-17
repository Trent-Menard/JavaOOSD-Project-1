package com.github.trentmenard;

public class Main {

    public static void main(String[] args) {
        ShowCollection showCollection = new ShowCollection();
        showCollection.readFromFile("all-weeks-global.tsv");

        // NetflixPanel extends NetflixFrame which returns the JFrame.
        // Prevent's direct access to JFrame/Panel. I.e.: must use netflixPanel.getJFrame/Panel
        NetflixPanel netflixPanel = new NetflixPanel(showCollection);

        // TODO: 10/10/2022 MAYBE: Replace ArrayList w/ Hash/TreeSet to prevent duplicates.
    }
}