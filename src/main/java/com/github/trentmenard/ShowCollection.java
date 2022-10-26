package com.github.trentmenard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void readFromFile() {

        byte[] read;

        try {
            read = Main.class.getClassLoader().getResource("all-weeks-global.tsv").openStream().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String bytesToString = new String(read);

        bytesToString.lines()
                .skip(1)
                .map(s -> s.split("\t"))
                .forEach(this::add);
    }

    private void add(String[] show){
        String wk = show[0];
        String cat = show[1];
        int rnk = Integer.parseInt(show[2]);
        String showTTL = show[3];
        String seasonTTL = show[4];
        int hrsVwd = Integer.parseInt(show[5]);
        int top10 = Integer.parseInt(show[6]);
        String lang = getLanguage(cat);

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
        Pattern pat = Pattern.compile("\\(([^()]+)\\)");
        Matcher mat = pat.matcher(show);
        boolean found = mat.find();
        if (found)
            // Remove () from capture group.
            return mat.group().replaceAll("[\\(\\)]", "");
        else
            return "Unknown";
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

    @Override
    public String toString() {
        return "ShowCollection{" +
                "allShows=" + allShows +
                ", movies=" + movies +
                ", tvShows=" + tvShows +
                ", rand=" + rand +
                '}';
    }
}