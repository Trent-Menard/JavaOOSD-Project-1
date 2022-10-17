package com.github.trentmenard;

public class Movie extends WeeklyShow {
    public Movie(String week, String category, int weeklyRank, String showTitle, String language, int weeklyHoursViewed, int cumulativeWeeksInTop10) {
        super(week, category, weeklyRank, showTitle, language, weeklyHoursViewed, cumulativeWeeksInTop10);
    }
}
