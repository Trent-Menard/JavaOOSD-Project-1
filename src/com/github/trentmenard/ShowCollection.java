package com.github.trentmenard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowCollection<T extends WeeklyShow<T>> extends WeeklyShow<T> {
    // Parent List containing TV & Movie objects.
    public final List<WeeklyShow<?>> showCollection = new ArrayList<>();
    private List<TVShow<T>> tvShows = new ArrayList<>();
    private List<Movie<T>> movies = new ArrayList<>();

    public void readFromFile(String file) {
        // Try-with-resources() auto-closes used resources.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read and map lines using a Stream & split field variables (defined in WeeklyShow) by tabs (\t)
            // The first line is column headers, so we can discard (skip). Then, determine its type (TV Show / Movie) &
            // and add it to showCollection.
            reader.lines()
                    .skip(1)
                    .map(s -> s.split("\t"))
                    .toList().forEach(s -> this.convertToTypeAndAdd(s[0], s[1], Integer.parseInt(s[2]), s[3], s[4], Integer.parseInt(s[5]), Integer.parseInt(s[6])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void convertToTypeAndAdd(String wk, String cat, int rnk, String showTTL, String seasonTTL, int hrsVwd, int top10) {

        String lang = getLanguage(showTTL);

        // If an entry's 'seasonTitle' is 'N/A' then it's a Movie, not TV Show.
        if (seasonTTL.equals("N/A"))
            this.showCollection.add(new Movie<T>(wk, cat, lang, rnk, showTTL, hrsVwd, top10));
        else
            this.showCollection.add(new TVShow<T>(wk, cat, lang, rnk, showTTL, seasonTTL, hrsVwd, top10));
    }

    private String getLanguage(String show) {
        // Regex matches between parenthesis in 'category' (English or Non-English)
        return show.lines()
                .filter(s -> s.matches("\\(([^()]+)\\)"))
                .limit(1).toString();
    }
}