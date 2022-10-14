package com.github.trentmenard;

public class Collection<T extends WeeklyShow> {
//    private final List<WeeklyShow> weeklyShows = new ArrayList<>();
//    private Random rand = new Random();
////
////    private final Set<T> weeklyShows3 = new HashSet<>();
////    private final Set<Movie> movies = new HashSet<>();
////    private final Set<TVShow> tvShows = new HashSet<>();
//
//    public void add(WeeklyShow show){
//        this.weeklyShows.add(show);
//    }
//
//    public void setIsPurged(String name, boolean value) {
//        getShowsByName(name).forEach(s -> s.setIsPurged(value));
//    }
//
//    public List<WeeklyShow> getShows(String date) {
//        return this.weeklyShows.stream().
//                filter(s -> s.getWeek().equalsIgnoreCase(date))
//                .toList();
//    }
//    public List<WeeklyShow> getShowsByName(String name) {
//        return this.weeklyShows.stream()
//                .filter(s -> s.getShowTitle().equalsIgnoreCase(name)).distinct()
//                .toList();
//    }
//
//    public WeeklyShow getRandomSuggestion(){
//        return this.weeklyShows.get(rand.nextInt(this.weeklyShows.size()));
//    }
//
//    // Prediction based on highest weekly hours viewed
//    public WeeklyShow getPredictiveSuggestion(WeeklyShow basedOn){
//        rand = new Random();
//
//        // Remove unpurged shows & shows with the same name
//        List<WeeklyShow> res = this.weeklyShows.stream()
//                .filter(s -> !s.getIsPurged() && !s.getShowTitle().equalsIgnoreCase(basedOn.getShowTitle()))
//                .toList();
//
//        // From previous, list of shows having a higher weeklyHoursViewed than originally provided
//        List<WeeklyShow> opt = res.stream()
//                .filter(s -> s.getWeeklyHoursViewed() >= basedOn.getWeeklyHoursViewed()).toList();
//
//        // If shows pass second test, recommend from there
//        // Otherwise, only recommend shows meeting criteria 1
//        return opt.size() >= 1 ? opt.get(rand.nextInt(opt.size())) : res.get(rand.nextInt(res.size()));
//    }
//
//    public List<WeeklyShow> getPredictiveSuggestions(List<WeeklyShow> basedOn) {
//        return basedOn.stream()
//                .map(this::getPredictiveSuggestion).toList();
//    }
//    public void readFromFile(String file) {
//        // Try-with-resources() auto-closes used resources.
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            // Read and map lines using a Stream & split field variables (defined in WeeklyShow) by tabs (\t)
//            // The first line is column headers, so we can discard (skip). Then, create a new WeeklyShow for
//            // each and add it to weeklyShows ArrayList.
//            reader.lines()
//                    .skip(1)
//                    .map(s -> s.split("\t"))
//                    .toList()
//                    .forEach(s -> this.weeklyShows.add(new WeeklyShow(s[0], s[1], Integer.parseInt(s[2]), s[3], s[4], Integer.parseInt(s[5]), Integer.parseInt(s[6]))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // If serialization were a concern, a better way to do this would be to call
//    // the .toString() method, but I wish to maintain its current format.
//    public void saveToFile(String name) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
//            StringBuilder sb = new StringBuilder();
//
//            // Write column headers
//            writer.write("week\tcategory\tweekly_rank\tshow_title\tseason_title\tweekly_hours_viewed\tis_purged\n");
//            // Write each object's values
//            for (WeeklyShow s : this.weeklyShows){
//                sb.append(s.getWeek()).append("\t")
//                        .append(s.getCategory()).append("\t")
//                        .append(s.getWeeklyRank()).append("\t")
//                        .append(s.getShowTitle()).append("\t")
////                        .append(s.getSeasonTitle()).append("\t")
//                        .append(s.getWeeklyHoursViewed()).append("\t")
//                        .append(s.getCumulativeWeeksInTop10()).append("\t")
//                        .append(s.getIsPurged()).append("\n");
//                writer.write(sb.toString());
//                sb = new StringBuilder();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Collection{" +
//                "weeklyShows=" + weeklyShows +
//                '}';
//    }
//
//    public List<WeeklyShow> getWeeklyShows() {
//        return Collections.unmodifiableList(weeklyShows);
//    }

//    public Set<Movie> getMovies() {
//        return movies;
//    }
//
//    public Set<TVShow> getTvShows() {
//        return tvShows;
//    }
}