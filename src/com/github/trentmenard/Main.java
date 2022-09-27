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
        initiallyEmpty.setWeek("2022-09-26");
        initiallyEmpty.setCategory("TV (English)");
        initiallyEmpty.setWeeklyRank(0);
        initiallyEmpty.setShowTitle("SpongeBob Square Pants");
        initiallyEmpty.setSeasonTitle("N/A");
        initiallyEmpty.setWeeklyHoursViewed(1234);
        initiallyEmpty.setCumulativeWeeksInTop10(0);

        showCollection.add(initiallyEmpty);
        showCollection.add(newShowManualEntry);

        // TODO: 9/26/2022 Modify purge() to purge EACH occurrence by TITLE.
        // TODO: 9/26/2022 Fix reader off by first digit.

        // Test random show suggestion
        System.out.println("\nRandom show recommendation: " + showCollection.getRandomSuggestion().getShowTitle());
        // Test predictive suggestion
        System.out.println("\nPredictive suggestion: " + showCollection.getPredictiveSuggestion().getShowTitle());
        // Test get shows by provided date
        System.out.println("\nShows by date: 2022-09-18");
        showCollection.getShows("2022-09-18")
                .forEach(s -> System.out.println(s.getShowTitle()));

        // Test show unpurge
        initiallyEmpty.setIsPurged(false);
        System.out.println(initiallyEmpty.getShowTitle() + " is purged: " + initiallyEmpty.getIsPurged());

        // Test show purge (placed after unpurge to shows in txt file)
        initiallyEmpty.setIsPurged(true);
        System.out.println("\n" + initiallyEmpty.getShowTitle() + " is purged: " + initiallyEmpty.getIsPurged());

        showCollection.saveToFile("test.txt");
    }
}