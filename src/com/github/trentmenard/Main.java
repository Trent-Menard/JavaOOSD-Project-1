package com.github.trentmenard;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Test Collection object - original data
        Collection showCollection = new Collection();
        showCollection.readFromFile("all-weeks-global.tsv");

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

        System.out.println("Listing all shows in collection:");
        System.out.println(showCollection);

        System.out.println("\nShows by date: 2022-09-18");
        showCollection.getShows("2022-09-18")
                .forEach(s -> System.out.println(s.getShowTitle()));

        showCollection.saveToFile("all-weeks-global-updated.txt");

        // Test other Collection object - contains updated data (didn't want to modify original)
        Collection showCollection2 = new Collection();
        showCollection2.readFromFile("all-weeks-global-updated.txt");

        // NOTE: These will likely be repeated in the output b/c the show can occur multiple times
        // throughout the file; i.e.: cumulative weeks. Ideally, TV shows & Movies would be separate
        // objects and duplicates removed - or merged - but I decided to go with ArrayList instead of Hash/Tree Set.

        // Test show unpurge
        showCollection2.setIsPurged("Ozark", false);
        showCollection2.getShowsByName("Ozark")
                .forEach(s -> System.out.println(s.getSeasonTitle() + " is purged: " + s.getIsPurged()));

        // Test show purge (placed after unpurge to show in txt file)
        showCollection2.setIsPurged("Ozark", true);
        showCollection2.getShowsByName("Ozark")
                .forEach(s -> System.out.println(s.getSeasonTitle() + " is purged: " + s.getIsPurged()));

        // Save changes to file.
        showCollection.saveToFile("all-weeks-global-updated.txt");

        // Output becomes cluttered with this but can uncomment to see. (I'd just open & compare files there)
//        System.out.println(showCollection2);

        // Test random show suggestion
        System.out.println("\nRandom show recommendation: " + showCollection2.getRandomSuggestion().getShowTitle());

        // Test Predictive Suggestion
        System.out.println("\nPredictive Suggestion: " + showCollection2.getPredictiveSuggestion(initiallyEmpty).getShowTitle() + "\n");

        // Test Predictive Suggestions from List
        System.out.println("Prediction Suggestion from List: ");
        List<WeeklyShow> recommendBasedOn = showCollection2.getShows("2022-04-17");
        showCollection2.getPredictiveSuggestions(recommendBasedOn).forEach(s -> System.out.println(s.getShowTitle()));
    }
}