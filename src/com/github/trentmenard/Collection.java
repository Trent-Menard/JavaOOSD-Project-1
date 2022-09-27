package com.github.trentmenard;

import java.io.*;
import java.util.*;

public class Collection {
    private final List<WeeklyShow> weeklyShows = new ArrayList<>();

    public void add(WeeklyShow show){
        this.weeklyShows.add(show);
    }

    public List<WeeklyShow> getShows(String date) {
        List<WeeklyShow> showsFromDate = new ArrayList<>();
        for (WeeklyShow show : weeklyShows) {
            if (show.getWeek().equals(date))
                showsFromDate.add(show);
        }
        return showsFromDate;
    }

    public WeeklyShow getRandomSuggestion() {
        int randVal;
        do {
            Random rand = new Random();
            randVal = rand.nextInt(this.weeklyShows.size());
        } while (this.weeklyShows.get(randVal).getIsPurged());

        return this.weeklyShows.get(randVal);
    }

    // Prediction based on highest weekly hours viewed
    public WeeklyShow getPredictiveSuggestion() {
        // It's possible the first index is purged so handle.
        int idx = 0;
        WeeklyShow predictiveSuggestion;
        do {
            predictiveSuggestion = this.weeklyShows.get(idx);
            idx++;
        } while (predictiveSuggestion.getIsPurged());

        for (WeeklyShow show : this.weeklyShows)
            if (show.getWeeklyHoursViewed() > predictiveSuggestion.getWeeklyHoursViewed() && !(show.getIsPurged()))
                predictiveSuggestion = show;
        return predictiveSuggestion;
    }

    public void readFromFile(String file){

        // Try-with-resources() auto-closes used resources.
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // First line is column headers so we can discard.
            String line = reader.readLine();
            // Columns within the file structure are separated with tabs (\t)
            // Each object will have the following values, respectively
            // Although technically redundant to create temporary individual variables,
            // (could directly val[idx] w/ casting), it's semantically clearer to do it this way :).
            while(line != null){
                line = reader.readLine();
                String[] vals = line.split("\t");
                String week = vals[0];
                String category = vals[1];
                int weeklyRank = Integer.parseInt(vals[2]);
                String showTitle = vals[3];
                String seasonTitle = vals[4];
                int weeklyHoursViewed = Integer.parseInt(vals[5]);
                int cumulativeWeeksInTop10 = Integer.parseInt(vals[6]);

                this.weeklyShows.add(new WeeklyShow(week, category, weeklyRank, showTitle, seasonTitle, weeklyHoursViewed, cumulativeWeeksInTop10));

                // All data has been read. Throws exception otherwise b/c tries to read "null".
                if (reader.read() == -1)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // If serialization were a concern, a better way to do this would be to call
    // the .toString() method, but I wish to maintain its current format.
    public void saveToFile(String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
            StringBuilder sb = new StringBuilder();

            // Write column headers
            writer.write("week\tcategory\tweekly_rank\tshow_title\tseason_title\tweekly_hours_viewed\tis_purged\n");

            for (WeeklyShow s : this.weeklyShows){
                sb.append(s.getWeek()).append("\t")
                        .append(s.getCategory()).append("\t")
                        .append(s.getWeeklyRank()).append("\t")
                        .append(s.getShowTitle()).append("\t")
                        .append(s.getSeasonTitle()).append("\t")
                        .append(s.getWeeklyHoursViewed()).append("\t")
                        .append(s.getCumulativeWeeksInTop10()).append("\t")
                        .append(s.getIsPurged()).append("\n");
                writer.write(sb.toString());
                sb = new StringBuilder();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Collection{" +
                "weeklyShows=" + weeklyShows +
                '}';
    }
}