package com.github.trentmenard;

public abstract class WeeklyShow<T> {
    private String week;
    private String category;
    private int weeklyRank;
    private String showTitle;
    private String seasonTitle;
    private int weeklyHoursViewed;
    private int cumulativeWeeksInTop10;
    private boolean isPurged;
    public String getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    public WeeklyShow(){
        this.week = "None (empty show)";
        this.category = "None (empty show)";
        this.weeklyRank = -1;
        this.showTitle = "None (empty show)";
        this.weeklyHoursViewed = -1;
        this.cumulativeWeeksInTop10 = -1;
    }
    protected WeeklyShow(String week, String category, int weeklyRank, String showTitle,
                      int weeklyHoursViewed, int cumulativeWeeksInTop10) {

        this.week = week;
        this.category = category;
        this.weeklyRank = weeklyRank;
        this.showTitle = showTitle;
        this.weeklyHoursViewed = weeklyHoursViewed;
        this.cumulativeWeeksInTop10 = cumulativeWeeksInTop10;
    }

    public WeeklyShow(String week, String category, int weeklyRank, String showTitle, String seasonTitle,
                         int weeklyHoursViewed, int cumulativeWeeksInTop10) {

        this(week, category, weeklyRank, showTitle, weeklyHoursViewed, cumulativeWeeksInTop10);
        this.seasonTitle = seasonTitle;
    }

    public String getWeek() { return week; }
    public String getCategory() { return category; }
    public int getWeeklyRank() { return weeklyRank; }
    public String getShowTitle() { return showTitle; }
    public int getWeeklyHoursViewed() { return weeklyHoursViewed; }
    public int getCumulativeWeeksInTop10() { return cumulativeWeeksInTop10; }
    public boolean getIsPurged() { return isPurged; }

    public void setWeek(String week) { this.week = week; }
    public void setCategory(String category) { this.category = category; }
    public void setWeeklyRank(int weeklyRank) { this.weeklyRank = weeklyRank; }
    public void setShowTitle(String showTitle) { this.showTitle = showTitle; }
    public void setWeeklyHoursViewed(int weeklyHoursViewed) { this.weeklyHoursViewed = weeklyHoursViewed; }
    public void setCumulativeWeeksInTop10(int cumulativeWeeksInTop10) { this.cumulativeWeeksInTop10 = cumulativeWeeksInTop10 ;}
    public void setIsPurged(boolean isPurged) { this.isPurged = isPurged; }
}