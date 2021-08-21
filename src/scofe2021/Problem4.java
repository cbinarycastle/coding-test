package scofe2021;

import java.util.*;

public class Problem4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Double> preferences = new HashMap<>();
        String[] preferencesArr = scanner.nextLine().split(" ");
        preferences.put("A", Double.parseDouble(preferencesArr[0]));
        preferences.put("B", Double.parseDouble(preferencesArr[1]));
        preferences.put("C", Double.parseDouble(preferencesArr[2]));
        preferences.put("D", Double.parseDouble(preferencesArr[3]));
        preferences.put("E", Double.parseDouble(preferencesArr[4]));

        String[] heightAndWidth = scanner.nextLine().split(" ");
        int height = Integer.parseInt(heightAndWidth[0]);
        int width = Integer.parseInt(heightAndWidth[1]);

        char[][] watchedInfo = new char[height][width];
        for (int i = 0; i < height; i++) {
            watchedInfo[i] = scanner.nextLine().toCharArray();
        }

        char[][] genres = new char[height][width];
        for (int i = 0; i < height; i++) {
            genres[i] = scanner.nextLine().toCharArray();
        }

        Queue<Contents> contentsQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Genre genre = new Genre(genres[y][x], watchedInfo[y][x]);
                if (genre.isWatched()) {
                    continue;
                }

                double preference = preferences.get(genre.getValue());

                Contents contents = new Contents(genre, preference, y, x);
                contentsQueue.add(contents);
            }
        }

        while (!contentsQueue.isEmpty()) {
            System.out.println(contentsQueue.poll());
        }
    }

    public static class Contents implements Comparable<Contents> {
        private final Genre genre;
        private final double preference;
        private final int y;
        private final int x;

        public Contents(Genre genre, double preference, int y, int x) {
            this.genre = genre;
            this.preference = preference;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Contents o) {
            int genreComparison = genre.compareTo(o.genre);

            if (genreComparison == 0) {
                int preferenceComparison = (int) Math.ceil(preference - o.preference);

                if (preferenceComparison == 0) {
                    int yPositionComparison = o.y - y;

                    if (yPositionComparison == 0) {
                        return o.x - x;
                    }

                    return yPositionComparison;
                }

                return preferenceComparison;
            }

            return genreComparison;
        }

        @Override
        public String toString() {
            return String.format("%s %.1f %d %d", genre.getValue(), preference, y, x);
        }
    }

    public static class Genre implements Comparable<Genre> {
        private final String value;
        private final WatchedInfo watchedInfo;

        public Genre(char value, char watchedInfo) {
            this.value = String.valueOf(value);
            this.watchedInfo = WatchedInfo.valueOf(String.valueOf(watchedInfo));
        }

        public String getValue() {
            return value;
        }

        @Override
        public int compareTo(Genre o) {
            return watchedInfo.compareTo(o.watchedInfo);
        }

        public boolean isWatched() {
            return watchedInfo == WatchedInfo.W;
        }
    }

    public enum WatchedInfo {
        W, O, Y;
    }
}
