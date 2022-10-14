package com.github.trentmenard;

public class TVShow<S> extends WeeklyShow<S> {
    private String seasonTitle;
    private String language;
    public TVShow(String week, String category, String language, int weeklyRank, String showTitle, String seasonTitle, int weeklyHoursViewed, int cumulativeWeeksInTop10) {
        super(week, category, weeklyRank, showTitle, seasonTitle, weeklyHoursViewed, cumulativeWeeksInTop10);
        this.seasonTitle = seasonTitle;
        this.language = language;
    }
}
