package com.github.trentmenard;

public abstract class WeeklyShow {
    private String week;
    private String category;
    private int weeklyRank;
    private String showTitle;
    private String language;
    private int weeklyHoursViewed;
    private int cumulativeWeeksInTop10;
    private boolean isPurged;
    public WeeklyShow(String week, String category, int weeklyRank, String showTitle, String language,
                      int weeklyHoursViewed, int cumulativeWeeksInTop10) {
        this.week = week;
        this.category = category;
        this.weeklyRank = weeklyRank;
        this.language = language;
        this.showTitle = showTitle;
        this.weeklyHoursViewed = weeklyHoursViewed;
        this.cumulativeWeeksInTop10 = cumulativeWeeksInTop10;
    }

    public String getWeek() { return week; }
    public String getCategory() { return category; }
    public int getWeeklyRank() { return weeklyRank; }
    public String getShowTitle() { return showTitle; }
    public String getLanguage() { return language; }
    public int getWeeklyHoursViewed() { return weeklyHoursViewed; }
    public int getCumulativeWeeksInTop10() { return cumulativeWeeksInTop10; }
    public boolean isPurged() { return isPurged; }

    public void setWeek(String week) { this.week = week; }
    public void setCategory(String category) { this.category = category; }
    public void setWeeklyRank(int weeklyRank) { this.weeklyRank = weeklyRank; }
    public void setShowTitle(String showTitle) { this.showTitle = showTitle; }
    public void setLanguage(String language) { this.language = language; }
    public void setWeeklyHoursViewed(int weeklyHoursViewed) { this.weeklyHoursViewed = weeklyHoursViewed; }
    public void setCumulativeWeeksInTop10(int cumulativeWeeksInTop10) { this.cumulativeWeeksInTop10 = cumulativeWeeksInTop10 ;}
    public void setPurged(boolean purged) { isPurged = purged; }
}