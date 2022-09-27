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
            // Read and map lines using a Stream & split field variables (defined in WeeklyShow) by tabs (\t)
            // The first line is column headers, so we can discard (skip). Then, create a new WeeklyShow for
            // each and add it to weeklyShows ArrayList.
            reader.lines()
                    .skip(1)
                    .map(s -> s.split("\t"))
                    .toList()
                    .forEach(s -> this.weeklyShows.add(new WeeklyShow(s[0], s[1], Integer.parseInt(s[2]), s[3], s[4], Integer.parseInt(s[5]), Integer.parseInt(s[6]))));
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