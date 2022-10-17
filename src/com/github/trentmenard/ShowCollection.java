package com.github.trentmenard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowCollection {
    // Parent List containing TV & Movie objects.
    private List<WeeklyShow> allShows;
    private List<Movie> movies;
    private List<TVShow> tvShows;
    private Random rand;
    public ShowCollection() {
        this.allShows = new ArrayList<>();
        this.movies = new ArrayList<>();
        this.tvShows = new ArrayList<>();
        this.rand = new Random();
    }

    public void add(Movie movie){
        this.movies.add(movie);
        this.allShows.add(movie);
    }

    public void add(TVShow tvShow){
        this.tvShows.add(tvShow);
        this.allShows.add(tvShow);
    }
    public void readFromFile(String file) {
        // Try-with-resources() auto-closes used resources.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read and map lines using a Stream & split field variables (defined in WeeklyShow) by tabs (\t)
            // The first line is column headers, so we can discard (skip). Then, determine its type (TV Show / Movie) &
            // and add it to showCollection.
            reader.lines()
                    .skip(1)
                    .map(s -> s.split("\t"))
                    .forEach(this::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void add(String[] show){
        String wk = show[0];
        String cat = show[1];
        int rnk = Integer.parseInt(show[2]);
        String showTTL = show[3];
        String seasonTTL = show[4];
        int hrsVwd = Integer.parseInt(show[5]);
        int top10 = Integer.parseInt(show[6]);
        String lang = getLanguage(show[3]);

//      If an entry's 'seasonTitle' is 'N/A' then it's a Movie, not TV Show.
        if (seasonTTL.equals("N/A")){
            Movie movie = new Movie(wk, cat, rnk, showTTL, lang, hrsVwd, top10);
            this.movies.add(movie);
            this.allShows.add(movie);
        } else {
            TVShow tvShow = new TVShow(wk, cat, rnk, showTTL, seasonTTL, lang, hrsVwd, top10);
            this.tvShows.add(tvShow);
            this.allShows.add(tvShow);
        }
    }
    private String getLanguage(String show) {
        // Regex matches between parenthesis in 'category' (English or Non-English)
        return show.lines()
                .filter(s -> s.matches("\\(([^()]+)\\)"))
                .limit(1).toString();
    }

    public List<WeeklyShow> getAllShows() {
        return allShows;
    }
    public List<Movie> getMovies() {
        return movies;
    }

    public List<TVShow> getTvShows() {
        return tvShows;
    }

    public WeeklyShow getRandomSuggestion(){
        return this.allShows.get(rand.nextInt(this.allShows.size()));
    }

    // Prediction based on highest weekly hours viewed
    public WeeklyShow getPredictiveSuggestion(WeeklyShow basedOn){
        rand = new Random();

        // Remove unpurged shows & shows with the same name
        List<WeeklyShow> res = this.allShows.stream()
                .filter(s -> !s.isPurged() && !s.getShowTitle().equalsIgnoreCase(basedOn.getShowTitle()))
                .toList();

        // From previous, list of shows having a higher weeklyHoursViewed than originally provided
        List<WeeklyShow> opt = res.stream()
                .filter(s -> s.getWeeklyHoursViewed() >= basedOn.getWeeklyHoursViewed()).toList();

        // If shows pass second test, recommend from there
        // Otherwise, only recommend shows meeting criteria 1
        return opt.size() >= 1 ? opt.get(rand.nextInt(opt.size())) : res.get(rand.nextInt(res.size()));
    }

    public List<WeeklyShow> getPredictiveSuggestions(List<WeeklyShow> basedOn) {
        return basedOn.stream()
                .map(this::getPredictiveSuggestion).toList();
    }

    public List<WeeklyShow> getShows(String nameOrDate) {
        return this.allShows.stream()
                .filter(s -> s.getShowTitle().equalsIgnoreCase(nameOrDate))
                .toList();
    }
}