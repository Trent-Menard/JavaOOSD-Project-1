package com.github.trentmenard;

public class Main {

    public static void main(String[] args) {
        // Test Collection object
        Collection showCollection = new Collection();
        showCollection.readFromFile("all-weeks-global.tsv");

        System.out.println("Listing all shows in collection:");
        System.out.println(showCollection);

        // Test default constructor
        WeeklyShow initiallyEmpty = new WeeklyShow();
        // Test overloaded constructor
        WeeklyShow newShowManualEntry = new WeeklyShow("2022-09-26","Films (English)",999,
                "New Show Manual Entry","N/A",12345,0);

        // Test manual entry
        initiallyEmpty.setWeek("2022-09-18");
        initiallyEmpty.setCategory("Films (English)");
        initiallyEmpty.setWeeklyRank(3);
        initiallyEmpty.setShowTitle("Love in the Villa");
        initiallyEmpty.setSeasonTitle("N/A");
        initiallyEmpty.setWeeklyHoursViewed(8870000);
        initiallyEmpty.setCumulativeWeeksInTop10(3);

        showCollection.add(initiallyEmpty);
        showCollection.add(newShowManualEntry);

        // TODO: 9/26/2022 Modify purge() to purge EACH occurrence by TITLE.
        // TODO: 9/26/2022 Implement writeToFile to retain changes.
        // Do not write directly to file. Change operations in list then copy at end.

        // Test random show suggestion
        System.out.println("\nRandom show recommendation: " + showCollection.getRandomSuggestion().getShowTitle());
        // Test predictive suggestion
        System.out.println("\nPredictive suggestion: " + showCollection.getPredictiveSuggestion().getShowTitle());
        // Test get shows by provided date
        System.out.println("\nShows by date: 2022-09-18");
        showCollection.getShows("2022-09-18")
                .forEach(s -> System.out.println(s.getShowTitle()));

        // Test show purge
        initiallyEmpty.setIsPurged(true);
        System.out.println("\n" + initiallyEmpty.getShowTitle() + " is purged: " + initiallyEmpty.getIsPurged());

        // Test show unpurge
        initiallyEmpty.setIsPurged(false);
        System.out.println(initiallyEmpty.getShowTitle() + " is purged: " + initiallyEmpty.getIsPurged());
    }
}