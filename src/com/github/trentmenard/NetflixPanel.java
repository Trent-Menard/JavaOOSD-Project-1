package com.github.trentmenard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
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
    private JPasswordField passwordField1;
    private JProgressBar progressBar1;
    private JComboBox<WeeklyShow> comboBox2;
    private JButton doneButton;
    private ButtonGroup filterButtonGroup = new ButtonGroup();
    private ShowCollection showCollection;
    private String query = "";
    private Timer timer;
    public NetflixPanel(ShowCollection showCollection) {
        this.showCollection = showCollection;
        comboBox2.setVisible(false);
        doneButton.setVisible(false);

        // Needed more room for the GIF so just created a temp JFrame ;).
        ImageIcon icon = new ImageIcon(this.getClass().getResource("Patrick.GIF"));
        JFrame frm = new JFrame("I ran out of room, lol.");
        JLabel jlbl = new JLabel();
        jlbl.setIcon(icon);
        frm.setContentPane(jlbl);
        frm.pack();
        frm.setVisible(true);

        timer = new Timer(0, e -> {
            progressBar1.setValue(progressBar1.getValue() + 10);
            if (progressBar1.getValue() == 100)
                timer.stop();
        });

        progressBar1.setToolTipText("Loading...");

        timer.start();
        timer.setDelay(1000);

        passwordField1.setToolTipText("Enter password:");

        netflixPanel.setBackground(new Color(224, 225, 221));

        showCollection.getAllShows().forEach(e -> comboBox1.addItem(e));

        filterButtonGroup.add(filterNoneRadioButton);
        filterButtonGroup.add(filterMoviesRadioButton);
        filterButtonGroup.add(filterTVShowsRadioButton);
        // By default, show Movies & TV Shows combined.
        filterButtonGroup.setSelected(filterNoneRadioButton.getModel(), true);
        comboBox1.setMaximumRowCount(25);

        this.registerListeners();

        doneButton.addActionListener(e -> {
            comboBox1.setEditable(false);

            Object selected = comboBox1.getSelectedItem();

            if (selected instanceof TVShow res){
                comboBox1.addItem(new TVShow(res.getWeek(), res.getCategory(), res.getWeeklyRank(), res.getShowTitle(), res.getSeasonTitle(), res.getLanguage(), res.getWeeklyHoursViewed(), res.getCumulativeWeeksInTop10()));
            }

            System.out.println(comboBox1.getSelectedItem());
        });
    }
    public JPanel getNetflixPanel() {
        return netflixPanel;
    }
    private WeeklyShow getUserInput(boolean isTVShow){
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

        String seasonTitle = "";
        if (isTVShow){
            // User Input: Season Title
            seasonTitle = JOptionPane.showInputDialog("Season Title:");
        }

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

        if (isTVShow)
            return new TVShow(week, category, weeklyRank, showTitle, seasonTitle, language, weeklyHoursViewed, cumulativeWeeksInTop10);
        else
            return new Movie(week, category, weeklyRank, showTitle, language, weeklyHoursViewed, cumulativeWeeksInTop10);
    }
    private void registerListeners(){
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
                    if (query.length() > 0)
                        query = query.substring(0, query.length() - 1);
                    else query = "";
                else
                    query += e.getKeyChar();

                comboBox1.removeAllItems();

                if (filterButtonGroup.isSelected(filterNoneRadioButton.getModel())){
                    List<WeeklyShow> shows = showCollection.getAllShows().stream()
                            .filter(s -> s.getShowTitle()
                                    .toLowerCase()
                                    .startsWith(query.toLowerCase()))
                            .toList();

                    shows.forEach(t -> comboBox1.addItem(t));

                } else if (filterButtonGroup.isSelected(filterMoviesRadioButton.getModel())){
                    List<Movie> movies = showCollection.getMovies().stream()
                            .filter(s -> s.getShowTitle()
                                    .toLowerCase()
                                    .startsWith(query.toLowerCase()))
                            .toList();

                    movies.forEach(t -> comboBox1.addItem(t));

                } else if (filterButtonGroup.isSelected(filterTVShowsRadioButton.getModel())){
                    List<TVShow> tvShows = showCollection.getTvShows().stream()
                            .filter(s -> s.getShowTitle()
                                    .toLowerCase()
                                    .startsWith(query.toLowerCase()))
                            .toList();

                    tvShows.forEach(t -> comboBox1.addItem(t));
                }
            }
        });

        addTVShowButton.addActionListener(e -> {
            TVShow res = (TVShow) getUserInput(true);
            showCollection.add(res);
            comboBox1.addItem(res);
        });

        addMovieButton.addActionListener(e -> {
            Movie res = (Movie) getUserInput(false);
            showCollection.add(res);
            comboBox1.addItem(res);
        });

        comboBox1.addActionListener(e -> {
            Object selected = comboBox1.getSelectedItem();

            if (selected instanceof TVShow res){
                updateTVShow(res);
                comboBox1.removeItem(res);
            }
            else if (selected instanceof Movie res){
                updateMovie(res);
            }
            // TODO: 10/17/2022 Menu to edit object.
        });

        filterNoneRadioButton.addActionListener(e -> {
            comboBox1.removeAllItems();
            showCollection.getAllShows().forEach(s -> comboBox1.addItem(s));
        });

        filterMoviesRadioButton.addActionListener(e -> {
            comboBox1.removeAllItems();
            showCollection.getMovies().forEach(s -> comboBox1.addItem(s));
        });

        filterTVShowsRadioButton.addActionListener(e -> {
            comboBox1.removeAllItems();
            showCollection.getTvShows().forEach(s -> comboBox1.addItem(s));
        });

    }

    private TVShow updateTVShow(TVShow tvShow){
        comboBox2.addItem(tvShow);
        comboBox2.setEditable(true);
        return null;
    }

    private Movie updateMovie(Movie movie){
        comboBox1.setEditable(true);
        doneButton.setVisible(true);
        return null;
    }
}