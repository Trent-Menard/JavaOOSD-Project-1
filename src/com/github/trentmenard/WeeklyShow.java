package com.github.trentmenard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeeklyShow {
    private String week;
    private String category;
    private int weeklyRank;
    private String showTitle;
    private String seasonTitle;
    private double weeklyHoursViewed;
    private int cumulativeWeeksInTop10;
    private final List<WeeklyShow> allShows = new ArrayList<>();

    public WeeklyShow(String week, String category, int weeklyRank, String showTitle,
                      String seasonTitle, double weeklyHoursViewed, int cumulativeWeeksInTop10) {
        this.week = week;
        this.category = category;
        this.weeklyRank = weeklyRank;
        this.showTitle = showTitle;
        this.seasonTitle = seasonTitle;
        this.weeklyHoursViewed = weeklyHoursViewed;
        this.cumulativeWeeksInTop10 = cumulativeWeeksInTop10;
        this.allShows.add(this);
    }

    public String getWeek() { return week; }
    public String getCategory() { return category; }
    public int getWeeklyRank() { return weeklyRank; }
    public String getShowTitle() { return showTitle; }
    public String getSeasonTitle() { return seasonTitle; }
    public double getWeeklyHoursViewed() { return weeklyHoursViewed; }
    public int getCumulativeWeeksInTop10() { return cumulativeWeeksInTop10; }

    public void setWeek(String week) { this.week = week; }
    public void setCategory(String category) { this.category = category; }
    public void setWeeklyRank(int weeklyRank) { this.weeklyRank = weeklyRank; }
    public void setShowTitle(String showTitle) { this.showTitle = showTitle; }
    public void setSeasonTitle(String seasonTitle) { this.seasonTitle = seasonTitle; }
    public void setWeeklyHoursViewed(double weeklyHoursViewed) { this.weeklyHoursViewed = weeklyHoursViewed; }
    public void setCumulativeWeeksInTop10(int cumulativeWeeksInTop10) { this.cumulativeWeeksInTop10 = cumulativeWeeksInTop10 ;}

    public List<WeeklyShow> getShows(String date) {
        List<WeeklyShow> shows = new ArrayList<>();
        for (WeeklyShow show : this.allShows)
            if (show.getWeek().equals(date))
                shows.add(show);
        return shows;
    }

    public WeeklyShow getRandomSuggestion() {
        Random rand = new Random();
        int randVal = rand.nextInt(this.allShows.size());
        return this.allShows.get(randVal);
    }

    @Override
    public String toString() {
        return "WeeklyShow{" +
                "week='" + week + '\'' +
                ", category='" + category + '\'' +
                ", weeklyRank=" + weeklyRank +
                ", showTitle='" + showTitle + '\'' +
                ", seasonTitle='" + seasonTitle + '\'' +
                ", weeklyHoursViewed=" + weeklyHoursViewed +
                ", cumulativeWeeksInTop10=" + cumulativeWeeksInTop10 +
                '}';
    }
}