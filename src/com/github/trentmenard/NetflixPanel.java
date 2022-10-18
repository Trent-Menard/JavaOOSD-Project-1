package com.github.trentmenard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetflixPanel {
    private JPanel netflixPanel;
    private JButton addTVShowButton;
    private JTextField searchTextField;
    private JComboBox<WeeklyShow> comboBox1;
    private JRadioButton filterNoneRadioButton;
    private JRadioButton filterMoviesRadioButton;
    private JRadioButton filterTVShowsRadioButton;
    private JButton addMovieButton;
    private ButtonGroup filterButtonGroup = new ButtonGroup();
    public NetflixPanel(ShowCollection showCollection) {
        netflixPanel.setBackground(new Color(224, 225, 221));

        showCollection.getAllShows().forEach(e -> comboBox1.addItem(e));

        filterButtonGroup.add(filterNoneRadioButton);
        filterButtonGroup.add(filterMoviesRadioButton);
        filterButtonGroup.add(filterTVShowsRadioButton);

        // By default, show Movies & TV Shows combined.
        filterButtonGroup.setSelected(filterNoneRadioButton.getModel(), true);

        // Not the best way of doing it but seems to work for now...
        StringBuilder query = new StringBuilder();
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                query.append(e.getKeyChar());
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    query.deleteCharAt(query.length() - 1);
            }
        });

        addTVShowButton.addActionListener(e -> {
            TVShow res = getNewTVShowUserInput();
            showCollection.add(res);
            comboBox1.addItem(res);
        });

        comboBox1.addActionListener(e -> {
            Object selected = comboBox1.getSelectedItem();
                TVShow res;
                if (selected instanceof TVShow)
                    res = (TVShow) selected;
            // TODO: 10/17/2022 Menu to edit object.
        });
    }
    public JPanel getNetflixPanel() {
        return netflixPanel;
    }

    private TVShow getNewTVShowUserInput(){
        // User Input: Week
        // TODO: 10/17/2022 Regex invalid date format check?
        String week = JOptionPane.showInputDialog("Week (YYYY-DD-MM):");
//        JOptionPane.showMessageDialog(null, "Format as YYYY-DD-MM\nExample: 2022-09-18", "Invalid Date Format", JOptionPane.WARNING_MESSAGE);

        // User Input: Category
        String category = "TBD";
        try{
            String[] cats = {"Films (English)", "Films (Non-English)", "TV (English)", "TV (Non-English)"};
            category = JOptionPane.showInputDialog(null, "Select a category:", "Category", JOptionPane.QUESTION_MESSAGE, null, cats, null).toString();
        }catch (NullPointerException ignored){
            // If the user presses "Cancel"
            System.exit(0);
        }

        // User Input: Weekly Rank
        int weeklyRank = -1;
        try{
            String[] cats = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",};
            weeklyRank = Integer.parseInt(JOptionPane.showInputDialog(null, "Select Weekly Rank:", "Weekly Rank", JOptionPane.QUESTION_MESSAGE, null, cats, null).toString());
        }catch (NullPointerException ignored){
            // If the user presses "Cancel"
            System.exit(0);
        }

        // User Input: Show Title
        String showTitle = JOptionPane.showInputDialog("Show Title:");

        // User Input: Season Title
        String seasonTitle = JOptionPane.showInputDialog("Season Title:");

        // Extraction: Language
        String language = "TBD";
        // Regex matches between parenthesis in 'category' (English or Non-English)
        Pattern pat = Pattern.compile("\\(([^()]+)\\)");
        Matcher mat = pat.matcher(category);
        boolean found = mat.find();
        if (found)
            // Remove () from capture group.
            language = mat.group().replaceAll("[\\(\\)]", "");
        else
            language = "Unknown";

        // User Input: Weekly Hours Viewed
        int weeklyHoursViewed = -1;
        boolean valid;
        do {
            try {
                weeklyHoursViewed = Integer.parseInt(JOptionPane.showInputDialog("Enter Weekly Hours Viewed:"));
                valid = true;
            }catch (NumberFormatException ignored){
                JOptionPane.showMessageDialog(null, "Format as an integer.\nExample: 12345", "Invalid Format", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        } while (!(valid));

        // User Input: Cumulative Weeks In Top 10
        int cumulativeWeeksInTop10 = -1;
        do {
            try {
                cumulativeWeeksInTop10 = Integer.parseInt(JOptionPane.showInputDialog("Cumulative Weeks In top 10:"));
                valid = true;
            }catch (NumberFormatException ignored){
                JOptionPane.showMessageDialog(null, "Format as an integer.\nExample: 12345", "Invalid Format", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        } while (!(valid));

        // User Input: Is purged?
        boolean isPurged = false;
        int res = JOptionPane.showConfirmDialog(null, "Is this show purged?", "Purged?", JOptionPane.YES_NO_OPTION);
        // X = -1 | NO = 1 | YES = 0
        // If the user presses "Cancel" or closes window.
        if (res == -1)
            System.exit(0);
        else
            isPurged = res == 0;
        return new TVShow(week, category, weeklyRank, showTitle, seasonTitle, language, weeklyHoursViewed, cumulativeWeeksInTop10);
    }
}