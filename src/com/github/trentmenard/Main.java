package com.github.trentmenard;

public class Main {

    public static void main(String[] args) {

        // Add WeeklyShow Test:
//        Shows allData = new Shows("./project1/netflixTopTenProcessed.txt");
        WeeklyShow weeklyShow1 = new WeeklyShow("2022-09-11","cat",1,
                "show title","season title",2500,1);

        weeklyShow1.setShowTitle("The 100");
        weeklyShow1.setCategory("Sci-Fi");

        System.out.println(weeklyShow1);
        System.out.println(weeklyShow1.getShows("2022-09-11"));
        System.out.println(weeklyShow1.getRandomSuggestion());
//        allData.add (newSIW);
//        Shows oneWeek = allData.getWeek ("2022-09-04");
//        System.out.println(oneWeek);
//        // etc. etc.
//        allData.writeToFile();
    }
}