package com.github.trentmenard;

public class Movie<M> extends WeeklyShow<M>{
    private String language;

    public Movie(String week, String category, String language, int weeklyRank, String showTitle, int weeklyHoursViewed, int cumulativeWeeksInTop10) {
        super(week, category, weeklyRank, showTitle, weeklyHoursViewed, cumulativeWeeksInTop10);
        this.language = language;
    }
}
