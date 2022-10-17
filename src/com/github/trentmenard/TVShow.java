package com.github.trentmenard;

public class TVShow extends WeeklyShow {
    private String seasonTitle;
    public TVShow(String week, String category, int weeklyRank, String showTitle, String seasonTitle, String language, int weeklyHoursViewed, int cumulativeWeeksInTop10) {
        super(week, category, weeklyRank, showTitle, language, weeklyHoursViewed, cumulativeWeeksInTop10);
        this.seasonTitle = seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) { this.seasonTitle = seasonTitle; }
    public String getSeasonTitle() { return seasonTitle; }
}
